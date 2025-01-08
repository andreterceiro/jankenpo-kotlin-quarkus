package com.example

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import kotlin.random.Random

@Path("/jankenpo")
class JankenpoResource {

    @GET
    @Path("/play")
    @Produces(MediaType.TEXT_PLAIN)
    fun play(@QueryParam("playerMove") playerMove: String): String {
        val game = JankenpoGame()

        // Convert the player's move from string to enum
        val player1Move = try {
            JankenpoGame.Move.valueOf(playerMove.uppercase())
        } catch (e: IllegalArgumentException) {
            return "Invalid move. Please choose ROCK, PAPER, or SCISSORS."
        }

        // Generate a random move for the computer
        val computerMove = JankenpoGame.Move.values().random()

        // Determine the winner
        val result = game.determineWinner(player1Move, computerMove)
        return "You chose $player1Move. Computer chose $computerMove. Result: $result"
    }
}
