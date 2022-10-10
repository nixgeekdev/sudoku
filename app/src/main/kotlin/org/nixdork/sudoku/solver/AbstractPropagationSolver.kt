package org.nixdork.sudoku.solver

import org.nixdork.sudoku.SUDOKU_DIGITS
import org.nixdork.sudoku.SUDOKU_GRID_SIZE
import org.nixdork.sudoku.squares

abstract class AbstractPropagationSolver(val grid: String) : Solver {
    /**
     * Convert grid to a map of possible values, {square: digits}, or
     */
    internal fun parseGrid(): Map<String, String>? {
        val values = squares.map { s -> s to SUDOKU_DIGITS }.toMap().toMutableMap()
        gridValues(grid).forEach { (s, d) ->
            if (d in SUDOKU_DIGITS && assign(values, s, d) != null) {
                return null
            }
        }
        return values
    }

    /**
     * Convert grid string into a map of {square: char} with '0' or '.' for empties
     */
    internal fun gridValues(grid: String): Map<String, String> {
        val chars = grid.filter { c -> c in SUDOKU_DIGITS || c in "0." }.map { it.toString() }.toList()
        assert(chars.size == SUDOKU_GRID_SIZE)
        return squares.zip(chars).toMap()
    }

    override fun solve(): Map<String, String>? {
        return parseGrid()?.let { search(it) }
    }

    /**
     * Depth-first search and propagation, try all possible values
     */
    abstract fun search(values: Map<String, String>): Map<String, String>

    /**
     * Eliminate all the other values (except `d`) from `values[s]` and propagate
     * Return `values`, except return `null` if a contradiction is detected
     */
    abstract fun assign(values: MutableMap<String, String>, s: String, d: String): MutableMap<String, String>?
}
