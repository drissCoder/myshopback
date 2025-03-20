package ma.altenshop.dto;

public class PanierItemRequest {

	
	private Integer idProduct;
	private Integer idPanier;
	private Integer quantity;
	
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getIdPanier() {
		return idPanier;
	}
	public void setIdPanier(Integer idPanier) {
		this.idPanier = idPanier;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
