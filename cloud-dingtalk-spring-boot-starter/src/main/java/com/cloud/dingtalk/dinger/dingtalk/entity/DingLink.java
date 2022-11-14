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
package com.cloud.dingtalk.dinger.dingtalk.entity;

import com.cloud.dingtalk.dinger.core.entity.LinkDeo;
import com.cloud.dingtalk.dinger.core.annatations.DingerLink;
import com.cloud.dingtalk.dinger.dingtalk.entity.enums.DingTalkMsgType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Link类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Data
public class DingLink extends DingTalkMessage {
    /** {@link Link} */
    private Link link;

    public DingLink() {
        setMsgtype(DingTalkMsgType.LINK.type());
    }

    public DingLink(Link link) {
        this();
        this.link = link;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Link implements Serializable {
        /** 消息标题 */
        private String title;
        /** 消息内容。如果太长只会部分展示 */
        private String text;
        /** 点击消息跳转的URL */
        private String messageUrl;
        /** 图片URL */
        private String picUrl;
    }

    @Override
    public void transfer(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (DingerLink.clazz.isInstance(value)) {
                LinkDeo link = (LinkDeo) value;
                this.link = new Link(link.getTitle(), link.getText(), link.getMessageUrl(), link.getPicUrl());
                break;
            }
        }
    }
}
