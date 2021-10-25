package com.gonzales.mark.n_puzzle

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MoveUtilTest {
    companion object {
        private val testCases: ArrayList<ArrayList<Int>> = generateTestCases()

        private val directions: Array<FlingDirection> = FlingDirection.values()

        private fun generateTestCases(): ArrayList<ArrayList<Int>> {
            val testCases: ArrayList<ArrayList<Int>> = ArrayList(PuzzleConstant.NUM_TILES)

            for (position in 0 until PuzzleConstant.NUM_TILES) {
                val testCase: ArrayList<Int> = ArrayList(PuzzleConstant.NUM_TILES)
                for (tile in 0 until PuzzleConstant.NUM_TILES) {
                    testCase.add(tile)
                }

                val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
                testCase[position] = testCase[blankTilePos].also {
                    testCase[blankTilePos] = testCase[position]
                }

                testCases.add(testCase)
            }

            return testCases
        }

        private fun assertMovable(
            allowedMoves: ArrayList<Pair<Int, FlingDirection>>,
            blankTilePos: Int
        ) {
            for (allowedMove in allowedMoves) {
                assertTrue(
                    MoveUtil.canMoveTile(
                        allowedMove.second,
                        allowedMove.first,
                        blankTilePos,
                        PuzzleConstant.NUM_COLUMNS
                    )
                )
            }
        }

        private fun assertNotMovable(
            allowedMoves: ArrayList<Pair<Int, FlingDirection>>,
            blankTilePos: Int
        ) {
            for (position in 0 until PuzzleConstant.NUM_TILES) {
                for (direction in directions) {
                    if (Pair(position, direction) !in allowedMoves) {
                        assertFalse(
                            MoveUtil.canMoveTile(
                                direction,
                                position,
                                blankTilePos,
                                PuzzleConstant.NUM_COLUMNS
                            )
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `Check if test cases cover all possible positions of the blank tile`() {
        val expectedPositionSet: HashSet<Int> = HashSet()
        val actualPositionSet: HashSet<Int> = HashSet()

        for (position in 0 until PuzzleConstant.NUM_TILES) {
            expectedPositionSet.add(position)
        }

        for (testCase in testCases) {
            actualPositionSet.add(testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER))
        }

        assertTrue(expectedPositionSet == actualPositionSet)
    }

    @Test
    fun `Check movable tiles when blank tile is at the top left position`() {
        val testCase: ArrayList<Int> = testCases[0]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(1, FlingDirection.LEFT), Pair(3, FlingDirection.UP))

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the top center position`() {
        val testCase: ArrayList<Int> = testCases[1]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(0, FlingDirection.RIGHT),
                Pair(2, FlingDirection.LEFT),
                Pair(4, FlingDirection.UP)
            )

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the top right position`() {
        val testCase: ArrayList<Int> = testCases[2]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(1, FlingDirection.RIGHT), Pair(5, FlingDirection.UP))

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the middle left position`() {
        val testCase: ArrayList<Int> = testCases[3]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(0, FlingDirection.DOWN),
                Pair(4, FlingDirection.LEFT),
                Pair(6, FlingDirection.UP)
            )

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the middle center position`() {
        val testCase: ArrayList<Int> = testCases[4]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(1, FlingDirection.DOWN),
                Pair(3, FlingDirection.RIGHT),
                Pair(5, FlingDirection.LEFT),
                Pair(7, FlingDirection.UP)
            )

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the middle right position`() {
        val testCase: ArrayList<Int> = testCases[5]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)

        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(2, FlingDirection.DOWN),
                Pair(4, FlingDirection.RIGHT),
                Pair(8, FlingDirection.UP),
            )

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the bottom left position`() {
        val testCase: ArrayList<Int> = testCases[6]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(3, FlingDirection.DOWN), Pair(7, FlingDirection.LEFT))

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the bottom center position`() {
        val testCase: ArrayList<Int> = testCases[7]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(4, FlingDirection.DOWN),
                Pair(6, FlingDirection.RIGHT),
                Pair(8, FlingDirection.LEFT)
            )

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check movable tiles when blank tile is at the bottom right position`() {
        val testCase: ArrayList<Int> = testCases[8]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(7, FlingDirection.RIGHT), Pair(5, FlingDirection.DOWN))

        assertMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the top left position`() {
        val testCase: ArrayList<Int> = testCases[0]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(1, FlingDirection.LEFT), Pair(3, FlingDirection.UP))

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the top center position`() {
        val testCase: ArrayList<Int> = testCases[1]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(0, FlingDirection.RIGHT),
                Pair(2, FlingDirection.LEFT),
                Pair(4, FlingDirection.UP)
            )

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the top right position`() {
        val testCase: ArrayList<Int> = testCases[2]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(1, FlingDirection.RIGHT), Pair(5, FlingDirection.UP))

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the middle left position`() {
        val testCase: ArrayList<Int> = testCases[3]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(0, FlingDirection.DOWN),
                Pair(4, FlingDirection.LEFT),
                Pair(6, FlingDirection.UP)
            )

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the middle center position`() {
        val testCase: ArrayList<Int> = testCases[4]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(1, FlingDirection.DOWN),
                Pair(3, FlingDirection.RIGHT),
                Pair(5, FlingDirection.LEFT),
                Pair(7, FlingDirection.UP)
            )

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the middle right position`() {
        val testCase: ArrayList<Int> = testCases[5]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)

        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(2, FlingDirection.DOWN),
                Pair(4, FlingDirection.RIGHT),
                Pair(8, FlingDirection.UP),
            )

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the bottom left position`() {
        val testCase: ArrayList<Int> = testCases[6]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(3, FlingDirection.DOWN), Pair(7, FlingDirection.LEFT))

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the bottom center position`() {
        val testCase: ArrayList<Int> = testCases[7]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(
                Pair(4, FlingDirection.DOWN),
                Pair(6, FlingDirection.RIGHT),
                Pair(8, FlingDirection.LEFT)
            )

        assertNotMovable(allowedMoves, blankTilePos)
    }

    @Test
    fun `Check immovable tiles when blank tile is at the bottom right position`() {
        val testCase: ArrayList<Int> = testCases[8]
        val blankTilePos: Int = testCase.indexOf(PuzzleConstant.BLANK_TILE_MARKER)
        val allowedMoves: ArrayList<Pair<Int, FlingDirection>> =
            arrayListOf(Pair(7, FlingDirection.RIGHT), Pair(5, FlingDirection.DOWN))

        assertNotMovable(allowedMoves, blankTilePos)
    }
}