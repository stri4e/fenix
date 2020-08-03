package com.github.server.mocks.controllers

import com.github.server.mocks.payload.Comment
import com.github.server.mocks.services.impl.CommentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class CommentController(private val commentService: CommentService) {

    var log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(
            "/comments/"
    )
    fun create(@RequestBody comment: Comment): Comment {
        log.info("Enter: {}", comment)
        return this.commentService.create(comment)
    }

    @GetMapping(
            "/comments/fetch/{id}"
    )
    fun readById(@PathVariable id: Long): Comment {
        log.info("Enter: read by id {}", id)
        return this.commentService.readById(id)
    }

    @DeleteMapping(
            "/comments/edit/{id}"
    )
    fun remove(@PathVariable id: Long) {
        log.info("Enter: remove product with id {}", id)
    }

}