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

import com.cloud.dingtalk.dinger.dingtalk.entity.enums.DingTalkMsgType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 独立跳转ActionCard类型
 *
 * @author shuai.zhou
 * @since 1.0
 */
@Data
public class DingActionCard extends DingTalkMessage {
    /**
     * {@link ActionCard}
     */
    private ActionCard actionCard;

    public DingActionCard() {
        setMsgtype(DingTalkMsgType.ACTION_CARD.type());
    }

    @Data
    public static class ActionCard implements Serializable {
        /**
         * 首屏会话透出的展示内容
         */
        private String title;
        /**
         * markdown格式的消息
         */
        private String text;
        /**
         * 0-按钮竖直排列，1-按钮横向排列
         */
        private String btnOrientation;
        /**
         * 按钮
         */
        private List<Button> btns;

        @Data
        public static class Button implements Serializable {
            /**
             * 按钮标题
             */
            private String title;
            /**
             * 点击按钮触发的URL
             */
            private String actionURL;

        }
    }

}
