package br.com.monitordenoticias.config

object DefaultTerms {
    /**
     * Mantém os 8 termos da v4.0.2 e acrescenta os 10 solicitados para a v4.0.3.
     */
    val NEWS: List<String> = listOf(
        "Marinha do Brasil",
        "Capitania dos Portos",
        "Distrito Naval",
        "NAM Atlântico",
        "Cisne Branco",
        "Fragata Marinha do Brasil",
        "Navio-Patrulha Marinha",
        "Programa Nuclear da Marinha",
        "MARINHA",
        "FRAGATA",
        "SUBMARINO",
        "MILITAR",
        "MILITARES",
        "MINISTRO DA DEFESA",
        "MAIOR NAVIO DA AMERICA",
        "FUZILEIROS NAVAIS",
        "FUZILEIRO NAVAL",
        "MINISTÉRIO DA DEFESA",
    ).distinct()
}
