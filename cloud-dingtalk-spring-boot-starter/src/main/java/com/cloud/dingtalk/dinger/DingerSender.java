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
package com.cloud.dingtalk.dinger;

import com.cloud.dingtalk.dinger.core.entity.DingerProperties;
import com.cloud.dingtalk.dinger.core.entity.DingerRequest;
import com.cloud.dingtalk.dinger.core.entity.DingerResponse;
import com.cloud.dingtalk.dinger.core.entity.enums.DingerType;
import com.cloud.dingtalk.dinger.core.entity.enums.MessageSubType;

/**
 * DingTalk Sender
 *
 * @author shuai.zhou
 * @since 1.0
 */
public interface DingerSender {

    /**
     * 发送消息到指定群
     * <p>
     * 使用配置的默认钉钉机器人, {@link DingerProperties#getDefaultDinger()}
     * <p>
     * 使用配置的默认消息类型TEXT,{@link MessageSubType}
     *
     * @param content 请求内容
     * @return 响应报文
     */
    DingerResponse send(String content);

    /**
     * 发送消息到指定群
     * <p>
     * 使用配置的默认钉钉机器人, {@link DingerProperties#getDefaultDinger()}
     * <p>
     * 使用配置的默认消息类型TEXT,{@link MessageSubType}
     *
     * @param request 请求体 {@link DingerRequest}
     * @return 响应报文
     */
    DingerResponse send(DingerRequest request);

    /**
     * 发送消息到指定群
     * <p>
     * 使用配置的默认钉钉机器人, {@link DingerProperties#getDefaultDinger()}
     *
     * @param messageSubType 消息类型{@link MessageSubType}
     * @param request        请求体 {@link DingerRequest}
     * @return 响应报文
     */
    DingerResponse send(MessageSubType messageSubType, DingerRequest request);

    /**
     * 发送消息到指定群
     *
     * @param dingerType     Dinger类型 {@link DingerType}
     * @param messageSubType 消息类型{@link MessageSubType}
     * @param request        请求体 {@link DingerRequest}
     * @return 响应报文
     */
    DingerResponse send(DingerType dingerType, MessageSubType messageSubType, DingerRequest request);

}
