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

import com.cloud.dingtalk.dinger.constant.DingerConstant;
import com.cloud.dingtalk.dinger.core.entity.DingerRequest;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.List;

/**
 * 默认markdown消息格式
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Slf4j
public class MarkDownMessage implements CustomMessage {

    @Override
    public String message(String projectId, DingerRequest request) {
        String content = request.getContent();
        String title = request.getTitle();
        List<String> phones = request.getPhones();
        // markdown在text内容里需要有@手机号
        StringBuilder text = new StringBuilder(title);
        if (phones != null && !phones.isEmpty()) {
            for (String phone : phones) {
                text.append(DingerConstant.DINGER_AT).append(phone);
            }
        }
        return MessageFormat.format(
                "#### 【Dinger通知】 {0} \n - 项目名称: {1}\n- 内容: {2}",
                text, projectId, content);
    }

}
