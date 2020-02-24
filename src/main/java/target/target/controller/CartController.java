package target.target.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import target.target.dto.CartDTO;
import target.target.exception.SystemException;
import target.target.model.Cart;
import target.target.model.Coupon;
import target.target.model.Product;
import target.target.repository.CartRepository;
import target.target.repository.CouponRepository;
import target.target.repository.ProductRepository;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CouponRepository couponRepository;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<?> add(@RequestParam String productId) throws SystemException {
		Cart cart = cartRepository.getCart();
		if (cart == null) {
			cart = new Cart();
		}
		cart.addItem(productRepository.findById(Long.valueOf(productId)));
		cartRepository.save(cart);
		return ResponseEntity.ok().body("Produto adicionado com sucesso");
	}

	@GetMapping(path = "/showCart")
	public ResponseEntity<CartDTO> showCart() {
		return ResponseEntity.ok().body(cartRepository.getCart().toDTO());
	}

	@DeleteMapping("/decreaseProduct/{id}")
	public ResponseEntity<?> decreaseProduct(@PathVariable("id") Long id) {
		Cart cart = cartRepository.getCart();
		Product productToRemove = cart.getItems().stream().filter(item -> item.getId().equals(id)).findFirst().get();
		cart.getItems().remove(productToRemove);
		cartRepository.save(cart);
		return ResponseEntity.ok().body("Quantidade diminuída com sucesso.");
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		Cart cart = cartRepository.getCart();
		cart.getItems().removeIf(item -> item.getId().equals(id));
		cartRepository.save(cart);
		return ResponseEntity.ok().body("Produto removido com sucesso.");
	}

	@PostMapping(path = "/applyCoupon")
	public @ResponseBody ResponseEntity<?> applyCoupon(@RequestParam String couponCode) throws SystemException {
		Cart cart = cartRepository.getCart();
		Coupon newCoupon = couponRepository.findByCode(couponCode);
		if (cart.getCoupon() == null || newCoupon.getDiscountPercentage() > cart.getCoupon().getDiscountPercentage()) {
			cart.setCoupon(newCoupon);
			cartRepository.save(cart);
		}
		return ResponseEntity.ok().body("Cupom aplicado com sucesso");
	}
}
