package com.github.server.mocks.emails.payload

class ConfirmEmail(
        val token: String,
        val email: String,
        val firstName: String,
        val lastName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConfirmEmail

        if (token != other.token) return false
        if (email != other.email) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = token.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }

    override fun toString(): String {
        return "ConfirmEmail(token='$token', email='$email', firstName='$firstName', lastName='$lastName')"
    }
}
