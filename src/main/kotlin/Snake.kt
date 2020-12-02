class Snake(private val width: Int, private val height: Int) {
    private var x = width / 2
    private var y = height / 2
    private var dirX = 1
    private var dirY = 0

    val tiles = ArrayList<Tile>()

    fun move() {
        x += dirX
        y += dirY

        if (!(x in 0 until width && x in 0 until height)) {
            die()
        }

        var last = Tile(x, y)
        var behind = Tile(x, y)
        tiles.forEach {
            behind = it
            it.x = last.x
            it.y = last.y

            if (it.x == this.x && it.y == this.y) {
                die()
            }

            last = it
        }

        tiles.add(behind.copy())
    }

    fun die() {

    }

    fun getArray(): Array<IntArray> {
        val array = Array(height) { IntArray(width) }
        tiles.forEach {
            array[it.y][it.x] = 1
        }
        array[y][x] = 2
        return array
    }
}

data class Tile(var x: Int, var y: Int)