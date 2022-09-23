package com.test.onebox.controller;

import com.test.onebox.model.Cart;
import com.test.onebox.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired CartService cartService;

    @GetMapping("cart/")
    @ResponseStatus(HttpStatus.OK)
    public Cart createCart() {
        return cartService.createCart();
    }

    @GetMapping("cart/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cart getCartById(@PathVariable int id) throws InterruptedException {
        return cartService.getCart(id);
    }

    @DeleteMapping("cart/remove")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCart(int id) throws InterruptedException {
        cartService.deleteCart(id);
    }

    @PutMapping("cart/{cartId}/add/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@PathVariable int cartId, @PathVariable long productId) throws InterruptedException {
        cartService.addProduct(cartId, productId);
    }




}
