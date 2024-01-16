package source;

import entity.GameField;
import plants.Grass;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal extends Organism {

    protected final int AVAILABLE_CELLS_BY_MOVE = getAvailableCellsByMove();
    protected final double KG_OF_CMPLT_SATURATION = getKgOfCmpltSaturation();
    protected final int maxDescendant = getMaxDescendant();
    protected double satietyFeeling;
    ThreadLocalRandom random = ThreadLocalRandom.current();

    protected Animal(int height, int width) {
        super(height, width);
        satietyFeeling = getKgOfCmpltSaturation();
    }

    public int getAvailableCellsByMove() {
        return 0;
    }

    public double getKgOfCmpltSaturation() {
        return 0;
    }

    @Override
    public void play(int currentIteration) {
        if (currentIteration != iterationWhereLastWasUsed.get()) {
            eat();
            for (int i = 0; i < maxDescendant / 3; i++) {
                reproduce();
            }
            move();
            satietyFeeling -= getKgOfCmpltSaturation() * 0.125;
            if (satietyFeeling <= 0) {
                die(this.organismType.getClassOfCurrentOrganism(), this);
            } else {
                iterationWhereLastWasUsed.incrementAndGet();
            }
        }
    }

    synchronized public void move() {
        for (int i = 1; i <= getAvailableCellsByMove(); i++) {
            moveToAnyCell(height, width);
        }
    }

    synchronized public void eat() {
        double currentCycleWeightOfBreakfest = 0;
        HashSet<Class<? extends Organism>> missingOrganisms = new HashSet<>();
        while (currentCycleWeightOfBreakfest < getKgOfCmpltSaturation() * 0.1 && satietyFeeling != getKgOfCmpltSaturation()) {
            try {
                currentCycleWeightOfBreakfest += findingFood(missingOrganisms);
            } catch (RuntimeException e) {
                break;
            }
        }
    }

    private double findingFood(HashSet<Class<? extends Organism>> missingOrganisms) {
        Class<? extends Organism> target = getAnimalFood().keySet().stream()
                .filter(organism -> !missingOrganisms.contains(organism)).findAny().orElseThrow(RuntimeException::new);
        HashSet<?> setOfTargets = currCell.getOrganisms().get(target);
        if (setOfTargets.size() > 0) {
            Organism possibleBreakfast = (Organism) setOfTargets.iterator().next();
            if (ThreadLocalRandom.current().nextInt(0, 100) <= getAnimalFood().get(target)) {
                eating(target, possibleBreakfast);
            }
            return possibleBreakfast.getWeightPerElement();
        } else {
            missingOrganisms.add(target);
        }
        return 0;
    }

    private void eating(Class<?> target, Organism possibleBreakfast) {
        if (possibleBreakfast.organismType == OrganismType.GRASS) {
            Grass possibleBreakfast1 = (Grass) possibleBreakfast;
            satietyFeeling += possibleBreakfast1.DamageToHealthPoint
                    (Math.min((getKgOfCmpltSaturation() - satietyFeeling), possibleBreakfast1.getHealthPoint()));
        } else {
            double breakfastWeight = possibleBreakfast.getWeightPerElement();
            double futureSatietyFelling = breakfastWeight + satietyFeeling;
            satietyFeeling = Math.min(futureSatietyFelling, getKgOfCmpltSaturation());
            die(target, possibleBreakfast);
        }
    }


    @Override
    synchronized public void reproduce() {
        if (descendantCounter.get() < maxDescendant && currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).size() > 1
                && currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).size() < getMaxValueOfOrganismsOnCell()
                && random.nextInt(0, 10) < 8) {
            findingPartnerThenReproducing();
        }
    }

    private void findingPartnerThenReproducing() {
        Animal partner = (Animal) currCell.getOrganisms().get(this.getClass()).stream()
                .findAny().orElseThrow(NoClassDefFoundError::new);
        partner.descendantCounter.incrementAndGet();
        descendantCounter.incrementAndGet();
        Animal animal = (Animal) OrganismFactory.createOrganism(this.organismType, this.height, this.width);
        animal.iterationWhereLastWasUsed = new AtomicInteger(iterationWhereLastWasUsed.incrementAndGet());
        currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).add(animal);
    }

    synchronized public void moveToAnyCell(int height, int width) {
        int choice = random.nextInt(0, 4);
        int futureCellWidth = width;
        int futureCellHeight = height;
        if (width == 0 && height == 0) {
            if (choice % 2 == 0) {
                futureCellHeight++;
            } else {
                futureCellWidth++;
            }
        } else if (height == 99 && width == 19) {
            if (choice % 2 == 0) {
                futureCellHeight--;
            } else {
                futureCellWidth--;
            }
        } else if (height == 99 && width == 0) {
            if (choice % 2 == 0) {
                futureCellHeight--;
            } else {
                futureCellWidth++;
            }
        } else if (height == 0 && width == 19) {
            if (choice % 2 == 0) {
                futureCellHeight++;
            } else {
                futureCellWidth--;
            }
        } else if (height == 0) {
            if ((choice + 2) % 3 == 0) {
                futureCellHeight++;
            } else if ((choice + 2) % 3 == 1) {
                futureCellWidth--;
            } else {
                futureCellWidth++;
            }
        } else if (width == 0) {
            if ((choice + 2) % 3 == 0) {
                futureCellWidth++;
            } else if ((choice + 2) % 3 == 1) {
                futureCellHeight--;
            } else {
                futureCellHeight++;
            }
        }
        if (GameField.getInstance().getCells()[futureCellHeight][futureCellWidth]
                .getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).size() < getMaxValueOfOrganismsOnCell()) {
            currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).remove(this);
            currCell = GameField.getInstance().getCells()[futureCellHeight][futureCellWidth];
            currCell.getOrganisms().get(this.getClass()).add(this);
            setHeight(futureCellHeight);
            setWidth(futureCellWidth);
        }
    }

    protected Map<Class<? extends Organism>, Integer> getAnimalFood() {
        return Map.of();
    }

    protected int getMaxDescendant() {
        if (getWeightPerElement() < 0.06) {
            return 15;
        } else if (getWeightPerElement() < 1) {
            return 9;
        } else if (getWeightPerElement() < 10) {
            return 6;
        } else {
            return 3;
        }
    }
}
