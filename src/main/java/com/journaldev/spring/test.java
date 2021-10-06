package com.journaldev.spring;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


import com.journaldev.spring.model.Seance;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cron="0/5 * * * * ?";
		try {
			JobDetail job1 = JobBuilder.newJob(Job1.class)
					.withIdentity("job1", "group1").build();

			Trigger trigger1 = TriggerBuilder.newTrigger()
					.withIdentity("cronTrigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(cron))
					.build();
			
			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);
			

			List<Seance> liste=new ArrayList<Seance>();
			Seance s=new Seance(); 
			s.setId(0);
			liste.add(s);
			Seance s1=new Seance(); 
			s1.setId(1);
			liste.add(s1);
			
			//Seance s3=liste.get(0);
			Seance s3=(Seance) BeanUtils.cloneBean(liste.get(0));
			s3.setId(6);
			for(Seance ss:liste)
			{
				System.out.println(ss.getId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
