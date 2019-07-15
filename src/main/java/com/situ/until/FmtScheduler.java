package com.situ.until;

import java.util.Map;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class FmtScheduler {

    private static final FmtScheduler formatScheduler = new FmtScheduler();

    private FmtScheduler() {
    }

    public static FmtScheduler getInit(Class<? extends Job> clazz, Map<String, Object> data) {
        formatScheduler.initScheduler();
        formatScheduler.initJob(clazz, data);
        return formatScheduler;
    }

    private Scheduler scheduler;
    private Trigger   trigger;
    private JobDetail job;

    private void initScheduler() {
        try {
            if (scheduler == null)
                scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void initJob(Class<? extends Job> clazz, Map<String, Object> data) {
        JobBuilder jobB = JobBuilder.newJob(clazz);
        jobB.withIdentity(UUID.randomUUID().toString());
        if (data != null) {
            JobDataMap jobData = new JobDataMap();
            jobData.putAll(data);
            jobB.usingJobData(jobData);
        }
        job = jobB.build();
    }

    private void start() {
        try {
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void startSimpleTrigger(int intervalSeconds) {
        initTrigger(SimpleScheduleBuilder.repeatSecondlyForever(intervalSeconds));
    }

    public void startCronTrigger(String cron) {
        initTrigger(CronScheduleBuilder.cronSchedule(cron));
    }

    private void initTrigger(ScheduleBuilder<? extends Trigger> builder) {
        TriggerBuilder<Trigger> tgrB = TriggerBuilder.newTrigger();
        tgrB.withIdentity(UUID.randomUUID().toString()).startNow().withSchedule(builder);
        trigger = tgrB.build();
        start();
    }

    public void stop() {
        // Thread.sleep(1000);// 运行一段时间后关闭
        try {
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
