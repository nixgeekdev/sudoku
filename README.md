# A Study in Sudoku

This is a port to kotlin of the original Norvig solver written in python. 
 
Throughout this program we have:

*    `r` is a row,    _e.g._ `"A"`
*    `c` is a column, _e.g._ `"3"`
*    `s` is a square, _e.g._ `"A3"`
*    `d` is a digit,  _e.g._ `"9"`
*    `u` is a unit,   _e.g._ `["A1","B1","C1","D1","E1","F1","G1","H1","I1"]`
*    `grid` is a grid, _e.g._ 81 non-blank chars, _e.g._ starting with `".18...7...`
*    `values` is a map of possible values, _e.g._ `{"A1":"12349", "A2":"8", ...}`

References used:

*   http://www.scanraid.com/BasicStrategies.htm
*   http://www.sudokudragon.com/sudokustrategy.htm
*   http://www.krazydad.com/blog/2005/09/29/an-index-of-sudoku-strategies/
*   http://www2.warwick.ac.uk/fac/sci/moac/currentstudents/peter_cock/python/sudoku/   
*   https://norvig.com/sudoku.html
