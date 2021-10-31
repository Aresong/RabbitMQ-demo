package com.rabbitmq;

import com.rabbitmq.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>标题: 生产者测试</p>
 * <p>描述: </p>
 * <p>类名: SpringbootRabbitmqProducerApplicationTest</p>
 * <p>单位: 北京大医通汇云技术有限公司</p>
 * <p>作者: songzh</p>
 * <p>日期: 2021/10/31 14:54</p>
 * <p>版本号: 0.1</p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class SpringbootRabbitmqProducerApplicationTest {

    @Autowired
    OrderService orderService;

    @Test
    public void contextLoads() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderService.makeOrder(userId, productId, num);
        }
    }

}
