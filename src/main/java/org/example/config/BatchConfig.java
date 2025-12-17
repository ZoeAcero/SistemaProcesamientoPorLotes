package org.example.config;

import org.example.model.Hechizo;
import org.example.processor.HechizoItemProcessor;
import org.example.listener.JobMisteriosListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

    @Bean
    public ItemReader<Hechizo> reader() {
        return new ListItemReader<>(List.of(
                new Hechizo("1", "Alohomora", "Hermione", 20, ""),
                new Hechizo("2", "Crucio", "Bellatrix", 95, ""),
                new Hechizo("3", "Wingardium Leviosa", "Ron", 15, ""),
                new Hechizo("4", "Avada Kedavra", "Voldemort", 100, "")
        ));
    }

    @Bean
    public ItemWriter<Hechizo> writer() {
        return chunk -> {
            System.out.println("--- REGISTRANDO LOTE EN EL ARCHIVO ---");
            chunk.forEach(h -> System.out.println("Guardado: " + h));
        };
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager tm,
                      ItemReader<Hechizo> reader, HechizoItemProcessor processor, ItemWriter<Hechizo> writer) {
        return new StepBuilder("pasoMágico", jobRepository)
                .<Hechizo, Hechizo>chunk(2, tm)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step1, JobMisteriosListener listener) {
        return new JobBuilder("jobMinisterio", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }
}