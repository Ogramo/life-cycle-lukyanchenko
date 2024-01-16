package source;

import herbivorous.*;
import plants.Grass;
import predators.*;

public enum OrganismType {
    BOAR(Boar.class),
    BUFFALO(Buffalo.class),
    CATERPILLAR(Caterpillar.class),
    DEER(Deer.class),
    DUCK(Duck.class),
    GOAT(Goat.class),
    HORSE(Horse.class),
    MOUSE(Mouse.class),
    RABBIT(Rabbit.class),
    SHEEP(Sheep.class),

    BEAR(Bear.class),

    BOA(Boa.class),

    EAGLE(Eagle.class),

    FOX(Fox.class),

    WOLF(Wolf.class),

    GRASS(Grass.class);

    private final Class<? extends Organism> classOfCurrentOrganism;

    OrganismType(final Class<? extends Organism> classOfCurrentOrganism) {
        this.classOfCurrentOrganism = classOfCurrentOrganism;
    }

    public static OrganismType getSuitableType(Organism organism) {
        if (organism == null) {
            return null;
        }

        for (OrganismType value : OrganismType.values()) {
            Class<? extends Organism> organismClass = value.getClassOfCurrentOrganism();

            if (organismClass != null && organismClass.equals(organism.getClass())) {
                return value;
            }
        }

        return null;
    }

    public Class<? extends Organism> getClassOfCurrentOrganism() {
        return classOfCurrentOrganism;
    }

    @Override
    public String toString() {
        return "OrganismType{" +
                "classOfCurrentOrganism=" + classOfCurrentOrganism +
                '}';
    }


}
