package predators;

import herbivorous.*;
import source.Organism;
import source.Predator;

import java.util.Map;

public class Bear extends Predator {
    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 5;
    private static final double WEIGHT_PER_ELEMENT = 500;
    private static final int AVAILABLE_CELLS_BY_MOVE = 2;
    private static final double KG_OF_CMPLT_SATURATION = 80;

    public Bear(int height, int width) {
        super(height, width);
    }

    @Override
    public int getAVAILABLE_CELLS_BY_MOVE() {
        return AVAILABLE_CELLS_BY_MOVE;
    }

    @Override
    public double getKG_OF_CMPLT_SATURATION() {
        return KG_OF_CMPLT_SATURATION;
    }

    @Override
    public double getWeightPerElement() {
        return WEIGHT_PER_ELEMENT;
    }

    @Override
    public int getMAX_VALUE_OF_ORGANISMS_ON_CELL() {
        return MAX_VALUE_OF_ORGANISMS_ON_CELL;
    }

    @Override
    public int getMaxDescendant() {
        return super.getMaxDescendant();
    }

    protected Map<Class<? extends Organism>, Integer> getAnimalFood() {
        return Map.of(Boa.class, 80, Horse.class, 40,
                Deer.class, 80, Rabbit.class, 80, Mouse.class, 90, Goat.class, 70, Sheep.class, 70,
                Boar.class, 50, Buffalo.class, 20, Duck.class, 10);
    }

}
