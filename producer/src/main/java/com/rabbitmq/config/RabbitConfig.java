package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>类名: RabbitConfig</p>
 * <p>单位: 北京大医通汇云技术有限公司</p>
 * <p>作者: songzh</p>
 * <p>日期: 2021/10/30 16:11</p>
 * <p>版本号: 0.1</p>
 */

@Configuration
public class RabbitConfig {

    //注入队列
    @Bean
    public Queue emailQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
           return new Queue("email.topic.queue", true, false, true);
        //一般设置一下队列的持久化就好,其余两个就是默认false
//        return new Queue("email.topic.queue", true);
    }
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.topic.queue", true);
    }
    @Bean
    public Queue wechatQueue() {
        return new Queue("wechat.topic.queue", true);
    }

    //注入Direct交换机
    @Bean
    public TopicExchange topicOrderExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new TopicExchange("topic_order_exchange", true, false);
    }

    //注入绑定  将队列和交换机绑定
    @Bean
    public Binding bindingTopic1() {
        return BindingBuilder.bind(wechatQueue()).to(topicOrderExchange()).with("#.first.#");
    }
    @Bean
    public Binding bindingTopic2() {
        return BindingBuilder.bind(smsQueue()).to(topicOrderExchange()).with("*.first.*");
    }
    @Bean
    public Binding bindingTopic3() {
        return BindingBuilder.bind(emailQueue()).to(topicOrderExchange()).with("#.basic.*");
    }

}
