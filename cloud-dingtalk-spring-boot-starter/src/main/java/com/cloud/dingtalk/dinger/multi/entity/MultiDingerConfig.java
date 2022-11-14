/*
 * Copyright ©2015-2022 Jaemon. All Rights Reserved.
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
package com.cloud.dingtalk.dinger.multi.entity;

import com.cloud.dingtalk.dinger.core.DingerConfig;
import com.cloud.dingtalk.dinger.multi.algorithm.AlgorithmHandler;

import java.util.List;

/**
 * MultiDingerConfig
 *
 * @author Jaemon
 * @since 1.0
 */
public class MultiDingerConfig {
    private AlgorithmHandler algorithmHandler;
    private List<DingerConfig> dingerConfigs;

    public MultiDingerConfig(AlgorithmHandler algorithmHandler, List<DingerConfig> dingerConfigs) {
        this.algorithmHandler = algorithmHandler;
        this.dingerConfigs = dingerConfigs;
    }

    public AlgorithmHandler getAlgorithmHandler() {
        return algorithmHandler;
    }

    public void setAlgorithmHandler(AlgorithmHandler algorithmHandler) {
        this.algorithmHandler = algorithmHandler;
    }

    public List<DingerConfig> getDingerConfigs() {
        return dingerConfigs;
    }

    public void setDingerConfigs(List<DingerConfig> dingerConfigs) {
        this.dingerConfigs = dingerConfigs;
    }
}
