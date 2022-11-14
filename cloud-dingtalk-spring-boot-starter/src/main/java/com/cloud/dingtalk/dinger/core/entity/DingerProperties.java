/*
 * Copyright ©2015-2022 shuai.zhou. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloud.dingtalk.dinger.core.entity;

import com.cloud.dingtalk.dinger.core.entity.enums.DingerType;
import com.cloud.dingtalk.dinger.exception.InvalidPropertiesFormatException;
import com.cloud.dingtalk.dinger.utils.ConfigTools;
import com.cloud.dingtalk.dinger.utils.DingerUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.cloud.dingtalk.dinger.constant.DingerConstant.DINGER_PROP_PREFIX;


/**
 * 属性配置类
 *
 * @author shuai.zhou
 * @since 1.0
 */
@ConfigurationProperties(prefix = DINGER_PROP_PREFIX)
@Data
@Slf4j
public class DingerProperties implements InitializingBean {

    /**
     * 是否启用DingTalk, 默认true, 选填
     */
    private boolean enabled = true;

    /**
     * dinger类型 <code>key={@link DingerType}, value={@link Dinger}</code>, 必填
     */
    private Map<DingerType, Dinger> dingers = new LinkedHashMap<>();

    /**
     * 项目名称, 必填 <code>eg: ${spring.application.name}</code>
     */
    private String projectId;

    /**
     * dinger xml配置路径(需要配置xml方式Dinger时必填), 选填
     *
     * <blockquote>
     * spring.dinger.dinger-locations: classpath*:dinger/*.xml
     * spring.dinger.dinger-locations: classpath*:dinger/*\/*.xml
     * </blockquote>
     */
    private String dingerLocations;

    /**
     * 默认的Dinger, 不指定则使用{@link DingerProperties#dingers}中的第一个, 选填
     */
    private DingerType defaultDinger;


    @Data
    public static class Dinger {
        /**
         * 请求地址前缀-选填
         */
        private String robotUrl;
        /**
         * 获取 access_token, 必填
         *
         * 填写Dinger机器人设置中 webhook access_token | key后面的值
         */
        private String tokenId;
        /**
         * 选填, 签名秘钥。 需要验签时必填(钉钉机器人提供)
         */
        private String secret;

        /**
         * 选填, 是否需要对tokenId进行解密, 默认false
         */
        private boolean decrypt = false;

        /**
         * 选填(当decrypt=true时, 必填), 解密密钥
         * <p>
         * <br /><br />
         *
         * <b>解密密钥获取方式</b>
         * <ul>
         *     <li>java -jar dinger-spring-boot-starter-[1.0.0].jar [tokenId]</li>
         *     <li>ConfigTools.encrypt(tokenId)</li>
         * </ul>
         */
        private String decryptKey;

        /**
         * 选填, 是否开启异步处理, 默认： false
         */
        private boolean async = false;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (Map.Entry<DingerType, Dinger> entry : dingers.entrySet()) {
            DingerType dingerType = entry.getKey();
            if (!dingerType.isEnabled()) {
                throw new InvalidPropertiesFormatException(
                        String.format("dinger=%s is disabled", dingerType)
                );
            }
            Dinger dinger = entry.getValue();

            String tokenId = dinger.getTokenId();
            {
                if (DingerUtils.isEmpty(tokenId)) {
                    throw new InvalidPropertiesFormatException(
                            "spring.dinger.token-id is empty."
                    );
                }
            }

            if (DingerUtils.isEmpty(dinger.robotUrl)) {
                dinger.robotUrl = dingerType.getRobotUrl();
            }

            if (dingerType == DingerType.WETALK) {
                dinger.secret = null;
            }

            boolean check = dinger.decrypt && DingerUtils.isEmpty(dinger.decryptKey);
            if (check) {
                throw new InvalidPropertiesFormatException(
                        "spring.dinger.decrypt is true but spring.dinger.decrypt-key is empty."
                );
            }

            if (dinger.decrypt) {
                dinger.tokenId = ConfigTools.decrypt(dinger.decryptKey, dinger.tokenId);
            } else {
                dinger.decryptKey = null;
            }

            if (defaultDinger == null) {
                defaultDinger = dingerType;
                if (log.isDebugEnabled()) {
                    log.debug("defaultDinger undeclared and use first dingers dingerType, defaultDinger={}.", defaultDinger);
                }
            }
        }

        if (dingers.isEmpty()) {
            throw new InvalidPropertiesFormatException(
                    "spring.dinger.dingers is empty."
            );
        }

        if (!defaultDinger.isEnabled()) {
            throw new InvalidPropertiesFormatException(
                    "spring.dinger.default-dinger is disabled."
            );
        }

        {
            if (DingerUtils.isEmpty(this.projectId)) {
                throw new InvalidPropertiesFormatException(
                        "spring.dinger.project-id is empty."
                );
            }
        }

    }
}
