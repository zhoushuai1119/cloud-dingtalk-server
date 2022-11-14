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


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认异步执行回调
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Slf4j
public class DefaultDingerAsyncCallable implements DingerAsyncCallback {

    @Override
    public void execute(String dingerId, String result) {
        if (log.isDebugEnabled()) {
            log.debug("dingerId=[{}], result=[{}].",
                    dingerId, result);
        }
    }
}
