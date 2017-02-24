package contacts.javafx.view.systeme;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.model.IModelInfo;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerInfo implements IController {
	
	
	// Composants de la vue
	
	@FXML
	private Label		labelTitre;
	@FXML
	private Label		labelMessage;

	
	// Injecteurs
	
	@Override
	public void setManagerGui( IManagerGui managerGui ) throws ExceptionAppli {
		
		// Injection  de dépendances
		IModelInfo modelInfo = managerGui.getModel( IModelInfo.class );
		
		// Data binding
		labelTitre.textProperty().bind( modelInfo.titreProperty() );
		labelMessage.textProperty().bind( modelInfo.messageProperty() );
	}

}
