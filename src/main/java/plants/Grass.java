package plants;

import source.Plants;

public class Grass extends Plants {

    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 1000;
    private static final double WEIGHT_PER_ELEMENT = 1;
    private double healthPoint = WEIGHT_PER_ELEMENT;

    public Grass(int height, int width) {
        super(height, width);
    }

    @Override
    public double getWeightPerElement() {
        return healthPoint;
    }

    @Override
    public int getMAX_VALUE_OF_ORGANISMS_ON_CELL() {
        return MAX_VALUE_OF_ORGANISMS_ON_CELL;
    }

    public double getHealthPoint() {
        return healthPoint;
    }

    public double DamageToHealthPoint(double healthPoint) {
        this.healthPoint -= healthPoint;
        if (this.healthPoint == 0) {
            die(this.organismType.getClassOfCurrentOrganism(), this);
        }
        return healthPoint;
    }
}
