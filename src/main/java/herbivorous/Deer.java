package herbivorous;

import plants.Grass;
import source.Herbivorous;
import source.Organism;

import java.util.Map;

public class Deer extends Herbivorous {
    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 20;
    private static final double WEIGHT_PER_ELEMENT = 300;
    private static final int AVAILABLE_CELLS_BY_MOVE = 4;
    private static final double KG_OF_CMPLT_SATURATION = 50;

    public Deer(int height, int width) {
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
        return Map.of(Grass.class, 100);
    }
}
