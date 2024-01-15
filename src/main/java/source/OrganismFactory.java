package source;

import herbivorous.*;
import plants.Grass;
import predators.*;

public class OrganismFactory {
    public static Organism createOrganism(OrganismType organismType, int height, int width) {
        return switch (organismType) {
            case BOAR -> new Boar(height, width);
            case BUFFALO -> new Buffalo(height, width);
            case CATERPILLAR -> new Caterpillar(height, width);
            case DEER -> new Deer(height, width);
            case DUCK -> new Duck(height, width);
            case GOAT -> new Goat(height, width);
            case HORSE -> new Horse(height, width);
            case MOUSE -> new Mouse(height, width);
            case RABBIT -> new Rabbit(height, width);
            case SHEEP -> new Sheep(height, width);
            case BEAR -> new Bear(height, width);
            case BOA -> new Boa(height, width);
            case EAGLE -> new Eagle(height, width);
            case FOX -> new Fox(height, width);
            case WOLF -> new Wolf(height, width);
            case GRASS -> new Grass(height, width);
        };
    }

    public static int getMaxValueOfOrganismsOnCell(OrganismType organismType) {
        return switch (organismType) {
            case BOAR -> 50;
            case BUFFALO -> 10;
            case CATERPILLAR -> 1000;
            case DEER, HORSE, EAGLE -> 20;
            case DUCK, GRASS -> 200;
            case GOAT, SHEEP -> 140;
            case MOUSE -> 500;
            case RABBIT -> 150;
            case BEAR -> 5;
            case BOA, FOX, WOLF -> 30;
        };
    }
}
