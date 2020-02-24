package target.target.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import target.target.dto.CartDTO;
import target.target.dto.CartProductDTO;

@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;

	@ManyToMany
	@JoinTable(name = "cart_product", joinColumns = {
			@JoinColumn(name = "cart_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "product_id", referencedColumnName = "id") })
	private List<Product> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public void addItem(Product product) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}

		this.items.add(product);
	}

	public CartDTO toDTO() {
		CartDTO cartDTO = new CartDTO();
		List<CartProductDTO> itemsDTO = new ArrayList<>();
		for (Product product : items) {
			CartProductDTO itemDTO = itemsDTO.stream().filter(item -> item.getProductId().equals(product.getId()))
					.findFirst().orElse(null);
			if (itemDTO == null) {
				itemsDTO.add(new CartProductDTO(product));
			} else {
				itemDTO.setProductQuantity(itemDTO.getProductQuantity() + 1);
			}
		}

		if (getCoupon() != null) {
		cartDTO.setCouponCode(getCoupon().getCode());
		cartDTO.setCouponDiscountPercentage(getCoupon().getDiscountPercentage());
		}	
		
		cartDTO.setItems(itemsDTO);
		cartDTO.calculateTotalValue();

		return cartDTO;
	}
}
