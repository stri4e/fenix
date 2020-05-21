package com.github.server.mocks.admins.controllers

import com.github.server.mocks.admins.payload.Comment
import com.github.server.mocks.admins.services.impl.CommentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class CommentController(private val commentService: CommentService) {

    var log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(
            "/v1/comments/"
    )
    fun create(@RequestBody comment: Comment): Comment {
        log.info("Enter: {}", comment)
        return this.commentService.create(comment)
    }

    @GetMapping(
            "/v1/comments/info/{id}"
    )
    fun readById(@PathVariable id: Long): Comment {
        log.info("Enter: read by id {}", id)
        return this.commentService.readById(id)
    }

    @DeleteMapping(
            "/v1/comments/edit/{id}"
    )
    fun remove(@PathVariable id: Long) {
        log.info("Enter: remove product with id {}", id)
    }

}