package entity;

import source.Organism;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LifeCycleStarter {
    GameField gameField = GameField.getInstance();
    ExecutorService service = Executors.newFixedThreadPool(6);

    public LifeCycleStarter() {
    }

    public void start() {
        for (int day = 1; day < 366; day++) {
            System.out.println("Day " + day);
            int currentIteration = day;
            for (Cell[] cellRow : gameField.getCells()) {
                service.submit(() -> {
                    for (Cell currentCell : cellRow) {
                        for (Map.Entry<Class<? extends Organism>, HashSet<Organism>> setEntry : currentCell.getOrganisms().entrySet()) {
                            HashSet<Organism> copySet = new HashSet<>(setEntry.getValue());
                            for (Organism organism : copySet) {
                                organism.play(currentIteration);
                            }
                        }
                    }
                });
            }
            StatisticPrinter.getInstance().printInfo();
        }
    }
}
