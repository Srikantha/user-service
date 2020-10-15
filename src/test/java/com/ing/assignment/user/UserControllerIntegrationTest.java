package com.ing.assignment.user;

import com.ing.assignment.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/userdetails";
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(),
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById() {
        System.out.println();
        User user = restTemplate.getForObject(getRootUrl() + "/1", User.class);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }


    @Test
    public void testUpdateUser() {
        int id = 1;
        User user = restTemplate.getForObject(getRootUrl() + id, User.class);
        user.setFirstName("admin1");
        user.setLastName("admin2");

        restTemplate.put(getRootUrl() + id, user);

        User updatedUser = restTemplate.getForObject(getRootUrl() + id, User.class);
        assertNotNull(updatedUser);
    }

    @Test
    public void testDeleteUser() {
        int id = 2;
        User user = restTemplate.getForObject(getRootUrl() + id, User.class);
        assertNotNull(user);

        restTemplate.delete(getRootUrl() + id);

        try {
            user = restTemplate.getForObject(getRootUrl() + id, User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
