package cn.itcast.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 创建RestTemplate对象并注入容器
     * @return
     */
    /**
     * 当服务消费者使用服务提供者的服务名去发起请求，调用服务提供者提供的接口时，eureka内置的rabbon组件
     * 会去拦截这个请求，就是LoadBalancerInterceptor这个负载均衡拦截器会拦截请求，并把请求中交给
     * RibbonLoadBalancerClient，RibbonLoadBalancerClient会获取请求中的服务命并交给DynamicServerListLoadBalancer，
     * DynamicServerListLoadBalancer则根据服务命去注册中心拉取对应的服务列表，获取到的服务列表会由IRule按照负载均衡策略，选择出服务实例，
     * 最终向LoadBalancerInterceptor返回选择到的服务实例，得到真实的请求地址去发起请求，进行调用！
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 通过定义IRule实现修改负载均衡规则方法一：代码方式
     * 此方法是全局有效的，就是说不管调用什么服务提供者，这个负载均衡策略都会生效
     * @return
     */
    /*@Bean
    public IRule randomRule() {
        return new RandomRule();
    }*/
}