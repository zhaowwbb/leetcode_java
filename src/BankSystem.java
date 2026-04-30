import java.util.Arrays;

public class BankSystem {

    public static void main(String[] args) {
        int[] balances = { 100, 200, 500, 50 }; // Initial balances for accounts 1, 2, 3, and 4
        String[] commands = {
                "withdraw 1 20",
                "deposit 2 50",
                "transfer 3 4 100",
                "withdraw 4 10"
        };

        System.out.println("Initial Balances: " + Arrays.toString(balances));

        processTransactions(balances, commands);
        System.out.println("[V1] Final Balances:   " + Arrays.toString(balances));

        balances = new int[] { 100, 200, 500, 50 };
        processTransactionsV2(balances, commands);
        System.out.println("[V2] Final Balances:   " + Arrays.toString(balances));

        balances = new int[] { 100, 200, 500, 50 };
        processTransactionsV3(balances, commands);
        System.out.println("[V3] Final Balances:   " + Arrays.toString(balances));

        balances = new int[] { 100, 200, 500, 50 };
        processTransactionsV4(balances, commands);
        System.out.println("[V4] Final Balances:   " + Arrays.toString(balances));
    }

    public static void processTransactions(int[] balances, String[] commands) {
        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String action = parts[0].toLowerCase();

            try {
                if (action.equals("withdraw")) {
                    int id = Integer.parseInt(parts[1]) - 1; // Adjust for 0-indexing
                    int amount = Integer.parseInt(parts[2]);

                    if (isValidAccount(id, balances) && balances[id] >= amount) {
                        balances[id] -= amount;
                    }

                } else if (action.equals("deposit")) {
                    int id = Integer.parseInt(parts[1]) - 1;
                    int amount = Integer.parseInt(parts[2]);

                    if (isValidAccount(id, balances)) {
                        balances[id] += amount;
                    }

                } else if (action.equals("transfer")) {
                    int sourceId = Integer.parseInt(parts[1]) - 1;
                    int targetId = Integer.parseInt(parts[2]) - 1;
                    int amount = Integer.parseInt(parts[3]);

                    if (isValidAccount(sourceId, balances) &&
                            isValidAccount(targetId, balances) &&
                            balances[sourceId] >= amount) {

                        balances[sourceId] -= amount;
                        balances[targetId] += amount;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing command: " + cmd);
            }
        }
    }

    // Helper method to ensure the ID exists in our array
    private static boolean isValidAccount(int id, int[] balances) {
        return id >= 0 && id < balances.length;
    }

    public static void processTransactionsV2(int[] balances, String[] commands) {
        int n = balances.length;
        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i];
            String[] strs = cmd.split(" ");
            String action = strs[0];
            int account1 = 0;
            int account2 = 0;
            int sum = 0;
            if (action.startsWith("withdraw")) {
                account1 = Integer.valueOf(strs[1]);
                if (account1 >= 1 && account1 <= n) {
                    sum = Integer.valueOf(strs[2]);
                    balances[account1 - 1] = balances[account1 - 1] - sum;
                }
            } else if (action.startsWith("deposit")) {
                account1 = Integer.valueOf(strs[1]);
                if (account1 >= 1 && account1 <= n) {
                    sum = Integer.valueOf(strs[2]);
                    balances[account1 - 1] = balances[account1 - 1] + sum;
                }
            } else if (action.startsWith("transfer")) {
                account1 = Integer.valueOf(strs[1]);
                account2 = Integer.valueOf(strs[2]);
                if (account1 >= 1 && account1 <= n && account2 >= 1 && account2 <= n) {
                    sum = Integer.valueOf(strs[3]);
                    balances[account1 - 1] = balances[account1 - 1] - sum;
                    balances[account2 - 1] = balances[account2 - 1] + sum;
                }
            }
        }

    }

    public static void processTransactionsV3(int[] balances, String[] commands) {
        // isValidAccount
        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String action = parts[0].toLowerCase();
            if (action.startsWith("withdraw")) {
                int id = Integer.valueOf(parts[1]) - 1;
                int amount = Integer.valueOf(parts[2]);
                if (isValidAccount(id, balances) && balances[id] >= amount) {
                    balances[id] -= amount;
                }
            } else if (action.startsWith("deposit")) {
                int id = Integer.valueOf(parts[1]) - 1;
                int amount = Integer.valueOf(parts[2]);
                if (isValidAccount(id, balances)) {
                    balances[id] += amount;
                }
            } else if (action.startsWith("transfer")) {
                int srcId = Integer.valueOf(parts[1]) - 1;
                int dstId = Integer.valueOf(parts[2]) - 1;
                int amount = Integer.valueOf(parts[3]);
                if (isValidAccount(srcId, balances) && isValidAccount(dstId, balances) && balances[srcId] >= amount) {
                    balances[srcId] -= amount;
                    balances[dstId] += amount;
                }
            }
        }
    }

    public static void processTransactionsV4(int[] balances, String[] commands) {
        for (String cmd : commands) {
            String[] strs = cmd.split(" ");
            String action = strs[0].toLowerCase();

            if (action.equals("deposit")) {
                int accountSrc = Integer.parseInt(strs[1]) - 1;
                int amount = Integer.parseInt(strs[2]);
                if (isValidAccount(accountSrc, balances) && amount > 0) {
                    balances[accountSrc] += amount;
                }

            } else if (action.equals("withdraw")) {
                int accountSrc = Integer.parseInt(strs[1]) - 1;
                int amount = Integer.parseInt(strs[2]);
                if (isValidAccount(accountSrc, balances) && balances[accountSrc] >= amount) {
                    balances[accountSrc] -= amount;
                }
            } else if (action.equals("transfer")) {
                int accountSrc = Integer.parseInt(strs[1]) - 1;
                int accountDst = Integer.parseInt(strs[2]) - 1;

                int amount = Integer.parseInt(strs[3]);
                if (isValidAccount(accountSrc, balances) && balances[accountSrc] >= amount
                        && isValidAccount(accountDst, balances)) {
                    balances[accountSrc] -= amount;
                    balances[accountDst] += amount;
                }
            }
        }
    }
}