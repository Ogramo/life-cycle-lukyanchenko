package source;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Plants extends Organism {
    protected final double WEIGHT_PER_ELEMENT = getWeightPerElement();
    private final int MAX_VALUE_OF_ORGANISMS_ON_CELL = getMAX_VALUE_OF_ORGANISMS_ON_CELL();
    private int descendantCounter;

    public Plants(int height, int width) {
        super(height, width);
    }

    protected Plants() {
        super();
    }

    @Override
    public void reproduce() {
        if (descendantCounter < 7 && currCell.getOrganisms().get(this.getClass()).size() < MAX_VALUE_OF_ORGANISMS_ON_CELL && ThreadLocalRandom.current().nextInt(0, 10) < 9) {
            descendantCounter++;
            try {
                Plants plant = (Plants) OrganismFactory.createOrganism(this.organismType, this.height, this.width);
                currCell.getOrganisms().get(this.getClass()).add(plant);
                plant.iterationWhereLastWasUsed = this.iterationWhereLastWasUsed + 1;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void play(int currentIteration) {
        if (iterationWhereLastWasUsed != currentIteration) {
            for (int i = 0; i < 3; i++) {
                reproduce();
            }
            iterationWhereLastWasUsed++;
        }
    }

}
