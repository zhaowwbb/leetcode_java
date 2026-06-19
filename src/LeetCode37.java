import java.util.*;

public class LeetCode37 {

    private boolean backtrack(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, row, col, c)) {
                            board[row][col] = c;

                            if (backtrack(board)) {
                                return true;
                            }

                            board[row][col] = '.'; // undo
                        }
                    }
                    return false; // no valid digit here → backtrack
                }
            }
        }
        return true; // all cells filled
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        // check row and column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c)
                return false;
            if (board[i][col] == c)
                return false;
        }

        // check 3x3 sub-box
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int d = boxColStart; d < boxColStart + 3; d++) {
                if (board[r][d] == c)
                    return false;
            }
        }

        return true;
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        // backtrack(board);
        // backtrackV2(board);
        // backtrackV3(board);
        backtrackV4(board);
    }

    public boolean isValid2(char[][] board, int r, int c, int cc) {
        for (int i = 0; i < 9; i++) {
            // row check
            if (board[r][i] == cc)
                return false;

            // column check
            if (board[i][c] == cc)
                return false;
        }

        int boxRow = (r / 3) * 3;
        int boxCol = (c / 3) * 3;
        for (int rowStart = boxRow; rowStart < boxRow + 3; rowStart++) {
            for (int colStart = boxCol; colStart < boxCol + 3; colStart++) {
                // System.out.println("Box row=" + rowStart + ", Box column=" + colStart);
                if (board[rowStart][colStart] == cc) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean backtrackV2(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char number = board[r][c];
                if (number == '.') {
                    for (char cc = '1'; cc <= '9'; cc++) {
                        if (isValid2(board, r, c, cc)) {
                            board[r][c] = cc;
                            if (backtrackV2(board)) {
                                return true;
                            } else {
                                board[r][c] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValidV3(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // row check
            if (board[row][i] == c)
                return false;
            // column check
            if (board[i][col] == c)
                return false;
        }

        int boxR = (row / 3) * 3;
        int boxC = (col / 3) * 3;
        for (int r = boxR; r < boxR + 3; r++) {
            for (int cc = boxC; cc < boxC + 3; cc++) {
                if (board[r][cc] == c)
                    return false;
            }
        }
        return true;
    }

    public boolean backtrackV3(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char number = board[row][col];
                if (number == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValidV3(board, row, col, c)) {
                            board[row][col] = c;
                            if (backtrackV3(board)) {
                                return true;
                            } else {
                                board[row][col] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    public boolean backtrackV4(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char number = '1'; number <= '9'; number++) {
                        if (isValidV4(board, row, col, number)) {
                            board[row][col] = number;
                            if (backtrackV4(board)) {
                                return true;
                            }
                            board[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidV4(char[][] board, int row, int col, char number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number)
                return false;
            if (board[i][col] == number)
                return false;
        }
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == number)
                    return false;
            }
        }
        return true;
    }

    // Helper method to visually format the 9x9 matrix for diagnostic outputs
    private static String formatBoard(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i > 0 && i % 3 == 0)
                sb.append("------+-------+------\n");
            for (int j = 0; j < 9; j++) {
                if (j > 0 && j % 3 == 0)
                    sb.append("| ");
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Helper method to perform a deep copy of a 2D char array to preserve original
    // states
    private static char[][] deepCopy(char[][] original) {
        char[][] copy = new char[9][9];
        for (int i = 0; i < 9; i++) {
            copy[i] = Arrays.copyOf(original[i], 9);
        }
        return copy;
    }

    // Test logic in the main method
    public static void main(String[] args) {
        LeetCode37 solver = new LeetCode37();

        // Sample puzzle input (unsolved configuration)
        char[][] inputBoard = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        // Expected solved board output configuration
        char[][] expectedBoard = {
                { '5', '3', '4', '6', '7', '8', '9', '1', '2' },
                { '6', '7', '2', '1', '9', '5', '3', '4', '8' },
                { '1', '9', '8', '3', '4', '2', '5', '6', '7' },
                { '8', '5', '9', '7', '6', '1', '4', '2', '3' },
                { '4', '2', '6', '8', '5', '3', '7', '9', '1' },
                { '7', '1', '3', '9', '2', '4', '8', '5', '6' },
                { '9', '6', '1', '5', '3', '7', '2', '8', '4' },
                { '2', '8', '7', '4', '1', '9', '6', '3', '5' },
                { '3', '4', '5', '2', '8', '6', '1', '7', '9' }
        };

        char[][][] testInputs = { inputBoard };
        char[][][] expectedOutputs = { expectedBoard };

        System.out.println("--- Running Sudoku Solver Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            char[][] currentInput = testInputs[i];
            char[][] expected = expectedOutputs[i];

            // Create a deep copy to keep an intact copy of the original board puzzle state
            char[][] originalPuzzleCopy = deepCopy(currentInput);

            // The single function call (mutates currentInput in-place)
            solver.solveSudoku(currentInput);

            // Deep validation check across the multi-dimensional structure
            boolean boardsMatch = true;
            for (int r = 0; r < 9; r++) {
                if (!Arrays.equals(currentInput[r], expected[r])) {
                    boardsMatch = false;
                    break;
                }
            }

            if (boardsMatch) {
                System.out.println(
                        "Test Case " + (i + 1) + ": PASSED\n\nSolved Board Result:\n" + formatBoard(currentInput));
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED!\n" +
                        "--- Original Puzzle ---\n" + formatBoard(originalPuzzleCopy) +
                        "--- Expected Solved ---\n" + formatBoard(expected) +
                        "--- Got Actual Result ---\n" + formatBoard(currentInput));
            }
        }
    }
}
