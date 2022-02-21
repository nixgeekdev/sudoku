/* Solve Every Sudoku Puzzle
 * 
 *  Throughout this program we have:
 *    r is a row,    e.g. "A"
 *    c is a column, e.g. "3"
 *    s is a square, e.g. "A3"
 *    d is a digit,  e.g. "9"
 *    u is a unit,   e.g. ["A1","B1","C1","D1","E1","F1","G1","H1","I1"]
 *    grid is a grid,e.g. 81 non-blank chars, e.g. starting with ".18...7...
 *    values is a map of possible values, e.g. {"A1":"12349", "A2":"8", ...}
 * 
 * References used:
 *   http://www.scanraid.com/BasicStrategies.htm
 *   http://www.sudokudragon.com/sudokustrategy.htm
 *   http://www.krazydad.com/blog/2005/09/29/an-index-of-sudoku-strategies/
 *   http://www2.warwick.ac.uk/fac/sci/moac/currentstudents/peter_cock/python/sudoku/   
 */

package org.nixdork.sudoku

import java.io.File

val digits = "123456789"
val rows = "ABCDEFGHI"
val cols = digits
val squares = cross(rows, cols)
val unitlist = cols.map { c -> cross(rows, "$c") } +
    rows.map { r -> cross("$r", cols) } +
    listOf("ABC","DEF","GHI").flatMap { rs -> listOf("123","456","789").map { cs -> cross(rs, cs) } }
val units = squares.map { s -> s to unitlist.filter { u -> u.contains(s) } }.toMap()
val peers = squares.map { s -> s to units[s]!!.flatten().toSortedSet() - listOf(s).toSet() }.toMap()

// Cross product of elements in A and elements in B
fun cross(A: String, B: String) = 
    A.flatMap { a -> B.map { b -> "$a$b" } }

//////////////////////////////// Unit Tests ////////////////////////////////
val grid1 = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"
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
    val values = squares.map { s -> s to digits }.toMap
}

// Convert grid into a map of {square: char} with "0" or "." for empties.
fun gridValues(grid: String) {
    val chars = grid.map { c -> 

    }
}

//////////////////////////////// Constraint Propagation ////////////////////////////////


//////////////////////////////// Display as 2-D grid ////////////////////////////////


//////////////////////////////// Search ////////////////////////////////

//////////////////////////////// Utilities ////////////////////////////////

// Parse a file into a list of strings, separated by `sep`
fun fromFile(fileName: String, sep: String?): List<String> {
    return if (sep == null) {
        File(fileName).readLines()
    } else {
        File(fileName).readText(Charsets.UTF_8).split(sep)
    }.filter { it.lenght == 0 }
}

//////////////////////////////// System test ////////////////////////////////

fun main() {
    test()
    // println(squares)
}
