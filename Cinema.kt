package cinema

const val MINIMUM_CAPACITY = 60
const val FRONT_SEAT_PRICE = 10
const val BACK_SEAT_PRICE = 8


fun main() {
    // stageOne()
    // stageTwo()
    // stageThree()
    stageFive()
}

fun stageOne() {
    // write your code here
    println("Cinema:")
    println("  1 2 3 4 5 6 7 8")
    for (i in 1..7) {
        println("$i S S S S S S S S")
    }
}

fun stageTwo() {
    println("Enter the number of rows:")
    val noRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val noSeatsInRow = readln().toInt()
    val totalIncome = when {
        noRows * noSeatsInRow <= 60 -> noRows * noSeatsInRow * 10
        else -> (noRows / 2) * noSeatsInRow * 10 + ((noRows / 2) + noRows % 2) * noSeatsInRow * 8
    }
    println("Total income: \n$$totalIncome")
}

fun stageThree() {

    println("Enter the number of rows:")
    val noRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val noSeatsInRow = readln().toInt()
    println()
    val cinema = List(noRows) { MutableList(noSeatsInRow) { 'S' } }
    showSeats(cinema)
    println()
    println("Enter a row number:")
    val seatRow = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNumber = readln().toInt()
    println()
    cinema[seatRow - 1][seatNumber - 1] = 'B'

    val ticketPrice = when {
        noRows * noSeatsInRow <= 60 -> 10
        seatRow <= noRows / 2 -> 10
        else -> 8
    }
    println("Ticket price: $$ticketPrice")
    println()
    showSeats(cinema)
}

fun stageFive() {
    println("Enter the number of rows:")
    val noRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val noSeatsInRow = readln().toInt()
    println()
    val cinemaSeats = List(noRows) { MutableList(noSeatsInRow) { 'S' } }
    while (true) {
        showMenu()
        when (readln().toInt()) {
            1 -> showSeats(cinemaSeats)
            2 -> {
                val (r, c) = buyTicket(cinemaSeats)
                cinemaSeats[r - 1][c - 1] = 'B'
            }

            3 -> showStatistics(cinemaSeats)
            0 -> return
        }
    }


}

fun showStatistics(cinema: List<List<Char>>) {
    val cols = cinema[0].size
    val rows = cinema.size
    var frontRow = 0
    var backRow = 0
    for (c in 0 until cols) {
        for (r in 0 until rows) {
            if (cinema[r][c] == 'B' && r <= (rows / 2 - 1)) {
                frontRow++
            } else if (cinema[r][c] == 'B' && r > (rows / 2 - 1)) {
                backRow++
            }
        }
    }
    var currentIncome = 0
    var totalIncome = 0

    if (rows * cols <= MINIMUM_CAPACITY) {
        currentIncome = (frontRow + backRow) * FRONT_SEAT_PRICE
        totalIncome = (rows * cols) * FRONT_SEAT_PRICE
    } else {
        currentIncome = frontRow * FRONT_SEAT_PRICE + backRow * BACK_SEAT_PRICE
        totalIncome = ((rows / 2) * FRONT_SEAT_PRICE + (rows - rows / 2) * BACK_SEAT_PRICE) * cols
    }
    val percentage = (frontRow + backRow).toDouble() / (rows * cols).toDouble() * 100.00
    val formatPercentage = "%.2f".format(percentage)

    println("Number of purchased tickets: ${frontRow + backRow}")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")

}

fun buyTicket(cinema: List<List<Char>>): Pair<Int, Int> {
    val cols = cinema[0].size
    val rows = cinema.size
    println("Enter a row number:")
    val seatRow = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNumber = readln().toInt()

    if (seatRow > rows || seatNumber > cols) {
        println("Wrong input!")
        return buyTicket(cinema)
    }
    if (cinema[seatRow - 1][seatNumber - 1] == 'B') {
        println("That ticket has already been purchased!")
        return buyTicket(cinema)
    }
    println()

    val ticketPrice = when {
        rows * cols <= 60 -> 10
        seatRow <= rows / 2 -> 10
        else -> 8
    }
    println("Ticket price: $$ticketPrice")
    println()
    return Pair(seatRow, seatNumber)
}

fun showMenu() {
    println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit ")
}

fun showSeats(cinema: List<List<Char>>) {
    val cols = cinema.size
    val rows = cinema[0].size
    println("Cinema:")
    println("  " + (1..rows).joinToString(" "))
    for (c in 0 until cols) {
        print("${c + 1} ")
        for (r in 0 until rows) {
            print("${cinema[c][r]} ")
        }
        println()
    }
    println()
}