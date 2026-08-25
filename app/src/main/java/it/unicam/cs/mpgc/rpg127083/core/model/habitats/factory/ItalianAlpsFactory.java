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
    private Habitat habitatInstance;

    public ItalianAlpsFactory(){
        registerAnimals();
    }

    private void registerAnimals() {
        Habitat habitat = createHabitat();
        animalRegistry.put(AnimalType.FOX, () -> new Fox(0, habitat, 85, 80, 75, 5));
        animalRegistry.put(AnimalType.WOLF, () -> new Wolf(1, habitat, 100, 100, 100, 5));
        animalRegistry.put(AnimalType.HARE, () -> new Hare(2, habitat, 50, 90, 70, 5));
        animalRegistry.put(AnimalType.BEARDED_VULTURE, () -> new BeardedVulture(3, habitat, 90, 70, 80, 5));
    }

    @Override
    public Habitat createHabitat() {
        if (habitatInstance == null)
            habitatInstance = new ItalianAlps(animalRegistry.keySet());
        return habitatInstance;
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
