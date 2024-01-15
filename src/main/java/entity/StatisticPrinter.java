package entity;

public class StatisticPrinter {
    private static StatisticPrinter instance;
    private final GameField gameField;

    private StatisticPrinter() {
        gameField = GameField.getInstance();
    }

    public static StatisticPrinter getInstance() {
        if (instance == null) {
            instance = new StatisticPrinter();
        }
        return instance;
    }

    public void printInfo() {
        System.out.println("Statistic Printing");
        for (Cell[] cell : gameField.getCells()) {
            for (Cell cell1 : cell) {
                System.out.println(cell1);
            }
        }
    }
}
