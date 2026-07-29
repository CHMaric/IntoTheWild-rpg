package it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.core.model.animals.*;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.ItalianAlps;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ItalianAlpsFactory implements HabitatFactory{

    private final Map<AnimalType, Supplier<Animal>> animalRegistry = new EnumMap<>(AnimalType.class);

    public ItalianAlpsFactory(){
        registerAnimals();
    }

    private void registerAnimals() {
        Habitat h = createHabitat();
        animalRegistry.put(AnimalType.FOX, () -> new Fox(0,AnimalType.FOX,h,85,80,75));
        animalRegistry.put(AnimalType.WOLF, () -> new Wolf(1,AnimalType.WOLF,h,100,100,100));
        animalRegistry.put(AnimalType.HARE, () -> new Hare(2, AnimalType.HARE, h, 50, 90, 70));
        animalRegistry.put(AnimalType.BEARDED_VULTURE, () -> new BeardedVulture(3, AnimalType.BEARDED_VULTURE, h, 90, 70, 80));
    }

    @Override
    public Habitat createHabitat() {
        return new ItalianAlps();
    }

    @Override
    public Animal createAnimal(AnimalType animalType) {
        Supplier<Animal> animalSupplier = animalRegistry.get(animalType);
        if(animalSupplier == null)
            throw new IllegalArgumentException(animalType + " not present in this habitat");
        return animalSupplier.get();
    }

    @Override
    public Set<AnimalType> getSupportedAnimalTypes() {
        return animalRegistry.keySet();
    }
}
