package com.clean.example.endtoend;

import com.clean.example.dataproviders.network.deviceclient.DeviceClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class MocksConfigurationForEndToEndTests {

    @Bean
    @Primary
    public DeviceClient mockDeviceClient() {
        return mock(DeviceClient.class);
    }

}
