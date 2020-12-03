import Game.Goal

class Snake(private val width: Int, private val height: Int) {
    private var x = width / 2
    private var y = height / 2
    private var dirX = 1
    private var dirY = 0

    private var tiles = ArrayList<Tile>()

    init {
        tiles.add(Tile(x - 1, y))
        tiles.add(Tile(x - 2, y))
        tiles.add(Tile(x - 3, y))
    }

    fun move(goal: Goal) {
        move(goal, dirX, dirY)
    }

    fun move(goal: Goal, dirX: Int, dirY: Int) {

        this.dirX = dirX
        this.dirY = dirY

        var tail = Tile(x, y)
        var oldPos = Tile(x, y)

        x += dirX
        y += dirY

        if (!(x in 0 until width && x in 0 until height)) {
            println("wall rip")
            die()
        }

        if (tiles.size > 0) {
            tiles.forEach {
                //TODO problem: copy position before moving but use old position to move
                tail = it.copy()

                it.x = oldPos.x
                it.y = oldPos.y

                oldPos = it

                if (it.x == this.x && it.y == this.y) {
                    println("self rip")
                    die()
                }
            }
        }

        if (goal.x == this.x && goal.y == this.y) {
            tiles.add(tail.copy())
            goal.rePos(width, height)
        }

    }

    private fun die() {
        tiles = ArrayList()
        tiles.add(Tile(x - 1, y))
        tiles.add(Tile(x - 2, y))
        tiles.add(Tile(x - 3, y))
        x = width / 2
        y = height / 2
        dirX = 1
        dirY = 0
    }

    fun getArray(): Array<IntArray> {
        val array = Array(height) { IntArray(width) }
        tiles.forEach {
            array[it.y][it.x] = 1
        }
        array[y][x] = 2
        return array
    }

    data class Tile(var x: Int, var y: Int)
}

