package target.target.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import target.target.model.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Integer>{

	@Modifying
	@Transactional
	@Query("delete from Coupon c where c.id=:id")
	void deleteById(@Param("id") Long id);
	
	@Query("select c from Coupon c where c.code=:code")
	Coupon findByCode(@Param("code") String code);
}
