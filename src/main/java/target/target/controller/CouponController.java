package target.target.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import target.target.dto.CouponDTO;
import target.target.model.Coupon;
import target.target.repository.CouponRepository;

@Controller
@RequestMapping(path = "/coupon")
public class CouponController {

	@Autowired
	private CouponRepository couponRepository;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<?> add(@RequestBody  Coupon coupon) {
		couponRepository.save(coupon);
		return ResponseEntity.ok().body("Inserido com sucesso");
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<CouponDTO>> list() {
		List<CouponDTO> coupons = ((List<Coupon>) couponRepository.findAll()).stream().map(coupon -> {
			return new CouponDTO(coupon);
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(coupons);
	}
	
	   @DeleteMapping("/delete/{id}")
	   public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		   couponRepository.deleteById(id);
	      return ResponseEntity.ok().body("Cupom deletado com sucesso.");
	   }
}
