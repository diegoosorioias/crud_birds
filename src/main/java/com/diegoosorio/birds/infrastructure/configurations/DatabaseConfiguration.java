package com.diegoosorio.birds.infrastructure.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/birdsBD");
        hikariConfig.setUsername("admin");
        hikariConfig.setPassword("admin");
        return new HikariDataSource(hikariConfig);
    }
}
