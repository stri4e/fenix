package com.github.products.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.products.config.ControllerApplicationContextConfig;
import com.github.products.controllers.IBrandsController;
import com.github.products.dto.BrandDto;
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

    @Test
    public void givenBrandDto_whenSave_thenReturnBrandDtoAndStatusCreate() throws Exception {
        BrandDto payload = requestTo_saveBrand();
        BrandDto exp = responseFrom_saveBrand();
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

}