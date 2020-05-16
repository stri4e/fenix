package com.github.users.center.services.impl;

import com.github.users.center.entity.User;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.exceptions.NotFound;
import com.github.users.center.repository.UserRepo;
import com.github.users.center.services.IUserService;
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
        UserRepo.class,
        UserService.class
})
class UserServiceTest {

    private IUserService us;

    private UserRepo ur;

    @BeforeEach
    void setUp() {
        this.ur = PowerMockito.mock(UserRepo.class);
        this.us = new UserService(this.ur);
    }

    @Test
    void create() {
        User data = ServiceMocks.user();
        PowerMockito.when(this.ur.save(data))
                .thenReturn(data);
        this.us.create(data);
        InOrder o = Mockito.inOrder(this.ur);
        o.verify(this.ur).save(data);
    }

    @Test
    void createException() {
        assertThrows(BadRequest.class, () -> {
            this.us.create(null);
        });
    }

    @Test
    void readById() {
        User exp = ServiceMocks.userExp();
        PowerMockito.when(this.ur.findById(ServiceMocks.ID))
                .thenReturn(Optional.of(exp));
        User act = this.us.readById(ServiceMocks.ID);
        assertEquals(exp, act);
    }

    @Test
    void readByIdException() {
        PowerMockito.when(this.ur.findById(ServiceMocks.ID))
                .thenReturn(Optional.empty());
        assertThrows(NotFound.class, () ->
                this.us.readById(ServiceMocks.ID)
        );
    }

    @Test
    void readByIdNull() {
        assertThrows(BadRequest.class, () ->
                this.us.readById(null)
        );
    }

    @Test
    void readByEmail() {
        User exp = ServiceMocks.userExp();
        PowerMockito.when(this.ur.findByEmail(ServiceMocks.EMAIL))
                .thenReturn(Optional.of(exp));
        User act = this.us.readByEmail(ServiceMocks.EMAIL);
        assertEquals(exp, act);
    }

    @Test
    void readByEmailException() {
        PowerMockito.when(this.ur.findByEmail(ServiceMocks.EMAIL))
                .thenReturn(Optional.empty());
        assertThrows(NotFound.class, () ->
                this.us.readByEmail(ServiceMocks.EMAIL)
        );
    }

    @Test
    void readByEmailNull() {
        assertThrows(BadRequest.class, () ->
                this.us.readByEmail(null)
        );
    }

    @Test
    void readByLogin() {
        User exp = ServiceMocks.userExp();
        PowerMockito.when(this.ur.findByLogin(ServiceMocks.LOGIN))
                .thenReturn(Optional.of(exp));
        User act = this.us.readByLogin(ServiceMocks.LOGIN);
        assertEquals(exp, act);
    }

    @Test
    void readByLoginException() {
        PowerMockito.when(this.ur.findByLogin(ServiceMocks.LOGIN))
                .thenReturn(Optional.empty());
        assertThrows(NotFound.class, () ->
                this.us.readByLogin(ServiceMocks.LOGIN)
        );
    }

    @Test
    void readByLoginNull() {
        assertThrows(BadRequest.class, () ->
                this.us.readByLogin(null)
        );
    }

    @Test
    void readByEmailOrLogin() {
        User exp = ServiceMocks.userExp();
        PowerMockito.when(this.ur.findByEmailOrLogin(
                ServiceMocks.EMAIL, ServiceMocks.LOGIN
        )).thenReturn(Optional.of(exp));
        User act = this.us.readByEmailOrLogin(
                ServiceMocks.EMAIL, ServiceMocks.LOGIN
        );
        assertEquals(exp, act);
    }

    @Test
    void readByEmailOrLoginException() {
        PowerMockito.when(this.ur.findByEmailOrLogin(
                ServiceMocks.EMAIL, ServiceMocks.LOGIN
        )).thenReturn(Optional.empty());
        assertThrows(NotFound.class, () ->
                this.us.readByEmailOrLogin(
                        ServiceMocks.EMAIL, ServiceMocks.LOGIN
                )
        );
    }

    @Test
    void readByEmailOrLoginEmailNull() {
        assertThrows(BadRequest.class, () ->
                this.us.readByEmailOrLogin(
                        null, ServiceMocks.LOGIN
                )
        );
    }

    @Test
    void readByEmailOrLoginEmailEmpty() {
        assertThrows(BadRequest.class, () ->
                this.us.readByEmailOrLogin(
                        "", ServiceMocks.LOGIN
                )
        );
    }

    @Test
    void readByEmailOrLoginLoginNull() {
        assertThrows(BadRequest.class, () ->
                this.us.readByEmailOrLogin(
                        ServiceMocks.EMAIL, null
                )
        );
    }

    @Test
    void readByEmailOrLoginLoginEmpty() {
        assertThrows(BadRequest.class, () ->
                this.us.readByEmailOrLogin(
                        ServiceMocks.EMAIL, ""
                )
        );
    }

    @Test
    void existsByEmailOrLogin() {
        PowerMockito.when(this.ur.existsByEmailOrLogin(
                ServiceMocks.EMAIL, ServiceMocks.LOGIN
        )).thenReturn(Boolean.TRUE);
        boolean act = this.us.existsByEmailOrLogin(
                ServiceMocks.EMAIL, ServiceMocks.LOGIN
        );
        assertTrue(act);
    }

    @Test
    void existsByEmailOrLoginEmailNull() {
        boolean act = this.us.existsByEmailOrLogin(
                null, ServiceMocks.LOGIN
        );
        assertFalse(act);
    }

    @Test
    void existsByEmailOrLoginEmailEmpty() {
        boolean act = this.us.existsByEmailOrLogin(
                "", ServiceMocks.LOGIN
        );
        assertFalse(act);
    }

    @Test
    void existsByEmailOrLoginLoginNull() {
        boolean act = this.us.existsByEmailOrLogin(
                ServiceMocks.EMAIL, null
        );
        assertFalse(act);
    }

    @Test
    void existsByEmailOrLoginLoginEmpty() {
        boolean act = this.us.existsByEmailOrLogin(
                ServiceMocks.EMAIL, ""
        );
        assertFalse(act);
    }

    @Test
    void updatePass() {
        PowerMockito.doNothing().when(this.ur)
                .updatePass(ServiceMocks.PASS, ServiceMocks.ID);
        this.us.updatePass(ServiceMocks.PASS, ServiceMocks.ID);
        Mockito.verify(this.ur)
                .updatePass(ServiceMocks.PASS, ServiceMocks.ID);
    }

    @Test
    void updatePassPassNull() {
        assertThrows(BadRequest.class, () ->
                this.us.updatePass(
                        null, ServiceMocks.ID
                )
        );
    }

    @Test
    void updatePassPassEmpty() {
        assertThrows(BadRequest.class, () ->
                this.us.updatePass(
                        "", ServiceMocks.ID
                )
        );
    }

    @Test
    void updatePassIdNull() {
        assertThrows(BadRequest.class, () ->
                this.us.updatePass(
                        ServiceMocks.PASS, null
                )
        );
    }

    @Test
    void updateIsEnable() {
        PowerMockito.doNothing().when(this.ur)
                .updateIsEnable(Boolean.TRUE, ServiceMocks.ID);
        this.us.updateIsEnable(Boolean.TRUE, ServiceMocks.ID);
        Mockito.verify(this.ur)
                .updateIsEnable(Boolean.TRUE, ServiceMocks.ID);
    }

    @Test
    void updateIsEnableIdNull() {
        assertThrows(BadRequest.class, () ->
                this.us.updateIsEnable(Boolean.TRUE, null
                )
        );
    }

}
