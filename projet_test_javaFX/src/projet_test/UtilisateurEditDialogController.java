package projet_test;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import projet_test.model.Utilisateur;
import projet_test.utilisation.DateUtilisation;

public class UtilisateurEditDialogController {

	 @FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    @FXML
	    private TextField streetField;
	    @FXML
	    private TextField postalCodeField;
	    @FXML
	    private TextField cityField;
	    @FXML
	    private TextField birthdayField;


	    private Stage dialogStage;
	    private Utilisateur utilisateur;
	    private boolean okClicked = false;

	    @FXML
	    private void initialize() {

	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    public void setUtilisateur(Utilisateur utilisateur){
	    	this.utilisateur = utilisateur;

	    	firstNameField.setText(utilisateur.getFirstName());
	        lastNameField.setText(utilisateur.getLastName());
	        streetField.setText(utilisateur.getStreet());
	        postalCodeField.setText(Integer.toString(utilisateur.getPostalCode()));
	        cityField.setText(utilisateur.getCity());
	        birthdayField.setText(DateUtilisation.format(utilisateur.getBirthday()));
	        birthdayField.setPromptText("dd.mm.yyyy");
	    }

	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	            utilisateur.setFirstName(firstNameField.getText());
	            utilisateur.setLastName(lastNameField.getText());
	            utilisateur.setStreet(streetField.getText());
	            utilisateur.setPostalCode(Integer.parseInt(postalCodeField.getText()));
	            utilisateur.setCity(cityField.getText());
	            utilisateur.setBirthday(DateUtilisation.parse(birthdayField.getText()));

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n";
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n";
	        }
	        if (streetField.getText() == null || streetField.getText().length() == 0) {
	            errorMessage += "No valid street!\n";
	        }

	        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
	            errorMessage += "No valid postal code!\n";
	        } else {
	            // try to parse the postal code into an int.
	            try {
	                Integer.parseInt(postalCodeField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid postal code (must be an integer)!\n";
	            }
	        }

	        if (cityField.getText() == null || cityField.getText().length() == 0) {
	            errorMessage += "No valid city!\n";
	        }

	        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
	            errorMessage += "No valid birthday!\n";
	        } else {
	            if (!DateUtilisation.validDate(birthdayField.getText())) {
	                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
	            }
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	        	Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("In");
				alert.setHeaderText(null);
				alert.setContentText(errorMessage);

				alert.showAndWait();
	            return false;
	        }
	    }
}
