package source;

import entity.Cell;
import entity.GameField;

public abstract class Organism {

    protected final double WEIGHT_PER_ELEMENT = getWeightPerElement();
    protected final int MAX_VALUE_OF_ORGANISMS_ON_CELL = getMAX_VALUE_OF_ORGANISMS_ON_CELL();
    protected OrganismType organismType;
    protected Cell currCell;
    protected int iterationWhereLastWasUsed = 0;
    protected int descendantCounter = 0;
    protected int width;
    protected int height;

    protected Organism() {

    }

    protected Organism(int height, int width) {
        this.width = width;
        this.height = height;
        organismType = OrganismType.getSuitableType(this);
        this.currCell = GameField.getInstance().getCells()[height][width];
    }

    public double getWeightPerElement() {
        return 0;
    }

    public int getMAX_VALUE_OF_ORGANISMS_ON_CELL() {
        return 0;
    }

    protected void die(Class<?> classOfDeadBody, Organism deadBody) {
        currCell.getOrganisms().get(classOfDeadBody).remove(deadBody);
    }

    public abstract void reproduce();

    public abstract void play(int currentIteration);

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
