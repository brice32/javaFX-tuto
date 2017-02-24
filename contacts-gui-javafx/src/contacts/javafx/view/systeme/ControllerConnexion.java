package contacts.javafx.view.systeme;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelConnexion;
import contacts.javafx.model.IModelInfo;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ControllerConnexion implements IController {

	// Champs
	
	private IManagerGui		managerGui;
	private IModelConnexion	modelConnexion;
	private IModelInfo		modelInfo;
	

	// Composants de la vue
	
	@FXML
	private TextField		fieldPseudo;
	@FXML
	private PasswordField	fieldMotDePasse;
	

	// Actions
	
	@FXML
	public void doConnexion()  {
		managerGui.execTask( () -> {
			try {
				modelConnexion.ouvrirSessionUtilisateur();
				modelInfo.titreProperty().set( "Bienvenue" );
				modelInfo.messageProperty().set( "Connexion réussie" );
				managerGui.showView(EnumView.Info);
			} catch (Exception e) {
				Platform.runLater( () -> managerGui.afficherErreur(e) );
			}
		} );
	}
	
	
	// Initialisation du Controller
	
    @Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		
		// Injection  de dépendances
		this.managerGui = managerGui;
		modelConnexion = managerGui.getModel( IModelConnexion.class );
		modelInfo = managerGui.getModel( IModelInfo.class );
		
		// Data binding
		FXCompte compteVue = modelConnexion.getCompteVue();
		fieldPseudo.textProperty().bindBidirectional( compteVue.pseudoProperty() );
		fieldMotDePasse.textProperty().bindBidirectional( compteVue.motDePasseProperty() );
	}
	

}
