public class CandyCrushEngine {
    private static int[][] grid = {
            { 1, 1, 2, 3, 3 }, // Top-left has 1, 1 (neighbor)
            { 1, 2, 2, 1, 2 }, // Top-left vertical neighbor is 1 -> Match!
            { 3, 3, 3, 2, 1 },
            { 1, 2, 1, 1, 2 },
            { 3, 1, 2, 3, 3 }
    };

    private static int[][] gridForTest = {
            { 1, 1, 2, 3, 3 }, // Top-left has 1, 1 (neighbor)
            { 1, 2, 2, 1, 2 }, // Top-left vertical neighbor is 1 -> Match!
            { 3, 3, 3, 2, 1 },
            { 1, 2, 1, 1, 2 },
            { 3, 1, 2, 3, 3 }
    };

    public static int[][] createTestGrid() {
        int rows = gridForTest.length;
        int cols = gridForTest[0].length;
        int[][] testGrid = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                testGrid[r][c] = gridForTest[r][c];
            }
        }

        return testGrid;
    }

    private static final int SIZE = 5;

    public static boolean processTurn() {
        boolean[][] toRemove = new boolean[SIZE][SIZE];
        boolean foundAny = false;

        // 1. Standard Horizontal & Vertical Checks
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                // Horizontal 3-in-a-row
                if (c <= SIZE - 3 && grid[r][c] != 0 &&
                        grid[r][c] == grid[r][c + 1] && grid[r][c] == grid[r][c + 2]) {
                    toRemove[r][c] = toRemove[r][c + 1] = toRemove[r][c + 2] = true;
                    foundAny = true;
                }
                // Vertical 3-in-a-row
                if (r <= SIZE - 3 && grid[r][c] != 0 &&
                        grid[r][c] == grid[r + 1][c] && grid[r][c] == grid[r + 2][c]) {
                    toRemove[r][c] = toRemove[r + 1][c] = toRemove[r + 2][c] = true;
                    foundAny = true;
                }
            }
        }

        // 2. Special Corner-L Checks (User Rule)
        if (checkCorner(0, 0, 0, 1, 1, 0)) { // Top-Left
            toRemove[0][0] = toRemove[0][1] = toRemove[1][0] = true;
            foundAny = true;
        }
        if (checkCorner(0, SIZE - 1, 0, SIZE - 2, 1, SIZE - 1)) { // Top-Right
            toRemove[0][SIZE - 1] = toRemove[0][SIZE - 2] = toRemove[1][SIZE - 1] = true;
            foundAny = true;
        }
        if (checkCorner(SIZE - 1, 0, SIZE - 2, 0, SIZE - 1, 1)) { // Bottom-Left
            toRemove[SIZE - 1][0] = toRemove[SIZE - 2][0] = toRemove[SIZE - 1][1] = true;
            foundAny = true;
        }
        if (checkCorner(SIZE - 1, SIZE - 1, SIZE - 2, SIZE - 1, SIZE - 1, SIZE - 2)) { // Bottom-Right
            toRemove[SIZE - 1][SIZE - 1] = toRemove[SIZE - 2][SIZE - 1] = toRemove[SIZE - 1][SIZE - 2] = true;
            foundAny = true;
        }

        if (!foundAny)
            return false;

        // 3. Clear matches
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (toRemove[r][c])
                    grid[r][c] = 0;
            }
        }
        System.out.println("Matches cleared (marked as 0):");
        printGrid();

        // 4. Gravity
        applyGravity();
        return true;
    }

    private static boolean checkCorner(int r, int c, int r1, int c1, int r2, int c2) {
        int val = grid[r][c];
        return val != 0 && val == grid[r1][c1] && val == grid[r2][c2];
    }

    public static void applyGravity() {
        for (int c = 0; c < SIZE; c++) {
            for (int r = SIZE - 1; r > 0; r--) {
                if (grid[r][c] == 0) {
                    for (int k = r - 1; k >= 0; k--) {
                        if (grid[k][c] != 0) {
                            grid[r][c] = grid[k][c];
                            grid[k][c] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void printGrid() {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void runCandyCrushEngineV1() {
        System.out.println("#### runCandyCrushEngineV1 ####");
        int step = 1;
        boolean matchesFound;

        System.out.println("--- Initial Grid ---");
        printGrid();

        do {
            System.out.println("\n--- Step " + step + ": Checking for matches ---");
            matchesFound = processTurn();
            if (matchesFound) {
                System.out.println("Grid after Gravity (Falling):");
                printGrid();
                step++;
            }
        } while (matchesFound);

        System.out.println("\n--- Final Stable Grid ---");
        printGrid();

    }
    ////////////////////////////////////////////////////////////////////////////

    public static void printGridV2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println("");
        }
    }

    public static boolean checkCornerV2(int[][] grid, int r1, int c1, int r2, int c2, int r3, int c3) {
        return (grid[r1][c1] != 0 && grid[r1][c1] == grid[r2][c2] && grid[r1][c1] == grid[r3][c3]);
    }

    public static boolean isGridHasMatchedV2(int[][] grid, boolean[][] toRemoved) {
        boolean foundMatched = false;
        int rows = grid.length;
        int cols = grid[0].length;
        // check
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // horizental 3
                if (c <= cols - 3 && grid[r][c] != 0 && grid[r][c] == grid[r][c + 1] && grid[r][c] == grid[r][c + 2]) {
                    foundMatched = true;
                    toRemoved[r][c] = toRemoved[r][c + 1] = toRemoved[r][c + 2] = true;
                }
                // vertical 3
                if (r <= rows - 3 && grid[r][c] != 0 && grid[r][c] == grid[r + 1][c] && grid[r][c] == grid[r + 2][c]) {
                    foundMatched = true;
                    toRemoved[r][c] = toRemoved[r + 1][c] = toRemoved[r + 2][c] = true;
                }

            }
        }

        // Top-left
        if (checkCornerV2(grid, 0, 0, 0, 1, 1, 0)) {
            foundMatched = true;
            toRemoved[0][0] = toRemoved[0][1] = toRemoved[1][0] = true;
        }
        // Top-right
        if (checkCornerV2(grid, 0, cols - 1, 0, cols - 2, 1, cols - 1)) {
            foundMatched = true;
            toRemoved[0][cols - 1] = toRemoved[0][cols - 2] = toRemoved[1][cols - 1] = true;
        }
        // Bottom-left
        if (checkCornerV2(grid, rows - 1, 0, rows - 2, 0, rows - 1, 1)) {
            foundMatched = true;
            toRemoved[rows - 1][0] = toRemoved[rows - 2][0] = toRemoved[rows - 1][1] = true;
        }
        // Bottom-right
        if (checkCornerV2(grid, rows - 1, cols - 1, rows - 1, cols - 2, rows - 2, cols - 1)) {
            foundMatched = true;
            toRemoved[rows - 1][cols - 1] = toRemoved[rows - 1][cols - 2] = toRemoved[rows - 2][cols - 1] = true;
        }

        return foundMatched;
    }

    public static void cleanGridV2(int[][] grid, boolean[][] toRemoved) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (toRemoved[r][c]) {
                    grid[r][c] = 0;
                }
            }
        }
    }

    public static void gridFallingV2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int c = 0; c < cols; c++) {
            for (int r = rows - 1; r > 0; r--) {
                if (grid[r][c] == 0) {
                    for (int k = r - 1; k >= 0; k--) {
                        if (grid[k][c] != 0) {
                            grid[r][c] = grid[k][c];
                            grid[k][c] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void runCandyCrushEngineV2(int[][] grid) {
        System.out.println("#### runCandyCrushEngineV2 ####");
        System.out.println("--- Initial Grid ---");
        printGridV2(grid);
        int step = 1;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] toRemoved = new boolean[rows][cols];

        boolean foundMatched = false;
        do {
            foundMatched = isGridHasMatchedV2(grid, toRemoved);
            if (foundMatched) {
                System.out.println("--- Clean Grid ---");
                cleanGridV2(grid, toRemoved);
                // printGridV2(grid);/* */
            }

            System.out.println("--- Step " + step + " ---");
            step++;
            printGridV2(grid);
            gridFallingV2(grid);
            System.out.println("Grid after gravity (Falling)");
        } while (foundMatched);

        System.out.println("--- Final Grid ---");
        printGridV2(grid);
    }

    public static void printGridV3(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println(" ");
        }
    }

    public static boolean findMatchedPatternV3(int[][] grid, boolean[][] toBeRemoved) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean foundMatched = false;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // horizontal check
                if (c < cols - 2 && grid[r][c] != 0 && grid[r][c] == grid[r][c + 1] && grid[r][c] == grid[r][c + 2]) {
                    foundMatched = true;
                    toBeRemoved[r][c] = toBeRemoved[r][c + 1] = toBeRemoved[r][c + 2] = true;
                }
                // vertical check
                if (r < rows - 2 && grid[r][c] != 0 && grid[r][c] == grid[r + 1][c] && grid[r][c] == grid[r + 2][c]) {
                    foundMatched = true;
                    toBeRemoved[r][c] = toBeRemoved[r + 1][c] = toBeRemoved[r + 2][c] = true;
                }
            }
        }

        // Top-left
        if (findCornerMatchedV3(grid, 0, 0, 0, 1, 1, 0)) {
            foundMatched = true;
            toBeRemoved[0][0] = toBeRemoved[0][1] = toBeRemoved[1][0] = true;
        }
        // Top-right
        if (findCornerMatchedV3(grid, 0, cols - 1, 0, cols - 2, 1, cols - 1)) {
            foundMatched = true;
            toBeRemoved[0][cols - 1] = toBeRemoved[0][cols - 2] = toBeRemoved[1][cols - 1] = true;
        }

        // Bottom-left
        if (findCornerMatchedV3(grid, rows - 1, 0, rows - 1, 1, rows - 2, 0)) {
            foundMatched = true;
            toBeRemoved[rows - 1][0] = toBeRemoved[rows - 1][1] = toBeRemoved[rows - 2][0] = true;
        }

        // Bottom-right
        if (findCornerMatchedV3(grid, rows - 1, cols - 1, rows - 1, cols - 2, rows - 2, cols - 1)) {
            foundMatched = true;
            toBeRemoved[rows - 1][cols - 1] = toBeRemoved[rows - 1][cols - 2] = toBeRemoved[rows - 2][cols - 1] = true;
        }

        return foundMatched;
    }

    public static boolean findCornerMatchedV3(int[][] grid, int r1, int c1, int r2, int c2, int r3, int c3) {
        return grid[r1][c1] != 0 && grid[r1][c1] == grid[r2][c2] && grid[r1][c1] == grid[r3][c3];
    }

    public static void clearGridV3(int[][] grid, boolean[][] toBeRemoved) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (toBeRemoved[r][c]) {
                    grid[r][c] = 0;
                }
            }
        }
    }

    public static void gravityFall(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int c = 0; c < cols; c++) {
            for (int r = rows - 1; r > 0; r--) {
                if (grid[r][c] == 0) {
                    for (int k = r - 1; k >= 0; k--) {
                        if (grid[k][c] != 0) {
                            grid[r][c] = grid[k][c];
                            grid[k][c] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void runCandyCrushEngineV3(int[][] grid) {
        System.out.println("#### runCandyCrushEngineV3 ####");
        System.out.println("--- Initial Grid ---");
        printGridV3(grid);
        int rows = grid.length;
        int cols = grid[0].length;
        int step = 1;
        boolean foundMatched = false;
        boolean[][] toBeRemoved = new boolean[rows][cols];
        do {
            foundMatched = findMatchedPatternV3(grid, toBeRemoved);
            if (foundMatched) {
                clearGridV3(grid, toBeRemoved);
            }
            System.out.println("Step " + step);
            step++;
            printGridV3(grid);
            gravityFall(grid);
        } while (foundMatched);

        System.out.println("--- Final Stable Grid ---");
        printGridV3(grid);
    }

    public static void main(String[] args) {
        runCandyCrushEngineV1();
        runCandyCrushEngineV2(createTestGrid());
        runCandyCrushEngineV3(createTestGrid());
    }
}