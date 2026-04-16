import java.util.*;

public class CellSimulation {

    static class Cell {
        String name;
        String familyCode;
        int size;

        Cell(String name, String familyCode, int size) {
            this.name = name;
            this.familyCode = familyCode;
            this.size = size;
        }

        @Override
        public String toString() {
            return String.format("Cell: %-10s | Family: %-10s | Size: %d", name, familyCode, size);
        }
    }

    public static List<Cell> simulateEating(String[] names, String[] families, int[] sizes) {
        System.out.println("##########  simulateEatingV1 ##########");
        LinkedList<Cell> list = new LinkedList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(new Cell(names[i], families[i], sizes[i]));
        }

        boolean changesMade = true;
        while (changesMade) {
            changesMade = false;

            for (int i = 0; i < list.size(); i++) {
                Cell current = list.get(i);
                Cell left = (i > 0) ? list.get(i - 1) : null;
                Cell right = (i < list.size() - 1) ? list.get(i + 1) : null;

                Cell predator = null;

                // RULE 1: Right neighbor eats if strictly bigger and different family
                if (right != null && !right.familyCode.equals(current.familyCode) && right.size > current.size) {
                    predator = right;
                }
                // RULE 2: Left neighbor priority if >= size and different family
                else if (left != null && !left.familyCode.equals(current.familyCode) && left.size >= current.size) {
                    predator = left;
                }

                if (predator != null) {
                    predator.size += current.size;
                    list.remove(i);
                    changesMade = true;
                    // Restart to ensure chain reactions (e.g., a cell grows and eats its next
                    // neighbor)
                    break;
                }
            }
        }
        return list;
    }

    public static void printSurvivors(List<Cell> survivors) {
        System.out.println("--- Final Survivors ---");
        if (survivors.isEmpty()) {
            System.out.println("No cells remain.");
        } else {
            survivors.forEach(System.out::println);
        }
    }

    public static List<Cell> simulateEatingV2(String[] names, String[] families, int[] sizes) {
        System.out.println("##########  simulateEatingV2 ##########");
        LinkedList<Cell> list = new LinkedList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(new Cell(names[i], families[i], sizes[i]));
        }
        boolean isContinue = true;
        while (isContinue) {
            isContinue = false;
            for (int i = 0; i < list.size(); i++) {
                // System.out.println("[" + i + "]");
                Cell current = list.get(i);
                Cell left = (i > 0) ? list.get(i - 1) : null;
                Cell right = (i < list.size() - 1) ? list.get(i + 1) : null;
                Cell predator = null;
                if (right != null && !right.familyCode.equals(current.familyCode) && right.size > current.size) {
                    predator = right;
                } else if (left != null && !left.familyCode.equals(current.familyCode) && left.size > current.size) {
                    predator = left;
                }
                if (left != null && right != null && left.size == right.size) {
                    // left take priority
                    if (predator != null) {
                        predator = left;
                    }
                }
                if (predator != null) {
                    predator.size += current.size;
                    System.out.println(
                            "predator [" + predator.name + "](" + predator.size + ")  eat [" + current.name + "]");
                    list.remove(i);
                    isContinue = true;
                    break;
                } else {
                    // current is predator
                    if (left != null && !left.familyCode.equals(current.familyCode) && left.size < current.size) {
                        // eat left
                        current.size += left.size;
                        list.remove(i - 1);
                        isContinue = true;
                        break;
                    } else if (right != null && !right.familyCode.equals(current.familyCode)
                            && right.size < current.size) {
                        // eat right
                        current.size += right.size;
                        list.remove(i + 1);
                        isContinue = true;
                        break;
                    } else {
                        // System.out.println(" No predator [" + i + "]");

                        // System.out.println(" left=" + left);
                        // System.out.println(" current=" + current);
                        // System.out.println(" right=" + right);
                    }

                }
            }
        }
        return list;
    }

    public static List<Cell> simulateEatingV3(String[] names, String[] families, int[] sizes) {
        System.out.println("##########  simulateEatingV3 ##########");
        LinkedList<Cell> list = new LinkedList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(new Cell(names[i], families[i], sizes[i]));
        }

        boolean changed;
        do {
            changed = false;
            // Iterate from Left to Right
            for (int i = 0; i < list.size(); i++) {
                Cell current = list.get(i);

                // Look for neighbors
                Cell left = (i > 0) ? list.get(i - 1) : null;
                Cell right = (i < list.size() - 1) ? list.get(i + 1) : null;

                // Priority 1: Current cell eats its RIGHT neighbor
                if (right != null && !current.familyCode.equals(right.familyCode) && current.size >= right.size) {
                    current.size += right.size;
                    list.remove(i + 1);
                    changed = true;
                    break; // Restart scan from left to maintain priority
                }

                // Priority 2: Current cell eats its LEFT neighbor
                // (Only if the left cell wasn't strong enough to eat 'current' in its own turn)
                if (left != null && !current.familyCode.equals(left.familyCode) && current.size > left.size) {
                    current.size += left.size;
                    list.remove(i - 1);
                    changed = true;
                    break; // Restart scan from left
                }
            }
        } while (changed);

        return list;
    }

    public static List<Cell> simulateEatingV4(String[] names, String[] families, int[] sizes) {
        System.out.println("##########  simulateEatingV4 ##########");
        List<Cell> list = new LinkedList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(new Cell(names[i], families[i], sizes[i]));
        }
        boolean isStop = false;
        boolean hasEat = false;
        while (!isStop) {
            for (int i = 0; i < list.size(); i++) {
                Cell current = list.get(i);
                Cell left = (i > 0) ? list.get(i - 1) : null;
                Cell right = (i < list.size() - 1) ? list.get(i + 1) : null;
                if (left != null && !left.familyCode.equals(current.familyCode)) {
                    if (left.size < current.size) {
                        // current eat left
                        hasEat = true;
                        current.size += left.size;
                        list.remove(i - 1);
                        break;
                    } else {
                        if (right != null && !right.familyCode.equals(current.familyCode)) {
                            if (left.size >= right.size) {
                                // left eat current
                                hasEat = true;
                                left.size += current.size;
                                list.remove(i);
                                break;
                            } else {
                                // right eat current
                                hasEat = true;
                                right.size += current.size;
                                list.remove(i);
                                break;
                            }
                        }
                    }
                }
            }
            if (hasEat) {
                isStop = false;
                hasEat = false;
            } else {
                isStop = true;
            }
        }

        return list;
    }

    public static List<Cell> simulateEatingV5(String[] names, String[] families, int[] sizes) {
        System.out.println("##########  simulateEatingV5 ##########");
        List<Cell> list = new LinkedList<>();
        // create Cell list
        for (int i = 0; i < names.length; i++) {
            list.add(new Cell(names[i], families[i], sizes[i]));
        }

        boolean changed = true;
        do {
            changed = false;
            for (int i = 0; i < list.size(); i++) {
                Cell current = list.get(i);
                Cell left = (i > 0) ? list.get(i - 1) : null;
                Cell right = (i < list.size() - 1) ? list.get(i + 1) : null;
                if (left != null && !left.familyCode.equals(current.familyCode)) {
                    // current eat left
                    if (left.size < current.size) {
                        changed = true;
                        current.size += left.size;
                        list.remove(i - 1);
                        break;
                    } else if (left.size > current.size) {
                        // check right or left to eat current
                        if (right != null && !current.familyCode.equals(right.familyCode) && right.size > left.size) {
                            // right eat current
                            changed = true;
                            right.size += current.size;
                            list.remove(i);
                            break;
                        } else {
                            // left eat current
                            changed = true;
                            left.size += current.size;
                            list.remove(i);
                            break;
                        }
                    }
                }
            }

        } while (changed);
        return list;
    }

    public static void main(String[] args) {
        // Test Data: 8 cells, 5 different families
        // Setup: Beta is between Alpha and Gamma (same family).
        // Zeta is between Epsilon and Eta (different families).
        String[] names = { "Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta" };
        String[] families = { "Red", "Blue", "Red", "Green", "Yellow", "Purple", "Cyan", "Red" };
        int[] sizes = { 50, 10, 50, 30, 15, 5, 20, 100 };

        List<Cell> survivors = simulateEating(names, families, sizes);
        printSurvivors(survivors);

        survivors = simulateEatingV2(names, families, sizes);
        printSurvivors(survivors);

        survivors = simulateEatingV3(names, families, sizes);
        printSurvivors(survivors);

        survivors = simulateEatingV4(names, families, sizes);
        printSurvivors(survivors);

        survivors = simulateEatingV5(names, families, sizes);
        printSurvivors(survivors);
    }

}