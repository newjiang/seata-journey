package org.newjiang.learning.seata.order;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrderApplication.class, args);
        context.getBean(RestTemplate.class).getInterceptors().addLast((request, body, execution) -> {
            System.out.println("http请求：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(request.getMethod() + " " + request.getURI());
            System.out.println(request.getHeaders());
            System.out.println("http响应：<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            ClientHttpResponse response = execution.execute(request, body);
            System.out.println(response.getHeaders());
            return response;
        });
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Filter getPrintHeaderFilter() {
        return (servletRequest, servletResponse, filterChain) -> {
            if (servletRequest instanceof HttpServletRequest request) {
                System.out.println("客户端请求：>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("请求地址：" + request.getRequestURI());
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    String headerValue = request.getHeader(headerName);
                    System.out.println(headerName + ": " + headerValue);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        };
    }
}
