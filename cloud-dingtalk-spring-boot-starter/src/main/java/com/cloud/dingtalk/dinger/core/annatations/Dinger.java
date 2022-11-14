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
package com.cloud.dingtalk.dinger.core.annatations;

import com.cloud.dingtalk.dinger.core.entity.enums.DingerType;

import java.lang.annotation.*;

/**
 * 指定当前 <code>***Dinger</code> 使用的 Dinger 类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Dinger {
    /**
     * 指定使用的Dinger
     *
     * @return DingerType {@link DingerType}
     */
    DingerType value();
}
