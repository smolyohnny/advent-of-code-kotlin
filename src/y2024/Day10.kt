package y2024

import java.nio.file.Files
import kotlin.io.path.Path

private val input: MutableList<String> = Files.readAllLines(Path("src\\day10.txt"))
private var array = Array(input.size) { Array(input.size) {0} }
private var positions: MutableList<List<Int>> = mutableListOf()
private var paths = 0

fun main() {
    fill2DArray()
    solve()
}

private fun solve() {
    var sum = 0
    var ways = 0

    for((row, line) in array.withIndex()){
        for ((index, element) in line.withIndex()){
            if (element != 0) continue
            findPath(row, index, 0)
            sum += positions.size
            ways += paths
            positions.clear()
            paths = 0
        }
    }
    println(sum)
    println(ways)
}

private fun findPath(row: Int, index: Int, value: Int) {
    if (value == 9){
        if (array[row][index] == 9) {
            paths++
            if (!positions.contains(listOf(row, index)))
                positions.add(listOf(row, index))
            return
        } else return
    }
    if (row != 0 && array[row-1][index] == value+1) findPath(row-1, index, value+1)
    if (row != array.size-1 && array[row+1][index] == value+1) findPath(row+1, index, value+1)
    if (index != 0 && array[row][index-1] == value+1) findPath(row, index-1, value+1)
    if (index != array.size-1 && array[row][index+1] == value+1) findPath(row, index+1, value+1)
}

private fun fill2DArray() {
    for ((row, line) in input.withIndex()) {
        for ((index, ch) in line.toCharArray().withIndex()) {
            array[row][index] = ch.digitToInt()
        }
    }
}