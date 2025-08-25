package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Skeleton template for a controller test using MockMvc.
 *
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 * @CsvSource and @MethodSource for your test data.
 *
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 *                 .andExpect(status().whateverStatusCodeYouExpect())
 *                 .andExpect(content().string("string-you-expect-in-response")).
 *                 .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
        @Autowired
        private MockMvc mockMvc;

        // 1. Basic functionality
        @Test
        void testRemoveCommonWords() throws Exception {
            mockMvc.perform(get("/remove").param("str", "eloquent"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("loquen"));

            mockMvc.perform(get("/remove").param("str", "country"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ountr"));

            mockMvc.perform(get("/remove").param("str", "person"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("erso"));
        }

        // 2. Edge cases
        @Test
        void testRemoveEdgeCases() throws Exception {
            // Exactly 2 chars -> empty string
            mockMvc.perform(get("/remove").param("str", "ab"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));

            // Exactly 3 chars -> one char remains
            mockMvc.perform(get("/remove").param("str", "xyz"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("y"));

            // Less than 2 chars -> 400 Bad Request
            mockMvc.perform(get("/remove").param("str", "a"))
                    .andExpect(status().isBadRequest());
        }

        // 3. Strings with numbers and special chars
        @Test
        void testRemoveSpecialChars() throws Exception {
            mockMvc.perform(get("/remove").param("str", "_123_%qwerty+"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("123_%qwerty"));

            mockMvc.perform(get("/remove").param("str", "@$"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }
    }


