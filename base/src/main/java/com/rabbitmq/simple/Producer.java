package com.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <p>标题: 生产者</p>
 * <p>描述: </p>
 * <p>类名: Producer</p>
 * <p>单位: 北京大医通汇云技术有限公司</p>
 * <p>作者: songzh</p>
 * <p>版本号: 0.1</p>
 */
public class Producer {

    public static void main(String[] args) {
        // 1: 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2: 设置连接属性
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = null;
        Channel channel = null;
        try {
            // 3: 从连接工厂中获取连接
            connection = connectionFactory.newConnection("生产者");
            // 4: 从连接中获取通道channel
            channel = connection.createChannel();

            //交换机exchange
            String  exchangeName = "test.fanout";
//            String  exchangeName = "test.direct";
//            String  exchangeName = "test.topic";

            //routingkey
            String routingKey = "";
//            String routingKey = "com.sms.email";

            // 6： 准备发送消息的内容
            String message = "from exchange:" + exchangeName;

            // 7: 发送消息给中间件rabbitmq-server
            // @params1: 交换机exchange
            // @params2: 队列名称/routingkey
            // @params3: 属性配置
            // @params4: 发送消息的内容
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
            System.out.println("消息发送成功!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("发送消息出现异常...");
        } finally {
            // 7: 释放连接关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
