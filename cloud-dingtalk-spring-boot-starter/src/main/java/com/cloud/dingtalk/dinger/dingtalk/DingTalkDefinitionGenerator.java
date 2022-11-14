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
package com.cloud.dingtalk.dinger.dingtalk;

import com.cloud.dingtalk.dinger.core.DingerDefinition;
import com.cloud.dingtalk.dinger.core.DingerDefinitionGenerator;
import com.cloud.dingtalk.dinger.core.DingerDefinitionGeneratorContext;
import com.cloud.dingtalk.dinger.core.DingerDefinitionHandler;
import com.cloud.dingtalk.dinger.core.annatations.DingerImageText;
import com.cloud.dingtalk.dinger.core.annatations.DingerMarkdown;
import com.cloud.dingtalk.dinger.core.annatations.DingerText;
import com.cloud.dingtalk.dinger.core.entity.enums.DingerDefinitionType;
import com.cloud.dingtalk.dinger.core.entity.enums.DingerType;
import com.cloud.dingtalk.dinger.core.entity.xml.MessageTag;
import com.cloud.dingtalk.dinger.core.annatations.DingerLink;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 钉钉消息体定义生成类
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Slf4j
public class DingTalkDefinitionGenerator extends DingerDefinitionHandler {

    /**
     * 生成生成注解文本消息体定义
     */
    public static class AnotationText extends DingerDefinitionGenerator<DingerText> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<DingerText> context) {
            return dingerTextHandler(DingerType.DINGTALK, context);
        }
    }


    /**
     * 生成注解Markdown消息体定义
     */
    public static class AnnotationMarkdown extends DingerDefinitionGenerator<DingerMarkdown> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<DingerMarkdown> context) {
            return dingerMarkdownHandler(DingerType.DINGTALK, context);
        }
    }


    /**
     * 生成XML文本消息体定义
     */
    public static class XmlText extends DingerDefinitionGenerator<MessageTag> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
            return xmlHandler(DingerDefinitionType.DINGTALK_XML_TEXT, context);
        }
    }


    /**
     * 生成XML Markdown消息体定义
     */
    public static class XmlMarkdown extends DingerDefinitionGenerator<MessageTag> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
            return xmlHandler(DingerDefinitionType.DINGTALK_XML_MARKDOWN, context);
        }
    }


    /**
     * 生成注解 ImageText消息体定义
     */
    public static class AnnotationImageText extends DingerDefinitionGenerator<DingerImageText> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<DingerImageText> context) {
            return dingerImageTextHandler(DingerType.DINGTALK, context);
        }
    }


    /**
     * 生成XML ImageText消息体定义
     */
    public static class XmlImageText extends DingerDefinitionGenerator<MessageTag> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
            return xmlHandler(DingerDefinitionType.DINGTALK_XML_IMAGETEXT, context);
        }
    }


    /**
     * 生成注解 Link消息体定义
     */
    public static class AnnotationLink extends DingerDefinitionGenerator<DingerLink> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<DingerLink> context) {
            return dingerLinkHandler(DingerType.DINGTALK, context);
        }
    }


    /**
     * 生成XML Link消息体定义
     */
    public static class XmlLink extends DingerDefinitionGenerator<MessageTag> {
        @Override
        public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
            return xmlHandler(DingerDefinitionType.DINGTALK_XML_LINK, context);
        }
    }
}
