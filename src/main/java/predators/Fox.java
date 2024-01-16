package predators;

import herbivorous.Caterpillar;
import herbivorous.Duck;
import herbivorous.Mouse;
import herbivorous.Rabbit;
import source.Organism;
import source.Predator;

import java.util.Map;

public class Fox extends Predator {
    private static final int MAX_VALUE_OF_ORGANISMS_ON_CELL = 30;
    private static final double WEIGHT_PER_ELEMENT = 8;
    private static final int AVAILABLE_CELLS_BY_MOVE = 2;
    private static final double KG_OF_CMPLT_SATURATION = 2;

    public Fox(int height, int width) {
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
        return Map.of(Rabbit.class, 70, Mouse.class, 90,
                Duck.class, 60, Caterpillar.class, 40);
    }
}
