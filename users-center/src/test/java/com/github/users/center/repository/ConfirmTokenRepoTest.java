package com.github.users.center.repository;

import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.github.users.center.repository.RepositoryMocks.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ConfirmTokenRepoTest {

    @Autowired
    private ConfirmTokenRepo ctr;

    @Autowired
    private UserRepo ur;

    @Test
    public void createCT() {
        User user = user();
        ConfirmToken data = confirmToken();
        ConfirmToken exp = confirmTokenExp();
        this.ur.save(user);
        ConfirmToken act = this.ctr.save(data);
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    void findByToken() {
        User user = user();
        ConfirmToken data = confirmToken();
        this.ur.save(user);
        ConfirmToken exp = this.ctr.save(data);
        ConfirmToken act = this.ctr.findByToken(exp.getToken())
                .orElse(null);
        assertNotNull(exp);
        assertNotNull(act);
        assertEquals(exp, act);
    }

}