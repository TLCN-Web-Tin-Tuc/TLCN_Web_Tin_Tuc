package com.example.demo;

import com.example.demo.job.MyJob;
import com.rometools.rome.io.FeedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@SpringBootApplication
public class DemoWebCrawlerApplication {

    public static void main(String[] args) throws  SchedulerException  {
        SpringApplication.run(DemoWebCrawlerApplication.class, args);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("triggerName", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever()).build();

        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("jobName", "group1").build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
