package org.nixdork.sudoku

const val SUDOKU_DIGITS = "123456789"
const val SUDOKU_ROWS = "ABCDEFGHI"
const val SUDOKU_COLS = SUDOKU_DIGITS
const val SUDOKU_GRID_SIZE = 81

const val GRID_DISPLAY_BORDER_REPEAT_FACTOR = 3
const val GRID_DISPLAY_BORDER_BEAM = "-"
const val GRID_DISPLAY_BORDER_CROSS_BEAM = "+"

/**
 * A list of all 87 squares (coordinates of Row, Col)
 */
val squares = cross(SUDOKU_ROWS, SUDOKU_COLS)

/**
 * A group of nine squares (column, row, or box) is called a unit
 * this is all the units available on a puzzle
 * a List of List of String > List<List<String>>
 */
val unitList = SUDOKU_COLS.map { c -> cross(SUDOKU_ROWS, c.toString()) } +
    SUDOKU_ROWS.map { r -> cross(r.toString(), SUDOKU_COLS) } +
    listOf("ABC", "DEF", "GHI").flatMap { rs ->
        listOf("123", "456", "789").map { cs ->
            cross(rs, cs)
        }
    }

/**
 * Each square belongs to 3 units
 * A Map with a square coord as key and a list of units as value
 * Map of String to List of List of String > Map<String, List<List<String>>>
 */
val units = squares.map { s -> s to unitList.filter { s in it } }.toMap()

/**
 * The squares that share a unit are the peers - each square has exactly 20 peers
 * A map with a square coord as key and a set of peers (string) as values
 * Map of String to Set of String > Map<String, Set<String>>
 */
val peers = squares.map { s -> s to (units[s]!!.flatten().toSet() - listOf(s).toSet()) }.toMap()