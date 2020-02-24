package target.target.dto;

import target.target.model.Product;

public class ProductDTO {

	private String id;
	
	private String description;
	
	private String unitValue;


	public ProductDTO(Product product) {
		this.id = product.getId().toString();
		this.description = product.getDescription();
		this.unitValue = product.getUnitValue().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

}
