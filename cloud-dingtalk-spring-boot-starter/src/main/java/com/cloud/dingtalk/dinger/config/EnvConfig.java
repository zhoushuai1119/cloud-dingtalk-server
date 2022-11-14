package com.cloud.dingtalk.dinger.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 获取环境变量
 * @author: zhou shuai
 * @date: 2022/11/14 15:05
 * @version: v1
 */
@Configuration
@Data
@Slf4j
public class EnvConfig implements InitializingBean {

    private String env;

    @Override
    public void afterPropertiesSet() {
        // 1. Try to get environment from JVM system property
        env = System.getProperty("spring.profiles.active");
        if (StringUtils.isNotBlank(env)) {
            env = env.trim();
            log.info("Environment is set to [{}] by JVM system property 'spring.profiles.active'.", env);
            return;
        }

        // 2. Try to get environment from OS environment variable
        env = System.getenv("spring.profiles.active");
        if (StringUtils.isNotBlank(env)) {
            env = env.trim();
            log.info("Environment is set to [{}] by OS env 'spring.profiles.active'.", env);
            return;
        }

        // 3. Try to get environment from OS environment variable
        env = System.getenv("ENV");
        if (StringUtils.isNotBlank(env)) {
            env = env.trim();
            log.info("Environment is set to [{}] by OS env variable 'ENV'.", env);
            return;
        }

        // 4. Set environment to UNKNOW.
        env = "UNKNOW";
        log.warn(
                "Environment is set to UNKNOW. Because it is not available in either (1) JVM system property 'spring.profiles.active', (2) OS env variable 'ENV'.");
    }

}
