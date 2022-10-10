package org.nixdork.sudoku

import org.apache.commons.lang3.StringUtils
import org.nixdork.sudoku.solver.Solver

// FUNCTIONS

/**
 * Cross product of elements in `rows` and elements in `cols`
 */
fun cross(rows: String, cols: String): List<String> =
    rows.flatMap { r -> cols.map { c -> "$r$c" } }

// EXTENSIONS

/**
 * Display values as a 2-D grid
 */
fun Solver.display(values: Map<String, String>) {
    val width = 1 + squares.maxOf { s -> values[s]!!.length }
    val line = GRID_DISPLAY_BORDER_BEAM
        .repeat(width * GRID_DISPLAY_BORDER_REPEAT_FACTOR)
        .plus(GRID_DISPLAY_BORDER_CROSS_BEAM)
        .repeat(GRID_DISPLAY_BORDER_REPEAT_FACTOR)
        .removeSuffix(GRID_DISPLAY_BORDER_CROSS_BEAM)
    SUDOKU_ROWS.forEach { r ->
        SUDOKU_COLS.forEach { c ->
            print(StringUtils.center(values["" + r + c], width))
            print(if (c in "36") "|" else "")
        }
        println()
        if (r in "CF") println(line)
    }
}
