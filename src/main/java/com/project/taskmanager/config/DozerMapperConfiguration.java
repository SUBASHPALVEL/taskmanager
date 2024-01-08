package com.project.taskmanager.config;


import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@Configuration
public class DozerMapperConfiguration {
    @Bean
    public Mapper mapper() {

        Mapper mapper = null;
        try {

            mapper = DozerBeanMapperBuilder.create().withMappingFiles(Arrays.asList(
                    "CustomMappings.xml")).build();

        } catch (Exception ex) {
            System.err.println("EXCEPTION : " + ex.getMessage());
            ex.printStackTrace();
        }
        return mapper;
    }
}