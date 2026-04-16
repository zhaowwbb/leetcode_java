import java.util.Arrays;

public class NeighborLogic {

    public static void main(String[] args) {
        // Initial grid
        int[][] grid = {
            {1, 2, 2},
            {4, 5, 6},
            {4, 8, 9}
        };

        System.out.println("Original Grid:");
        printGrid(grid);

        processGrid(grid);

        System.out.println("\nUpdated Grid (Neighbors with same value set to 0):");
        printGrid(grid);
    }

    public static void processGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Use a boolean matrix to track which cells should be cleared
        // This prevents one change from affecting the logic of the next cell
        boolean[][] shouldClear = new boolean[rows][cols];

        // 4-way direction vectors: Up, Down, Left, Right
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) continue; // Skip already empty cells

                for (int i = 0; i < 4; i++) {
                    int nr = r + dRow[i];
                    int nc = c + dCol[i];

                    // Boundary Check & Value Match Check
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        if (grid[nr][nc] == grid[r][c]) {
                            shouldClear[r][c] = true;
                            shouldClear[nr][nc] = true;
                        }
                    }
                }
            }
        }

        // Final Pass: Apply the changes
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (shouldClear[r][c]) {
                    grid[r][c] = 0;
                }
            }
        }
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}