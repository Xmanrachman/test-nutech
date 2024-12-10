package com.nutech.test.sims.ppob.service.utility;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
          .driverClassName("org.postgresql.Driver")
          .url("jdbc:postgresql://postgres.railway.internal:5432/railway")
          .username("postgres")
          .password("xmtADtsuRDKzavZJqeqiltLWNFhAXAHy")
          .build();	
    }
}
