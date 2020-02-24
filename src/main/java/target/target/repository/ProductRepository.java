package target.target.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import target.target.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Modifying
	@Transactional
	@Query("delete from Product p where p.id=:id")
	void deleteById(@Param("id") Long id);
	
	@Query("select p from Product p where p.id=:id")
	Product findById(@Param("id") Long id);
}
