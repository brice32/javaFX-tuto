package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXCompte;
import javafx.collections.ObservableList;

public interface IModelCompte {

	ObservableList<FXCompte> getComptes();

	FXCompte getCompteVue();
	
	void actualiserListe() throws ExceptionAppli;

	void preparerAjouter();

	void preparerModifier(FXCompte compte);

	void validerMiseAJour() throws ExceptionAppli;

	void supprimer(FXCompte compte) throws ExceptionAppli;

}