package org.example.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobMisteriosListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(">>> INICIANDO TRABAJO EN EL DEPARTAMENTO DE MISTERIOS...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println(">>> TRABAJO COMPLETADO CON ÉXITO.");
        } else {
            System.out.println(">>> EL TRABAJO HA FALLADO. ESTADO: " + jobExecution.getStatus());
        }
    }
}