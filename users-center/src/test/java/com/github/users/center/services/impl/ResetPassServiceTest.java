package com.github.users.center.services.impl;

import com.github.users.center.entity.PassResetToken;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.repository.PassResetRepo;
import com.github.users.center.services.IResetPassService;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {
        PassResetRepo.class,
        ResetPassService.class
})
class ResetPassServiceTest {

    private IResetPassService rps;

    private PassResetRepo prr;

    @BeforeEach
    void setUp() {
        this.prr = PowerMockito.mock(PassResetRepo.class);
        this.rps = new ResetPassService(this.prr);
    }

    @Test
    void create() {
        PassResetToken data = ServiceMocks.passResetToken();
        PowerMockito.when(this.prr.save(data))
                .thenReturn(data);
        this.rps.create(data);
        InOrder o = Mockito.inOrder(this.prr);
        o.verify(this.prr).save(data);
    }

    @Test
    void createException() {
        assertThrows(BadRequest.class, () -> this.rps.create(null));
    }

    @Test
    void readByToken() {
        PassResetToken data = ServiceMocks.passResetToken();
        PowerMockito.when(this.prr.findByToken(ServiceMocks.TOKEN))
                .thenReturn(Optional.of(data));
        this.rps.readByToken(ServiceMocks.TOKEN);
    }

    @Test
    void readByTokenTokenNull() {
        assertThrows(BadRequest.class, () ->
                this.rps.readByToken(null)
        );
    }

    @Test
    void readByTokenTokenEmpty() {
        assertThrows(BadRequest.class, () ->
                this.rps.readByToken("")
        );
    }

    @Test
    void delete() {
        int count = 1;
        PassResetToken data = ServiceMocks.passResetToken();
        PowerMockito.doNothing().when(this.prr)
                .delete(data);
        this.rps.delete(data);
        Mockito.verify(this.prr, Mockito.times(count))
                .delete(data);
    }

}