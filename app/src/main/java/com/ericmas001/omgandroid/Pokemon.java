package com.ericmas001.omgandroid;

public class Pokemon {
    String name;
    String type;
    int photoId;
    boolean selected = false;

    Pokemon(String name, String type, int photoId) {
        this.name = name;
        this.type = type;
        this.photoId = photoId;
    }
}
