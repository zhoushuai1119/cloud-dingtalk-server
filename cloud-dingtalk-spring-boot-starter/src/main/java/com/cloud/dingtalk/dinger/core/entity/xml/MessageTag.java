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
package com.cloud.dingtalk.dinger.core.entity.xml;

import com.cloud.dingtalk.dinger.core.annatations.PriorityColumn;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * MessageTag
 *
 * @author shuai.zhou
 * @since 1.0
 */
@XmlRootElement(name = "message")
public class MessageTag {
    private String identityId;
    @PriorityColumn(clazz = BodyTag.class, column = "type", priority = true)
    private String dingerType;
    private BodyTag body;
    private ConfigurationTag configuration;

    @XmlAttribute(required = true, name = "id")
    public String getIdentityId() {
        return identityId;
    }

    @XmlAttribute(name = "type")
    public String getDingerType() {
        return dingerType.toUpperCase();
    }

    public BodyTag getBody() {
        return body;
    }

    public ConfigurationTag getConfiguration() {
        return configuration;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public void setDingerType(String dingerType) {
        this.dingerType = dingerType;
    }

    public void setBody(BodyTag body) {
        this.body = body;
    }

    public void setConfiguration(ConfigurationTag configuration) {
        this.configuration = configuration;
    }
}
