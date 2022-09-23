package com.test.onebox;

import com.test.onebox.model.Cart;
import com.test.onebox.model.Carts;
import com.test.onebox.model.Product;
import com.test.onebox.repository.ProductRepository;
import com.test.onebox.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OneboxApplicationTests {

	@Autowired	CartService cartService;
	@Autowired	ProductRepository productRepository;

	@Test
	void createCart_resultOk() throws Exception {
		assertNotNull(cartService.createCart());
		assertInstanceOf(Cart.class, cartService.createCart());
	}

	@Test
	void addsProducts() throws Exception {
		cartService.createCart();
		cartService.createCart();
		cartService.createCart();
		productRepository.saveAll(List.of(
				(new Product("Bagpack", 90)),
				(new Product("Handbag", 60))
		));
		cartService.addProduct(3,1);
		cartService.addProduct(3,2);
		assertInstanceOf(Product.class, cartService.getCart(3).getProducts().get(0));
		assertEquals("Bagpack", cartService.getCart(3).getProducts().get(0).getDescription());
	}

	@Test
	void getCartById_Ok() throws Exception {
		cartService.createCart();
		cartService.createCart();
		assertNotNull(cartService.getCart(1));
		assertEquals(2, cartService.getCart(2).getId());
	}

	@Test
	void deleteCar_Ok() throws Exception {
		Carts carts = Carts.getInstance();
		assertTrue(true);
		cartService.createCart();
		cartService.createCart();
		assertEquals(2, cartService.getCart(2).getId());
		int size = carts.getList().size();
		cartService.deleteCart(2);
//		assertTrue(carts.getList().size() == size-1);
		assertNull(cartService.getCart(2));
	}


	@Test
	void deletesCarrAfter7seg() throws Exception {
		Carts carts = Carts.getInstance();
		int size = carts.getList().size();
		assertEquals(1, cartService.getCart(1).getId());
		Thread.sleep(7000);
		assertNull(carts.getByIdIn5Seg(1));
		assertTrue(carts.getList().size() == size-1);
	}


}
