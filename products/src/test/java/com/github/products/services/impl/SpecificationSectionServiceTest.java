package com.github.products.services.impl;

import com.github.products.config.ServiceApplicationContextConfig;
import com.github.products.entity.SpecificationSection;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.repository.SpecSectionRepo;
import com.github.products.services.ISpecSectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.products.services.impl.SpecSectionServiceMocks.*;
import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
@Import(value = ServiceApplicationContextConfig.class)
public class SpecificationSectionServiceTest {

    @Autowired private SpecSectionRepo specSectionRepo;

    @Autowired private ISpecSectionService instance;

    @Test
    public void givenSpecSection_whenCreate_thenReturnNewSpecSection() {
        SpecificationSection data = entityBeforeSave();
        SpecificationSection exp = entityAfterSave();
        Mockito.when(this.specSectionRepo.save(data))
                .thenReturn(exp);
        SpecificationSection act = this.instance.create(data);
        assertEquals(exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullSpecSection_whenCreate_thenThrowEntityBadRequest() {
        this.instance.create(null);
    }

    @Test
    public void givenSpecSectionId_whenFindById_thenReturnSpecSection() {
        var specSectionId = 1L;
        SpecificationSection exp = entityAfterFindById();
        Mockito.when(this.specSectionRepo.findById(specSectionId))
                .thenReturn(Optional.of(exp));
        SpecificationSection act = this.instance.readById(specSectionId);
        assertEquals(exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullSpecSectionId_whenFindById_thenThrowEntityBadRequest() {
        this.instance.readById(null);
    }

    @Test
    public void givenSpecSection_whenUpdateTitle_thenCallToUpdateTitle() {
        var id = 1L;
        var title = "test-title";
        Mockito.doNothing().when(this.specSectionRepo)
                .updateTitle(id, title);
        this.instance.updateTitle(id, title);
        Mockito.verify(this.specSectionRepo)
                .updateTitle(id, title);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullId_whenUpdateTitle_thenThrowEntityBadRequest() {
        var title = "test-title";
        this.instance.updateTitle(null, title);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullTitle_whenUpdateTitle_thenThrowEntityBadRequest() {
        var id = 1L;
        this.instance.updateTitle(id, null);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenEmptyTitle_whenUpdateTitle_thenThrowEntityBadRequest() {
        var id = 1L;
        this.instance.updateTitle(id, "");
    }

    @Test
    public void givenSpecSection_whenUpdate_thenCallToSave() {
        SpecificationSection data = entityToUpdate();
        Mockito.when(this.specSectionRepo.save(data))
                .thenReturn(data);
        this.instance.update(data);
        Mockito.verify(this.specSectionRepo)
                .save(data);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullSpecSection_whenUpdate_thenThrowEntityBadRequest() {
        this.instance.update(null);
    }

    @Test
    public void givenSpecSectionId_whenRemove_thenDoCallToDeleteById() {
        var id = 1L;
        Mockito.doNothing().when(this.specSectionRepo)
                .deleteById(id);
        this.instance.remove(id);
        Mockito.verify(this.specSectionRepo)
                .deleteById(id);
    }

}