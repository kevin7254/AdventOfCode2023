fun main() {
    fun part1(input: List<String>): Int {

        // 2022 day one example
        println(input.splitOnEmptyStringAndTransform {
            it.sumOf { it.toInt() }
        })

        return input.chunkWhen {
            it == ""
        }.maxOf {
            it.toIntList().sum()
        }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("Day01_test")
    check(part1(testInput) == 24000) { "Part 1 test fails! is ${part1(testInput)} should be 24000" }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
