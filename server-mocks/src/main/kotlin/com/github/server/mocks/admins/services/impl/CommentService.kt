package com.github.server.mocks.admins.services.impl

import com.github.server.mocks.admins.payload.Comment
import com.github.server.mocks.admins.services.ICommentService
import com.github.server.mocks.utils.IdsGenerator
import org.springframework.stereotype.Service

@Service
class CommentService(private val idsGenerator: IdsGenerator): ICommentService {

    override fun create(comment: Comment): Comment {
        comment.id = this.idsGenerator.getId()
        return comment
    }

    override fun readById(id: Long): Comment {
        return Comment(
                this.idsGenerator.getId(),
                "Alex",
                "This is supper product"
        )
    }

    override fun remove(id: Long) {}

}
