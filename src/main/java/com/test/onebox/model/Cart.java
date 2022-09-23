package com.test.onebox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Cart {
    private static int idCounter = 0;
    private int id;
    private List<Product> products = new ArrayList<>();
    private long createTime = System.currentTimeMillis();

    public Cart() {
        this.idCounter = idCounter + 1;
        this.id = idCounter;
    }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public Cart addProduct(Product product) {
        this.products.add(product);
        return this;
    }

    public long getCreateTime() { return createTime; }
    public void setCreateTime(long createTime) { this.createTime = createTime; }

}
