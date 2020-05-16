package com.github.users.center.services.impl;

import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.repository.ConfirmTokenRepo;
import com.github.users.center.services.IConfirmService;
import com.github.users.center.services.ServiceMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static com.github.users.center.services.ServiceMocks.confirmToken;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {
        ConfirmService.class,
        ConfirmTokenRepo.class
})
class ConfirmServiceTest {

    private IConfirmService cs;

    private ConfirmTokenRepo ctr;

    @BeforeEach
    public void setUp() {
        this.ctr = PowerMockito.mock(ConfirmTokenRepo.class);
        this.cs = new ConfirmService(this.ctr);
    }

    @Test
    void create() {
        ConfirmToken data = confirmToken();
        PowerMockito.when(this.ctr.save(data))
                .thenReturn(data);
        this.cs.create(data);
        InOrder o = Mockito.inOrder(this.ctr);
        o.verify(this.ctr).save(data);
    }

    @Test
    void createException() {
        assertThrows(
                BadRequest.class,
                () -> this.cs.create(null)
        );
    }

    @Test
    void readByToken() {
        ConfirmToken exp = confirmToken();
        PowerMockito.when(this.ctr.findByToken(ServiceMocks.TOKEN))
                .thenReturn(Optional.of(exp));
        ConfirmToken act = this.cs.readByToken(ServiceMocks.TOKEN);
        assertEquals(exp, act);
    }

    @Test
    void readByTokenException() {
        assertThrows(BadRequest.class, () -> this.cs.readByToken(null));
    }

}