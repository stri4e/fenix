package com.github.admins.controllers.impl;

import com.github.admins.dto.CommentDto;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static com.github.admins.AdminsConstant.LOCALHOST;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class CommentControllerTest extends CommentTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String commentUrl;

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startServer() {
        mockServer = ClientAndServer.startClientAndServer(2222);
    }

    @AfterClass
    public static void downServer() {
        mockServer.stop();
    }

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/comment"
        );
        this.commentUrl = new URL(url).toString();
    }

    @Test
    public void findById() {
        readById();
        CommentDto exp = CommentControllerMocks.expCommentDto();
        String url = String.format("%s%s", this.commentUrl, "/1");
        ResponseEntity<CommentDto> response = this.restTemplate.getForEntity(
                url, CommentDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CommentDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void remove() {
        delete();
        String url = String.format("%s%s", this.commentUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
