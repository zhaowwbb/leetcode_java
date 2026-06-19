import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LeetCode6 {

    public String convertV2(String s, int numRows) {
        if (null == s)
            return null;
        if (numRows == 1 || s.length() == 1)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int currentRow = 0;
        boolean goDown = false;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            // System.out.println("currentRow=" + currentRow + ", c=" + c);
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == (numRows - 1)) {
                goDown = !goDown;
            }
            if (goDown) {
                currentRow += 1;
            } else {
                currentRow -= 1;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(rows[i]);
        }

        return sb.toString();
    }

    public String convertV3(String s, int numRows) {
        if (null == s || numRows == 1)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        int bottomRow = numRows - 1;
        boolean goDown = false;

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == bottomRow) {
                goDown = !goDown;
            }
            currentRow += goDown ? 1 : -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(rows[i]);
        }
        return sb.toString();
    }

    public String convert(String s, int numRows) {
        if (null == s)
            return null;
        if (s.length() == 1)
            return s;

        int cycle = 2 * numRows - 2;
        int numColumn = (s.length() / cycle) * (numRows - 1) + 1;
        // System.out.println("numColumn=" + numColumn);
        // System.out.printf("cycle=%d, numRows=%d, numColumn=%d %n", cycle, numRows,
        // numColumn);
        String[][] array = new String[numRows][numColumn];
        // int columnPos = 0;
        int x = 0, y = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            int t = i % cycle;
            // System.out.printf("t=%d%n", t);

            int startColumn = (i / cycle) * (numRows - 1);

            if (t < numRows) {
                x = t;
                y = startColumn;
            } else {
                x = (numRows - 1) - ((t + 1) % numRows);
                y = startColumn + ((t + 1) % numRows);
            }
            // System.out.printf("t=%d, x=%d,y=%d, c=%c %n",t, x, y, c);
            array[x][y] = String.valueOf(c);

            // y = ((i + 1) / cycle) * (numRows - 1);

            // int x = i %
            // array[j]
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumn; j++) {
                if (array[i][j] != null) {
                    System.out.print("[" + array[i][j] + "]");
                } else {
                    System.out.print("[ ]");
                }

            }
            System.out.println("");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumn; j++) {
                if (array[i][j] != null) {
                    sb.append(array[i][j]);
                }

            }
        }
        return sb.toString();
    }

    public String convertV4(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        boolean isDown = false;
        int n = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            rows[n].append(c);
            if (n == 0 || n == numRows - 1) {
                isDown = !isDown;
            }
            if (isDown) {
                n++;
            } else {
                n--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(rows[i]);
        }
        return sb.toString();
    }

    public String convertV5(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        boolean goingDown = false;
        int row = 0;
        for (Character c : s.toCharArray()) {
            rows[row].append(c);
            if (row == 0 || row == numRows - 1) {
                goingDown = !goingDown;
            }
            row += goingDown ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder r : rows) {
            sb.append(r);
        }
        return sb.toString();
    }

    public String convertV6(String s, int numRows) {
        if (s.length() <= numRows)
            return s;
        if (numRows == 1)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        boolean goDown = false;
        int rowNo = 0;
        for (char c : s.toCharArray()) {
            if (rowNo == 0 || rowNo == numRows - 1) {
                goDown = !goDown;
            }
            rows[rowNo].append(c);
            if (goDown) {

                rowNo++;
            } else {
                rowNo--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder ss : rows) {
            sb.append(ss.toString());
        }
        return sb.toString();
    }

    public String convertV7(String s, int numRows) {
        if (numRows > s.length() || numRows == 1)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int currentRow = 0;
        boolean goDown = false;
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                goDown = !goDown;
            }
            if (goDown) {
                currentRow++;
            } else {
                currentRow--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(rows[i].toString());
        }
        return sb.toString();
    }

    public String convertV8(String s, int numRows) {
        if (numRows == 1 || s.length() < numRows)
            return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        boolean goDown = false;
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (pos == 0) {
                goDown = true;
            }
            if (pos == numRows - 1) {
                goDown = false;
            }
            rows[pos].append(c);
            if (goDown) {
                pos++;
            } else {
                pos--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            sb.append(rows[i]);
        }
        return sb.toString();
    }

    static class ZigzagTestCase {
        String description;
        String input;
        int numRows;
        String expected;

        public ZigzagTestCase(String description, String input, int numRows, String expected) {
            this.description = description;
            this.input = input;
            this.numRows = numRows;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode6 solver = new LeetCode6();
        System.out.println("Running Zigzag Conversion Test Suite...\n------------------------------------------");

        // 1. Define collection of test configurations
        List<ZigzagTestCase> testCases = new ArrayList<>();

        // Example 1: "PAYPALISHIRING", numRows = 3 -> "PAHNAPLSIIGYIR"
        testCases.add(new ZigzagTestCase(
                "Standard multi-row standard configuration",
                "PAYPALISHIRING",
                3,
                "PAHNAPLSIIGYIR"));

        // Example 2: "PAYPALISHIRING", numRows = 4 -> "PINALSIGYAHRPI"
        testCases.add(new ZigzagTestCase(
                "Increased depth row boundaries",
                "PAYPALISHIRING",
                4,
                "PINALSIGYAHRPI"));

        // Edge Case: numRows = 1
        testCases.add(new ZigzagTestCase(
                "Single row pass-through boundary",
                "ABCDE",
                1,
                "ABCDE"));

        // Edge Case: numRows greater than string length
        testCases.add(new ZigzagTestCase(
                "Row count exceeds string sequence length",
                "AB",
                4,
                "AB"));

        // 2. Iterate and verify test structures, invoking core logic exactly once per
        // scenario
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            ZigzagTestCase tc = testCases.get(i);

            // Core execution point
            // String actual = solver.convert(tc.input, tc.numRows);
            String actual = solver.convertV8(tc.input, tc.numRows);

            if (Objects.equals(tc.expected, actual)) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Input:    \"" + tc.input + "\" with rows: " + tc.numRows);
                System.err.println("       Expected: \"" + tc.expected + "\"");
                System.err.println("       Actual:   \"" + actual + "\"");
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
