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

import com.cloud.dingtalk.dinger.DingerSender;
import com.cloud.dingtalk.dinger.core.entity.DingerCallback;
import com.cloud.dingtalk.dinger.core.entity.DingerProperties;
import com.cloud.dingtalk.dinger.core.entity.enums.MessageSubType;
import com.cloud.dingtalk.dinger.exception.DingerException;
import com.cloud.dingtalk.dinger.support.CustomMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * AbstractDingTalkSender
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Slf4j
public abstract class AbstractDingerSender extends DingerHelper implements DingerSender {

    protected DingerProperties dingerProperties;
    protected DingerManagerBuilder dingTalkManagerBuilder;

    public AbstractDingerSender(DingerProperties dingerProperties, DingerManagerBuilder dingTalkManagerBuilder) {
        this.dingerProperties = dingerProperties;
        this.dingTalkManagerBuilder = dingTalkManagerBuilder;
    }

    /**
     * 消息类型校验
     *
     * @param messageSubType
     *              消息类型
     * @return
     *              消息生成器
     */
    protected CustomMessage customMessage(MessageSubType messageSubType) {
        return messageSubType == MessageSubType.TEXT ? dingTalkManagerBuilder.textMessage : dingTalkManagerBuilder.markDownMessage;
    }

    /**
     * 异常回调
     *
     * @param dingerId
     *          dingerId
     * @param message
     *          message
     * @param ex
     *          ex
     * @param <T>
     *          T
     */
    protected <T> void exceptionCallback(String dingerId, T message, DingerException ex) {
        DingerCallback dkExCallable = new DingerCallback(dingerId, message, ex);
        dingTalkManagerBuilder.dingerExceptionCallback.execute(dkExCallable);
    }

}
