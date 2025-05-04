package controller

import model.Player
import model.Pokemon
import model.Tipo
import service.BattleService
import java.util.*

class GameController {
    private val scanner = Scanner(System.`in`)
    private val pokemonsDisponiveis = listOf(
        Pokemon("Bulbasaur", Tipo.GRAMA),
        Pokemon("Ivysaur", Tipo.GRAMA),
        Pokemon("Venusaur", Tipo.GRAMA),
        Pokemon("Charmander", Tipo.FOGO),
        Pokemon("Charmeleon", Tipo.FOGO),
        Pokemon("Charizard", Tipo.FOGO),
        Pokemon("Squirtle", Tipo.AGUA),
        Pokemon("Wartortle", Tipo.AGUA),
        Pokemon("Blastoise", Tipo.AGUA),
        Pokemon("Caterpie", Tipo.INSETO),
        Pokemon("Metapod", Tipo.INSETO),
        Pokemon("Butterfree", Tipo.INSETO),
        Pokemon("Weedle", Tipo.INSETO),
        Pokemon("Kakuna", Tipo.INSETO),
        Pokemon("Beedrill", Tipo.INSETO),
        Pokemon("Pidgey", Tipo.NORMAL),
        Pokemon("Pidgeotto", Tipo.NORMAL),
        Pokemon("Pidgeot", Tipo.NORMAL),
        Pokemon("Rattata", Tipo.NORMAL),
        Pokemon("Raticate", Tipo.NORMAL),
        Pokemon("Spearow", Tipo.NORMAL),
        Pokemon("Fearow", Tipo.NORMAL),
        Pokemon("Ekans", Tipo.VENENO),
        Pokemon("Arbok", Tipo.VENENO),
        Pokemon("Pikachu", Tipo.ELETRICO),
        Pokemon("Raichu", Tipo.ELETRICO),
        Pokemon("Sandshrew", Tipo.TERRA),
        Pokemon("Sandslash", Tipo.TERRA),
        Pokemon("Nidoran♀", Tipo.VENENO),
        Pokemon("Nidorina", Tipo.VENENO),
        Pokemon("Nidoqueen", Tipo.VENENO),
        Pokemon("Nidoran♂", Tipo.VENENO),
        Pokemon("Nidorino", Tipo.VENENO),
        Pokemon("Nidoking", Tipo.VENENO),
        Pokemon("Clefairy", Tipo.FADA),
        Pokemon("Clefable", Tipo.FADA),
        Pokemon("Vulpix", Tipo.FOGO),
        Pokemon("Ninetales", Tipo.FOGO),
        Pokemon("Jigglypuff", Tipo.NORMAL),
        Pokemon("Wigglytuff", Tipo.NORMAL),
        Pokemon("Zubat", Tipo.VENENO),
        Pokemon("Golbat", Tipo.VENENO),
        Pokemon("Oddish", Tipo.GRAMA),
        Pokemon("Gloom", Tipo.GRAMA),
        Pokemon("Vileplume", Tipo.GRAMA),
        Pokemon("Paras", Tipo.INSETO),
        Pokemon("Parasect", Tipo.INSETO),
        Pokemon("Venonat", Tipo.INSETO),
        Pokemon("Venomoth", Tipo.INSETO),
        Pokemon("Diglett", Tipo.TERRA),
        Pokemon("Dugtrio", Tipo.TERRA),
        Pokemon("Meowth", Tipo.NORMAL),
        Pokemon("Persian", Tipo.NORMAL),
        Pokemon("Psyduck", Tipo.AGUA),
        Pokemon("Golduck", Tipo.AGUA),
        Pokemon("Mankey", Tipo.LUTADOR),
        Pokemon("Primeape", Tipo.LUTADOR),
        Pokemon("Growlithe", Tipo.FOGO),
        Pokemon("Arcanine", Tipo.FOGO),
        Pokemon("Poliwag", Tipo.AGUA),
        Pokemon("Poliwhirl", Tipo.AGUA),
        Pokemon("Poliwrath", Tipo.AGUA),
        Pokemon("Abra", Tipo.PSIQUICO),
        Pokemon("Kadabra", Tipo.PSIQUICO),
        Pokemon("Alakazam", Tipo.PSIQUICO),
        Pokemon("Machop", Tipo.LUTADOR),
        Pokemon("Machoke", Tipo.LUTADOR),
        Pokemon("Machamp", Tipo.LUTADOR),
        Pokemon("Bellsprout", Tipo.GRAMA),
        Pokemon("Weepinbell", Tipo.GRAMA),
        Pokemon("Victreebel", Tipo.GRAMA),
        Pokemon("Tentacool", Tipo.AGUA),
        Pokemon("Tentacruel", Tipo.AGUA),
        Pokemon("Geodude", Tipo.PEDRA),
        Pokemon("Graveler", Tipo.PEDRA),
        Pokemon("Golem", Tipo.PEDRA),
        Pokemon("Ponyta", Tipo.FOGO),
        Pokemon("Rapidash", Tipo.FOGO),
        Pokemon("Slowpoke", Tipo.AGUA),
        Pokemon("Slowbro", Tipo.AGUA),
        Pokemon("Magnemite", Tipo.ELETRICO),
        Pokemon("Magneton", Tipo.ELETRICO),
        Pokemon("Farfetch'd", Tipo.NORMAL),
        Pokemon("Doduo", Tipo.NORMAL),
        Pokemon("Dodrio", Tipo.NORMAL),
        Pokemon("Seel", Tipo.AGUA),
        Pokemon("Dewgong", Tipo.AGUA),
        Pokemon("Grimer", Tipo.VENENO),
        Pokemon("Muk", Tipo.VENENO),
        Pokemon("Shellder", Tipo.AGUA),
        Pokemon("Cloyster", Tipo.AGUA),
        Pokemon("Gastly", Tipo.FANTASMA),
        Pokemon("Haunter", Tipo.FANTASMA),
        Pokemon("Gengar", Tipo.FANTASMA),
        Pokemon("Onix", Tipo.PEDRA),
        Pokemon("Drowzee", Tipo.PSIQUICO),
        Pokemon("Hypno", Tipo.PSIQUICO),
        Pokemon("Krabby", Tipo.AGUA),
        Pokemon("Kingler", Tipo.AGUA),
        Pokemon("Voltorb", Tipo.ELETRICO),
        Pokemon("Electrode", Tipo.ELETRICO),
        Pokemon("Exeggcute", Tipo.GRAMA),
        Pokemon("Exeggutor", Tipo.GRAMA),
        Pokemon("Cubone", Tipo.TERRA),
        Pokemon("Marowak", Tipo.TERRA),
        Pokemon("Hitmonlee", Tipo.LUTADOR),
        Pokemon("Hitmonchan", Tipo.LUTADOR),
        Pokemon("Lickitung", Tipo.NORMAL),
        Pokemon("Koffing", Tipo.VENENO),
        Pokemon("Weezing", Tipo.VENENO),
        Pokemon("Rhyhorn", Tipo.TERRA),
        Pokemon("Rhydon", Tipo.TERRA),
        Pokemon("Chansey", Tipo.NORMAL),
        Pokemon("Tangela", Tipo.GRAMA),
        Pokemon("Kangaskhan", Tipo.NORMAL),
        Pokemon("Horsea", Tipo.AGUA),
        Pokemon("Seadra", Tipo.AGUA),
        Pokemon("Goldeen", Tipo.AGUA),
        Pokemon("Seaking", Tipo.AGUA),
        Pokemon("Staryu", Tipo.AGUA),
        Pokemon("Starmie", Tipo.AGUA),
        Pokemon("Mr. Mime", Tipo.PSIQUICO),
        Pokemon("Scyther", Tipo.INSETO),
        Pokemon("Jynx", Tipo.GELO),
        Pokemon("Electabuzz", Tipo.ELETRICO),
        Pokemon("Magmar", Tipo.FOGO),
        Pokemon("Pinsir", Tipo.INSETO),
        Pokemon("Tauros", Tipo.NORMAL),
        Pokemon("Magikarp", Tipo.AGUA),
        Pokemon("Gyarados", Tipo.AGUA),
        Pokemon("Lapras", Tipo.AGUA),
        Pokemon("Ditto", Tipo.NORMAL),
        Pokemon("Eevee", Tipo.NORMAL),
        Pokemon("Vaporeon", Tipo.AGUA),
        Pokemon("Jolteon", Tipo.ELETRICO),
        Pokemon("Flareon", Tipo.FOGO),
        Pokemon("Porygon", Tipo.NORMAL),
        Pokemon("Omanyte", Tipo.PEDRA),
        Pokemon("Omastar", Tipo.PEDRA),
        Pokemon("Kabuto", Tipo.PEDRA),
        Pokemon("Kabutops", Tipo.PEDRA),
        Pokemon("Aerodactyl", Tipo.PEDRA),
        Pokemon("Snorlax", Tipo.NORMAL),
        Pokemon("Articuno", Tipo.GELO),
        Pokemon("Zapdos", Tipo.ELETRICO),
        Pokemon("Moltres", Tipo.FOGO),
        Pokemon("Dratini", Tipo.DRAGAO),
        Pokemon("Dragonair", Tipo.DRAGAO),
        Pokemon("Dragonite", Tipo.DRAGAO),
        Pokemon("Mewtwo", Tipo.PSIQUICO),
        Pokemon("Mew", Tipo.PSIQUICO)
    )




    fun iniciarJogo() {
        println("Digite o nome do Player 1:")
        val p1 = Player(scanner.nextLine())
        println("Digite o nome do Player 2:")
        val p2 = Player(scanner.nextLine())

        println("${p1.nome}, escolha seus 6 Pokémons:")
        escolherPokemons(p1)
        println("${p2.nome}, escolha seus 6 Pokémons:")
        escolherPokemons(p2)

        val battleService = BattleService()
        battleService.batalhar(p1, p2)
    }

    private fun escolherPokemons(player: Player) {
        pokemonsDisponiveis.forEachIndexed { i, p -> println("$i - ${p.nome} (${p.tipo})") }

        while (player.time.size < 6) {
            println("Escolha o número do seu Pokémon #${player.time.size + 1}:")
            val index = scanner.nextInt()
            if (index in pokemonsDisponiveis.indices) {
                player.time.add(pokemonsDisponiveis[index])
            } else {
                println("Escolha inválida.")
            }
        }
    }
}
