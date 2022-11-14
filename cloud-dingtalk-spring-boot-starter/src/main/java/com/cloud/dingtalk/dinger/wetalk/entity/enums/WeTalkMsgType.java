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
package com.cloud.dingtalk.dinger.wetalk.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * WeTalk支持的消息类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@AllArgsConstructor
@Getter
public enum WeTalkMsgType {
    /**
     * text类型
     */
    TEXT("text"),

    /**
     * markdown类型
     */
    MARKDOWN("markdown"),

    /**
     * 图片类型
     */
    IMAGE("image"),

    /**
     * 图文类型
     */
    NEWS("news"),

    /**
     * 文件类型
     */
    FILE("file");

    private String type;

}
