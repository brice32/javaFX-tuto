package contacts.javafx.fxb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FXPersonne {
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty nom = new SimpleStringProperty();
	private final StringProperty prenom = new SimpleStringProperty();
	private final ObservableList<FXTelephone> telephones = FXCollections.observableArrayList();

	public FXPersonne(){

	}

	public FXPersonne(int id,String nom,String prenom){
		setId(id);
		setNom(nom);
		setPrenom(prenom);
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

	public final StringProperty nomProperty() {
		return this.nom;
	}

	public final String getNom() {
		return this.nomProperty().get();
	}

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}

	public final StringProperty prenomProperty() {
		return this.prenom;
	}

	public final String getPrenom() {
		return this.prenomProperty().get();
	}

	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}

	@Override
	public String toString() {
		return getNom() + " " + getPrenom();
	}

	public ObservableList<FXTelephone> getTelephones(){
		return telephones;
	}

}
