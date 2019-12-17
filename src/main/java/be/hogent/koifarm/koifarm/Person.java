package be.hogent.koifarm.koifarm;

import java.util.UUID;

abstract class Person {
    private final UUID id;
    private final String name;

    Person(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }  

}
