package model;

public enum SaleType {

	Comida("Comida", 5), 
	Material_educativo("Material_educativo", 2), 
	Artesanias("Artesanias", 7), 
	Otros("Otros", 10);

	private String title;
	private int iva;

	private SaleType(String title, int iva) {
		this.title = title;
		this.iva = iva;
	}

	public int getIva() {
		return iva;
	}

	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           