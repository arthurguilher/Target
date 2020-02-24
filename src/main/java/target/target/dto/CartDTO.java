package target.target.dto;

import java.math.BigDecimal;
import java.util.List;

import target.target.util.GeneralUtils;

public class CartDTO {

	private String couponCode;
	
	private int couponDiscountPercentage;
	
	private BigDecimal totalValue = BigDecimal.ZERO;

	private BigDecimal totalValueWithDiscount = BigDecimal.ZERO;

	private BigDecimal totalDiscountByItems = BigDecimal.ZERO;

	private BigDecimal totalProgressiveDiscount = BigDecimal.ZERO;
	
	private BigDecimal totalCouponDiscount = BigDecimal.ZERO;
	
	private List<CartProductDTO> items;

	public void calculateTotalValue() {
		for (CartProductDTO item : items) {

			item.setProductValue(item.getProductUnitValue().multiply(BigDecimal.valueOf(item.getProductQuantity())));
			item.setProductValueWithDiscount(item.getProductValue());

			if (item.getProductQuantity() >= 10) {
				item.setProductUnitValueWithDiscount(GeneralUtils.applyDiscount(item.getProductUnitValue(), 10));
				item.setProductValueWithDiscount(GeneralUtils.applyDiscount(item.getProductValue(), 10));
				setTotalDiscountByItems(getTotalDiscountByItems().add(item.getProductValue().subtract(item.getProductValueWithDiscount())));
			}
		}

		setTotalValue(items.stream().map(CartProductDTO::getProductValue).reduce(BigDecimal.ZERO, BigDecimal::add));
		setTotalValueWithDiscount(items.stream().map(CartProductDTO::getProductValueWithDiscount)
				.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		applyProgressiveDiscount();
		applyCoupon();
	}
	
	private void applyProgressiveDiscount() {

		if (totalValueWithDiscount.compareTo(new BigDecimal(1000)) >= 0 && totalValueWithDiscount.compareTo(new BigDecimal(5000)) < 0) {
			setTotalValueWithDiscount(GeneralUtils.applyDiscount(totalValueWithDiscount, 5));
			setTotalProgressiveDiscount(getTotalValue().subtract(getTotalValueWithDiscount()).subtract(getTotalDiscountByItems()));
		} else if (totalValueWithDiscount.compareTo(new BigDecimal(5000)) >= 0 && totalValueWithDiscount.compareTo(new BigDecimal(10000)) < 0) {
			setTotalValueWithDiscount(GeneralUtils.applyDiscount(totalValueWithDiscount, 7));
			setTotalProgressiveDiscount(getTotalValue().subtract(getTotalValueWithDiscount()).subtract(getTotalDiscountByItems()));
		} else if (totalValueWithDiscount.compareTo(new BigDecimal(10000)) >= 0) {
			setTotalValueWithDiscount(GeneralUtils.applyDiscount(totalValueWithDiscount, 10));
			setTotalProgressiveDiscount(getTotalValue().subtract(getTotalValueWithDiscount()).subtract(getTotalDiscountByItems()));
		}
	}
	
	private void applyCoupon() {
		if (getCouponCode() != null) {
			setTotalValueWithDiscount(GeneralUtils.applyDiscount(getTotalValueWithDiscount(), getCouponDiscountPercentage()));
		}
		
		setTotalCouponDiscount(getTotalValue().subtract(getTotalDiscountByItems()).subtract(getTotalProgressiveDiscount()).subtract(getTotalValueWithDiscount()));
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public List<CartProductDTO> getItems() {
		return items;
	}

	public void setItems(List<CartProductDTO> items) {
		this.items = items;
	}
	
	public BigDecimal getTotalValueWithDiscount() {
		return totalValueWithDiscount;
	}

	public void setTotalValueWithDiscount(BigDecimal totalValueWithDiscount) {
		this.totalValueWithDiscount = totalValueWithDiscount;
	}

	public BigDecimal getTotalDiscountByItems() {
		return totalDiscountByItems;
	}

	public void setTotalDiscountByItems(BigDecimal totalDiscountByItems) {
		this.totalDiscountByItems = totalDiscountByItems;
	}

	public BigDecimal getTotalProgressiveDiscount() {
		return totalProgressiveDiscount;
	}

	public void setTotalProgressiveDiscount(BigDecimal totalProgressiveDiscount) {
		this.totalProgressiveDiscount = totalProgressiveDiscount;
	}

	public BigDecimal getTotalCouponDiscount() {
		return totalCouponDiscount;
	}

	public void setTotalCouponDiscount(BigDecimal totalCouponDiscount) {
		this.totalCouponDiscount = totalCouponDiscount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public int getCouponDiscountPercentage() {
		return couponDiscountPercentage;
	}

	public void setCouponDiscountPercentage(int couponDiscountPercentage) {
		this.couponDiscountPercentage = couponDiscountPercentage;
	}
}