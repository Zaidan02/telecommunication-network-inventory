package com.clean.example.endtoend;

import com.clean.example.yatspec.SpringSpecRunner;
import com.clean.example.yatspec.YatspecTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@RunWith(SpringSpecRunner.class)
@SpringBootTest(classes = {ApplicationConfigurationForEndToEndTests.class, MocksConfigurationForEndToEndTests.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class EndToEndYatspecTest extends YatspecTest {

    @LocalServerPort
    protected int port;

}
