package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cat")
public class Cat extends Animal {
    private String chipId;

    // Getters et Setters

    public String getChipId() {

        return chipId;
    }

    public void setChipId(String chipId) {

        this.chipId = chipId;
    }
}
