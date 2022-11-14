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
package com.cloud.dingtalk.dinger.support;

import com.cloud.dingtalk.dinger.core.entity.DingerRequest;

/**
 * 自定义消息接口
 *
 * @author shuai.zhou
 * @since 1.0
 */
public interface CustomMessage {

    /**
     * 自定义消息
     *
     * @param projectId
     *              projectId
     * @param request
     *              消息请求体 {@link DingerRequest}
     * @return
     *              消息内容字符串
     */
    String message(String projectId, DingerRequest request);

}