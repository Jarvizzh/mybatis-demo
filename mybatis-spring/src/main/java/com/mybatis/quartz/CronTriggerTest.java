package com.mybatis.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZJH on 2017/11/25.
 */
public class CronTriggerTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建JobDetail实例，将该实例和HelloJob.class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob1", "group1")
                .build();

        Date date = new Date();
        date.setTime(date.getTime() + 3000);  //当前时间3s后开始执行
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);  //执行3s后结束
        //创建CronTrigger实例，
        /**
         * Cron表达式 由7个子组成的字符串，描述时间的详细信息。
         *  格式：[秒] [分] [时] [日] [月] [周] [年]
         *
         */
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger1", "group1")
//                .startAt(date)
//                .endAt(endDate)
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();

        //创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("current time is: " + sdf.format(d));

        //绑定jobDetail 和 trigger
        scheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(2000);
        scheduler.standby(); //挂起
        Thread.sleep(3000);
        scheduler.start();

        scheduler.shutdown(true);
//        scheduler.shutdown(false);
        System.out.println(scheduler.isShutdown());
    }
}
