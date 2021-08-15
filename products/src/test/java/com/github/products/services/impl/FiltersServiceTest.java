package com.github.products.services.impl;

import com.github.products.config.ServiceApplicationContextConfig;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Filter;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.repository.FiltersRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.products.services.impl.FiltersServiceMocks.*;
import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
@Import(value = ServiceApplicationContextConfig.class)
public class FiltersServiceTest {

    @Autowired
    private FiltersRepo filtersRepo;

    @Autowired
    private FiltersService filtersService;

    @Test
    public void givenFilter_whenCreate_thenReturnFilter() {
        Filter data = beforeCreate();
        Filter exp = afterCreate();
        Mockito.when(this.filtersRepo.save(data))
                .thenReturn(exp);
        Filter act = this.filtersService.create(data);
        assertEquals("Should comparing filter and return true", exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullCriteria_whenCreate_thenReturnFilter() {
        this.filtersService.create(null);
    }

    @Test
    public void givenFilterId_whenReadById_thenReturnFilter() {
        var filterId = 1L;
        Filter exp = toReadById();
        Mockito.when(this.filtersRepo.findById(filterId))
                .thenReturn(Optional.of(exp));
        Filter act = this.filtersService.readById(filterId);
        assertEquals("Should comparing filter and return true", exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullFilterId_whenReadById_thenThrowEntityBadRequest() {
        this.filtersService.readById(null);
    }

    @Test
    public void givenFilter_whenUpdate_thenUpdateFilter() {
        Filter data = toUpdate();
        Mockito.when(this.filtersRepo.save(data))
                .thenReturn(data);
        this.filtersService.update(data);
        Mockito.verify(this.filtersRepo).save(data);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullFilter_whenUpdate_thenThrowEntityBadRequest() {
        this.filtersService.update(null);
    }

    @Test
    public void givenFilter_whenUpdateTitle_thenUpdateFilter() {
        Filter data = toUpdate();
        Mockito.doNothing().when(this.filtersRepo)
                .updateTitle(data);
        this.filtersService.updateTitle(data);
        Mockito.verify(this.filtersRepo).updateTitle(data);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullFilter_whenUpdateTitle_thenThrowEntityBadRequest() {
        this.filtersService.updateTitle(null);
    }

    @Test
    public void givenFilterId_whenDelete_thenChangeFilterStatus() {
        var filterId = 1L;
        Mockito.doNothing().when(this.filtersRepo)
                .updateStatus(filterId, EntityStatus.off);
        this.filtersService.delete(filterId);
        Mockito.verify(this.filtersRepo)
                .updateStatus(filterId, EntityStatus.off);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullFilterId_whenDelete_thenChangeFilterStatus() {
        this.filtersService.delete(null);
    }

}