package ma.altenshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import ma.altenshop.dto.PanierItemRequest;
import ma.altenshop.entities.Panier;
import ma.altenshop.entities.Product;
import ma.altenshop.repository.PanierRepository;
import ma.altenshop.repository.ProductRepository;


@Service
public class PanierService {

	
	
	private PanierRepository panierRepository;
	
	private ProductRepository productRepository;
	
	public PanierService(PanierRepository panierRepository, ProductRepository productRepository) {
		this.panierRepository = panierRepository;
		this.productRepository = productRepository;
	}


	@Transactional
	public Panier ajouterProduitToPanier(PanierItemRequest panierItemRequest) throws Exception {
		
		Panier panier = null;
		Product product = null;
		if(panierItemRequest.getIdProduct() != null) {
			product = productRepository.findById(panierItemRequest.getIdProduct())
								.orElseThrow(() -> new Exception("Product not exists"));
		}
	
		if(panierItemRequest.getIdPanier() == null) {
			panier = new Panier();
		}else {
			panier = panierRepository.findById(panierItemRequest.getIdPanier())
									.orElseThrow(() -> new Exception("Panier not exists"));
		}
		panier.getProducts().add(product);
		panier = panierRepository.saveAndFlush(panier);
		
		return panier;
	}
	
		
	@Transactional
	public void supprimerPanier(PanierItemRequest panierItemRequest) throws Exception {
		
		Panier panier = null;
		productRepository.deleteById(panierItemRequest.getIdProduct());
		panier = panierRepository.findById(panierItemRequest.getIdPanier())
					.orElseThrow(() -> new Exception("Panier not exists"));
		if(panier.getProducts().isEmpty())
				panierRepository.deleteById(panierItemRequest.getIdPanier());
	}
	
	@Transactional
	public Panier increaseDecreaseQantity(@RequestBody PanierItemRequest panierItemRequest) throws Exception {
		
		Panier panier = null;
			
		panier = panierRepository.findById(panierItemRequest.getIdPanier())
					.orElseThrow(() -> new Exception("Panier not exists"));
			
		panier.getProducts().stream().map((product) -> {
		int quantity = product.getQuantity() + panierItemRequest.getQuantity();
		if(product.getId() == panierItemRequest.getIdProduct() && quantity > 0) {
				product.setQuantity(quantity);
		}
			return product;
		});
		
		return panierRepository.saveAndFlush(panier);
		
	}
}
