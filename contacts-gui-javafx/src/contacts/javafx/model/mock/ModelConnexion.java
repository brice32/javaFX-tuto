package contacts.javafx.model.mock;

import java.util.logging.Level;
import java.util.logging.Logger;

import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.ExceptionValidation;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.model.IModelConnexion;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class ModelConnexion implements IModelConnexion {


	// Logger
	private static final Logger logger = Logger.getLogger( ModelConnexion.class.getName() );


	// Données observables
	private final FXCompte         compteVue = new FXCompte();

	// Compte connecté
	private final ObjectProperty<FXCompte>	compteConnecte = new SimpleObjectProperty<>();


	// Autres champs
	private IModelCompte		modelCompte;


	// Getters

	@Override
	public FXCompte getCompteVue() {
		return compteVue;
	}

	@Override
	public ObjectProperty<FXCompte> compteConnecteProperty() {
		return compteConnecte;
	}

	@Override
	public FXCompte getCompteConnecte() {
		return compteConnecte.get();
	}


	// Injecteurs

	public void setModelCompte(IModelCompte modelCompte) {
		this.modelCompte = modelCompte;

		compteVue.pseudoProperty().set( "geek" );
		compteVue.motDePasseProperty().set( "geek" );
	}


	// Actions


	@Override
	public void ouvrirSessionUtilisateur() throws ExceptionAppli {

		modelCompte.actualiserListe();
		compteConnecte.set( null ) ;
		for ( FXCompte compte : modelCompte.getComptes() ) {
			if ( compte.getPseudo().equals(compteVue.getPseudo() )
					&& compte.getMotDePasse().equals( compteVue.getMotDePasse()) ) {
				compteConnecte.set( compte ) ;
			}
		}

		// Message de log
		String logMessage;
		if( compteConnecte.get() == null ) {
			logMessage = "Pseudo ou mot de passe invalide : " + compteVue.pseudoProperty().get() + " / " + compteVue.motDePasseProperty().get();
		} else {
			logMessage = "\n    Utilisateur connecté : " + compteConnecte.get().getId() +  " " + compteConnecte.get().getPseudo();
			logMessage += "\n    Roles :";
			for( String role : compteConnecte.get().getRoles() ) {
				logMessage += " " + role;
			}
		}
		logger.log( Level.CONFIG, logMessage );

		if( compteConnecte.get() == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		}
	}


	@Override
	public void fermerSessionUtilisateur() throws ExceptionAppli  {
		compteConnecte.set( null );
	}

}
