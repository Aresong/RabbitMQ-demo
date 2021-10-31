package com.rabbitmq.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * <p>标题: 短信服务</p>
 * <p>描述: </p>
 * <p>类名: SMSService</p>
 * <p>单位: 北京大医通汇云技术有限公司</p>
 * <p>作者: songzh</p>
 * <p>日期: 2021/10/31 16:03</p>
 * <p>版本号: 0.1</p>
 */
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        // sms.topic.queue 是队列名字，这个名字你可以自定随便定义。
        value = @Queue(value = "sms.topic.queue", autoDelete = "false"),
        // topic_order_exchange 交换机的名字 必须和生产者保持一致
        exchange = @Exchange(value = "topic_order_exchange",
                // 这里是确定的rabbitmq模式是：fanout 是以广播模式 、 发布订阅模式
                type = ExchangeTypes.TOPIC)
))

@Service
public class SMSService {

    // @RabbitHandler 代表此方法是一个消息接收的方法。该不要有返回值
    @RabbitHandler
    public void messageReceive(String message){
        // 此处省略发邮件的逻辑
        System.out.println("sms-------------->" + message);
    }

}
