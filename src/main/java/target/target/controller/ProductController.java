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

import target.target.dto.ProductDTO;
import target.target.model.Product;
import target.target.repository.ProductRepository;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;


	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<?> add(@RequestBody Product product) {
		productRepository.save(product);
		return ResponseEntity.ok().body("Inserido com sucesso");
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<ProductDTO>> list() {
		List<ProductDTO> products = ((List<Product>) productRepository.findAll()).stream().map(product -> {
			return new ProductDTO(product);
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(products);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok().body("Produto deletado com sucesso.");
	}
	
}
