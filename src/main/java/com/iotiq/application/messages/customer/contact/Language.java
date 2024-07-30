package com.iotiq.application.messages.customer.contact;

public enum Language {
    ENGLISH,
    SPANISH,
    CHINESE,
    HINDI,
    ARABIC,
    PORTUGUESE,
    RUSSIAN,
    JAPANESE,
    GERMAN,
    FRENCH;

    @Override
    public String toString() {
        // Converts enum constant name to a more readable format
        String[] words = name().toLowerCase().split("_");
        StringBuilder readableName = new StringBuilder();
        for (String word : words) {
            readableName.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
        return readableName.toString().trim();
    }
}

