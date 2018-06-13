package com.mybatis.quartz.spr;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by ZJH on 2017/11/26.
 */
public class MyJobClassBean extends QuartzJobBean {

    private int intValue;
    private AnotherBean anotherBean;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("MyJobClassBean: intValue = " + intValue);
        anotherBean.printAnotherMessage();
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public AnotherBean getAnotherBean() {
        return anotherBean;
    }

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }
}
