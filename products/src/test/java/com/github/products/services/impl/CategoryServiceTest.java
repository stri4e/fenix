package com.github.products.services.impl;

import com.github.products.config.ServiceApplicationContextConfig;
import com.github.products.entity.Category;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.repository.CategoryRepo;
import com.github.products.services.ICategoryService;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.github.products.services.impl.CategoryServiceMock.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Import(value = ServiceApplicationContextConfig.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void whenRead_thenReturnListOfCategory() {
        List<Category> exp = entities_inDatabase_statusOn();
        Mockito.when(this.categoryRepo.findAll(Sort.by("id")))
                .thenReturn(exp);
        List<Category> act = this.categoryService.readAllStatusOn();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void givenCategory_whenCreate_thenSaveNewCategoryAndReturnCategory() {
        Category category = entity_toSave();
        Category exp = entity_inDatabase();
        Mockito.when(this.categoryRepo.save(category))
                .thenReturn(exp);
        Category act = this.categoryService.create(category);
        assertEquals("Should comparing categories and return true ", exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void whenCreate_thenThrowEntityBadRequest() {
        this.categoryService.create(null);
    }

    @Test
    public void givenCategoryName_whenReadByName_thenReturnCategory() {
        var categoryName = "category-test-1";
        Category exp = entity_inDatabase();
        Mockito.when(this.categoryRepo.findByName(categoryName))
                .thenReturn(Optional.ofNullable(exp));
        Category act = this.categoryService.readByName(categoryName);
        assertEquals("Should comparing categories and return true ", exp, act);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenNullCategoryName_whenReadByName_thenThrowParametersBadRequest() {
        this.categoryService.readByName(null);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenEmptyCategoryName_whenReadByName_thenThrowParametersBadRequest() {
        this.categoryService.readByName("");
    }

    @Test
    public void givenCategoryId_whenReadByName_thenReturnCategory() {
        var categoryId = 1L;
        Category exp = entity_inDatabase();
        Mockito.when(this.categoryRepo.findById(categoryId))
                .thenReturn(Optional.of(exp));
        Category act = this.categoryService.readById(categoryId);
        assertEquals("Should comparing categories and return true ", exp, act);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenNullCategoryId_whenReadByName_thenThrowParametersBadRequest() {
        this.categoryService.readByName(null);
    }

    @Test
    public void givenCategory_whenUpdate_thenUpdateCategoryName() {
        Category category = entity_toUpdate();
        Mockito.doNothing().when(this.categoryRepo)
                .updateName(category.getId(), category.getName());
        this.categoryService.update(category);
        Mockito.verify(this.categoryRepo)
                .updateName(category.getId(), category.getName());
    }

    @Test(expected = EntityBadRequest.class)
    public void whenUpdate_thenThrowEntityBadRequest() {
        this.categoryService.update(null);
    }

    @Test
    public void givenId_whenRemove_thenDoUpdateCategoryStatusOnOff() {
        var categoryId = 1L;
        Mockito.doNothing().when(this.categoryRepo)
                .updateStatus(categoryId, EntityStatus.off);
        this.categoryService.removeId(categoryId);
        Mockito.verify(this.categoryRepo)
                .updateStatus(categoryId, EntityStatus.off);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenNullId_whenRemove_thenDoUpdateCategoryStatusOnOff() {
        this.categoryService.removeId(null);
    }

    @Test
    public void whenReadAll_thenReturnListOfCategory() {
        List<Category> exp = entities_inDatabase_statusOn();
        Mockito.when(this.categoryRepo.findAll())
                .thenReturn(exp);
        List<Category> act = this.categoryService.readAll();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

}