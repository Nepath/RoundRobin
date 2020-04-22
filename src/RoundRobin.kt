fun main(){
    var quit =false
    var menuOption:String? = ""
    var selected= false

    while(!quit) {

        println("Menu: (Wybierz opcje)")
        println("1. Nowa Gra")
        println("2. Autorzy")
        println("3. Quit")
        while (!selected) {
            menuOption = readLine()
            try {
                menuOption!!.toInt()
                if (menuOption.toInt() in 1..3) {
                    selected = true
                } else {
                    print("Blad sprobuj jeszcze raz (2-30): ")
                }
            } catch (e: NumberFormatException) {
                print("Blad sprobuj jeszcze raz (2-30): ")
            }
        }
        selected=false
        println("/////////////////////////////////////////////////////////////////////////////////////")
        when (menuOption!!.toInt()) {
            1 -> createGame()
            2 -> authors()
            3 -> quit = true
        }
        System.out.flush()
    }
}

fun createGame(){
    print("Wpisz ilosc uczestnikow (maks 30): ")
    var input:String? = ""
    var amountOfPlayers = 0
    var amountofPlayersIsInt=false

    while(!amountofPlayersIsInt) {
        input = readLine()
        try {
            input!!.toInt()
            if (input.toInt() in 2..29) {
                amountofPlayersIsInt=true
            }
            else{
                print("Blad sprobuj jeszcze raz (2-30): ")
            }
        } catch (e: NumberFormatException) {
            print("Blad sprobuj jeszcze raz (2-30): ")
        }
    }
    amountOfPlayers = input!!.toInt()
    println("Udało sie! Liczba uczestników to: $amountOfPlayers.")
    println("Dla szybkości programu uzupełnimy uczestników automatycznie.")
    println("Nazwy użytnowników to 'gracz' + numer ID gracza")

    val listofPlayers = mutableListOf<Player>()
    for(i in 1..amountOfPlayers){
        listofPlayers.add(Player(i, "Gracz $i", 0, 0,0))
    }



       listofPlayers.shuffle()

    for(i in 0 until amountOfPlayers){
        listofPlayers[i].id = i + 1
    }
    if(amountOfPlayers %2 != 0){
        val bot = Player(listofPlayers.size+1, "Bot", 0, 0, 0)
        listofPlayers.add(bot)
        amountOfPlayers++
    }

    val matches = Matches(amountOfPlayers, ArrayList(listofPlayers))
    matches.drawRounds()
}

private fun authors(){
    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    println("----Mateusz Adamek----\n----Mateusz Kowol----")
    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
}
