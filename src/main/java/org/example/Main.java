package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.model.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore")) {
            EntityManager em = emf.createEntityManager();

            System.out.println("Création des tables et insertion des données...");
            em.getTransaction().begin();

            // 🔹 Création de 3 animaleries
            PetStore petStore1 = new PetStore();
            petStore1.setName("Animal Paradise");
            petStore1.setManagerName("Alice Johnson");

            PetStore petStore2 = new PetStore();
            petStore2.setName("Jungle Pet Shop");
            petStore2.setManagerName("Bob Martin");

            PetStore petStore3 = new PetStore();
            petStore3.setName("Ocean Creatures");
            petStore3.setManagerName("Charlie Davis");

            // 🔹 Création de 3 adresses
            Address address1 = new Address();
            address1.setNumber("42");
            address1.setStreet("Rue des Animaux");
            address1.setZipCode("75001");
            address1.setCity("Paris");

            Address address2 = new Address();
            address2.setNumber("24");
            address2.setStreet("Boulevard des Bêtes");
            address2.setZipCode("69002");
            address2.setCity("Lyon");

            Address address3 = new Address();
            address3.setNumber("88");
            address3.setStreet("Avenue des Océans");
            address3.setZipCode("33000");
            address3.setCity("Bordeaux");

            petStore1.setAddress(address1);
            petStore2.setAddress(address2);
            petStore3.setAddress(address3);

            // 🔹 Création de 3 produits
            Product product1 = new Product();
            product1.setCode("FOOD01");
            product1.setLabel("Premium Dog Food");
            product1.setType(ProdType.FOOD);
            product1.setPrice(20.0);

            Product product2 = new Product();
            product2.setCode("ACC01");
            product2.setLabel("Dog Collar");
            product2.setType(ProdType.ACCESSORY);
            product2.setPrice(10.0);

            Product product3 = new Product();
            product3.setCode("TOY01");
            product3.setLabel("Cat Toy");
            product3.setType(ProdType.CLEANING);
            product3.setPrice(15.0);

            petStore1.setProducts(List.of(product1, product2, product3));
            petStore2.setProducts(List.of(product1, product3));
            petStore3.setProducts(List.of(product2, product3));

            // 🔹 Création de 3 poissons
            Fish fish1 = new Fish();
            fish1.setBirth(new Date());
            fish1.setCouleur("Gold");
            fish1.setLivingEnv(FishLivEnv.FRESH_WATER);
            fish1.setPetStore(petStore1);

            Fish fish2 = new Fish();
            fish2.setBirth(new Date());
            fish2.setCouleur("Blue");
            fish2.setLivingEnv(FishLivEnv.FRESH_WATER);
            fish2.setPetStore(petStore2);

            Fish fish3 = new Fish();
            fish3.setBirth(new Date());
            fish3.setCouleur("Red");
            fish3.setLivingEnv(FishLivEnv.FRESH_WATER);
            fish3.setPetStore(petStore3);

            // 🔹 Création de 3 chats
            Cat cat1 = new Cat();
            cat1.setBirth(new Date());
            cat1.setCouleur("Black");
            cat1.setChipId("CHIP1234");
            cat1.setPetStore(petStore1);

            Cat cat2 = new Cat();
            cat2.setBirth(new Date());
            cat2.setCouleur("White");
            cat2.setChipId("CHIP5678");
            cat2.setPetStore(petStore2);

            Cat cat3 = new Cat();
            cat3.setBirth(new Date());
            cat3.setCouleur("Brown");
            cat3.setChipId("CHIP9101");
            cat3.setPetStore(petStore3);

            // 🔹 Association des animaux aux animaleries
            petStore1.setAnimals(List.of(fish1, cat1));
            petStore2.setAnimals(List.of(fish2, cat2));
            petStore3.setAnimals(List.of(fish3, cat3));

            // 🔹 Persistance des objets
            em.persist(address1);
            em.persist(address2);
            em.persist(address3);
            em.persist(petStore1);
            em.persist(petStore2);
            em.persist(petStore3);
            em.persist(product1);
            em.persist(product2);
            em.persist(product3);
            em.persist(fish1);
            em.persist(fish2);
            em.persist(fish3);
            em.persist(cat1);
            em.persist(cat2);
            em.persist(cat3);

            em.getTransaction().commit();

            System.out.println("Données insérées avec succès !");

            // 🔹 Extraction des animaux d'une animalerie donnée (ex: "Animal Paradise")
            String storeName = "Animal Paradise";
            TypedQuery<Animal> query = em.createQuery(
                    "SELECT a FROM Animal a WHERE a.petStore.name = :storeName", Animal.class);
            query.setParameter("storeName", storeName);

            List<Animal> animals = query.getResultList();

            System.out.println("\nAnimaux présents dans l'animalerie '" + storeName + "':");
            for (Animal animal : animals) {
                if (animal instanceof Fish) {
                    System.out.println("- Poisson " + ((Fish) animal).getCouleur() + " (" + ((Fish) animal).getLivingEnv() + ")");
                } else if (animal instanceof Cat) {
                    System.out.println("- Chat " + ((Cat) animal).getCouleur() + " (ID puce: " + ((Cat) animal).getChipId() + ")");
                }
            }

            em.close();
        }
    }
}
