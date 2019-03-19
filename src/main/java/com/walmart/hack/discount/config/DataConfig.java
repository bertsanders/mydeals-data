package com.walmart.hack.discount.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

  @Bean
  public DataSource dataSource(@Value("${DATABASE_URL}") String databaseUri) throws URISyntaxException {
    URI dbUri = new URI(databaseUri);

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

    return DataSourceBuilder.create()
        .url(dbUrl)
        .username(username)
        .password(password)
    .build();
  }
}
