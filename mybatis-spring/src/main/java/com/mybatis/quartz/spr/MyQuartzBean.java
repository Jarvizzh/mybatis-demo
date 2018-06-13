package com.mybatis.quartz.spr;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZJH on 2017/11/26.
 */
@Component("myQuartzBean")
public class MyQuartzBean {

    public void printMessage(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("MyQuartzBean exec time :" + sdf.format(new Date()));
    }
}
