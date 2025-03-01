package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "petstore")
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String managerName;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private List<Animal> animals;

    @ManyToMany
    @JoinTable(name = "petstore_product",
            joinColumns = @JoinColumn(name = "petstore_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getManagerName() {

        return managerName;
    }

    public void setManagerName(String managerName) {

        this.managerName = managerName;
    }

    public List<Animal> getAnimals() {

        return animals;
    }

    public void setAnimals(List<Animal> animals) {

        this.animals = animals;
    }

    public List<Product> getProducts() {

        return products;
    }

    public void setProducts(List<Product> products) {

        this.products = products;
    }

    public Address getAddress() {

        return address;
    }

    public void setAddress(Address address) {

        this.address = address;
    }
}
