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
package com.cloud.dingtalk.dinger.multi.entity;

import com.cloud.dingtalk.dinger.constant.DingerConstant;
import com.cloud.dingtalk.dinger.core.DingerConfig;
import com.cloud.dingtalk.dinger.multi.algorithm.AlgorithmHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MultiDingerAlgorithmDefinition
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiDingerAlgorithmDefinition {
    /**
     * dingerClass + {@link DingerConstant#SPOT_SEPERATOR} + {@link AlgorithmHandler}.simpleName
     */
    private String key;
    /**
     * 算法处理类 {@link AlgorithmHandler}
     *
     * <br>
     * <code>
     *     // 父类.class.isAssignableFrom(子类.class) <br>
     *     AlgorithmHandler.class.isAssignableFrom(algorithm) = true
     * </code>
     */
    private Class<? extends AlgorithmHandler> algorithm;
    /**
     * 有效的钉钉机器人配置集合
     */
    private List<DingerConfig> dingerConfigs;

    /** handler name */
    private String dingerConfigHandlerClassName;

}
