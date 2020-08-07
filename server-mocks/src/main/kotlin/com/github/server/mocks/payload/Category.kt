package com.github.server.mocks.payload

class Category {

    var id: Long? = null
    var name: String? = null

    constructor()

    constructor(id: Long?, name: String?) {
        this.id = id
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "CategoryDto(id=$id, name='$name')"
    }

}