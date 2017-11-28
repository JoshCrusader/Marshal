/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quartz;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import DAO.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.CronScheduleBuilder.monthlyOnDayAndHourAndMinute;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * This class makes it possible for the database and the system to automatically update 
 * and insert to the database new monthly dues every month
 * 
 * @author Patrick
 */
public class QuartzListener implements ServletContextListener {
    
    Scheduler scheduler = null;

    /**
     *
     * This class holds the Job and Trigger classes and methods.
     * These methods makes it possible for data to be inserted to the database once a month. <p>
     * 
     * This is executed once the web server runs.
     * 
     * @param servletContext
     */
    @Override
        public void contextInitialized(ServletContextEvent servletContext) {
                System.out.println("Monthly Trigger Started");
                
                try {
                        // Setup the Job class and the Job group
                        JobDetail job = newJob(InsertMonthly.class).withIdentity(
                                        "CronQuartzJob", "Group").build();

                        // Create a Trigger that fires every 5 minutes.
                        Trigger trigger = newTrigger()
                        .withIdentity("trigger3", "group1")
                        .startNow()
                        .withSchedule(monthlyOnDayAndHourAndMinute(1, 12, 0)) // fire on the 5th day of every month at 15:00
                        .build();
                                
                        /*.startNow()
                        .withSchedule(cronSchedule("0 0 15 5 * ?")) // fire on the 5th day of every month at 15:00
                        .build();*/

                        // Setup the Job and Trigger with Scheduler & schedule jobs
                        scheduler = new StdSchedulerFactory().getScheduler();
                        scheduler.start();
                        scheduler.scheduleJob(job, trigger);
                }
                catch (SchedulerException e) {
                        e.printStackTrace();
                } catch (Exception ex) {
                        Logger.getLogger(QuartzListener.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

    /**
     *
     * This is called when the user/admin shuts down the server
     * 
     * @param servletContext
     */
    @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Monthly Trigger Destroyed");
                try 
                {
                        scheduler.shutdown();
                } 
                catch (SchedulerException e) 
                {
                        e.printStackTrace();
                }
        }
    
}
