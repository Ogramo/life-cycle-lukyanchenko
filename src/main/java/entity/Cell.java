package entity;

import source.Organism;
import source.OrganismFactory;
import source.OrganismType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class Cell {
    private final Random random = new Random();

    private final HashMap<Class<? extends Organism>, HashSet<Organism>> organisms = new HashMap<>();
    private int height;
    private int width;

    public Cell() {
    }

    public void cellGeneration(int height, int width) {
        this.height = height;
        this.width = width;
        for (OrganismType organismType : OrganismType.values()) {
            HashSet<Organism> organismHashSet = new HashSet<>();
            int maxValueOfOrganismsOnCell = OrganismFactory.getMaxValueOfOrganismsOnCell(organismType);
            int sizeOfCurrentOrganisms = random.nextInt(1, maxValueOfOrganismsOnCell);
            for (int k = 0; k < sizeOfCurrentOrganisms; k++) {
                organismHashSet.add(OrganismFactory.createOrganism(organismType, height, width));
                organisms.put(organismType.getClassOfCurrentOrganism(), organismHashSet);
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("-----------------\nCell[").append(this.height).append("][").append(this.width).append("]: \n");
        for (Map.Entry<Class<? extends Organism>, HashSet<Organism>> organism : organisms.entrySet()) {
            System.out.println(organism.getKey() + ": " + organism.getValue().size());
        }
        result.append("-----------------");
        return result.toString();
    }

    public HashMap<Class<? extends Organism>, HashSet<Organism>> getOrganisms() {
        return organisms;
    }
}
