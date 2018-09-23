package com.yieldbroker.assignment.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.yieldbroker.assignment.model" })
@EnableJpaRepositories(basePackages = { "com.yieldbroker.assignment.repository" })
@EnableTransactionManagement
public class RepositoryConfig {

}
