package y2024

import java.nio.file.Files
import kotlin.io.path.Path

private val input = Files.readString(Path("src\\y2024\\inputs\\day3.txt"))
private val mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    var sum = 0
    mulRegex.findAll(input).forEach {
        val num1 = it.groups[1]?.value?.toInt() ?: 0
        val num2 = it.groups[2]?.value?.toInt() ?: 0
        sum += num1 * num2
    }
    println(sum)
}

private fun solvePart2() {
    val doRegex = "do\\(\\)".toRegex()
    val dontRegex = "don't\\(\\)".toRegex()
    var sum = 0
    var currentPosition = 0

    val mulList = mulRegex.findAll(input, currentPosition).map { it.range.start }.toList()

    val doList = doRegex.findAll(input, currentPosition).map { it.range.start }.toList()

    val dontList = dontRegex.findAll(input, currentPosition).map { it.range.start }.toList()

    var doable = true
    while (currentPosition < input.length) {
        if (doable && mulList.contains(currentPosition)) {
            val matchResult = mulRegex.find(input, currentPosition)

            val num1 = matchResult?.groups?.get(1)?.value?.toInt() ?: 0
            val num2 = matchResult?.groups?.get(2)?.value?.toInt() ?: 0
            sum += num1 * num2
        }
        if (doList.contains(currentPosition)) doable = true
        if (dontList.contains(currentPosition)) doable = false
        currentPosition++
    }
    println(sum)
}
