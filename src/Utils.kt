import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readLines()

/**
 * Reads lines from the given test input txt file.
 */
fun readTestInput(name: String) = Path("src/test_input/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T> Iterable<T>.chunkWhen(predicate: (T) -> Boolean): List<List<T>> {
    val result = mutableListOf<List<T>>()
    var chunk = mutableListOf<T>()

    for (element in this) {
        if (predicate(element)) {
            if (chunk.isNotEmpty()) {
                result.add(chunk)
                chunk = mutableListOf()
            }
        } else {
            chunk.add(element)
        }
    }

    if (chunk.isNotEmpty()) result.add(chunk)
    return result
}

fun String.toIntList(delimiter: String = " "): List<Int> =
    this.split(delimiter).map { it.toInt() }

fun List<String>.toIntList(): List<Int> =
    map { it.toInt() }

fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

fun lcm(a: Int, b: Int): Int = a / gcd(a, b) * b

fun <T> create2DGrid(rows: Int, columns: Int, initialValue: T): List<MutableList<T>> =
    MutableList(rows) { MutableList(columns) { initialValue } }

fun <T> List<T>.permutations(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val perms = mutableListOf<List<T>>()
    val sub = this[0]

    for (perm in this.drop(1).permutations())
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, sub)
            perms.add(newPerm)
        }
    return perms
}

fun <T> Collection<T>.frequencyMap(): Map<T, Int> = groupingBy { it }.eachCount()

fun <T> List<String>.splitOnEmptyStringAndTransform(transform: (List<String>) -> T): List<T> {
    val result = mutableListOf<List<String>>()
    var currentChunk = mutableListOf<String>()

    for (str in this) {
        if (str.isEmpty()) {
            if (currentChunk.isNotEmpty()) {
                result.add(currentChunk)
                currentChunk = mutableListOf()
            }
        } else {
            currentChunk.add(str)
        }
    }

    if (currentChunk.isNotEmpty()) {
        result.add(currentChunk)
    }

    return result.map(transform)
}
