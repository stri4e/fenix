package com.github.products.services.impl;

import com.github.products.config.ServiceApplicationContextConfig;
import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.repository.BrandRepo;
import com.github.products.services.IBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.github.products.services.impl.BrandServiceMock.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@Import(value = ServiceApplicationContextConfig.class)
public class BrandServiceTest {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private IBrandService brandService;

    public BrandServiceTest() {
    }

    @Test
    public void givenBrand_whenCreate_thenSaveNewBrandAndReturnNewBrand() {
        Brand brand = dataToCreate();
        Brand exp = entityInDatabase_id_1();
        Mockito.when(this.brandRepo.save(brand))
                .thenReturn(exp);
        Brand act = this.brandService.create(brand);
        assertEquals(exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNull_whenCreate_thenThrowBadRequest() {
        Mockito.when(this.brandRepo.save(null))
                .thenThrow(new EntityBadRequest());
        this.brandService.create(null);
    }

    @Test
    public void givenBrandName_whenReadByName_thenReturnBrand() {
        var brandName = "brand-test";
        Brand exp = entityInDatabase_id_1();
        Mockito.when(this.brandRepo.findByName(brandName))
                .thenReturn(Optional.of(exp));
        Brand act = this.brandService.readByName(brandName);
        assertEquals(exp, act);
    }

    @Test(expected = EntityNotFound.class)
    public void givenBrandName_whenReadByName_thenThrowNotFound() {
        var brandName = "brand-test";
        Mockito.when(this.brandRepo.findByName(brandName))
                .thenReturn(Optional.empty());
        this.brandService.readByName(brandName);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenNullString_whenReadByName_thenThrowBadRequest() {
        this.brandService.readByName(null);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenEmptyString_whenReadByName_thenThrowBadRequest() {
        this.brandService.readByName("");
    }

    @Test
    public void givenBrandId1_whenReadById_thenReturnBrand() {
        var brandId = 1L;
        Brand exp = entityInDatabase_id_1();
        Mockito.when(this.brandRepo.findById(brandId))
                .thenReturn(Optional.of(exp));
        Brand act = this.brandService.readById(brandId);
        assertEquals(exp, act);
    }

    @Test
    public void givenBrandId2_whenReadById_thenReturnBrand() {
        var brandId = 2L;
        Brand exp = entityInDatabase_id_2();
        Mockito.when(this.brandRepo.findById(brandId))
                .thenReturn(Optional.of(exp));
        Brand act = this.brandService.readById(brandId);
        assertEquals(exp, act);
    }

    @Test(expected = EntityNotFound.class)
    public void givenBrandId_whenReadById_thenThrowNotFound() {
        var brandId = 1L;
        Mockito.when(this.brandRepo.findById(brandId))
                .thenReturn(Optional.empty());
        this.brandService.readById(brandId);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNull_whenReadById_thenThrowBadRequest() {
        this.brandService.readById(null);
    }

    @Test
    public void givenEntityStatusOn_whenReadAllByStatus_thenReturnListOfBrands() {
        List<Brand> exp = entitiesInDatabase_statusOn();
        Mockito.when(this.brandRepo.findAllByStatus(EntityStatus.on))
                .thenReturn(exp);
        List<Brand> act = this.brandService.readAllByStatus(EntityStatus.on);
        assertThat(act, containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void givenEntityStatusOff_whenReadAllByStatus_thenReturnListOfBrands() {
        List<Brand> exp = entitiesInDatabase_statusOff();
        Mockito.when(this.brandRepo.findAllByStatus(EntityStatus.off))
                .thenReturn(exp);
        List<Brand> act = this.brandService.readAllByStatus(EntityStatus.off);
        assertThat(act, containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void givenBrandIdAndName_whenUpdate_thenDoUpdate() {
        var id = 1L;
        var name = "brand-name-test";
        Mockito.doNothing().when(this.brandRepo).updateName(id, name);
        this.brandService.update(id, name);
        Mockito.verify(this.brandRepo).updateName(id, name);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenBrandNullAndName_whenUpdate_thenThrowBadRequest() {
        var name = "brand-name-test";
        this.brandService.update(null, name);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenBrandIdAndNullName_whenUpdate_thenThrowBadRequest() {
        var id = 1L;
        this.brandService.update(id, null);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenBrandIdAndEmptyName_whenUpdate_thenThrowBadRequest() {
        var id = 1L;
        this.brandService.update(id, "");
    }

    @Test
    public void givenBrandId_whenDelete_thenChangeBrandStatus() {
        var id = 1L;
        Mockito.doNothing().when(this.brandRepo)
                .updateStatus(id, EntityStatus.off);
        this.brandService.delete(id);
        Mockito.verify(this.brandRepo).updateStatus(id, EntityStatus.off);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenBrandIdNull_whenDelete_thenThrowBadRequest() {
        this.brandService.delete(null);
    }

}
