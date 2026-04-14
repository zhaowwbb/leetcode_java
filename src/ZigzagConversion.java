public class ZigzagConversion {

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
        System.out.println("numColumn=" + numColumn);
        System.out.printf("cycle=%d, numRows=%d, numColumn=%d %n", cycle, numRows, numColumn);
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

    public void test(String s, int numRows, String expected) {
        System.out.println("s=" + s + ",numRows" + numRows);
        String result = "";
        result = convert(s, numRows);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = convertV2(s, numRows);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = convertV3(s, numRows);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = convertV4(s, numRows);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = convertV5(s, numRows);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);
    }

    public static void main(String[] args) {
        ZigzagConversion util = new ZigzagConversion();
        String s = "";

        s = "PAYPALISHIRING";
        util.test(s, 3, "PAHNAPLSIIGYIR");
        s = "PAYPALISHIRING";
        util.test(s, 4, "PINALSIGYAHRPI");
        s = "A";
        util.test(s, 1, "A");
    }
}
