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

import com.cloud.dingtalk.dinger.core.entity.ImageTextDeo;
import com.cloud.dingtalk.dinger.dingtalk.entity.enums.DingTalkMsgType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * FeedCard类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Data
public class DingFeedCard extends DingTalkMessage {

    /**
     * {@link FeedCard}
     */
    private FeedCard feedCard;

    public DingFeedCard() {
        setMsgtype(DingTalkMsgType.FEED_CARD.getType());
    }

    public DingFeedCard(List<FeedCard.Link> links) {
        this();
        this.feedCard = new FeedCard(links);
    }

    @Data
    public static class FeedCard implements Serializable {
        /**
         * {@link Link}
         */
        private List<Link> links;

        public FeedCard() {
        }

        public FeedCard(List<Link> links) {
            this.links = links;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Link implements Serializable {
            /**
             * 单条信息文本
             */
            private String title;
            /**
             * 点击单条信息到跳转链接
             */
            private String messageURL;
            /**
             * 单条信息后面图片的URL
             */
            private String picURL;

        }
    }

    @Override
    public void transfer(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (List.class.isInstance(value)) {
                List<ImageTextDeo> imageTexts = (List<ImageTextDeo>) value;
                for (ImageTextDeo imageText : imageTexts) {
                    this.feedCard.links.add(new FeedCard.Link(imageText.getTitle(), imageText.getUrl(), imageText.getPicUrl()));
                }
                break;
            }
        }
    }
}
