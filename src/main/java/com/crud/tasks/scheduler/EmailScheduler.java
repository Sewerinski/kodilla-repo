package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 8-10 * * *")
    public void sendInformationEmail() {

        long size = taskRepository.count();

        String taskName;

        if(size == 1) {
            taskName = " task";
        } else {
            taskName = " tasks";
        }

            simpleEmailService.send(new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    null,
                    "Currently in database you got: " + size + taskName));
    }
}
