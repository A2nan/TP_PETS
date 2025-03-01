package org.example.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date birth;
    private String couleur;

    @ManyToOne
    @JoinColumn(name = "petstore_id")
    private PetStore petStore;

    // Getter et Setter
    public PetStore getPetStore() {

        return petStore;
    }

    public void setPetStore(PetStore petStore) {

        this.petStore = petStore;
    }

    public String getCouleur() {

        return couleur;
    }

    public void setCouleur(String couleur) {

        this.couleur = couleur;
    }

    public Date getBirth() {

        return birth;
    }

    public void setBirth(Date birth) {

        this.birth = birth;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }
}
