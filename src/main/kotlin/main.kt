fun main(args: Array<String>) {
    val snakeView = SnakeView()
    val game = Game(15, 15, snakeView)
    snakeView.setGame(game)
    game.start()
}