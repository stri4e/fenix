package com.github.server.mocks.controllers

import com.github.server.mocks.payload.Category
import com.github.server.mocks.services.impl.CategoryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class CategoryController(private val categoryService: CategoryService) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass);

    @PostMapping(
            "/v1/categories/edit"
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody payload: Category): Category {
        log.info("Enter: {}", payload)
        return this.categoryService.create(payload);
    }

    @GetMapping(
            "/v1/categories/fetch/{name}"
    )
    @ResponseStatus(HttpStatus.OK)
    fun readByName(@PathVariable name: String): Category {
        log.info("Enter: {}", name)
        return this.categoryService.readByName(name)
    }

    @GetMapping(
            "/v1/categories"
    )
    @ResponseStatus(HttpStatus.OK)
    fun readAll(): List<Category> {
        log.info("Enter: read all category.")
        return this.categoryService.readAll()
    }

    @PutMapping(
            "/v1/categories/edit"
    )
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody category: Category) {
        log.info("Enter: update category.")
    }

    @DeleteMapping(
            "/v1/categories/edit/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable id: Long) {
        log.info("Enter: remove category.")
    }

}
