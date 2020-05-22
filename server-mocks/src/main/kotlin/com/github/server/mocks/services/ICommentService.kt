package com.github.server.mocks.services

import com.github.server.mocks.payload.Comment

interface ICommentService {

    fun create(comment: Comment): Comment

    fun readById(id: Long): Comment

    fun remove(id: Long)

}