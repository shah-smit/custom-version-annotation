package com.example.bean;

import com.example.bean.model.GreetingRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "2.0"})
    public void greetingWithVersion(String version) throws Exception {
        this.mockMvc
                .perform(get("/"+version+"/greeting"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Greeting from v"+version)));
    }

    @ParameterizedTest
    @CsvSource({"1.0,Hi from", "2.0,Hello from"})
    public void greetingWithVersionAndMessage(String version, String message) throws Exception {
        this.mockMvc
                .perform(get("/"+version+"/greeting/"+message))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(message+" v"+version)));
    }

    @Test
    public void greetingWithVersionAndMultipleMessage() throws Exception {
        String version = "1.0";
        String message = "hi";
        String secondMessage = "from";

        this.mockMvc
                .perform(get("/"+version+"/greeting/"+message+"/"+secondMessage))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(message+" "+secondMessage+" v"+version)));
    }

    @Test
    public void greetingWithIncorrectVersionAndMultipleMessage() throws Exception {
        String version = "2.0";
        String message = "hi";
        String secondMessage = "from";
        this.mockMvc
                .perform(get("/"+version+"/greeting/"+message+"/"+secondMessage))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource({"1.0,Hi from", "2.0,Hello from"})
    public void greetingWithBodyMessage(String version, String message) throws Exception {
        GreetingRequest greetingRequest = new GreetingRequest();
        greetingRequest.setMessage(message);
        this.mockMvc
                .perform(post("/"+version+"/greeting/")
                            .contentType(APPLICATION_JSON)
                            .content(toJSON(greetingRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(message+" v"+version)));
    }

    private String toJSON(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}