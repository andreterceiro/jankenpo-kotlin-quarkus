package com.example

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import kotlin.random.Random
import jakarta.inject.Inject
import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import com.example.model.Jankenpo

@Path("/")
class JankenpoResource {

    @Inject
    lateinit var jankenpo: Jankenpo

    @Inject
    lateinit var form: Template

    @Inject
    lateinit var result: Template

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun play(@QueryParam("playerMove") playerMove: String?): TemplateInstance {
        var error = ""

        // Validate the playerMove parameter
        if (playerMove.isNullOrBlank()) {
            error = "Please provide your move as ROCK, PAPER, or SCISSORS."
            return form.data("error", error)
        }

        // Generate a random move for the computer
        val computerMove = Jankenpo.Move.values().random()

        // Convert the player's move from string to enum
        val player1Move: Jankenpo.Move = try {
            Jankenpo.Move.valueOf(playerMove.uppercase())
        } catch (e: IllegalArgumentException) {
            error = "Invalid move. Please choose ROCK, PAPER, or SCISSORS."
            return form.data("error", error)
        }

        // Determine the winner
        val resultData = jankenpo.determineWinner(player1Move, computerMove)

        // Return the HTML template with the result
        return result
            .data("result", resultData)
            .data("computerMove", computerMove)
            .data("playerMove", player1Move)
            .data("error", error)
    }
}
