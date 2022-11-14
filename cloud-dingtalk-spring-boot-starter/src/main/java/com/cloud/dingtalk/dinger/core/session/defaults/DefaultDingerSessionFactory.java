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
package com.cloud.dingtalk.dinger.core.session.defaults;

import com.cloud.dingtalk.dinger.core.session.Configuration;
import com.cloud.dingtalk.dinger.core.session.DingerSession;
import com.cloud.dingtalk.dinger.core.session.DingerSessionFactory;

/**
 * DefaultDingerSessionFactory
 *
 * @author Jaemon
 * @version 1.2
 */
public class DefaultDingerSessionFactory implements DingerSessionFactory {
    private Configuration configuration;

    public DefaultDingerSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DingerSession dingerSession() {
        return new DefaultDingerSession(configuration);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
