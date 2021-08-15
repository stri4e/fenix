package com.github.products.services.impl;

import com.github.products.config.ServiceApplicationContextConfig;
import com.github.products.entity.Criteria;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.repository.CriteriaRepo;
import com.github.products.services.ICriteriaService;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.github.products.services.impl.CriteriaServiceMocks.*;
import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
@Import(value = ServiceApplicationContextConfig.class)
public class CriteriaServiceTest {

    @Autowired
    private CriteriaRepo criteriaRepo;

    @Autowired
    private ICriteriaService criteriaService;

    @Test
    public void givenCriteria_whenCreate_thenReturnNewCriteria() {
        Criteria data = entity_toSave();
        Criteria exp = entity_inDatabase();
        Mockito.when(this.criteriaRepo.save(data))
                .thenReturn(exp);
        Criteria act = this.criteriaService.create(data);
        assertEquals("Should comparing criteria and return true ", exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNull_whenCreate_thenThrowEntityBadRequest() {
        this.criteriaService.create(null);
    }

    @Test
    public void givenCriteriaId_whenReadById_thenReturnCriteria() {
        var criteriaId = 1L;
        Criteria exp = entity_inDatabase();
        Mockito.when(this.criteriaRepo.findById(criteriaId))
                .thenReturn(Optional.of(exp));
        Criteria act = this.criteriaService.readById(criteriaId);
        assertEquals("Should comparing criteria and return true ", exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullCriteriaId_whenReadById_thenThrowEntityBadRequest() {
        this.criteriaService.readById(null);
    }

    @Test
    public void givenListOfCriteriaIds_whenReadAllByIds_thenReturnListOfCriteria() {
        List<Long> data = List.of(1L, 2L, 3L, 4L, 5L);
        List<Criteria> exp = entities_inDatabase();
        Mockito.when(this.criteriaRepo.findAllById(data))
                .thenReturn(exp);
        List<Criteria> act = this.criteriaService.readAllByIds(data);
        assertThat("Should comparing criteria and return true ",
                act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray())
        );
    }

    @Test(expected = EntityBadRequest.class)
    public void givenEmptyListOfCriteriaIds_whenReadAllByIds_thenThrowEntityBadRequest() {
        this.criteriaService.readAllByIds(List.of());
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullListOfCriteriaIds_whenReadAllByIds_thenThrowEntityBadRequest() {
        this.criteriaService.readAllByIds(null);
    }

    @Test
    public void givenCriteria_whenUpdate_thenUpdateCriteriaValue() {
        Criteria data = entity_toUpdate();
        Criteria exp = entity_afterUpdate();
        Mockito.when(this.criteriaRepo.save(data))
                .thenReturn(exp);
        this.criteriaService.update(data);
        Mockito.verify(this.criteriaRepo).save(exp);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNull_whenUpdate_thenThrowEntityBadRequest() {
        this.criteriaService.update(null);
    }

    @Test
    public void givenCriteriaIdAndStatusOn_whenUpdateStatus_thenUpdateCriteriaStatus() {
        var criteriaId = 1L;
        var status = EntityStatus.on;
        Mockito.doNothing().when(this.criteriaRepo)
                .updateStatus(criteriaId, status);
        this.criteriaService.updateStatus(criteriaId, status);
        Mockito.verify(this.criteriaRepo)
                .updateStatus(criteriaId, status);
    }

    @Test
    public void givenCriteriaIdAndStatusOff_whenUpdateStatus_thenUpdateCriteriaStatus() {
        var criteriaId = 1L;
        var status = EntityStatus.off;
        Mockito.doNothing().when(this.criteriaRepo)
                .updateStatus(criteriaId, status);
        this.criteriaService.updateStatus(criteriaId, status);
        Mockito.verify(this.criteriaRepo)
                .updateStatus(criteriaId, status);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullCriteriaId_whenUpdateStatus_thenThrowEntityBadRequest() {
        this.criteriaService.updateStatus(null, EntityStatus.off);
    }

}