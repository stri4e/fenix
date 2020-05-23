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
    fun create(product: Product): Product {
        log.info("Enter: {}", product)
        return this.productService.create(product)
    }

    @GetMapping(
            path = ["/info/{id}"]
    )
    fun readByParams(
            @PathVariable(name = "id", required = false) id: Long,
            @RequestParam(name = "values", required = false) ids: List<Long>
    ): ResponseEntity<*> {
        log.info("Enter: id = {}, ids = {}", id, ids)
        if (Objects.nonNull(id)) {
            return ResponseEntity(this.productService.readById(id), HttpStatus.OK)
        } else if (Objects.nonNull(ids)) {
            return ResponseEntity(this.productService.readByIds(ids), HttpStatus.OK)
        } else {
            return ResponseEntity(this.productService.readByIds(ids), HttpStatus.OK)
        }
    }

    @PutMapping(
            path = ["/edit"]
    )
    fun update(@RequestBody product: Product) {
        log.info("Enter: {}", product)
        this.productService.update(product)
    }

    @PutMapping(
            path = ["/edit/{id}/{status}"]
    )
    fun updateStatus(
            @PathVariable(name = "id") id: Long,
            @PathVariable(name = "status") status: ProductStatus) {
        log.info("Enter: id = {}, status = {}", id, status)
        this.productService.updateStatus(id, status)
    }

}