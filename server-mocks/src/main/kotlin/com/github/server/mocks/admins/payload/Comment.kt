package com.github.server.mocks.admins.payload

class Comment(
        var id: Long,
        val name: String,
        val comment: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false
        if (name != other.name) return false
        if (comment != other.comment) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + comment.hashCode()
        return result
    }

    override fun toString(): String {
        return "Comment(id=$id, name='$name', comment='$comment')"
    }

}