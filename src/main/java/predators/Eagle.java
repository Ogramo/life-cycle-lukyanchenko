package predators;

import herbivorous.Caterpillar;
import herbivorous.Mouse;
import herbivorous.Rabbit;
import source.Organism;
import source.Predator;

import java.util.Map;

public class Eagle extends Predator {
    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 20;
    private static final double WEIGHT_PER_ELEMENT = 6;
    private static final int AVAILABLE_CELLS_BY_MOVE = 3;
    private static final double KG_OF_CMPLT_SATURATION = 1;

    public Eagle(int height, int width) {
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
        return Map.of(Fox.class, 10, Rabbit.class, 90,
                Mouse.class, 90, Caterpillar.class, 80);
    }
}

