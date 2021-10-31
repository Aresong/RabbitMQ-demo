package com.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>类名: OrderService</p>
 * <p>单位: 北京大医通汇云技术有限公司</p>
 * <p>作者: songzh</p>
 * <p>日期: 2021/10/31 14:42</p>
 * <p>版本号: 0.1</p>
 */
@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 1: 定义交换机
    private String exchangeName = "topic_order_exchange";

    // 2: 路由key
    private String routeKey = "com.first.basic.message";

    public void makeOrder(Long userId, Long productId, int num) {
        // 1： 模拟用户下单
        String orderNumber = UUID.randomUUID().toString();
        // 2: 根据商品id productId 去查询商品的库存
        // int numstore = productSerivce.getProductNum(productId);
        // 3:判断库存是否充足
        // if(num >  numstore ){ return  "商品库存不足..."; }
        // 4: 下单逻辑
        // orderService.saveOrder(order);
        // 5: 下单成功要扣减库存
        // 6: 下单完成以后
        System.out.println("用户 " + userId + ",订单编号是：" + orderNumber);
        
        // 发送订单信息给RabbitMQ topic
        rabbitTemplate.convertAndSend(exchangeName, routeKey, orderNumber);
    }

}
