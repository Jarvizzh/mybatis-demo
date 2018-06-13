package com.mybatis.service;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ZJH on 2017/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations={"classpath*:config/spring.xml", "classpath*:config/spring-mvc.xml", "classpath*:config/sqlMapConfig.xml"})
public abstract class AbstractTest {

    static
    {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(
                new PatternLayout("%-6r [%p] %c - %m%n")));
    }

}
