package cn.itcast.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * eureka注册中心原理
     * eureka分为：
     * 1：eureka注册中心
     * 2：eureka客户端
     *  eureka客户端在启动时，会把自己的信息注册到eureka注册中心：比如服务名称、服务地址、服务IP
     *  当其他eureka客户端需要调用时，会从eureka注册中心拉取服务信息
     *  拉取成功后，后根据服务命去负载均衡选出一个服务实例进行调用
     *
     *  eureka客户端默认每隔三十秒会香eureka注册中心发送一次心跳，确认自己的健康状态，也就是说如果eureka在
     *  固定时间内没有收到客户端的心跳，就会把该eureka客户端从服务列表中剔除，那么就可以感知到eureka客户端的健康状态
     */

    /**
     * 创建RestTemplate对象并注入容器
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}