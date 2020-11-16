package com.github.users.center.repository;

import com.github.users.center.entity.RefreshSession;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("createAt", "updateAt")
                .isEqualTo(exp);
    }

    @Test
    public void findAllByUserId() {
        RefreshSession data = refreshSession();
        List<RefreshSession> exp = refreshSessions();
        this.rsr.save(data);
        List<RefreshSession> act = this.rsr.findAllByUserId(USER_ID);
        Assertions.assertThat(act)
                .usingElementComparatorIgnoringFields("createAt", "updateAt")
                .contains(exp.toArray(new RefreshSession[]{}));
    }

}