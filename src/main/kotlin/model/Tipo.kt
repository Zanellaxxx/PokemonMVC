package model

enum class Tipo(var vantagens: List<Tipo>) {
    FOGO(emptyList()),
    AGUA(emptyList()),
    GRAMA(emptyList()),
    ELETRICO(emptyList()),
    PEDRA(emptyList()),
    TERRA(emptyList()),
    VOADOR(emptyList()),
    VENENO(emptyList()),
    INSETO(emptyList()),
    NORMAL(emptyList()),
    FANTASMA(emptyList()),
    PSIQUICO(emptyList()),
    LUTADOR(emptyList()),
    GELO(emptyList()),
    DRAGAO(emptyList()),
    ACO(emptyList()),
    FADA(emptyList());

    companion object {
        init {
            FOGO.vantagens = listOf(GRAMA)
            AGUA.vantagens = listOf(FOGO)
            GRAMA.vantagens = listOf(AGUA)
            ELETRICO.vantagens = listOf(AGUA)
            PEDRA.vantagens = listOf(FOGO, ELETRICO)
            TERRA.vantagens = listOf(FOGO, ELETRICO)
            VOADOR.vantagens = listOf(GRAMA)
            INSETO.vantagens = listOf(GRAMA)
            FANTASMA.vantagens = listOf(NORMAL) // Exemplo, sem efeito
            PSIQUICO.vantagens = listOf(VENENO)
            LUTADOR.vantagens = listOf(NORMAL, PEDRA)
            GELO.vantagens = listOf(GRAMA, VOADOR)
        }
    }
}
