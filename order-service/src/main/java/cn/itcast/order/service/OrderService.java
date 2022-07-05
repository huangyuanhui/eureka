package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2：利用RestTemplate发送http请求，查询用户
        /**
         * 此处将服务提供者的地址硬编码在代码中，是有很大问题的，一是在开发时。会有开发环境、测试环境、
         * 生产环境、每次环境的变更服务的地址可能也会跟着变化，采用硬编码的方式，每次都要修改代码
         * 二是服务会做集群，同一服务的多个集群的地址是不一样的！
         */
        String url = "http://localhost:8081/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }
}
