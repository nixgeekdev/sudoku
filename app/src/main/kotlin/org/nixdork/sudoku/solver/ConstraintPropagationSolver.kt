package org.nixdork.sudoku.solver

import org.nixdork.sudoku.peers
import org.nixdork.sudoku.units

/**
 * Solve Every Sudoku Puzzle - See http://norvig.com/sudoku.html
 *
 * A port from python 2.7 to kotlin 1.7
 *
 * Conventions:
 *
 *   Everything is dealt with as a `String` alone or in a `Collection` type.
 *   The textual format used to specify the initial state of a puzzle: we
 *   reserve the name `grid` for this. The internal representation of any
 *   state of a puzzle, partially solved or complete: this we call a `values`
 *   collection because it will give all the remaining possible values for
 *   each square. For the textual format `grid` we'll allow a string with
 *   1-9 indicating a digit, and a 0 or period specifying an empty square.
 *   All other characters are ignored.
 *
 * Throughout this program we have:
 *
 *   `r` is a row,     e.g. "A"
 *   `c` is a column,  e.g. "3"
 *   `s` is a square,  e.g. "A3"
 *   `d` is a digit,   e.g. "9"
 *   `u` is a unit,    e.g. ["A1","B1","C1","D1","E1","F1","G1","H1","I1"]
 *   `grid` is a grid, e.g. 81 non-blank chars, e.g. starting with ".18...7...
 *     a `String`
 *   `values` is a map of possible values, e.g. {"A1":"12349", "A2":"8", ...}
 *     a `Map<String, String>` generated by `parseGrid()`
 */
class Sudoku(grid: String) : AbstractPropagationSolver(grid) {

    override fun search(values: Map<String, String>): Map<String, String> {
        TODO("Not yet implemented")
    }

    // CONSTRAINT PROPAGATION

    /**
     * Eliminate all the other values (except `d`) from `values[s]` and propagate
     * Return `values`, except return `null` if a contradiction is detected
     */
    override fun assign(values: MutableMap<String, String>, s: String, d: String): MutableMap<String, String>? {
        values[s]?.replace(d, "")?.forEach {
            if (eliminate(values, s, "$it") == null) {
                return null
            }
        }

        return values
    }

    @Suppress("ReturnCount")
    private fun eliminate(values: MutableMap<String, String>, s: String, d: String): MutableMap<String, String>? {
        if (!values[s]!!.contains(s)) return values // Already eliminated

        values[s] = values[s]?.replace(d, "").toString()

        // (1) If a square s is reduced to one value d2, then eliminate d2 from the peers
        if (values[s]?.length == 0) {
            return null // Contradiction: removed last value
        } else if (values[s]?.length == 1) {
            val d2 = values[s]?.get(0).toString()
            peers[s]?.forEach { s2 ->
                if (eliminate(values, s2, d2) == null)
                    return null
            }
        }

        // (2) If a unit u is reduced to only one place for a value d, then put it there
        units[s]?.forEach { u ->
            val dplaces = u.filter { s -> values[s]!!.contains(d) }
            if (dplaces.isEmpty()) {
                return null
            } else if (dplaces.size == 1) { // d can only be in one place in unit; assign it there
                if (assign(values, dplaces.first(), d) == null) {
                    return null
                }
            }
        }

        return values
    }




}