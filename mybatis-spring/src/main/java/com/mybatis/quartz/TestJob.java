package com.mybatis.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZJH on 2017/11/25.
 */
public class TestJob implements Job {

    private String msg;
    private float fl;
    private double dl;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("current exec time is: " + sdf.format(date));

        //具体业务逻辑
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        System.out.println(jobKey.getName() + ", " + jobKey.getGroup());
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobDataMap.get("msg"));
        System.out.println(jobDataMap.get("fl"));

        TriggerKey key = jobExecutionContext.getTrigger().getKey();
        System.out.println(key.getName() + ", " + key.getGroup());
        JobDataMap jobDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
        System.out.println(jobDataMap.get("msg"));
        System.out.println(jobDataMap1.get("dl"));

        System.out.println("========merge map=======");
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        System.out.println(map.get("msg"));  // key相同情况下，优先获取trigger的jobDataMap的值
        System.out.println(map.get("float"));
        System.out.println(map.get("double"));

        //也可直接在实现了Job接口的类中定义属性，通过getter/setter直接获取JobDataMap中同名属性的值
        System.out.println(msg + "," + fl + "," + dl);
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public float getFl() {
        return fl;
    }

    public void setFl(float fl) {
        this.fl = fl;
    }

    public double getDl() {
        return dl;
    }

    public void setDl(double dl) {
        this.dl = dl;
    }
}
