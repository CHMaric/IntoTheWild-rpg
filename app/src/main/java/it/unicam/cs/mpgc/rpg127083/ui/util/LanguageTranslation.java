package it.unicam.cs.mpgc.rpg127083.ui.util;

import java.util.Map;

public final class LanguageTranslation {
    private static final Map<String, String> ITALIAN_TRANSLATIONS = Map.of(
            "wolf", "Lupo",
            "hare", "Lepre",
            "fox", "Volpe",
            "bearded vulture", "Gipeto",
            "italian alps", "Alpi Italiane"
    );

    private LanguageTranslation(){}

    public static String translateToItalian(String englishTerm) {
        return ITALIAN_TRANSLATIONS.getOrDefault(englishTerm.toLowerCase(), englishTerm);
    }

}
