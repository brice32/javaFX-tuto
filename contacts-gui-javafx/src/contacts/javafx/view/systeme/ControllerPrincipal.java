package contacts.javafx.view.systeme;

import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.Roles;
import contacts.javafx.model.IModelConnexion;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class ControllerPrincipal implements IController  {


	// Composants de la vue

	@FXML
	private MenuItem	menuItemSeDeconnecter;
	@FXML
	private Menu 		menuDonnees;
	@FXML
	private MenuItem	menuItemComptes;


	// Autres champs

	private IManagerGui		managerGui;
	private IModelConnexion	modelConnexion;


	// Actions

	@FXML
	public void doSeDeconnecter() {
		try {
			modelConnexion.fermerSessionUtilisateur();
			managerGui.reinit();
			setManagerGui(managerGui);
			managerGui.showView( EnumView.Connexion );
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		}
	}

	@FXML
	public void doQuitter() {
		managerGui.close();
	}

	@FXML
	public void doAfficherListeComptes() {
		managerGui.showView( EnumView.CompteListe );;

	}

	@FXML
	public void doAfficherListePersonnes(){
		managerGui.showView( EnumView.PersonneListe );
	}

	// Initialisation du Controller

    @Override
	public void setManagerGui( IManagerGui managerGui ) throws ExceptionAppli {

		// Injection  de dépendances
		this.managerGui = managerGui;
		modelConnexion = managerGui.getModel( IModelConnexion.class );

		// Le changement du compte connecté modifie automatiquement le menu
		modelConnexion.compteConnecteProperty().addListener(
				( ov, oldValue, newValue) -> {
					configurerMenu();
				}
			);

		// Adapte le menu
		configurerMenu();
	}


	// Méthodes auxiliaires

	private void configurerMenu() {

		menuItemSeDeconnecter.setDisable(true);

		menuDonnees.setVisible(false);
		menuItemComptes.setVisible(false);

		if( modelConnexion.getCompteConnecte() != null ) {
			menuItemSeDeconnecter.setDisable(false);
			if( modelConnexion.getCompteConnecte().isInRole( Roles.UTILISATEUR) ) {
				menuDonnees.setVisible(true);
			}
			if( modelConnexion.getCompteConnecte().isInRole( Roles.ADMINISTRATEUR ) ) {
				menuDonnees.setVisible(true);
				menuItemComptes.setVisible(true);
			}
		}
	}

}
