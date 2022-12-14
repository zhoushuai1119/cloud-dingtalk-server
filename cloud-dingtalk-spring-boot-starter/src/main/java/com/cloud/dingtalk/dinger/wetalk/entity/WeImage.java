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

import com.cloud.dingtalk.dinger.wetalk.entity.enums.WeTalkMsgType;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业微信-消息类型-图片类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Data
public class WeImage extends WeTalkMessage {

    /**
     * 图片（base64编码前）最大不能超过2M，支持JPG,PNG格式
     * */
    private Image image;

    public WeImage() {
        setMsgtype(WeTalkMsgType.IMAGE.getType());
    }

    @Data
    public static class Image implements Serializable {
        /** 图片内容的base64编码
         */
        private String base64;
        /**
         * 图片内容（base64编码前）的md5值
         * */
        private String md5;

    }

}
