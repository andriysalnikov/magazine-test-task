package ua.pp.salnikov.magazine_test_task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ua.pp.salnikov.magazine_test_task.exception.custom.UserAlreadyExistException;

import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:sql/create-tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/drop-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "spring")
    @Test
    void whenGetAllUsers_shouldReturnStatus200AndListOfAllUsers() throws Exception {
        File file = new File("src/test/resources/json/list_of_all_users.json");
        JsonNode jsonFromFile = new ObjectMapper().readValue(file, JsonNode.class);
        MvcResult result = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(jsonFromFile.toString(), result.getResponse().getContentAsString());
    }

    @WithMockUser(value = "spring")
    @Test
    void whenGetAllUsersOlderThan30_shouldReturnStatus200AndListOfAllUsersOlderThan30() throws Exception {
        File file = new File("src/test/resources/json/list_of_all_users_older_than.json");
        JsonNode jsonFromFile = new ObjectMapper().readValue(file, JsonNode.class);
        MvcResult result = mockMvc.perform(get("/users/older-than/{age}", 30))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(jsonFromFile.toString(), result.getResponse().getContentAsString());
    }

    @WithMockUser(value = "spring")
    @Test
    void whenGetAllUsersWithArticlesOfColor_shouldReturnStatus200AndListOfAllUsersWithArticlesOfColor()
            throws Exception {
        File file = new File("src/test/resources/json/list_of_all_users_with_articles_of_color.json");
        JsonNode jsonFromFile = new ObjectMapper().readValue(file, JsonNode.class);
        MvcResult result = mockMvc.perform(get("/users/articles-color/{color}", "blue"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(jsonFromFile.toString(), result.getResponse().getContentAsString());
    }

    @WithMockUser(value = "spring")
    @Test
    void whenGetAllUserNamesWithCountOfArticlesGreaterThan3_shouldReturnStatus200AndListOfAllUserNamesWithArticlesCountGreaterThan3() throws Exception {
        File file = new File("src/test/resources/json/list_of_all_users_with_articles_count_greater_than.json");
        JsonNode jsonFromFile = new ObjectMapper().readValue(file, JsonNode.class);
        MvcResult result = mockMvc.perform(get("/users/articles-count/{count}", 3))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(jsonFromFile.toString(), result.getResponse().getContentAsString());
    }

    @WithMockUser(value = "spring")
    @Test
    void whenCreateUser_shouldReturnStatus201AndCreatedUser() throws Exception {
        File file = new File("src/test/resources/json/user_request.json");
        JsonNode jsonFromFileRequest = new ObjectMapper().readValue(file, JsonNode.class);
        file = new File("src/test/resources/json/user_response.json");
        JsonNode jsonFromFileResponse = new ObjectMapper().readValue(file, JsonNode.class);
        MvcResult result = mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFromFileRequest.toString()))
                .andExpect(status().isCreated())
                .andReturn();
        assertEquals(jsonFromFileResponse.toString(), result.getResponse().getContentAsString());
    }

    @WithMockUser(value = "spring")
    @Test
    void whenCreateExistUser_shouldReturnStatus400AndThrowException() throws Exception {
        File file = new File("src/test/resources/json/user_exist_request.json");
        JsonNode jsonFromFile = new ObjectMapper().readValue(file, JsonNode.class);
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFromFile.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserAlreadyExistException))
                .andExpect(result -> assertEquals("'User' with this 'name' already exists",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @WithMockUser(value = "spring")
    @ParameterizedTest
    @CsvSource({"name", "password", "age"})
    void whenCreateUserWithoutSomeField_shouldReturnStatus400AndThrowException(String field) throws Exception {
        File file = new File("src/test/resources/json/user_request.json");
        JsonNode jsonFromFile = new ObjectMapper().readValue(file, JsonNode.class);
        ((ObjectNode) jsonFromFile).remove(field);
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFromFile.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
    }

}