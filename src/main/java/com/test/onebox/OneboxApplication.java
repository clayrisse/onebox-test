package com.test.onebox;

import com.test.onebox.model.Cart;
import com.test.onebox.model.Carts;
import com.test.onebox.model.Product;
import com.test.onebox.repository.ProductRepository;
import com.test.onebox.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OneboxApplication implements CommandLineRunner {

	public static void main(String[] args) { SpringApplication.run(OneboxApplication.class, args); }

	@Autowired	ProductRepository productRepository;
	@Autowired	CartService cartService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello Onebox");

		productRepository.saveAll(List.of(
				(new Product("Bagpack", 90)),
				(new Product("Handbag", 60)),
				(new Product("Suitcase 55cm", 120)),
				(new Product("Suitcase 60cm", 140)),
				(new Product("Suitcase 65cm", 160))
		));


//		cartService.createCart();
//		cartService.createCart();
//		cartService.createCart();
//		cartService.createCart();
//		Carts carts = Carts.getInstance();
//		System.out.println(carts.getList().size());
//		cartService.deleteCart(1);
//		System.out.println(carts.getList().size());
//		System.out.println(cartService.getCart(2));
//		cartService.addProduct(2,1);
//		cartService.addProduct(2,2);
//
//		System.err.println("----1");
//		System.err.println(cartService.getCart(2).getProducts());
//		System.err.println("----2");
//
//		System.err.println(carts.getList().size());
//		cartService.deleteCart(3);
//		Thread.sleep(4000);
//		System.err.println(carts.getList().size());
//		System.err.println("--------------------------------------3");
//		cartService.addProduct(2,2);
//		System.err.println("--------------------------------------4");
//		System.err.println(carts.getList().size());
//		cartService.createCart();
//		System.err.println(carts.getList().size());
//		System.err.println("----4");
//		System.err.println("--------------------------------------5");
//		Thread.sleep(1000);
//		cartService.addProduct(5,2);
//		System.err.println(carts.getList().size());

	};

}
