package contacts.javafx.model;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.fxb.FXTelephone;
import javafx.collections.ObservableList;

public interface IModelPersonne {

	void actualiserListe();

	ObservableList<FXPersonne> getPersonnes();

	void supprimer(FXPersonne personne);

	FXPersonne getPersonneVue();

	void preparerModifier(FXPersonne personne);

	void preparerModifier();

	void ValiderMiseAJour();

	void ajouterTelephone();

	void supprimerTelephone(FXTelephone telephone);

	// Initialisation
	void refresh() throws ExceptionAppli;

}