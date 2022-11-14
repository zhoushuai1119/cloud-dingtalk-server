package com.cloud.dingtalk.example.dinger;

import com.cloud.dingtalk.dinger.DingerSender;
import com.cloud.dingtalk.dinger.core.entity.DingerRequest;
import com.cloud.dingtalk.dinger.core.entity.enums.MessageSubType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/11/14 12:04
 * @version: v1
 */
@Component
public class AppInit implements InitializingBean {

    @Autowired
    private DingerSender dingerSender;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 发送text类型消息
        dingerSender.send(
                MessageSubType.TEXT,
                DingerRequest.request("服务 Hello World, Hello Dinger")
        );
    }
}
