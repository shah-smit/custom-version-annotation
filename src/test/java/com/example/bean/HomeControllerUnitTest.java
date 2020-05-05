package com.example.bean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //bean.get.endpoint=/{version}/greeting
    //bean.get.endpoint.message=/{version}/greeting/{message}

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "2.0"})
    public void greetingWithVersion(String version) throws Exception {
        assertThat(this.restTemplate
                .getForObject("http://localhost:" + port + "/"+version+"/greeting", String.class))
                .contains("Greeting from v"+version);
    }

    @ParameterizedTest
    @CsvSource({"1.0,Hi from", "2.0,Hello from"})
    public void greetingWithVersionAndMessage(String version, String message) throws Exception {
        assertThat(this.restTemplate
                .getForObject("http://localhost:" + port + "/"+version+"/greeting/"+message, String.class))
                .contains(message+" v"+version);
    }
}