package com.cloud.dingtalk.dinger.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 获取环境变量
 * @author: zhou shuai
 * @date: 2022/11/14 15:05
 * @version: v1
 */
@Configuration
@Slf4j
public class EnvironConfiguration implements InitializingBean, ApplicationContextAware {

    @Getter
    private String env;

    private ApplicationContext applicationContext;

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

        // 3. Try to get environment from ActiveProfiles
        String[] profiles  = getActiveProfile();
        if (ArrayUtils.isNotEmpty(profiles)) {
            env = profiles[0].trim();
            log.info("Environment is set to [{}] by OS env 'ActiveProfiles'.", env);
            return;
        }

        // 4. Try to get environment from OS environment variable
        env = System.getenv("ENV");
        if (StringUtils.isNotBlank(env)) {
            env = env.trim();
            log.info("Environment is set to [{}] by OS env variable 'ENV'.", env);
            return;
        }

        // 5. Set environment to UNKNOW.
        env = "UNKNOW";
        log.warn(
                "Environment is set to UNKNOW. Because it is not available in either (1) JVM system property 'spring.profiles.active', (2) OS env variable 'ENV'.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取当前环境
     */
    public String[] getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

}
