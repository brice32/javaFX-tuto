package projet_test;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import projet_test.model.Utilisateur;
import projet_test.model.UtilisateurConnect;
import projet_test.utilisation.DateUtilisation;


public class UtilisateurOverviewController {
	@FXML
	private TableView<Utilisateur> utilisateurTable;
	@FXML
    private TableColumn<Utilisateur, String> firstNameColumn;
	@FXML
	private TableColumn<Utilisateur, String> lastNameColumn;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;
	@FXML
	private MainApp mainApp;



	public UtilisateurOverviewController() {

	}
	@FXML
	private void initialize() {
	        // Initialize the person table with the two columns.
	        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

	        showUtilisateurDetails(null);

	        utilisateurTable.getSelectionModel().selectedItemProperty().addListener(
	        		(observable,oldValue,newValue) -> showUtilisateurDetails(newValue));
		}

	public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        utilisateurTable.setItems(mainApp.getUtilisateurData());
	    }

	private void showUtilisateurDetails(Utilisateur utilisateur){
		if(utilisateur !=null){
			firstNameLabel.setText(utilisateur.getFirstName());
			lastNameLabel.setText(utilisateur.getLastName());
			streetLabel.setText(utilisateur.getStreet());
		    postalCodeLabel.setText(Integer.toString(utilisateur.getPostalCode()));
		    cityLabel.setText(utilisateur.getCity());
		    birthdayLabel.setText(DateUtilisation.format(utilisateur.getBirthday()));
		}
		else{
	        // Person is null, remove all the text.
	        firstNameLabel.setText("");
	        lastNameLabel.setText("");
	        streetLabel.setText("");
	        postalCodeLabel.setText("");
	        cityLabel.setText("");
	        birthdayLabel.setText("");
	    }

	}

	@FXML
	private void handleDeleteUtilisateur(){
		int selectedIndex = utilisateurTable.getSelectionModel().getSelectedIndex();
		Utilisateur selectedUtilisateur = utilisateurTable.getSelectionModel().getSelectedItem();
		if(selectedIndex>=0){
		UtilisateurConnect.utlisateurDelete(mainApp.connection, selectedUtilisateur);
		utilisateurTable.getItems().remove(selectedIndex);
		}else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("I have a great message for you!");

			alert.showAndWait();
		}
//		System.out.println(selectedIndex);
	}

	@FXML
	private void handleNewUtilisateur() {
	    Utilisateur tempUtilisateur = new Utilisateur();
	    boolean okClicked = mainApp.showUtilisateurEditDialog(tempUtilisateur);
	    if (okClicked) {
	        mainApp.getUtilisateurData().add(tempUtilisateur);
	        UtilisateurConnect.utilisateurCreate(mainApp.connection, tempUtilisateur);
	        this.handleReloadUtilisateurTable();
	    }
	}

	@FXML
	private void handleEditUtilisateur() {
	    Utilisateur selectedUtilisateur = utilisateurTable.getSelectionModel().getSelectedItem();
	    if (selectedUtilisateur != null) {
	        boolean okClicked = mainApp.showUtilisateurEditDialog(selectedUtilisateur);
	        if (okClicked) {
	            showUtilisateurDetails(selectedUtilisateur);
	            UtilisateurConnect.utilisateurModifier(mainApp.connection, selectedUtilisateur);
	        }

	    } else {
	        // Nothing selected.

	        Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");
			alert.showAndWait();
	    }
	}

	@FXML
	private void handleReloadUtilisateurTable(){
		utilisateurTable.setItems(UtilisateurConnect.listUtilisateur(mainApp.connection));
	}
}
