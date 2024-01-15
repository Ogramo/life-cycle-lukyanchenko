package source;

import entity.GameField;
import plants.Grass;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {

    protected final int AVAILABLE_CELLS_BY_MOVE = getAVAILABLE_CELLS_BY_MOVE();
    protected final double KG_OF_CMPLT_SATURATION = getKG_OF_CMPLT_SATURATION();
    protected final int maxDescendant = getMaxDescendant();
    protected double satietyFeeling;
    ThreadLocalRandom random = ThreadLocalRandom.current();

    protected Animal(int height, int width) {
        super(height, width);
        satietyFeeling = getKG_OF_CMPLT_SATURATION();
    }

    public int getAVAILABLE_CELLS_BY_MOVE() {
        return 0;
    }

    public double getKG_OF_CMPLT_SATURATION() {
        return 0;
    }

    @Override
    public void play(int currentIteration) {
        if (currentIteration != iterationWhereLastWasUsed) {
            eat();
            for (int i = 0; i < maxDescendant / 3; i++) {
                reproduce();
            }
            move();
            satietyFeeling -= getKG_OF_CMPLT_SATURATION() * 0.125;
            if (satietyFeeling <= 0) {
                die(this.organismType.getClassOfCurrentOrganism(), this);
            } else {
                iterationWhereLastWasUsed++;
            }
        }
    }

    public void move() {
        for (int i = 1; i <= getAVAILABLE_CELLS_BY_MOVE(); i++) {
            moveToAnyCell(height, width);
        }
    }

    public void eat() {
        double currentCycleWeightOfBreakfest = 0;
        List<Class<? extends Organism>> missingOrganisms = new ArrayList<>();
        while (currentCycleWeightOfBreakfest < getKG_OF_CMPLT_SATURATION() * 0.04 && satietyFeeling == getKG_OF_CMPLT_SATURATION()) {
            try {
                currentCycleWeightOfBreakfest += findingFood(missingOrganisms);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    private double findingFood(List<Class<? extends Organism>> missingOrganisms) {
        Class<? extends Organism> target = getAnimalFood().keySet().stream()
                .filter(organism -> !missingOrganisms.contains(organism)).findAny().orElseThrow(NoSuchElementException::new);
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
        double breakfastWeight = possibleBreakfast.getWeightPerElement();
        if (possibleBreakfast.organismType == OrganismType.GRASS) {
            Grass possibleBreakfast1 = (Grass) possibleBreakfast;
            satietyFeeling += possibleBreakfast1.DamageToHealthPoint
                    (Math.min((getKG_OF_CMPLT_SATURATION() - satietyFeeling), breakfastWeight));
        } else {
            double futureSatietyFelling = breakfastWeight + satietyFeeling;
            satietyFeeling = Math.min(futureSatietyFelling, getKG_OF_CMPLT_SATURATION());
            die(target, possibleBreakfast);
        }
    }


    @Override
    public void reproduce() {
        if (descendantCounter < maxDescendant && currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).size() > 1
                && currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).size() < getMAX_VALUE_OF_ORGANISMS_ON_CELL()
                && random.nextInt(0, 10) < 8) {
            findingPartnerThenReproducing();
        }
    }

    private void findingPartnerThenReproducing() {
        Animal partner = (Animal) currCell.getOrganisms().get(this.getClass()).stream()
                .findAny().orElseThrow(NoClassDefFoundError::new);
        partner.descendantCounter++;
        descendantCounter++;
        Animal animal = (Animal) OrganismFactory.createOrganism(this.organismType, this.height, this.width);
        animal.iterationWhereLastWasUsed = this.iterationWhereLastWasUsed + 1;
        currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).add(animal);
    }

    public void moveToAnyCell(int height, int width) {
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
                .getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).size() < getMAX_VALUE_OF_ORGANISMS_ON_CELL()) {
            currCell.getOrganisms().get(this.organismType.getClassOfCurrentOrganism()).remove(this);
            currCell = GameField.getInstance().getCells()[futureCellHeight][futureCellWidth];
            currCell.getOrganisms().get(this.getClass()).add(this);
            this.height = futureCellHeight;
            this.width = futureCellWidth;
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
