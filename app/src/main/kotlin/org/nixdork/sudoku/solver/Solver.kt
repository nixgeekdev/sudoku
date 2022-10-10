package org.nixdork.sudoku.solver

interface Solver {
    fun solve(): Map<String, String>?
}
