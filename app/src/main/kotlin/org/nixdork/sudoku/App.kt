package org.nixdork.sudoku

import java.io.File

val digits = "123456789"
val rows = "ABCDEFGHI"
val cols = digits
val squares = cross(rows, cols)
val unitlist = cols.map { c -> 
    cross(rows, "$c") } +
    rows.map { r -> cross("$r", cols) } +
    listOf("ABC","DEF","GHI").flatMap { rs -> listOf("123","456","789").map { cs -> cross(rs, cs) } 
}
val units = squares.map { s -> s to unitlist.filter { u -> u.contains(s) } }.toMap()
val peers = squares.map { s -> s to units[s]!!.flatten().toSortedSet() - listOf(s).toSet() }.toMap()

// Cross product of elements in A and elements in B
fun cross(A: String, B: String) = 
    A.flatMap { a -> B.map { b -> "$a$b" } }

//////////////////////////////// Unit Tests ////////////////////////////////
val grid1 = "..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3.."
val grid2 = "4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......"
val hard1 = ".....6....59.....82....8....45........3........6..3.54...325..6.................."

fun test() {
    val u = listOf(
        listOf("A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2"),
        listOf("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9"),
        listOf("A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3")
    )
    val p = setOf("A2", "B2", "D2", "E2", "F2", "G2", "H2", "I2", "C1", "C3", 
                  "C4", "C5", "C6", "C7", "C8", "C9", "A1", "A3", "B1", "B3")
    assert(squares.size == 81) { "Bad size for squares" }
    assert(unitlist.size == 27) { "Bad size for unitlist" }
    squares.forEach { 
        assert(units[it]!!.size == 3) { "Bad size for unit $it" }
        assert(peers[it]!!.size == 20) { "Bad size for peer $it" }
    }
    assert(units["C2"] == u) { "Bad value for unit C2 ${units["C2"]}" }
    assert(peers["C2"] == p) { "Bad value for peer C2 ${peers["C2"]}" }
    println("all unit tests pass!")
}

//////////////////////////////// Parse a Grid ////////////////////////////////

// Convert grid to a dict of possible values, {square: digits}, or return False if a contradiction is detected
fun parseGrid(grid: String): Map<String, String> {
    //val values = squares.map { s -> s to digits }.toMap
    return emptyMap()
}

// Convert grid into a map of {square: char} with "." for empties.
fun gridValues(grid: String) =
    grid.filter { c -> c in digits || c == '.' }
        .map { it.toString() }
        .zip(squares) { c, s -> s to c }
        .toMap()

//////////////////////////////// Constraint Propagation ////////////////////////////////


//////////////////////////////// Display as 2-D grid ////////////////////////////////


//////////////////////////////// Search ////////////////////////////////

//////////////////////////////// Utilities ////////////////////////////////

// Parse a file into a list of strings, separated by `sep`
fun fromFile(fileName: String, sep: String? = null): List<String> {
    return if (sep == null) {
        File(fileName).readLines()
    } else {
        File(fileName).readText(Charsets.UTF_8).split(sep)
    }.filter { it.length != 0 }
}

//////////////////////////////// System test ////////////////////////////////

fun main() {
    test()
    // println(squares)
}
