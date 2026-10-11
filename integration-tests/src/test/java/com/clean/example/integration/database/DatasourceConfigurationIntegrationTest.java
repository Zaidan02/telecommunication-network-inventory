package com.clean.example.integration.database;

import com.clean.example.configuration.DatasourceConfiguration;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class DatasourceConfigurationIntegrationTest {

    @Test
    public void initializesExampleDataOnceAcrossMultiplePooledConnections() throws Exception {
        JdbcConnectionPool pool = new DatasourceConfiguration().dataSource();
        try {
            // Hold the first connection so the pool must open another physical connection.
            try (Connection first = pool.getConnection();
                 Connection second = pool.getConnection();
                 Statement statement = second.createStatement()) {
                assertThat(first.getSchema()).isEqualTo("CLEAN_ARCHITECTURE");
                assertThat(second.getSchema()).isEqualTo("CLEAN_ARCHITECTURE");
                try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM BB_ACCESS_DEVICE")) {
                    assertThat(result.next()).isTrue();
                    assertThat(result.getInt(1)).isEqualTo(2);
                }
            }
        } finally {
            pool.dispose();
        }
    }
}
