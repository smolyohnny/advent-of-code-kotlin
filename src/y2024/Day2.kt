package y2024

import java.nio.file.Files
import kotlin.io.path.Path

private val input: MutableList<String> = Files.readAllLines(Path("src\\y2024\\inputs\\day2.txt"))
private var sum1 = 0
private var sum2 = 0

fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    for (line in input) {
        val strArr = line.split(" ").toTypedArray()
        val intList: MutableList<Int> = mutableListOf()
        var isSafe = false

        for (i in strArr) intList.add(i.toInt())

        for (i in 0..intList.size - 2) {
            if (intList[i] > intList[i + 1] && intList[i] - intList[i + 1] <= 3) {
                if (i == intList.size - 2) isSafe = true
            } else break
        }
        for (i in 0..intList.size - 2) {
            if (intList[i] < intList[i + 1] && intList[i + 1] - intList[i] <= 3) {
                if (i == intList.size - 2) isSafe = true
            } else break
        }
        if (isSafe) sum1++
    }
    println(sum1)
}

private fun solvePart2() {
    for (line in input) {
        var isSafe = false
        val strArr = line.split(" ").toTypedArray()
        val intList: MutableList<Int> = mutableListOf()

        for (i in strArr) intList.add(i.toInt())

        for (l in 0..<intList.size) {
            val altList = removeElement(intList, l)
            for (i in 0..altList.size - 2) {
                if (altList[i] > altList[i + 1] && altList[i] - altList[i + 1] <= 3) {
                    if (i == altList.size - 2) isSafe = true
                } else break
            }
            for (i in 0..altList.size - 2) {
                if (altList[i] < altList[i + 1] && altList[i + 1] - altList[i] <= 3) {
                    if (i == altList.size - 2) isSafe = true
                } else break
            }
        }
        if (isSafe) sum2++
    }
    println(sum2)
}

private fun removeElement(list: MutableList<Int>, index: Int): MutableList<Int> {
    val tempList: MutableList<Int> = mutableListOf()
    for (i in list) tempList.add(i)
    tempList.removeAt(index)
    return tempList
}