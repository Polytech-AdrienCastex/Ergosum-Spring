package com.epul.ergosum.metier;

public class CatalogueQuantites {
	private String id;
	private String quantiteDistribuee;
	private String quantite;

	public CatalogueQuantites(String id, String quantiteDistribuee,
			String quantite) {
		this.id = id;
		this.quantiteDistribuee = quantiteDistribuee;
		this.quantite = quantite;
	}

	public CatalogueQuantites() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuantiteDistribuee() {
		return quantiteDistribuee;
	}

	public void setQuantiteDistribuee(String quantiteDistribuee) {
		this.quantiteDistribuee = quantiteDistribuee;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}
}
