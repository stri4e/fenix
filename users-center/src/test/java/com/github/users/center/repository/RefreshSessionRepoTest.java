package com.github.users.center.repository;

import com.github.users.center.entity.RefreshSession;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static com.github.users.center.repository.RepositoryMocks.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RefreshSessionRepoTest {

    @Autowired
    private RefreshSessionRepo rsr;

    @Test
    public void create() {
        RefreshSession data = refreshSession();
        RefreshSession exp = refreshSessionExp();
        RefreshSession act = this.rsr.save(data);
        assertEquals(exp, act);
    }

    @Test
    public void findAllByUserId() {
        RefreshSession data = refreshSession();
        List<RefreshSession> exp = refreshSessions();
        this.rsr.save(data);
        List<RefreshSession> act = this.rsr.findAllByUserId(USER_ID);
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

}