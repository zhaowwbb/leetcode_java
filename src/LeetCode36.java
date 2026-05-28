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

    public void test(char[][] board, boolean expected) {
        // printBoard(board);
        boolean actual = isValidSudoku(board);
        System.out.printf("[V1] expect=%b, actual=%b%n", expected, actual);
        actual = isValidSudokuV2(board);
        System.out.printf("[V2] expect=%b, actual=%b%n", expected, actual);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode36 lc = new LeetCode36();

        String[][] board1 = {
                { "5", "3", ".", ".", "7", ".", ".", ".", "." },
                { "6", ".", ".", "1", "9", "5", ".", ".", "." },
                { ".", "9", "8", ".", ".", ".", ".", "6", "." },
                { "8", ".", ".", ".", "6", ".", ".", ".", "3" },
                { "4", ".", ".", "8", ".", "3", ".", ".", "1" },
                { "7", ".", ".", ".", "2", ".", ".", ".", "6" },
                { ".", "6", ".", ".", ".", ".", "2", "8", "." },
                { ".", ".", ".", "4", "1", "9", ".", ".", "5" },
                { ".", ".", ".", ".", "8", ".", ".", "7", "9" }
        };

        // Convert it
        char[][] finalBoard = convertToCharMatrix(board1);
        lc.test(finalBoard, true);

        String[][] board2 = {
                { "8", "3", ".", ".", "7", ".", ".", ".", "." },
                { "6", ".", ".", "1", "9", "5", ".", ".", "." },
                { ".", "9", "8", ".", ".", ".", ".", "6", "." },
                { "8", ".", ".", ".", "6", ".", ".", ".", "3" },
                { "4", ".", ".", "8", ".", "3", ".", ".", "1" },
                { "7", ".", ".", ".", "2", ".", ".", ".", "6" },
                { ".", "6", ".", ".", ".", ".", "2", "8", "." },
                { ".", ".", ".", "4", "1", "9", ".", ".", "5" },
                { ".", ".", ".", ".", "8", ".", ".", "7", "9" }
        };

        finalBoard = convertToCharMatrix(board2);
        lc.test(finalBoard, false);

        String[][] board3 = {
                { ".", ".", "4", ".", ".", ".", "6", "3", "." },
                { ".", ".", ".", ".", ".", ".", ".", ".", "." },
                { "5", ".", ".", ".", ".", ".", ".", "9", "." },
                { ".", ".", ".", "5", "6", ".", ".", ".", "." },
                { "4", ".", "3", ".", ".", ".", ".", ".", "1" },
                { ".", ".", ".", "7", ".", ".", ".", ".", "." },
                { ".", ".", ".", "5", ".", ".", ".", ".", "." },
                { ".", ".", ".", ".", ".", ".", ".", ".", "." },
                { ".", ".", ".", ".", ".", ".", ".", ".", "." }
        };

        finalBoard = convertToCharMatrix(board3);
        lc.test(finalBoard, false);
    }
}
