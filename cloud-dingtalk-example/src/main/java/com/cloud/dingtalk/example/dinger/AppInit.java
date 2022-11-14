package com.cloud.dingtalk.example.dinger;

import com.cloud.dingtalk.dinger.DingerSender;
import com.cloud.dingtalk.dinger.core.entity.DingerRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<String> phones = List.of("17756228281");
        // 发送text类型消息
        dingerSender.send(
                DingerRequest.request("服务 Hello World, Hello Dinger", phones)
        );
    }
}
