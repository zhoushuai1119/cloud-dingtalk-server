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
package com.cloud.dingtalk.dinger.config;

import com.cloud.dingtalk.dinger.constant.DingerConstant;
import lombok.Data;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Dinger默认Http客户端配置
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Configuration
@ConditionalOnMissingBean(name = DingerConstant.DINGER_REST_TEMPLATE)
@ConfigurationProperties(prefix = DingerConstant.DINGER_PROPERTIES_PREFIX + "http-client")
@AutoConfigureAfter(BeanConfiguration.class)
@Data
public class DingerHttpClientConfig {

    /**
     * 连接超时时间
     */
    private Integer connectTimeout = 10;
    /**
     * 读超时时间
     */
    private Integer readTimeout = 20;
    /**
     * 连接池中整体的空闲连接的最大数量
     */
    private Integer maxIdleConnections = 200;
    /**
     * 连接空闲时间最多为 300 秒
     */
    private Long keepAliveDuration = 300L;

    @Bean(name = DingerConstant.DINGER_REST_TEMPLATE)
    public RestTemplate restTemplate(ClientHttpRequestFactory okHttp3ClientHttpRequestFactory) {
        return new RestTemplate(okHttp3ClientHttpRequestFactory);
    }

    @Bean(name = "okHttp3ClientHttpRequestFactory")
    public ClientHttpRequestFactory httpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory(okHttpConfigClient());
    }

    public OkHttpClient okHttpConfigClient(){
        return new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    public ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
    }

}
