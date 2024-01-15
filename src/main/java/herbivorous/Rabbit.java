package herbivorous;

import plants.Grass;
import source.Herbivorous;
import source.Organism;

import java.util.Map;

public class Rabbit extends Herbivorous {
    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 150;
    private static final double WEIGHT_PER_ELEMENT = 2;
    private static final int AVAILABLE_CELLS_BY_MOVE = 2;
    private static final double KG_OF_CMPLT_SATURATION = 0.45;

    public Rabbit(int height, int width) {
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

    public Map<Class<? extends Organism>, Integer> getAnimalFood() {
        return Map.of(Grass.class, 100);
    }
}
