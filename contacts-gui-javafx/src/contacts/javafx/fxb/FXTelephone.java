package contacts.javafx.fxb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXTelephone {

	private final IntegerProperty id = new SimpleIntegerProperty();
//	private final IntegerProperty idPersonne = new SimpleIntegerProperty();
	private final StringProperty libelle = new SimpleStringProperty();
	private final StringProperty numero = new SimpleStringProperty();


	public FXTelephone(FXTelephone telephone){
		setId(telephone.getId());
		setLibelle(telephone.getLibelle());
		setNumero(telephone.getNumero());
	}

	public FXTelephone(){

	}

//	public final IntegerProperty idTelephoneProperty() {
//		return this.idTelephone;
//	}
//
//
//	public final int getIdTelephone() {
//		return this.idTelephoneProperty().get();
//	}
//
//
//	public final void setIdTelephone(final int idTelephone) {
//		this.idTelephoneProperty().set(idTelephone);
//	}
//
//
//	public final IntegerProperty idPersonneProperty() {
//		return this.idPersonne;
//	}
//
//
//	public final int getIdPersonne() {
//		return this.idPersonneProperty().get();
//	}
//
//
//	public final void setIdPersonne(final int idPersonne) {
//		this.idPersonneProperty().set(idPersonne);
//	}


	public final StringProperty libelleProperty() {
		return this.libelle;
	}


	public final String getLibelle() {
		return this.libelleProperty().get();
	}


	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}


	public final StringProperty numeroProperty() {
		return this.numero;
	}


	public final String getNumero() {
		return this.numeroProperty().get();
	}


	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}


	public final int getId() {
		return this.idProperty().get();
	}


	public final void setId(final int id) {
		this.idProperty().set(id);
	}



//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getLibelle() {
//		return libelle;
//	}
//
//	public void setLibelle(String libelle) {
//		this.libelle = libelle;
//	}
//
//	public String getNumero() {
//		return numero;
//	}
//
//	public void setNumero(String numero) {
//		this.numero = numero;
//	}
//
//	public IntegerProperty idProperty(){
//		return new SimpleIntegerProperty(id);
//	}
//
//	public StringProperty libelleProperty(){
//		return new SimpleStringProperty(libelle);
//	}
//
//	public StringProperty numeroProperty(){
//		return new SimpleStringProperty(numero);
//	}
}
