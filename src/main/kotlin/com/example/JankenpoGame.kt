package com.example

class JankenpoGame {

    enum class Move {
        ROCK, PAPER, SCISSORS
    }

    fun determineWinner(player1: Move, player2: Move): String {
        if (player1 == player2) {
            return "Draw"
        }
        return when (player1) {
            Move.ROCK -> if (player2 == Move.SCISSORS) "Player 1 wins" else "Computer wins"
            Move.PAPER -> if (player2 == Move.ROCK) "Player 1 wins" else "Computer wins"
            Move.SCISSORS -> if (player2 == Move.PAPER) "Player 1 wins" else "Computer wins"
        }
    }
}
