package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String label;

    @Enumerated(EnumType.STRING)
    private ProdType type;

    private double price;

    @ManyToMany(mappedBy = "products")
    private List<PetStore> petStores;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public ProdType getType() {

        return type;
    }

    public void setType(ProdType type) {

        this.type = type;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public List<PetStore> getPetStores() {

        return petStores;
    }

    public void setPetStores(List<PetStore> petStores) {

        this.petStores = petStores;
    }
}
