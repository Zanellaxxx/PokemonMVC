package service

import model.Player
import model.Pokemon
import model.Tipo
import kotlin.random.Random

class BattleService {

    private val vidaPadrao = 50
    private val vidaMap = mutableMapOf<Pokemon, Int>()

    private val RESET = "\u001B[0m"
    private val GREEN = "\u001B[32m"
    private val YELLOW = "\u001B[33m"
    private val RED = "\u001B[31m"
    private val CYAN = "\u001B[36m"
    private val MAGENTA = "\u001B[35m"

    private val vantagensPorTipo: Map<Tipo, List<Tipo>> = mapOf(
        Tipo.FOGO to listOf(Tipo.GRAMA),
        Tipo.AGUA to listOf(Tipo.FOGO),
        Tipo.GRAMA to listOf(Tipo.AGUA),
        Tipo.ELETRICO to listOf(Tipo.AGUA),
        Tipo.PEDRA to listOf(Tipo.FOGO, Tipo.ELETRICO),
        Tipo.TERRA to listOf(Tipo.FOGO, Tipo.ELETRICO),
        Tipo.VOADOR to listOf(Tipo.GRAMA),
        Tipo.INSETO to listOf(Tipo.GRAMA),
        Tipo.FANTASMA to listOf(Tipo.NORMAL),
        Tipo.PSIQUICO to listOf(Tipo.VENENO),
        Tipo.LUTADOR to listOf(Tipo.NORMAL, Tipo.PEDRA),
        Tipo.GELO to listOf(Tipo.GRAMA, Tipo.VOADOR),
        Tipo.DRAGAO to listOf(Tipo.GELO),
        Tipo.FADA to listOf(Tipo.DRAGAO)
    )

    fun batalhar(player1: Player, player2: Player) {
        (player1.time + player2.time).forEach {
            vidaMap[it] = vidaPadrao
        }

        println("\n--- Batalha Iniciada! ---")

        var p1Index = 0
        var p2Index = 0
        var turnoDeSorte = Random.nextBoolean()
        var turno = 1

        while (p1Index < player1.time.size && p2Index < player2.time.size) {
            val p1Pokemon = player1.time[p1Index]
            val p2Pokemon = player2.time[p2Index]

            println("\n$CYAN${player1.nome.uppercase()}$RESET envia $CYAN${p1Pokemon.nome.uppercase()}$RESET (${p1Pokemon.tipo})")
            println("$MAGENTA${player2.nome.uppercase()}$RESET envia $MAGENTA${p2Pokemon.nome.uppercase()}$RESET (${p2Pokemon.tipo})")

            val vencedor = batalharPokemons(p1Pokemon, p2Pokemon, turnoDeSorte, turno)

            if (vencedor == p1Pokemon) {
                println("$RED${p2Pokemon.nome} foi derrotado!$RESET")
                p2Index++
            } else {
                println("$RED${p1Pokemon.nome} foi derrotado!$RESET")
                p1Index++
            }
            turnoDeSorte = !turnoDeSorte
            turno++
        }

        println("\n--- Fim da Batalha ---")
        val vencedor = if (p1Index == player1.time.size) player2.nome else player1.nome
        println("\uD83C\uDFC6 O vencedor é: $vencedor!")
    }

    private fun batalharPokemons(p1: Pokemon, p2: Pokemon, turnoDeSorte: Boolean, turno: Int): Pokemon {
        var vidaP1 = vidaMap[p1] ?: vidaPadrao
        var vidaP2 = vidaMap[p2] ?: vidaPadrao

        var turnoAtual = turnoDeSorte

        var rodada = 1

        while (vidaP1 > 0 && vidaP2 > 0) {
            println("\n------ TURNO $turno.$rodada ------")

            if (turnoAtual) {
                val dano = calcularDano(p1, p2)
                vidaP2 -= dano
                println("⚔️  ${p1.nome} ataca ${p2.nome} causando ${RED}$dano$RESET de dano.")
                println("❤️ Vida de ${p2.nome}: ${corVida(vidaP2.coerceAtLeast(0))}")
                println("${barraVida(vidaP2.coerceAtLeast(0))}")
            } else {
                val dano = calcularDano(p2, p1)
                vidaP1 -= dano
                println("⚔️  ${p2.nome} ataca ${p1.nome} causando ${RED}$dano$RESET de dano.")
                println("❤️ Vida de ${p1.nome}: ${corVida(vidaP1.coerceAtLeast(0))}")
                println("${barraVida(vidaP1.coerceAtLeast(0))}")
            }
            turnoAtual = !turnoAtual
            rodada++
        }

        val vencedor = if (vidaP1 > 0) p1 else p2
        vidaMap[p1] = vidaP1.coerceAtLeast(0)
        vidaMap[p2] = vidaP2.coerceAtLeast(0)

        return vencedor
    }

    private fun calcularDano(atacante: Pokemon, defensor: Pokemon): Int {
        val danoBase = if (atacante.tipo.vantagens.contains(defensor.tipo)) {
            Random.nextInt(20, 30)
        } else {
            Random.nextInt(10, 20)
        }

        val chanceCritico = Random.nextInt(1, 101) <= 20

        return if (chanceCritico) {
            danoBase * 2
        } else {
            danoBase
        }
    }

    fun corVida(vida: Int): String {
        return when {
            vida >= 30 -> "$GREEN$vida$RESET"
            vida >= 15 -> "$YELLOW$vida$RESET"
            else -> "$RED$vida$RESET"
        }
    }

    fun barraVida(vida: Int): String {
        val total = 50
        val vidaClamp = vida.coerceAtLeast(0).coerceAtMost(50)
        val barras = "█".repeat(vidaClamp * total / 50)
        val vazios = "░".repeat(total - barras.length)
        return barras + vazios
    }
}
