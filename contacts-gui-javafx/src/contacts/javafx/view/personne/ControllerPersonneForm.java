package contacts.javafx.view.personne;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.fxb.FXTelephone;
import contacts.gui.EditingCell;
import contacts.javafx.model.IModelPersonne;
import contacts.javafx.model.mock.ModelPersonne;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sun.security.tools.keytool.Main;

public class ControllerPersonneForm implements  IController{


	@FXML
	private TextField	textFieldId;
	@FXML
	private TextField	textFieldNom;
	@FXML
	private TextField	textFieldPrenom;

	IModelPersonne modelPersonne;

	@FXML
	private TableView<FXTelephone> tableViewTelphones;

	@FXML
    private  TableColumn<FXTelephone, Number> columnId;

	@FXML
    private  TableColumn<FXTelephone, String> columnLibelle;

	@FXML
    private  TableColumn<FXTelephone, String> columnNumero;

	private IManagerGui managerGui;

//	@FXML
//	private void initialize(){
//
//
//
//	}

	@FXML
	private void doValider(){

		try {
			modelPersonne.ValiderMiseAJour();
			managerGui.showView(EnumView.PersonneListe);
		} catch (ExceptionAppli e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			managerGui.afficherErreur(e);
		}
//		Main.showViewPersonneListe();


	}
	@FXML
	private void doAnnuler(){
//		Main.showViewPersonneListe();
		managerGui.showView( EnumView.PersonneListe );

	}

	@FXML
	private void doAjouterTelephone(){
		modelPersonne.ajouterTelephone();
	}

	@FXML
	private void doSupprimerTelephone(){
//		modelPersonne.supprimerTelephone();
		 ObservableList<FXTelephone> selectedItems = tableViewTelphones.getSelectionModel().getSelectedItems();
		 FXTelephone telephone = selectedItems.get(0);
		 modelPersonne.supprimerTelephone(telephone);
	}

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		// TODO Auto-generated method stub
		this.managerGui = managerGui ;
//		modelPersonne = Main.getModelPersonne();
		modelPersonne = managerGui.getModel( IModelPersonne.class );
		FXPersonne personneVue = modelPersonne.getPersonneVue();
		textFieldId.textProperty().bind( Bindings.convert( personneVue.idProperty() ) );
		textFieldNom.textProperty().bindBidirectional( personneVue.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( personneVue.prenomProperty() );
//		tableViewTelphones.setItems(Main.getModelPersonne().getPersonneVue().getTelephones()) ;
		tableViewTelphones.setItems(modelPersonne.getPersonneVue().getTelephones()) ;
		columnId.setCellValueFactory( cellData -> cellData.getValue().idProperty() );
		columnLibelle.setCellValueFactory( cellData -> cellData.getValue().libelleProperty() );
		columnNumero.setCellValueFactory( cellData -> cellData.getValue().numeroProperty() );
		columnLibelle.setCellFactory( column -> new EditingCell<>() );
		columnNumero.setCellFactory( column -> new EditingCell<>() );
	}

}
