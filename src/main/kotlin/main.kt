fun main(args: Array<String>) {
    println("Snake Game by Nils written in Kotlin")

    val snakeView = SnakeView()
    val game = Game(15, 15, snakeView)
    snakeView.setGame(game)
    game.start()
}