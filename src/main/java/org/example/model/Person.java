package org.example.model;

import com.github.javafaker.Faker;
import lombok.Getter;

@Getter
public class Person {
    private final String name;
    private final String address;
    private final String email;
    private final String company;
    private final String imageUrl;

    public Person(String name, String address, String email, String company, String imageUrl) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.company = company;
        this.imageUrl = imageUrl;
    }

    public Person(Faker faker, String imageUrl) {
        this.name = faker.name().fullName();
        this.address = faker.address().streetAddress();
        this.email = faker.internet().emailAddress();
        this.company = faker.company().name();
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
