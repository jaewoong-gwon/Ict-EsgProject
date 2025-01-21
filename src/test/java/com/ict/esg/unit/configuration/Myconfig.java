package com.ict.esg.unit.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ToString
@ConfigurationProperties("spring.datasource")
@Configuration
public class Myconfig {

  private String driverClassName;
  private String url;
  private String username;
  private String password;

}
