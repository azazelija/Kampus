package ru.kampus.service.quartz;

import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class QuartzFactory {

    @Resource
    private Scheduler scheduler;

    public void addJob(Job job, String jobName, String triggerName, Date startDate) {

        //  1. Создайте экземпляр JobDetail и укажите Quartz.
        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                .withIdentity(jobName)
                .build();

        //  2. Создать триггер
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName)
                .startAt(startDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

        try {
            //  3. Удалите задание, чтобы избежать ошибок, вызванных повторным добавлением задания.
            this.removeJob(jobName, triggerName);

            //  4. Планирование исполнения
            scheduler.scheduleJob(jobDetail, trigger);

            this.startSchedule();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обновить время задачи
     */
    public void rescheduleJob(String triggerName, Date startDate) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
        try {
            //  Получить триггер
            Trigger trigger = scheduler.getTrigger(triggerKey);
            //  Восстановите триггер в соответствии с новым выражением cronExpression
            trigger = trigger
                    .getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .startAt(startDate)
                    .build();
            //  Сбросить выполнение задания в соответствии с новым триггером
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Начать миссию
     */
    public void startSchedule() {
        try {
            if (scheduler.isShutdown()) {
                scheduler.resumeAll();
            } else {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удалить задачу и триггер
     */
    public void removeJob(String jobName, String triggerName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);

        try {
            scheduler.pauseTrigger(triggerKey);         //  Остановить триггер
            scheduler.unscheduleJob(triggerKey);        //  Удалить триггер
            scheduler.deleteJob(jobKey);                //  Удалить задачу
        }catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
