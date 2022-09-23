package com.test.onebox.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class  Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private long amount;

    public Product(String description, long amount) {
        this.description = description;
        this.amount = amount;
    }

    public long getId() { return id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getAmount() { return amount; }
    public void setAmount(long amount) { this.amount = amount; }


}
