class Matches(val amountofPlayers: Int, val listofPlayers: ArrayList<Player>) {

    var firstGroup = mutableListOf<Player>()
    var secondGroup = mutableListOf<Player>()

    var matchHistory = mutableListOf<Dual>()

    init {
        for (i in 0 until amountofPlayers) {
            if (i < amountofPlayers / 2) {
                firstGroup.add(listofPlayers[i])
            } else {
                secondGroup.add(listofPlayers[i])
            }
        }
        secondGroup.sortByDescending { it.id } /// do usuniecia

//        secondGroup.forEach {
//            println("cipsko ${it.name} z id ${it.id}")
//        }
    }

    fun drawRounds() {
        for (j in 0..listofPlayers.size - 2) {
            if(j==0 || listofPlayers.size-1 %j ==0){
                println("///////////////////////////////////////////////////")
                println("runda 1")
            }


            for (i in 0 until firstGroup.size) {
                matchHistory.add(Dual(firstGroup[i].id, secondGroup[i].id))
            }
            secondGroup.add(firstGroup[firstGroup.size - 1])
            firstGroup.removeAt(firstGroup.size - 1)
            firstGroup.add(1, secondGroup[0])
            secondGroup.removeAt(0)
        }

        matchHistory.forEach {
            val blackPlayer = listofPlayers[it.blackPlayerId - 1]
            val whitePlayer = listofPlayers[it.whitePlayerId - 1]
            var isResult = false
            var result: String? = ""

            if (blackPlayer.id != amountofPlayers && whitePlayer.id != amountofPlayers) {


                println("Białymi gra: ${whitePlayer.name}")
                println("vs.")
                println("Czarnymi gra: ${blackPlayer.name}")
                println("Wpisz kto wygrał: ")
                println("1. ${whitePlayer.name} ")
                println("2. ${blackPlayer.name} ")
                println("3. Remis")

                while (!isResult) {
                    result = readLine()
                    try {
                        result!!.toInt()
                        if (result.toInt() in 1..3) {
                            isResult = true
                        } else {
                            print("Blad sprobuj jeszcze raz: ")
                        }
                    } catch (e: NumberFormatException) {
                        print("Blad sprobuj jeszcze raz: ")
                    }
                }
                when (result!!.toInt()) {
                    1 -> {
                        it.winner = 1
                        whitePlayer.points += 3
                    }

                    2 -> {
                        it.winner = 2
                        blackPlayer.points += 3
                    }
                    3 -> {
                        it.winner = 3
                        whitePlayer.points += 1
                        blackPlayer.points += 1
                    }
                }
            }
        }
        listofPlayers.sortByDescending { it.points }
        var i = 1
        listofPlayers.forEach {
            println("$i. ${it.name} punktow ${it.points}")
            i++
        }
    }

}