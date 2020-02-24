package target.target.dto;

import target.target.model.Coupon;

public class CouponDTO {

	private String id;
	
	private String code;
	
	private String discountPercentage;

	public CouponDTO(Coupon coupon) {
		this.id = coupon.getId().toString();
		this.code = coupon.getCode();
		this.discountPercentage = coupon.getDiscountPercentage().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}



}
