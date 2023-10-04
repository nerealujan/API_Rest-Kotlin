package es.APIRestktor.plugins.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val name: String, val age: Int, val email: String) {
}