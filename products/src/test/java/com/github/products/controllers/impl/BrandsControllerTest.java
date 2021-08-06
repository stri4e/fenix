package com.github.products.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.products.config.ControllerApplicationContextConfig;
import com.github.products.controllers.IBrandsController;
import com.github.products.dto.BrandDto;
import com.github.products.entity.EntityStatus;
import com.github.products.services.IBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.github.products.controllers.impl.BrandsControllerMocks.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {BrandsController.class, IBrandsController.class})
@Import(ControllerApplicationContextConfig.class)
public class BrandsControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IBrandService brandService;

    //============================================
    //=========== Save Brand =====================
    //============================================

    @Test
    public void givenBrandDto_whenSave_thenReturnBrandDtoAndStatusCreate() throws Exception {
        BrandDto payload = requestTo_saveBrand();
        BrandDto exp = responseFrom_controller();
        Mockito.when(this.brandService.create(entityBefore_create()))
                .thenReturn(entityAfter_create());
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/brands/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.mapper.writeValueAsString(payload))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenNull_whenSave_thenStatusBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/brands/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenBrandDtoWithEmptyName_whenSave_thenStatusBadRequest() throws Exception {
        BrandDto payload = new BrandDto();
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/brands/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.mapper.writeValueAsString(payload))
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    //============================================
    //=========== Find By Name ===================
    //============================================

    @Test
    public void givenBrandName_whenFindByName_thenReturnBrandDtoAndStatusOk() throws Exception {
        BrandDto exp = responseFrom_controller();
        Mockito.when(this.brandService.readByName("Nike"))
                .thenReturn(entityIn_database());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/fetch/Nike")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenNull_whenFindByName_thenReturnStatusBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/fetch")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenEmpty_whenFindByName_thenReturnStatusBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/fetch")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    //============================================
    //=========== Find By Status =================
    //============================================

    @Test
    public void givenStatusOn_whenFindByStatus_thenReturnBrandsArrayManyStatusOk() throws Exception {
        List<BrandDto> exp = responseBrandArrayManyFrom_controller();
        Mockito.when(this.brandService.readAllByStatus(EntityStatus.on))
                .thenReturn(entitiesInDatabaseMany_findAllByStatusOn());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/on")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenStatusOn_whenFindByStatus_thenReturnBrandsArrayTwoStatusOk() throws Exception {
        List<BrandDto> exp = responseBrandArrayTwoFrom_controller();
        Mockito.when(this.brandService.readAllByStatus(EntityStatus.on))
                .thenReturn(entitiesInDatabaseTwo_findAllByStatusOn());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/on")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenStatusOn_whenFindByStatus_thenReturnBrandsArrayOneStatusOk() throws Exception {
        List<BrandDto> exp = responseBrandArrayOneFrom_controller();
        Mockito.when(this.brandService.readAllByStatus(EntityStatus.on))
                .thenReturn(entitiesInDatabaseOne_findAllByStatusOn());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/on")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenStatusOff_whenFindByStatus_thenReturnBrandsArrayManyStatusOk() throws Exception {
        List<BrandDto> exp = responseBrandArrayManyFrom_controller();
        Mockito.when(this.brandService.readAllByStatus(EntityStatus.off))
                .thenReturn(entitiesInDatabaseMany_findAllByStatusOff());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/off")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenStatusOff_whenFindByStatus_thenReturnBrandsArrayTwoStatusOk() throws Exception {
        List<BrandDto> exp = responseBrandArrayTwoFrom_controller();
        Mockito.when(this.brandService.readAllByStatus(EntityStatus.off))
                .thenReturn(entitiesInDatabaseTwo_findAllByStatusOff());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/off")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenStatusOff_whenFindByStatus_thenReturnBrandsArrayOneStatusOk() throws Exception {
        List<BrandDto> exp = responseBrandArrayOneFrom_controller();
        Mockito.when(this.brandService.readAllByStatus(EntityStatus.off))
                .thenReturn(entitiesInDatabaseOne_findAllByStatusOff());
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/off")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content()
                        .json(this.mapper.writeValueAsString(exp))
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenWrongStatus_whenFindByStatus_thenReturnStatusBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/brands/omg")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    //============================================
    //=========== Update Brand ===================
    //============================================

    @Test
    public void givenBrandDto_whenUpdate_thenReturnStatusOk() throws Exception {
        BrandDto payload = requestTo_updateBrand();
        Mockito.doNothing().when(this.brandService).update(1L, "Nike");
        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/v1/brands/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(payload))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenBrandDtoNameNull_whenUpdate_thenReturnStatusOk() throws Exception {
        BrandDto payload = new BrandDto(1L);
        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/v1/brands/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.mapper.writeValueAsString(payload))
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenNull_whenUpdate_thenReturnStatusBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/v1/brands/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

    //============================================
    //=========== Remove Brand ===================
    //============================================

    @Test
    public void whenRemove_thenReturnStatusNoContent() throws Exception {
        Mockito.doNothing().when(this.brandService).delete(1L);
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/brands/edit/1")
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenRemove_thenReturnStatusBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/brands/edit/ ")
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andDo(MockMvcResultHandlers.print());
    }

}
