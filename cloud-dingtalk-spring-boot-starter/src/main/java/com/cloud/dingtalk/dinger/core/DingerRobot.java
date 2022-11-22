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
package com.cloud.dingtalk.dinger.core;

import com.cloud.dingtalk.dinger.core.entity.DingerProperties;
import com.cloud.dingtalk.dinger.core.entity.DingerRequest;
import com.cloud.dingtalk.dinger.core.entity.DingerResponse;
import com.cloud.dingtalk.dinger.core.entity.MsgType;
import com.cloud.dingtalk.dinger.core.entity.enums.DingerResponseCodeEnum;
import com.cloud.dingtalk.dinger.core.entity.enums.DingerType;
import com.cloud.dingtalk.dinger.core.entity.enums.MessageSubType;
import com.cloud.dingtalk.dinger.exception.AsyncCallException;
import com.cloud.dingtalk.dinger.exception.SendMsgException;
import com.cloud.dingtalk.dinger.support.CustomMessage;
import com.cloud.dingtalk.dinger.support.client.MediaTypeEnum;
import com.cloud.dingtalk.dinger.support.sign.SignBase;
import com.cloud.dingtalk.dinger.utils.DingerUtils;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Map;


/**
 * DingTalk Robot
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Slf4j
public class DingerRobot extends AbstractDingerSender {

    public DingerRobot(DingerProperties dingerProperties, DingerManagerBuilder dingTalkManagerBuilder) {
        super(dingerProperties, dingTalkManagerBuilder);
    }

    @Override
    public DingerResponse send(String content) {
        return send(dingerProperties.getDefaultDinger(), MessageSubType.TEXT, DingerRequest.request(content));
    }

    @Override
    public DingerResponse send(DingerRequest request) {
        return send(dingerProperties.getDefaultDinger(), MessageSubType.TEXT, request);
    }

    @Override
    public DingerResponse send(MessageSubType messageSubType, DingerRequest request) {
        return send(dingerProperties.getDefaultDinger(), messageSubType, request);
    }

    @Override
    public DingerResponse send(DingerType dingerType, MessageSubType messageSubType, DingerRequest request) {
        if (!messageSubType.isSupport()) {
            return DingerResponse.failed(DingerResponseCodeEnum.MESSAGE_TYPE_UNSUPPORTED);
        }
        CustomMessage customMessage = customMessage(messageSubType);
        String msgContent = customMessage.message(dingerProperties.getProjectId(), request);
        request.setContent(msgContent);

        MsgType msgType = messageSubType.msgType(dingerType, request);

        return send(msgType);
    }


    /**
     * @param message 消息内容
     * @param <T>     T
     * @return 响应内容 {@link DingerResponse}
     */
    protected <T extends MsgType> DingerResponse send(T message) {
        DingerType dingerType = message.getDingerType();
        String dkid = dingTalkManagerBuilder.dingerIdGenerator.dingerId();
        Map<DingerType, DingerProperties.Dinger> dingers = dingerProperties.getDingers();
        if (!dingerProperties.isEnabled() || !dingers.containsKey(dingerType)) {
            return DingerResponse.failed(dkid, DingerResponseCodeEnum.DINGER_DISABLED);
        }

        DingerConfig localDinger = getLocalDinger();
        // dinger is null? use global configuration and check whether dinger send
        boolean dingerConfig = localDinger != null;
        try {
            DingerProperties.Dinger dinger;
            if (dingerConfig) {
                dinger = new DingerProperties.Dinger();
                BeanUtils.copyProperties(localDinger, dinger);
                dinger.setAsync(localDinger.getAsyncExecute());
                dinger.setRobotUrl(dingers.get(dingerType).getRobotUrl());
            } else {
                dinger = dingers.get(dingerType);
            }

            StringBuilder webhook = new StringBuilder();
            webhook.append(dinger.getRobotUrl()).append(dinger.getTokenId());

            if (log.isInfoEnabled()) {
                log.info("dingerId={} send message and use dinger={}, tokenId={}.", dkid, dingerType, dinger.getTokenId());
            }

            // 处理签名问题(只支持DingTalk)
            if (DingerUtils.isNotEmpty((dinger.getSecret()))) {
                SignBase sign = dingTalkManagerBuilder.dingerSignAlgorithm.sign(dinger.getSecret().trim());

                if (dingerType == DingerType.DINGTALK) {
                    webhook.append(sign.transfer());
                } else if (dingerType == DingerType.BYTETALK) {
                    message.signAttributes(sign);
                }
            }

            Map<String, String> headers = ImmutableMap.of("Content-Type", MediaTypeEnum.JSON.getType());

            // 异步处理, 直接返回标识id
            if (dinger.isAsync()) {
                dingTalkManagerBuilder.dingTalkExecutor.execute(() -> {
                    try {
                        String result = dingTalkManagerBuilder.dingerHttpClient.post(
                                webhook.toString(), headers, message
                        );
                        dingTalkManagerBuilder.dingerAsyncCallback.execute(dkid, result);
                    } catch (Exception e) {
                        exceptionCallback(dkid, message, new AsyncCallException(e));
                    }
                });
                return DingerResponse.success(dkid, dkid);
            }

            String response = dingTalkManagerBuilder.dingerHttpClient.post(
                    webhook.toString(), headers, message);
            return DingerResponse.success(dkid, response);
        } catch (Exception e) {
            exceptionCallback(dkid, message, new SendMsgException(e));
            return DingerResponse.failed(dkid, DingerResponseCodeEnum.SEND_MESSAGE_FAILED);
        }
    }

}
