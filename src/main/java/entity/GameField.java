package entity;

import source.Organism;

import java.util.HashSet;
import java.util.Map;

public class GameField implements Runnable {
    private static volatile GameField instance;
    private final Cell[][] cells;

    private GameField() {
        cells = new Cell[100][20];
    }

    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
            instance.iteration();
        }
        return instance;
    }

    public void iteration() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].cellGeneration(i, j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }


    @Override
    public void run() {
        for (int day = 1; day < 366; day++) {
            System.out.println("Day " + day);
            for (Cell[] cellRow : cells) {
                for (Cell currentCell : cellRow) {
                    for (Map.Entry<Class<? extends Organism>, HashSet<Organism>> setEntry : currentCell.getOrganisms().entrySet()) {
                        HashSet<Organism> copySet = new HashSet<>(setEntry.getValue());
                        for (Organism organism : copySet) {
                            organism.play(day);
                        }
                    }
                }
            }
            StatisticPrinter.getInstance().printInfo();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
