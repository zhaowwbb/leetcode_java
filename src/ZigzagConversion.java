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
            System.out.println("currentRow=" + currentRow + ", c=" + c);
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

    public static void main(String[] args) {
        ZigzagConversion util = new ZigzagConversion();
        String s = "";
        String result = "";
        int numRows = 0;

        s = "PAYPALISHIRING";
        numRows = 3;
        result = util.convert(s, numRows);
        System.out.println("expected: PAHNAPLSIIGYIR");
        System.out.println("actual  : " + result);
        result = util.convertV2(s, numRows);
        System.out.println("expected: PAHNAPLSIIGYIR");
        System.out.println("actual  : " + result);

        s = "PAYPALISHIRING";
        numRows = 4;
        result = util.convert(s, numRows);
        System.out.println("expected: PINALSIGYAHRPI");
        System.out.println("actual  : " + result);
        result = util.convertV2(s, numRows);
        System.out.println("expected: PINALSIGYAHRPI");
        System.out.println("actual  : " + result);

        s = "A";
        numRows = 1;
        result = util.convert(s, numRows);
        // System.out.println("expected: A, actual: " + result);

        System.out.println("expected: A");
        System.out.println("actual  : " + result);
    }
}
