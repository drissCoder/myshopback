package ma.altenshop.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.altenshop.dto.PanierItemRequest;
import ma.altenshop.service.PanierService;

@RestController
@RequestMapping("/panier")
//@Service
public class PanierController {

	
	
	private PanierService panierService;
		
	public PanierController(PanierService panierService) {
		this.panierService = panierService;
	}


	//@Transactional
	@PostMapping("/add-product-to-panier")
	public ResponseEntity<?> ajouterProduitToPanier(@RequestBody PanierItemRequest panierItemRequest){
		
		try {
			return ResponseEntity.ok(panierService.ajouterProduitToPanier(panierItemRequest));
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
	
		
	@DeleteMapping("/supprimer-product-from-panier")
	public ResponseEntity<?> supprimerPanier(@RequestBody PanierItemRequest panierItemRequest){
			
		try {
			panierService.supprimerPanier(panierItemRequest);
			return ResponseEntity.noContent().build();	
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
	
	@PostMapping("/increase-decrease-qantity")
	public ResponseEntity<?> increaseDecreaseQantity(@RequestBody PanierItemRequest panierItemRequest){
		
		try {
			return ResponseEntity.ok(panierService.increaseDecreaseQantity(panierItemRequest));
		}catch(Exception e) {
			ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
			return ResponseEntity.of(problemDetail).build();
		}
	}
}
