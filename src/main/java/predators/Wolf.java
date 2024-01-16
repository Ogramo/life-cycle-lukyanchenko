package predators;

import herbivorous.*;
import source.Organism;
import source.Predator;

import java.util.Map;

public class Wolf extends Predator {
    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 30;
    private static final double WEIGHT_PER_ELEMENT = 50;
    private static final int AVAILABLE_CELLS_BY_MOVE = 3;
    private static final double KG_OF_CMPLT_SATURATION = 8;

    public Wolf(int height, int width) {
        super(height, width);
    }

    @Override
    public int getAvailableCellsByMove() {
        return AVAILABLE_CELLS_BY_MOVE;
    }

    @Override
    public double getKgOfCmpltSaturation() {
        return KG_OF_CMPLT_SATURATION;
    }

    @Override
    public double getWeightPerElement() {
        return WEIGHT_PER_ELEMENT;
    }

    @Override
    public int getMaxValueOfOrganismsOnCell() {
        return MAX_VALUE_OF_ORGANISMS_ON_CELL;
    }

    @Override
    public int getMaxDescendant() {
        return super.getMaxDescendant();
    }

    protected Map<Class<? extends Organism>, Integer> getAnimalFood() {
        return Map.of(Horse.class, 10, Deer.class, 15,
                Rabbit.class, 60, Mouse.class, 80, Goat.class, 60, Sheep.class, 70, Boar.class, 15,
                Buffalo.class, 10, Duck.class, 40);
    }
}
