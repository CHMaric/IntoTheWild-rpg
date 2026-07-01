package it.unicam.cs.mpgc.rpg127083.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

import java.util.HashMap;
import java.util.Map;

public class HabitatRegistry {
    private final Map<String, HabitatFactory> registry = new HashMap<>();

    public void registerFactory(String label, HabitatFactory factory) {
        registry.put(label.toUpperCase(), factory);
    }

    public HabitatFactory getFactory(String label) {
        HabitatFactory factory = registry.get(label.toUpperCase());
        if (factory == null)
            throw new IllegalArgumentException("No factory registered for label: " + label);
        return factory;
    }
}
