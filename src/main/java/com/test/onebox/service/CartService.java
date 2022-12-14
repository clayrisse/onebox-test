package com.test.onebox.service;

import com.test.onebox.model.Cart;
import com.test.onebox.model.Carts;
import com.test.onebox.model.Product;
import com.test.onebox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CartService {

    @Autowired
    ProductRepository productRepository;

    public Cart createCart(){
        Carts carts = Carts.getInstance();
        return carts.createCart();
    }

    public Cart checkCartExistsAndReturnProductsIfApply(int cartId) throws InterruptedException {
        Carts carts = Carts.getInstance();
        List<Product> productList = carts.getProductsList(cartId);
        if (carts.getById(cartId) == null) {
            if (productList.size() > 0) {
                for (Product product : productList) {
                    if (productRepository.findById(product.getId()).isPresent()) {
                        Product foundProduct = productRepository.findById(product.getId()).get();
                        foundProduct.setAmount(product.getAmount() + 1);
                        productRepository.save(foundProduct);
                    }
                }
            }
            return null;
        }
        return carts.getById(cartId);
    }

    public void deleteCart(int id) throws InterruptedException {
        Carts carts = Carts.getInstance();
        if (!carts.getList().containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart with id '" + id + "' not found");
        }
        checkCartExistsAndReturnProductsIfApply(id);
        carts.removeCartfromList(id);
    }

    public Cart getCart(int id) throws InterruptedException {
        Carts carts = Carts.getInstance();
        if (!carts.getList().containsKey(id)) {
            return null;
        };
        return checkCartExistsAndReturnProductsIfApply(id);
    }

    public Cart addProduct(int cartId, long productId) throws InterruptedException {
        checkCartExistsAndReturnProductsIfApply(cartId);

        Cart cart = getCart(cartId);
        if (productRepository.findById(productId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with id '" + productId + "' not found");
        }
        Product product = productRepository.findById(productId).get();
        cart.addProduct(product);
        product.setAmount(product.getAmount() - 1);
        productRepository.save(product);
        return cart;
    }

}
