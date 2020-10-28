package com.github.users.center.utils;


import com.github.users.center.TestConfig;
import com.github.users.center.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void createUserAccessToken() {
        UUID exp =  UUID.fromString("0158fe0e-0423-4d51-a4db-4802127fea63");
        var user = UtilsMocks.userExp();
        String token = this.jwtTokenProvider.userAccessToken(user);
        UUID act = TestUtils.parserUserToken(token);
        assertEquals(exp, act);
    }

    @Test
    public void createUserAccessTokenFailed() {
        String token = this.jwtTokenProvider.userAccessToken(null);
        assertNull(token);
    }

    @Test
    public void createAdminAccessToken() {
        UUID exp =  UUID.fromString("0158fe0e-0423-4d51-a4db-4802127fea63");
        var user = UtilsMocks.userExp();
        String token = this.jwtTokenProvider.adminAccessToken(user);
        UUID act = TestUtils.parserAdminToken(token);
        assertEquals(exp, act);
    }

    @Test
    public void createAdminAccessTokenFailed() {
        String token = this.jwtTokenProvider.adminAccessToken(null);
        assertNull(token);
    }

}