package com.github.server.mocks.controllers

import com.github.server.mocks.payload.Product
import com.github.server.mocks.payload.ProductStatus
import com.github.server.mocks.services.IProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("/v1")
class ProductController(private val productService: IProductService) {

    var log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(
            path = ["/edit"]
    )
    fun create(@RequestBody product: Product): Product {
        log.info("Enter: {}", product)
        return this.productService.create(product)
    }

    @GetMapping(
            path = ["/fetch/un-publish"]
    )
    fun readUnpublish(): ResponseEntity<*> {
        log.info("Enter: find all un-publish")
        return ResponseEntity(this.productService.readAll(), HttpStatus.OK)
    }

    @GetMapping(
            path = ["/fetch/{id}"]
    )
    fun readByParams(
            @PathVariable(name = "id", required = false) id: Long,
            @RequestParam(name = "values", required = false) ids: List<Long>?
    ): ResponseEntity<*> {
        log.info("Enter: id = {}, ids = {}", id, ids)
        return when {
            Objects.nonNull(id) -> {
                ResponseEntity(this.productService.readById(id), HttpStatus.OK)
            }
            Objects.nonNull(ids) -> {
                ResponseEntity(this.productService.readByIds(ids), HttpStatus.OK)
            }
            else -> {
                ResponseEntity(this.productService.readByIds(ids), HttpStatus.OK)
            }
        }
    }

    @GetMapping(
            path = ["/fetch"]
    )
    fun readByParams(
            @RequestParam(name = "values", required = false) ids: List<Long>?
    ): ResponseEntity<*> {
        log.info("Enter: ids = {}", ids)
        return ResponseEntity(this.productService.readByIds(ids), HttpStatus.OK)
    }

    @PutMapping(
            path = ["/edit"]
    )
    fun update(@RequestBody product: Product) {
        log.info("Enter: {}", product)
        this.productService.update(product)
    }

    @DeleteMapping(
            path = ["/edit/{id}/{status}"]
    )
    fun updateStatus(
            @PathVariable(name = "id") id: Long,
            @PathVariable(name = "status") status: ProductStatus) {
        log.info("Enter: id = {}, status = {}", id, status)
        this.productService.updateStatus(id, status)
    }

}