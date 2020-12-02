import Game.Goal

class Snake(private val width: Int, private val height: Int) {
    private var x = width / 2
    private var y = height / 2
    private var dirX = 1
    private var dirY = 0

    var tiles = ArrayList<Tile>()

    public fun move(goal: Goal, dirX: Int, dirY: Int) {

        this.dirX = dirX
        this.dirY= dirY

        var tail = Tile(x, y)
        var front = Tile(x, y)

        x += dirX
        y += dirY

        if (!(x in 0 until width && x in 0 until height)) {
            println("wall rip")
            die()
        }

        if (tiles.size > 0) {
            tiles.forEach {
                tail = it
                it.x = front.x
                it.y = front.y

                if (it.x == this.x && it.y == this.y) {
                    println("self rip")
                    die()
                }

                front = it
            }
        }

        if(goal.x == this.x && goal.y == this.y){
            println("append")
            tiles.add(tail.copy())
            goal.rePos(width, height)
        }

    }

    private fun die() {
        println("rip")
        tiles = ArrayList()
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

