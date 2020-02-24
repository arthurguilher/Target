package target.target.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import target.target.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{

	@Query("select c from Cart c")
	Cart getCart();
}
