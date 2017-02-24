package contacts.javafx.model.standard;

import java.util.logging.Level;
import java.util.logging.Logger;

import contacts.commun.dto.DtoCompte;
import contacts.commun.service.IServiceConnexion;
import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.ExceptionValidation;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelConnexion;
import contacts.javafx.util.mapper.IMapperDtoFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class ModelConnexion implements IModelConnexion {
	
	// Logger
	private static final Logger logger = Logger.getLogger( ModelConnexion.class.getName() );
	
	
	// Données observables 
	
	// Vue connexion
	private final FXCompte		compteVue = new FXCompte();

	// Compte connecté
	private final ObjectProperty<FXCompte>	compteConnecte = new SimpleObjectProperty<>();

	
	// Autres champs
	private IMapperDtoFX		mapper;
	private IServiceConnexion	serviceConnexion;
	

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
	
	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

	public void setServiceConnexion(IServiceConnexion serviceConnexion) {
		this.serviceConnexion = serviceConnexion;

		compteVue.pseudoProperty().set( "geek" );
		compteVue.motDePasseProperty().set( "geek" );
	}
	
	
	// Actions


	@Override
	public void ouvrirSessionUtilisateur() throws ExceptionAppli {

		
		DtoCompte dto = serviceConnexion.sessionUtilisateurOuvrir(
				compteVue.pseudoProperty().get(), compteVue.motDePasseProperty().get() );
		
		// Message de log
		String logMessage;
		if( dto == null ) {
			logMessage = "Pseudo ou mot de passe invalide : " + compteVue.pseudoProperty().get() + " / " + compteVue.motDePasseProperty().get();
		} else {
			logMessage = "\n    Utilisateur connecté : " + dto.getId() +  " " + dto.getPseudo();
			logMessage += "\n    Roles :";
			for( String role : dto.getRoles() ) {
				logMessage += " " + role;
			}
		}
		logger.log( Level.CONFIG, logMessage );
		
		if( dto == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		} else {
			compteConnecte.set( mapper.map( dto ) );
		}
	}
	

	@Override
	public void fermerSessionUtilisateur() throws ExceptionAppli  {
		serviceConnexion.sessionUtilisateurFermer();
		compteConnecte.set( null );
	}

}
