package contacts.javafx;

import contacts.gui.EditingCell;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.fxb.FXTelephone;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerPersonneForm {


	@FXML
	private TextField	textFieldId;
	@FXML
	private TextField	textFieldNom;
	@FXML
	private TextField	textFieldPrenom;

	ModelPersonne modelPersonne;

	@FXML
	private TableView<FXTelephone> tableViewTelphones;

	@FXML
    private  TableColumn<FXTelephone, Number> columnId;

	@FXML
    private  TableColumn<FXTelephone, String> columnLibelle;

	@FXML
    private  TableColumn<FXTelephone, String> columnNumero;

	@FXML
	private void initialize(){
		modelPersonne = Main.getModelPersonne();
		FXPersonne personneVue = modelPersonne.getPersonneVue();
		textFieldId.textProperty().bind( Bindings.convert( personneVue.idProperty() ) );
		textFieldNom.textProperty().bindBidirectional( personneVue.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( personneVue.prenomProperty() );
		tableViewTelphones.setItems(Main.getModelPersonne().getPersonneVue().getTelephones()) ;
		columnId.setCellValueFactory( cellData -> cellData.getValue().idProperty() );
		columnLibelle.setCellValueFactory( cellData -> cellData.getValue().libelleProperty() );
		columnNumero.setCellValueFactory( cellData -> cellData.getValue().numeroProperty() );
		columnLibelle.setCellFactory( column -> new EditingCell<>() );
		columnNumero.setCellFactory( column -> new EditingCell<>() );


	}

	@FXML
	private void doValider(){
		modelPersonne.ValiderMiseAJour();
		Main.showViewPersonneListe();
	}
	@FXML
	private void doAnnuler(){
		Main.showViewPersonneListe();
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

}
