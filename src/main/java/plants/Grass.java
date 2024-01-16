package plants;

import source.Plants;

public class Grass extends Plants {

    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 1000;
    private static final double WEIGHT_PER_ELEMENT = 1;

    public Grass(int height, int width) {
        super(height, width);
    }

    @Override
    public double getWeightPerElement() {
        return WEIGHT_PER_ELEMENT;
    }

    @Override
    public int getMaxValueOfOrganismsOnCell() {
        return MAX_VALUE_OF_ORGANISMS_ON_CELL;
    }

    public double getHealthPoint() {
        return healthPoint.get();
    }


}
