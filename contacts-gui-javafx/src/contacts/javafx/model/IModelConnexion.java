package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXCompte;
import javafx.beans.property.ObjectProperty;

public interface IModelConnexion {

	FXCompte getCompteVue();

	ObjectProperty<FXCompte> compteConnecteProperty();

	FXCompte getCompteConnecte();

	void ouvrirSessionUtilisateur() throws ExceptionAppli;

	void fermerSessionUtilisateur() throws ExceptionAppli;

}