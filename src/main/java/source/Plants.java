package source;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Plants extends Organism {
    protected final double WEIGHT_PER_ELEMENT = getWeightPerElement();
    private final int MAX_VALUE_OF_ORGANISMS_ON_CELL = getMAX_VALUE_OF_ORGANISMS_ON_CELL();
    protected AtomicReference<Double> healthPoint = new AtomicReference<>(WEIGHT_PER_ELEMENT);
    private int descendantCounter;

    public Plants(int height, int width) {
        super(height, width);
    }


    @Override
    synchronized public void reproduce() {
        if (descendantCounter < 7 && currCell.getOrganisms().get(this.getClass()).size() < MAX_VALUE_OF_ORGANISMS_ON_CELL && ThreadLocalRandom.current().nextInt(0, 10) < 9) {
            descendantCounter++;
            try {
                Plants plant = (Plants) OrganismFactory.createOrganism(this.organismType, this.height, this.width);
                currCell.getOrganisms().get(this.getClass()).add(plant);
                plant.iterationWhereLastWasUsed = new AtomicInteger(iterationWhereLastWasUsed.incrementAndGet());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void play(int currentIteration) {
        if (iterationWhereLastWasUsed.get() != currentIteration) {
            for (int i = 0; i < 3; i++) {
                reproduce();
            }
            iterationWhereLastWasUsed.incrementAndGet();
        }
    }

    public double DamageToHealthPoint(double healthPoint) {
        this.healthPoint.updateAndGet(hP -> this.healthPoint.get() - hP);
        if (this.healthPoint.get() == 0) {
            die(this.organismType.getClassOfCurrentOrganism(), this);
        }
        return healthPoint;
    }
}
