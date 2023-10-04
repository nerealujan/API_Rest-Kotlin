package es.APIRestktor.routes

import es.APIRestktor.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val users = mutableListOf(
    User(1, "Josefa", 34, "josefa@example.com"),
    User(2, "Maria del Mar", 34, "mariadelmar@example.com")
)

fun Route.userRouting(){
    route("/user"){
        get {
            //todo nerea aqui iria la bbdd
            if (users.isNotEmpty()){
                call.respond(users)
            } else {
                call.respondText("No hay usuarios", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Id no encontrado",
                status = HttpStatusCode.BadRequest
            )
            val user = users.find { it.id == id.toInt() } ?: return@get call.respondText(
                "Usuario con $id no encontrado",
                status = HttpStatusCode.NotFound
            )
            call.respond(user)
        }
        post {
            val user = call.receive<User>()
            users.add(user)
            call.respond("Usuario creado correctamente")
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Id no encontrado",
                status = HttpStatusCode.BadRequest
            )
            if (users.removeIf{it.id == id.toInt()}){
                call.respondText("Usuario eliminado correctamente", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("No encontado", status = HttpStatusCode.NotFound)
            }
        }
    }
}