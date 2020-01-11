package com.graykey.xcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @SpringBootApplication ：等同于：@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置
 * @ServletComponentScan ： 注意：该注解加上后 拦截器失效;  去掉后 过滤器和监听器失效。
 */
@ServletComponentScan
@SpringBootApplication
public class XcoreApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(XcoreApplication.class);
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        SpringApplication.run(XcoreApplication.class, args);
    }

    /**
     * 查看 springboot 在启动的时候为我们注入了哪些bean
     *
     * 用@Bean标注方法等价于XML中配置的bean
     */
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            int beanNum = 0;
//            System.out.println("********************************************SpringBoot在启动时注入的Bean--开始********************************************");
//            for (String beanName : beanNames) {
//                beanNum++;
//                System.out.println(beanName);
//            }
//            System.out.println("********************************************SpringBoot在启动时注入的Bean--结束********************************************");
//            System.out.println("经统计,SpringBoot在启动时共注入 " + beanNum + " 个Bean!");
//        };
//    }

}
