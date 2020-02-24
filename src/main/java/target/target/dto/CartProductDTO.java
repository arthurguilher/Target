package target.target.dto;

import java.math.BigDecimal;

import target.target.model.Product;

public class CartProductDTO {
	
	private Long productId;
	
	private String productDescription;
	
	private Long productQuantity;
	
	private BigDecimal productValue = BigDecimal.ZERO;
	
	private BigDecimal productValueWithDiscount = BigDecimal.ZERO;

	private BigDecimal productUnitValue = BigDecimal.ZERO;
	
	private BigDecimal productUnitValueWithDiscount = BigDecimal.ZERO;
	
	public CartProductDTO(Product product) {
		this.productId = product.getId();
		this.productDescription = product.getDescription();
		this.productUnitValue = product.getUnitValue();
		this.productUnitValueWithDiscount = product.getUnitValue();
		this.productQuantity = 1L;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}
	public BigDecimal getProductValue() {
		return productValue;
	}
	public void setProductValue(BigDecimal productValue) {
		this.productValue = productValue;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getProductValueWithDiscount() {
		return productValueWithDiscount;
	}
	public void setProductValueWithDiscount(BigDecimal productValueWithDiscount) {
		this.productValueWithDiscount = productValueWithDiscount;
	}
	public BigDecimal getProductUnitValueWithDiscount() {
		return productUnitValueWithDiscount;
	}
	public void setProductUnitValueWithDiscount(BigDecimal productUnitValueWithDiscount) {
		this.productUnitValueWithDiscount = productUnitValueWithDiscount;
	}
	public BigDecimal getProductUnitValue() {
		return productUnitValue;
	}
	public void setProductUnitValue(BigDecimal productUnitValue) {
		this.productUnitValue = productUnitValue;
	}

}
