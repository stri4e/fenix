package com.github.server.mocks.services.impl

import com.github.server.mocks.payload.Category
import com.github.server.mocks.services.ICategoryService
import com.github.server.mocks.utils.IdsGenerator
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val idsGenerator: IdsGenerator
): ICategoryService {

    override fun create(category: Category): Category {
        category.id = idsGenerator.getId()
        return category
    }

    override fun readByName(name: String): Category {
        return Category(idsGenerator.getId(), "Phone")
    }

    override fun readAll(): List<Category> {
        return listOf(
                Category(idsGenerator.getId(), "Phone"),
                Category(idsGenerator.getId(), "Computer"),
                Category(idsGenerator.getId(), "Soft"),
                Category(idsGenerator.getId(), "Instruments"),
                Category(idsGenerator.getId(), "Sport")
        )
    }

    override fun update(category: Category) {}

    override fun remove(id: Long) {}


}