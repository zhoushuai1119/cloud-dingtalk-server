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

import cn.hutool.core.util.ArrayUtil;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link ParameterNameDiscoverer} implementation that tries several discoverer
 * delegates in succession. Those added first in the {@code addDiscoverer} method
 * have highest priority. If one returns {@code null}, the next will be tried.
 *
 * <p>The default behavior is to return {@code null} if no discoverer matches.
 *
 * @author shuai.zhou
 * @since 1.2
 * @see org.springframework.core.PrioritizedParameterNameDiscoverer
 */
public class PrioritizedParameterNameDiscoverer implements ParameterNameDiscoverer {

    private final List<ParameterNameDiscoverer> parameterNameDiscoverers = new LinkedList<>();


    /**
     * Add a further {@link ParameterNameDiscoverer} delegate to the list of
     * discoverers that this {@code PrioritizedParameterNameDiscoverer} checks.
     * @param pnd pnd
     */
    public void addDiscoverer(ParameterNameDiscoverer pnd) {
        this.parameterNameDiscoverers.add(pnd);
    }


    @Override
    public String[] getParameterNames(Method method) {
        for (ParameterNameDiscoverer pnd : this.parameterNameDiscoverers) {
            String[] result = pnd.getParameterNames(method);
            if (ArrayUtil.isNotEmpty(result)) {
                return result;
            }
        }
        return null;
    }

    @Override
    public String[] getParameterNames(Constructor<?> ctor) {
        for (ParameterNameDiscoverer pnd : this.parameterNameDiscoverers) {
            String[] result = pnd.getParameterNames(ctor);
            if (ArrayUtil.isNotEmpty(result)) {
                return result;
            }
        }
        return null;
    }

}
