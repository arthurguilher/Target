package target.target.util;

import java.math.BigDecimal;

public class GeneralUtils {
	
	public static BigDecimal applyDiscount(BigDecimal valueToApply, int discount) {
		BigDecimal discountValue = valueToApply.multiply(BigDecimal.valueOf(discount)).divide(new BigDecimal(100)); 
		return valueToApply.subtract(discountValue);
	}
}
