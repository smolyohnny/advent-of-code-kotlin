package y2024

import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.math.abs

private val input: List<String> = Files.readAllLines(Path("src\\y2024\\inputs\\day1.txt"))
private val col1 = mutableListOf<Int>()
private val col2 = mutableListOf<Int>()
private var sum1 = 0
private var sum2 = 0

fun main() {
    fillLists()
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    for (i in 0..<col1.size) sum1 += abs(col1[i] - col2[i])
    println(sum1)
}

private fun solvePart2() {
    val size = col1.size
    for (i in 0..<size) {
        var appearences = 0
        for (j in 0..<size) {
            if (col1[i] == col2[j]) appearences++
        }
        sum2 += col1[i] * appearences
    }
    println(sum2)
}

private fun fillLists() {
    for (str in input) {
        val arr = str.split("   ").toTypedArray()
        col1.add((arr[0]).toInt())
        col2.add((arr[1]).toInt())
    }
    col1.sort()
    col2.sort()
}