class Game(val height: Int, val width: Int) {

    fun start() {
        val snake = Snake(width, height)
        val goal = Goal(9, 7)

        render(snake, goal)
    }

    fun render(snake: Snake, goal: Goal) {
        val snakeArray = snake.getArray()

        println("-".repeat(width + 2))
        for (i in 0..height) {
            var line = ""
            for (j in 0..width + 2) {
                line += if (j == 0 || j == width + 1) {
                    "|"
                } else {
                    val jNorm = j - 1
                    if (goal.x == j && goal.y == i) {
                        "X"
                    } else if (snakeArray[i][jNorm] == 1) {
                        "#"
                    } else if (snakeArray[i][jNorm] == 2) {
                        "O"
                    } else {
                        " "
                    }
                }
            }
            println(line)
        }
        println("-".repeat(width + 2))
    }

    data class Goal(var x: Int, var y: Int) {

    }
}