package com.test.onebox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//singleton
public class Carts {

    private static volatile Carts carts_instance = null;
    private HashMap<Integer,Cart> list = new HashMap<Integer,Cart>();

    private Carts() {}

    public static Carts getInstance() {
        if (carts_instance == null) carts_instance = new Carts();
        return carts_instance;
    }

    public HashMap<Integer, Cart> getList() { return list; }
    public void setList(HashMap<Integer, Cart> list) { this.list = list; }

    public Cart getById(int id) throws InterruptedException {
        if (!this.getList().containsKey(id)) throw new RuntimeException("Cart id not found!");
//        System.err.println(System.currentTimeMillis() - this.getList().get(id).getCreateTime() + "<   3000 ...get");
//        if (System.currentTimeMillis() - this.getList().get(id).getCreateTime() < 1000*3) {
        if (System.currentTimeMillis() - this.getList().get(id).getCreateTime() < 1000*60*10) {
            return getList().get(id);
        }
        removeCartfromList(id);
        return null;
    }

    public List<Product> getProductsList(int id) throws InterruptedException {
        if (!this.getList().containsKey(id)) return new ArrayList<Product>();
        return getList().get(id).getProducts();
    }


    public Cart createCart() {
        Cart cart = new Cart();
        this.list.put(cart.getId(), cart);
        return cart;
    }

    public void removeCartfromList(int cartId) {
        this.list.remove(cartId);
    }

}


