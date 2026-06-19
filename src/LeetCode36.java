import java.util.*;

public class LeetCode36 {

    public static char[][] convertToCharMatrix(String[][] board) {
        if (board == null)
            return null;

        int rows = board.length;
        int cols = board[0].length;
        char[][] charBoard = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Take the first character of the string ("5" -> '5')
                Character c = board[i][j].charAt(0);
                // System.out.println("Character=[" + c + "], number =" + (c - '0'));
                charBoard[i][j] = board[i][j].charAt(0);
            }
        }
        return charBoard;
    }

    public void printBoard(char[][] board) {
        System.out.println("Successfully converted to char[][]:\n");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print("'" + c + "' ");
            }
            System.out.println();
        }
    }

    public void resetVisited(boolean[] visited) {
        Arrays.fill(null, visited);
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
    }

    public boolean isVisited(boolean[] visited, char c) {
        int index = c - '0';
        // System.out.println("c=" + c + ", number=" + (c - '0'));
        if (index >= 1 && index <= 9) {
            // int index = c - '0';
            if (visited[index]) {
                return true;
            } else {
                visited[index] = true;
                return false;
            }
        }
        return false;
    }

    public boolean isValidSudoku(char[][] board) {
        int len = board.length;
        boolean[] visited = new boolean[len + 1];
        // check row
        for (int i = 0; i < len; i++) {
            char[] row = board[i];
            Arrays.fill(visited, false);
            for (int j = 0; j < row.length; j++) {
                if (isVisited(visited, row[j])) {
                    // found duplicate
                    return false;
                }
            }
        }
        // check column
        for (int c = 0; c < len; c++) {
            Arrays.fill(visited, false);
            for (int r = 0; r < board.length; r++) {
                if (isVisited(visited, board[r][c])) {
                    // found duplicate
                    return false;
                }
            }
        }
        // check 3*3 sub box
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(visited, false);
                // 1-9 box
                int x = 3 * i;
                int y = 3 * j;
                if (isVisited(visited, board[x][y]) ||
                        isVisited(visited, board[x][y + 1]) ||
                        isVisited(visited, board[x][y + 2]) ||
                        isVisited(visited, board[x + 1][y]) ||
                        isVisited(visited, board[x + 1][y + 1]) ||
                        isVisited(visited, board[x + 1][y + 2]) ||
                        isVisited(visited, board[x + 2][y]) ||
                        isVisited(visited, board[x + 2][y + 1]) ||
                        isVisited(visited, board[x + 2][y + 2])) {
                    return false;
                }

            }
        }

        return true;
    }

    public boolean isValidSudokuV2(char[][] board) {
        HashSet<String> set = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char c = board[row][col];
                if (c != '.') {
                    int number = c - '0';
                    String rowKey = number + " in row " + row;
                    String colKey = number + " in col " + col;
                    String subboxKey = number + " subbox " + row / 3 + "-" + col / 3;
                    if (!set.add(rowKey) || !set.add(colKey) || !set.add(subboxKey)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Helper method to format the 9x9 matrix for readable terminal debugging output
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

    // Test logic in the main method
    public static void main(String[] args) {
        LeetCode36 solver = new LeetCode36();

        // Test case 1: Valid Board
        char[][] validBoard = {
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

        // Test case 2: Invalid Board (Value '8' repeated in the top-left 3x3 sub-grid)
        char[][] invalidBoard = {
                { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        char[][][] testInputs = { validBoard, invalidBoard };
        boolean[] expectedOutputs = { true, false };

        System.out.println("--- Running Valid Sudoku Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            char[][] currentInput = testInputs[i];
            boolean expected = expectedOutputs[i];

            // The single function call
            boolean actual = solver.isValidSudokuV2(currentInput);

            // Validation check
            if (actual == expected) {
                System.out.println(
                        "Test Case " + (i + 1) + ": PASSED (Expected: " + expected + " -> Got: " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED!\n" + formatBoard(currentInput) +
                        "  Expected: " + expected + " | Got: " + actual + "\n");
            }
        }
    }
}
