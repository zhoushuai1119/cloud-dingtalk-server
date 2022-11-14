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
package com.cloud.dingtalk.dinger.wetalk.entity;

import com.cloud.dingtalk.dinger.core.entity.ImageTextDeo;
import com.cloud.dingtalk.dinger.wetalk.entity.enums.WeTalkMsgType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 企业微信-消息类型-图文类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Data
public class WeNews extends WeTalkMessage {
    private static final int ARTICLE_LIMIT = 8;
    /** 图文类型 */
    private News news;

    public WeNews() {
        setMsgtype(WeTalkMsgType.NEWS.getType());
    }

    public WeNews(List<News.Article> articles) {
        this();
        this.news = new News(articles);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class News implements Serializable {
        /** 图文消息，一个图文消息支持1到8条图文 */
        private List<Article> articles;


        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Article implements Serializable {

            /** 标题，不超过128个字节，超过会自动截断 */
            private String title;

            /** 描述，不超过512个字节，超过会自动截断 */
            private String description;

            /** 点击后跳转的链接。 */
            private String url;

            /** 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150。 */
            private String picurl;
        }
    }

    @Override
    public void transfer(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (List.class.isInstance(value)) {
                List<ImageTextDeo> imageTexts = (List<ImageTextDeo>) value;
                int size = imageTexts.size();
                size = size > ARTICLE_LIMIT ? ARTICLE_LIMIT : size;
                for (int i = 0; i < size; i++) {

                }
                for (ImageTextDeo imageText : imageTexts) {
                    this.news.articles.add(new News.Article(imageText.getTitle(), imageText.getDescription(), imageText.getUrl(), imageText.getPicUrl()));
                }
            }
            break;
        }
    }
}
