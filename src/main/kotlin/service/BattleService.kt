package service

import model.Player
import model.Pokemon

class BattleService {
    fun batalhar(p1: Player, p2: Player) {
        println("Iniciando batalha entre ${p1.nome} e ${p2.nome}!")

        for (i in 0 until 6) {
            val poke1 = p1.time[i]
            val poke2 = p2.time[i]
            val vencedor = calcularVantagem(poke1, poke2)

            println("Rodada ${i + 1}: ${poke1.nome} vs ${poke2.nome} -> ${vencedor ?: "Empate"}")
        }
    }

    private fun calcularVantagem(p1: Pokemon, p2: Pokemon): String? {
        return when {
            p1.tipo.vantagens.contains(p2.tipo) -> p1.nome
            p2.tipo.vantagens.contains(p1.tipo) -> p2.nome
            else -> null
        }
    }
}
