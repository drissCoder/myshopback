package ma.altenshop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		}else {
			throw new Exception("Product does not exists");
		}
	
		panier = panierRepository.findById(panierItemRequest.getIdPanier())
									.orElseThrow(() -> new Exception("Panier not exists"));
		


		product.setPanier(panier);

		return panier;
	}
	
		
	@Transactional
	public void supprimerPanier(PanierItemRequest panierItemRequest) throws Exception {
		
		Panier panier = null;
		panier = panierRepository.findById(panierItemRequest.getIdPanier())
					.orElseThrow(() -> new Exception("Panier not exists"));
		if(panier.getProducts() != null && !panier.getProducts().isEmpty()) {
			for(Product p : panier.getProducts()) {
				if(p.getId() == panierItemRequest.getIdProduct())
					p.setPanier(null);
			}
		}
		if(panier.getProducts().isEmpty())
				panierRepository.deleteById(panierItemRequest.getIdPanier());
	}
	
	@Transactional
	public Panier increaseDecreaseQantity(@RequestBody PanierItemRequest panierItemRequest) throws Exception {
		
		Panier panier = null;
			
		panier = panierRepository.findById(panierItemRequest.getIdPanier())
					.orElseThrow(() -> new Exception("Panier not exists"));
		List<Product> products = productRepository.findByPanier(panier);
		if(products.isEmpty())
			throw new Exception("Panier vide, le produit n'est pas dans le panier");
		
		productRepository.saveAll(products.stream().map(p -> {
			int quantity = p.getQuantity() + panierItemRequest.getQuantity();
			if(p.getId() == panierItemRequest.getIdProduct() && quantity > 0) {
					p.setQuantity(quantity);
			}
				return p;
			}).toList());
		
		return panier;
		
	}
	
	@Transactional
	public Panier creerPanier() throws Exception {
		
		return panierRepository.save(new Panier());
	}
	
	@Transactional
	public Panier getPanier(Integer idPanier) throws Exception {
		
		return panierRepository.findById(idPanier)
				.orElseThrow(() -> new Exception("Panier not exists"));
	}
}
	
