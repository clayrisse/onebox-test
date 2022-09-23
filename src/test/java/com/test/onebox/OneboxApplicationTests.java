package com.test.onebox;

import com.test.onebox.model.Cart;
import com.test.onebox.model.Carts;
import com.test.onebox.model.Product;
import com.test.onebox.repository.ProductRepository;
import com.test.onebox.service.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OneboxApplicationTests {

	@Autowired	CartService cartService;
	@Autowired	ProductRepository productRepository;

	@BeforeEach
	void init() {
		cartService.createCart();
		cartService.createCart();
		cartService.createCart();
		cartService.createCart();
	}

	@AfterEach
	void destroy() {
        productRepository.deleteAll();
	}

	@Test
	void createCart_resultOk() throws Exception {
		assertNotNull(cartService.createCart());
		assertInstanceOf(Cart.class, cartService.createCart());
	}

	@Test
	void getCartById_Ok() throws Exception {
		assertNotNull(cartService.getCart(4));
		assertEquals(3, cartService.getCart(3).getId());
	}

	@Test
	void deleteCar_Ok() throws Exception {
		assertEquals(3, cartService.getCart(3).getId());
		Carts carts = Carts.getInstance();
		int size = carts.getList().size();
		cartService.deleteCart(3);
		assertTrue(carts.getList().size() == size-1);
		assertNull(cartService.getCart(3));
	}

	@Test
	void addsProducts() throws Exception {
		productRepository.saveAll(List.of(
				(new Product("Bagpack", 90)),
				(new Product("Handbag", 60))
		));
		cartService.addProduct(2,1);
		cartService.addProduct(2,2);
		assertInstanceOf(Product.class, cartService.getCart(2).getProducts().get(0));
		assertEquals("Bagpack", cartService.getCart(2).getProducts().get(0).getDescription());
	}


	@Test
	void deletesCarrAfter10seg() throws Exception {
		Carts carts = Carts.getInstance();
		int size = carts.getList().size();
		assertEquals(1, cartService.getCart(1).getId());
		Thread.sleep(10000);
		assertNull(carts.getByIdIn5Seg(1));
		assertTrue(carts.getList().size() == size-1);
	}


}
