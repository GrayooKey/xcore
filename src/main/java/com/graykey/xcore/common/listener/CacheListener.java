package com.graykey.xcore.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器
 * 在传统的以Spring-Web程序中，我们会继承ContextLoaderListener来实现一些早期运行的初始化代码。但是现在迁移到Spring-Boot后发现不能这么做了。
 * Listener改为实现ServletContextListener，还要在类上加@WebListener注解。其余可以不动
 *
 * @author xuezb
 * @date 2020-01-11
 */
@WebListener
public class CacheListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(CacheListener.class);


    /**
     * 用来通知监听器web应用初始化过程已经开始
     * 在所有filter和servlet初始化前会通知所有实现了ServletContextListener的对监听器。
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("加载数据字典...");
    }


    /**
     * 用来通知servletContext即将关闭
     * 在通知所有ServletContextListener监听器servletContext销毁之前，所有servlet和filter都已被销毁。
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("销毁servletContext...");
    }

}
