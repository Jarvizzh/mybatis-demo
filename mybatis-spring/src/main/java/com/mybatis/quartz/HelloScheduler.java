package com.mybatis.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZJH on 2017/11/24.
 */
public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {
        //创建JobDetail实例，将该实例和HelloJob.class绑定
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("myJob", "group1")
                .usingJobData("msg","j_msg")
                .usingJobData("fl", 0.6F)
                .build();

        Date date = new Date();
        date.setTime(date.getTime() + 3000);  //当前时间3s后  calendar.add(Calendar.SECOND, 3);
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);
        //创建Trigger实例，定义该Job立即执行，每隔2s执行一次，再重复3次
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
//                .startNow()
                .startAt(date)
                .endAt(endDate)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2).withRepeatCount(3))
                .usingJobData("msg","t_msg")
                .usingJobData("dl",2.7D)
                .build();

        //创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("current time is: " + sdf.format(date));

        //绑定jobDetail 和 trigger
        scheduler.scheduleJob(jobDetail, trigger);


    }

}
