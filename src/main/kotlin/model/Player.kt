package model

data class Player(
    val nome: String,
    val time: MutableList<Pokemon> = mutableListOf()
)