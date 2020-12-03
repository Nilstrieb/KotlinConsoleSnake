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
        var moveToPos = Tile(x, y)

        x += dirX
        y += dirY

        if (!(x in 0 until width && x in 0 until height)) {
            die()
        }

        if (tiles.size > 0) {
            tiles.forEach {
                tail = it.copy()
                val buffer = moveToPos
                moveToPos = it.copy()

                it.x = buffer.x
                it.y = buffer.y

                if (it.x == this.x && it.y == this.y) {
                    die()
                }
            }
        }

        if (goal.x == x && goal.y == y) {
            println("goal")
            tiles.add(tail.copy())
            goal.rePos(width, height)
        } else {
            println("no goal $x $y  ${goal.x} ${goal.y}")
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

    fun isOccupied(x: Int, y: Int): Boolean {
        if (this.x == x && this.y == y){
            return true;
        }
        tiles.forEach{
            if(it.x == x && it.y == y){
                return true;
            }
        }
        return false;
    }

    data class Tile(var x: Int, var y: Int)
}

