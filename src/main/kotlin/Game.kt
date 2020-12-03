import java.util.*
import kotlin.random.Random

class Game(val height: Int, val width: Int, val view: SnakeView) {

    var keyBuffer = ""

    fun start() {
        val snake = Snake(width, height)
        val goal = Goal(9, 7)

        while (true) {
            render(snake, goal)
            Thread.sleep(1000)
            when(keyBuffer){
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
            for (j in 0..width + 1) {
                out.append(
                    if (j == 0 || j == width + 1) {
                        "| "
                    } else {
                        val jNorm = j - 1
                        if (goal.x == j && goal.y == i) {
                            "X "
                        } else if (snakeArray[i][jNorm] == 1) {
                            "# "
                        } else if (snakeArray[i][jNorm] == 2) {
                            "O "
                        } else {
                            ". "
                        }
                    }
                )
            }
            out.append("\n")
        }
        out.append("-".repeat(2 * width + 3))
        view.output(out.toString())
    }

    fun keyTyped(key: String) {
        keyBuffer = key
    }

    data class Goal(var x: Int, var y: Int) {
        fun rePos(width: Int, height: Int) {
            x = Random.nextInt(0, width - 1)
            y = Random.nextInt(0, height - 1)
        }
    }
}