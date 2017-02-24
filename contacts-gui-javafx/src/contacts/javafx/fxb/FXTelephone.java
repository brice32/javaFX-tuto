package contacts.javafx.fxb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXTelephone {

	int id;
	String libelle;
	String numero;

	public FXTelephone(FXTelephone telephone){
		id=telephone.getId();
		libelle=telephone.getLibelle();
		numero=telephone.getNumero();
	}

	public FXTelephone(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public IntegerProperty idProperty(){
		return new SimpleIntegerProperty(id);
	}

	public StringProperty libelleProperty(){
		return new SimpleStringProperty(libelle);
	}

	public StringProperty numeroProperty(){
		return new SimpleStringProperty(numero);
	}
}
