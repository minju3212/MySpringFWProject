package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {
	@Autowired
	ShoppingCart shoppingCart;
	
	@Autowired
	OrderService orderService;
	
	@Test
	void testShoppingCart() {
		assertNotNull(shoppingCart);
		assertEquals(2, shoppingCart.getProducts().size());
		assertEquals("사과", shoppingCart.getProducts().get(0).getName());
		System.out.println(shoppingCart.getTotalPrice());
		assertEquals(4000, shoppingCart.getTotalPrice());
	}
	
	@Test
	void testOrderService() {
		assertNotNull(orderService);
		//assertEquals("products", orderService.getShoppingCart());
		System.out.println(shoppingCart.getTotalPrice());
		assertEquals(4000, orderService.calculateOrderTotal());
	}

}
