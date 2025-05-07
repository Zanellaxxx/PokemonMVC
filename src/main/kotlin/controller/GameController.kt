package controller

import model.Player
import model.Pokemon
import model.Tipo
import service.BattleService
import java.util.*

class GameController {
    private val scanner = Scanner(System.`in`)

    private val pokemonsDisponiveis: Map<Int, Pokemon> = mapOf(
        0 to Pokemon("Bulbasaur", Tipo.GRAMA),
        1 to Pokemon("Ivysaur", Tipo.GRAMA),
        2 to Pokemon("Venusaur", Tipo.GRAMA),
        3 to Pokemon("Charmander", Tipo.FOGO),
        4 to Pokemon("Charmeleon", Tipo.FOGO),
        5 to Pokemon("Charizard", Tipo.FOGO),
        6 to Pokemon("Squirtle", Tipo.AGUA),
        7 to Pokemon("Wartortle", Tipo.AGUA),
        8 to Pokemon("Blastoise", Tipo.AGUA),
        9 to Pokemon("Caterpie", Tipo.INSETO),
        10 to Pokemon("Metapod", Tipo.INSETO),
        11 to Pokemon("Butterfree", Tipo.INSETO),
        12 to Pokemon("Weedle", Tipo.INSETO),
        13 to Pokemon("Kakuna", Tipo.INSETO),
        14 to Pokemon("Beedrill", Tipo.INSETO),
        15 to Pokemon("Pidgey", Tipo.NORMAL),
        16 to Pokemon("Pidgeotto", Tipo.NORMAL),
        17 to Pokemon("Pidgeot", Tipo.NORMAL),
        18 to Pokemon("Rattata", Tipo.NORMAL),
        19 to Pokemon("Raticate", Tipo.NORMAL),
        20 to Pokemon("Spearow", Tipo.NORMAL),
        21 to Pokemon("Fearow", Tipo.NORMAL),
        22 to Pokemon("Ekans", Tipo.VENENO),
        23 to Pokemon("Arbok", Tipo.VENENO),
        24 to Pokemon("Pikachu", Tipo.ELETRICO),
        25 to Pokemon("Raichu", Tipo.ELETRICO),
        26 to Pokemon("Sandshrew", Tipo.TERRA),
        27 to Pokemon("Sandslash", Tipo.TERRA),
        28 to Pokemon("Nidoran♀", Tipo.VENENO),
        29 to Pokemon("Nidorina", Tipo.VENENO),
        30 to Pokemon("Nidoqueen", Tipo.VENENO),
        31 to Pokemon("Nidoran♂", Tipo.VENENO),
        32 to Pokemon("Nidorino", Tipo.VENENO),
        33 to Pokemon("Nidoking", Tipo.VENENO),
        34 to Pokemon("Clefairy", Tipo.FADA),
        35 to Pokemon("Clefable", Tipo.FADA),
        36 to Pokemon("Vulpix", Tipo.FOGO),
        37 to Pokemon("Ninetales", Tipo.FOGO),
        38 to Pokemon("Jigglypuff", Tipo.NORMAL),
        39 to Pokemon("Wigglytuff", Tipo.NORMAL),
        40 to Pokemon("Zubat", Tipo.VENENO),
        41 to Pokemon("Golbat", Tipo.VENENO),
        42 to Pokemon("Oddish", Tipo.GRAMA),
        43 to Pokemon("Gloom", Tipo.GRAMA),
        44 to Pokemon("Vileplume", Tipo.GRAMA),
        45 to Pokemon("Paras", Tipo.INSETO),
        46 to Pokemon("Parasect", Tipo.INSETO),
        47 to Pokemon("Venonat", Tipo.INSETO),
        48 to Pokemon("Venomoth", Tipo.INSETO),
        49 to Pokemon("Diglett", Tipo.TERRA),
        50 to Pokemon("Dugtrio", Tipo.TERRA),
        51 to Pokemon("Meowth", Tipo.NORMAL),
        52 to Pokemon("Persian", Tipo.NORMAL),
        53 to Pokemon("Psyduck", Tipo.AGUA),
        54 to Pokemon("Golduck", Tipo.AGUA),
        55 to Pokemon("Mankey", Tipo.LUTADOR),
        56 to Pokemon("Primeape", Tipo.LUTADOR),
        57 to Pokemon("Growlithe", Tipo.FOGO),
        58 to Pokemon("Arcanine", Tipo.FOGO),
        59 to Pokemon("Poliwag", Tipo.AGUA),
        60 to Pokemon("Poliwhirl", Tipo.AGUA),
        61 to Pokemon("Poliwrath", Tipo.AGUA),
        62 to Pokemon("Abra", Tipo.PSIQUICO),
        63 to Pokemon("Kadabra", Tipo.PSIQUICO),
        64 to Pokemon("Alakazam", Tipo.PSIQUICO),
        65 to Pokemon("Machop", Tipo.LUTADOR),
        66 to Pokemon("Machoke", Tipo.LUTADOR),
        67 to Pokemon("Machamp", Tipo.LUTADOR),
        68 to Pokemon("Bellsprout", Tipo.GRAMA),
        69 to Pokemon("Weepinbell", Tipo.GRAMA),
        70 to Pokemon("Victreebel", Tipo.GRAMA),
        71 to Pokemon("Tentacool", Tipo.AGUA),
        72 to Pokemon("Tentacruel", Tipo.AGUA),
        73 to Pokemon("Geodude", Tipo.PEDRA),
        74 to Pokemon("Graveler", Tipo.PEDRA),
        75 to Pokemon("Golem", Tipo.PEDRA),
        76 to Pokemon("Ponyta", Tipo.FOGO),
        77 to Pokemon("Rapidash", Tipo.FOGO),
        78 to Pokemon("Slowpoke", Tipo.AGUA),
        79 to Pokemon("Slowbro", Tipo.AGUA),
        80 to Pokemon("Magnemite", Tipo.ELETRICO),
        81 to Pokemon("Magneton", Tipo.ELETRICO),
        82 to Pokemon("Farfetch'd", Tipo.NORMAL),
        83 to Pokemon("Doduo", Tipo.NORMAL),
        84 to Pokemon("Dodrio", Tipo.NORMAL),
        85 to Pokemon("Seel", Tipo.AGUA),
        86 to Pokemon("Dewgong", Tipo.AGUA),
        87 to Pokemon("Grimer", Tipo.VENENO),
        88 to Pokemon("Muk", Tipo.VENENO),
        89 to Pokemon("Shellder", Tipo.AGUA),
        90 to Pokemon("Cloyster", Tipo.AGUA),
        91 to Pokemon("Gastly", Tipo.FANTASMA),
        92 to Pokemon("Haunter", Tipo.FANTASMA),
        93 to Pokemon("Gengar", Tipo.FANTASMA),
        94 to Pokemon("Onix", Tipo.PEDRA),
        95 to Pokemon("Drowzee", Tipo.PSIQUICO),
        96 to Pokemon("Hypno", Tipo.PSIQUICO),
        97 to Pokemon("Krabby", Tipo.AGUA),
        98 to Pokemon("Kingler", Tipo.AGUA),
        99 to Pokemon("Voltorb", Tipo.ELETRICO),
        100 to Pokemon("Electrode", Tipo.ELETRICO),
        101 to Pokemon("Exeggcute", Tipo.GRAMA),
        102 to Pokemon("Exeggutor", Tipo.GRAMA),
        103 to Pokemon("Cubone", Tipo.TERRA),
        104 to Pokemon("Marowak", Tipo.TERRA),
        105 to Pokemon("Hitmonlee", Tipo.LUTADOR),
        106 to Pokemon("Hitmonchan", Tipo.LUTADOR),
        107 to Pokemon("Lickitung", Tipo.NORMAL),
        108 to Pokemon("Koffing", Tipo.VENENO),
        109 to Pokemon("Weezing", Tipo.VENENO),
        110 to Pokemon("Rhyhorn", Tipo.TERRA),
        111 to Pokemon("Rhydon", Tipo.TERRA),
        112 to Pokemon("Chansey", Tipo.NORMAL),
        113 to Pokemon("Tangela", Tipo.GRAMA),
        114 to Pokemon("Kangaskhan", Tipo.NORMAL),
        115 to Pokemon("Horsea", Tipo.AGUA),
        116 to Pokemon("Seadra", Tipo.AGUA),
        117 to Pokemon("Goldeen", Tipo.AGUA),
        118 to Pokemon("Seaking", Tipo.AGUA),
        119 to Pokemon("Staryu", Tipo.AGUA),
        120 to Pokemon("Starmie", Tipo.AGUA),
        121 to Pokemon("Mr. Mime", Tipo.PSIQUICO),
        122 to Pokemon("Scyther", Tipo.INSETO),
        123 to Pokemon("Jynx", Tipo.GELO),
        124 to Pokemon("Electabuzz", Tipo.ELETRICO),
        125 to Pokemon("Magmar", Tipo.FOGO),
        126 to Pokemon("Pinsir", Tipo.INSETO),
        127 to Pokemon("Tauros", Tipo.NORMAL),
        128 to Pokemon("Magikarp", Tipo.AGUA),
        129 to Pokemon("Gyarados", Tipo.AGUA),
        130 to Pokemon("Lapras", Tipo.AGUA),
        131 to Pokemon("Ditto", Tipo.NORMAL),
        132 to Pokemon("Eevee", Tipo.NORMAL),
        133 to Pokemon("Vaporeon", Tipo.AGUA),
        134 to Pokemon("Jolteon", Tipo.ELETRICO),
        135 to Pokemon("Flareon", Tipo.FOGO),
        136 to Pokemon("Porygon", Tipo.NORMAL),
        137 to Pokemon("Omanyte", Tipo.PEDRA),
        138 to Pokemon("Omastar", Tipo.PEDRA),
        139 to Pokemon("Kabuto", Tipo.PEDRA),
        140 to Pokemon("Kabutops", Tipo.PEDRA),
        141 to Pokemon("Aerodactyl", Tipo.PEDRA),
        142 to Pokemon("Snorlax", Tipo.NORMAL),
        143 to Pokemon("Articuno", Tipo.GELO),
        144 to Pokemon("Zapdos", Tipo.ELETRICO),
        145 to Pokemon("Moltres", Tipo.FOGO),
        146 to Pokemon("Dratini", Tipo.DRAGAO),
        147 to Pokemon("Dragonair", Tipo.DRAGAO),
        148 to Pokemon("Dragonite", Tipo.DRAGAO),
        149 to Pokemon("Mewtwo", Tipo.PSIQUICO),
        150 to Pokemon("Mew", Tipo.PSIQUICO)
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
        pokemonsDisponiveis.forEach { (i, p) ->
            println("$i - ${p.nome} (${p.tipo})")
        }

        while (player.time.size < 6) {
            println("Escolha o número do seu Pokémon #${player.time.size + 1}:")
            val index = scanner.nextInt()
            val pokemonEscolhido = pokemonsDisponiveis[index]

            if (pokemonEscolhido != null) {
                player.time.add(pokemonEscolhido)
            } else {
                println("Escolha inválida.")
            }
        }
    }
}