package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>标题: 消费者Application</p>
 * <p>描述: </p>
 * <p>类名: ConsumerApplication</p>
 * <p>单位: 北京大医通汇云技术有限公司</p>
 * <p>作者: songzh</p>
 * <p>日期: 2021/10/29 16:59</p>
 * <p>版本号: 0.1</p>
 */

@SpringBootApplication
@EnableRabbit //开启将基于注解的RabbitMQ模式
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
