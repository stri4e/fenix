package com.github.server.mocks.services.impl

import com.github.server.mocks.payload.Product
import com.github.server.mocks.payload.ProductStatus
import com.github.server.mocks.services.IProductService
import com.github.server.mocks.utils.IdsGenerator
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class ProductService(private val idsGenerator: IdsGenerator): IProductService {

    val productMock: Product = Product(
            idsGenerator.getId(),
            "Nokia",
            BigDecimal("12.2"),
            25,
            "This is good product.",
            "img",
            listOf("1", "2", "3"),
            Date(1590226815636)
    );

    val productsMocks: List<Product> = listOf(
            Product(
                    idsGenerator.getId(),
                    "Nokia",
                    BigDecimal("12.2"),
                    25,
                    "This is good product.",
                    "img",
                    listOf("1", "2", "3"),
                    Date(1590226815636)
            ),
            Product(
                    idsGenerator.getId(),
                    "IPhone",
                    BigDecimal("100.2"),
                    100,
                    "This is good product.",
                    "img",
                    listOf("1", "2", "3"),
                    Date(1590226815636)
            ),
            Product(
                    idsGenerator.getId(),
                    "Sumsung",
                    BigDecimal("50.2"),
                    500,
                    "This is good product.",
                    "img",
                    listOf("1", "2", "3"),
                    Date(1590226815636)
            ),
            Product(
                    idsGenerator.getId(),
                    "Xiaomi",
                    BigDecimal("14.2"),
                    100,
                    "This is good product.",
                    "img",
                    listOf("1", "2", "3"),
                    Date(1590226815636)
            ),
            Product(
                    idsGenerator.getId(),
                    "Huawei",
                    BigDecimal("24.2"),
                    560,
                    "This is good product.",
                    "img",
                    listOf("1", "2", "3"),
                    Date(1590226815636)
            )
    )

    override fun create(product: Product): Product {
        return productMock
    }

    override fun readById(id: Long): Product {
        return productMock
    }

    override fun readByIds(ids: List<Long>): List<Product> {
        return productsMocks
    }

    override fun readAll(): List<Product> {
        return productsMocks
    }


    override fun update(product: Product) {
    }

    override fun updateStatus(id: Long, status: ProductStatus) {
    }
}