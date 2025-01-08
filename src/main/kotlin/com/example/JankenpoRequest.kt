package com.example

data class JankenpoRequest(
    val player1: JankenpoGame.Move,
    val player2: JankenpoGame.Move
)
