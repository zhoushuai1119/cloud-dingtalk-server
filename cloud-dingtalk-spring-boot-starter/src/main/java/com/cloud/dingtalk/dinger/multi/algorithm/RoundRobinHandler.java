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
package com.cloud.dingtalk.dinger.multi.algorithm;

import com.cloud.dingtalk.dinger.core.DingerConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 轮询算法
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Slf4j
public class RoundRobinHandler implements AlgorithmHandler {
    /** 索引值 */
    private volatile int index = DEFAULT_INDEX;

    @Override
    public DingerConfig handler(List<DingerConfig> dingerConfigs, DingerConfig defaultDingerConfig) {
        int size = dingerConfigs.size();
        int idx = index;

        synchronized (this) {
            index++;
            index = index >= size ? DEFAULT_INDEX : index;

            if (log.isDebugEnabled()) {
                log.debug("#{}# 当前使用第{}个机器人", algorithmId(), idx);
            }
            log.info("#{}# 当前使用第{}个机器人", algorithmId(), idx);
        }

        return dingerConfigs.get(idx);
    }
}
