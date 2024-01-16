package source;

import entity.Cell;
import entity.GameField;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Organism {

    protected final double WEIGHT_PER_ELEMENT = getWeightPerElement();
    protected final int MAX_VALUE_OF_ORGANISMS_ON_CELL = getMaxValueOfOrganismsOnCell();
    protected OrganismType organismType;
    protected Cell currCell;
    protected AtomicInteger iterationWhereLastWasUsed = new AtomicInteger(0);
    protected AtomicInteger descendantCounter = new AtomicInteger(0);
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

    public int getMaxValueOfOrganismsOnCell() {
        return 0;
    }

    synchronized protected void die(Class<?> classOfDeadBody, Organism deadBody) {
        currCell.getOrganisms().get(classOfDeadBody).remove(deadBody);
        notifyAll();
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
}
