package com.github.server.mocks.admins.services

import com.github.server.mocks.admins.payload.Comment

interface ICommentService {

    fun create(comment: Comment): Comment

    fun readById(id: Long): Comment

    fun remove(id: Long)

}