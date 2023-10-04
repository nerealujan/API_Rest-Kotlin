package es.APIRestktor.plugins

import es.APIRestktor.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Nerea!")
        }
        userRouting()
    }
}
