import kotlin.random.Random

class Game(private val height: Int, private val width: Int, private val view: SnakeView) {

    private var keyBuffer = ""
    private var highScore = 0

    fun start() {
        val snake = Snake(width, height, this)
        val goal = Goal(9, 3, snake)

        while (true) {
            render(snake, goal)
            Thread.sleep(200)
            when (keyBuffer) {
                "w" -> snake.move(goal, 0, -1)
                "a" -> snake.move(goal, -1, 0)
                "s" -> snake.move(goal, 0, 1)
                "d" -> snake.move(goal, 1, 0)
                else -> snake.move(goal)
            }
        }
    }

    private fun render(snake: Snake, goal: Goal) {

        val out = StringBuilder()
        val snakeArray = snake.getArray()
        out.append("-".repeat(2 * width + 3)).append("\n")
        for (i in 0 until height) {
            for (j in -1..width) {
                out.append(
                    if (j == -1 || j == width) {
                        "| "
                    } else {
                        if (snakeArray[i][j] == 1) {
                            "# "
                        } else if (snakeArray[i][j] == 2) {
                            "O "
                        } else if (goal.x == j && goal.y == i) {
                            "X "
                        } else {
                            "  "
                        }
                    }
                )
            }
            out.append("\n")
        }
        out.append("-".repeat(2 * width + 3))
        out.append("\nScore: ${snake.getPoints()}")
        out.append("\nHighscore: $highScore")
        view.output(out.toString())
    }

    fun keyTyped(key: String) {
        keyBuffer = key
    }

    fun rip(score: Int) {
        if (highScore < score) {
            highScore = score
        }
    }

    data class Goal(var x: Int, var y: Int, val snake: Snake) {
        fun rePos(width: Int, height: Int) {
            do {
                x = Random.nextInt(0, width - 1)
                y = Random.nextInt(0, height - 1)
            } while (snake.isOccupied(x, y))
        }
    }
}