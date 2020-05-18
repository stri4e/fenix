package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICategoryController;
import com.github.admins.dto.CategoryDto;
import com.github.admins.payload.Category;
import com.github.admins.services.ICategoryService;
import com.github.admins.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.admins.utils.TransferObj.fromCategory;
import static com.github.admins.utils.TransferObj.toCategory;

@RestController
@RequestMapping(path = "/v1/category")
@RequiredArgsConstructor
public class CategoryController implements ICategoryController {

    private final ICategoryService cs;

    @Override
    public ResponseEntity<CategoryDto> addCategory(@Valid CategoryDto payload) {
        Category tc = toCategory(payload);
        CategoryDto result = fromCategory(this.cs.create(tc));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryDto> getByName(String name) {
        Category c = this.cs.readByName(name);
        return new ResponseEntity<>(fromCategory(c), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<Category> categories = this.cs.readAll();
        List<CategoryDto> result = categories.stream()
                .map(TransferObj::fromCategory)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateCategory(@Valid CategoryDto payload) {
        this.cs.update(toCategory(payload));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeCategory(Long id) {
        this.cs.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
