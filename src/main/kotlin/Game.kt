import java.util.*
import kotlin.random.Random

class Game(val height: Int, val width: Int) {

    fun start() {
        val snake = Snake(width, height)
        val goal = Goal(9, 7)

        val scn = Scanner(System.`in`)

        while(true) {

            render(snake, goal)

            when(scn.nextLine()){
                "w" -> {snake.move(goal, 0, -1)}
                "a" -> {snake.move(goal, -1, 0)}
                "s" -> {snake.move(goal, 0, 1)}
                "d" -> {snake.move(goal, 1, 0)}
            }
        }
    }

    private fun render(snake: Snake, goal: Goal) {

        val snakeArray = snake.getArray()
        println()
        println("-".repeat(width + 2))
        for (i in 0 until height) {
            var line = ""
            for (j in 0..width + 1) {
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
                        "."
                    }
                }
            }
            println(line)
        }
        println("-".repeat(width + 2))
    }

    data class Goal(var x: Int, var y: Int) {
        fun rePos(width: Int, height: Int) {
            x = Random.nextInt(0, width - 1)
            y = Random.nextInt(0, height - 1)
        }
    }
}