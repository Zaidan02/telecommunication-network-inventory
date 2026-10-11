package com.clean.example.configuration;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
public class DatasourceConfiguration {

    private static final String SCHEMA_INITIALISATION_SCRIPT = "h2-schema.sql";

    @Bean(destroyMethod = "dispose")
    public JdbcConnectionPool dataSource() {
        // we're using the in-memory h2 database for simplicity for this example.
        // For more info on h2 see http://www.h2database.com/html/features.html
        // Each application context owns its database. Every pooled connection selects the schema,
        // while tables and example data are initialized only once below.
        String jdbcUrl = "jdbc:h2:mem:example-" + UUID.randomUUID() +
                ";MODE=Oracle;INIT=CREATE SCHEMA IF NOT EXISTS CLEAN_ARCHITECTURE\\;SET SCHEMA CLEAN_ARCHITECTURE";
        String username = "CLEAN_ARCHITECTURE";
        String password = "";
        JdbcConnectionPool connectionPool = JdbcConnectionPool.create(jdbcUrl, username, password);
        new ResourceDatabasePopulator(new ClassPathResource(SCHEMA_INITIALISATION_SCRIPT)).execute(connectionPool);
        return connectionPool;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
