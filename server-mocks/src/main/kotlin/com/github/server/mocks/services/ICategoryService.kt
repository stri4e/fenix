package com.github.server.mocks.services

import com.github.server.mocks.payload.Category

interface ICategoryService {

    fun create(category: Category): Category

    fun readByName(name: String): Category

    fun readAll(): List<Category>

    fun update(category: Category)

    fun remove(id: Long)

}