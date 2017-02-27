package contacts.javafx.view.personne;

import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.model.IModelPersonne;
import contacts.javafx.model.mock.ModelPersonne;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ControllerPersonneListe implements IController {

	@FXML
	private ListView<FXPersonne> listViewPersonnes;

	private IModelPersonne modelPersonne;

	@FXML
	private Button Modifier;

	@FXML
	private Button Supprimer;

	private IManagerGui managerGui;

	@FXML
	private void initialize() {

		// modelPersonne = Main.getModelPersonne();

	}

	@FXML
	private void doAjouter() {
		modelPersonne.preparerModifier();
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.PersonneForm);
	}

	@FXML
	private void doModifier() {
		ObservableList<FXPersonne> selectedItems = listViewPersonnes.getSelectionModel().getSelectedItems();
		FXPersonne personne = (FXPersonne) selectedItems.get(0);
		modelPersonne.preparerModifier(personne);
		// Main.showViewPersonneForm();
		managerGui.showView(EnumView.PersonneForm);
	}

	@FXML
	private void doSupprimer() {
		String message;
		ObservableList<FXPersonne> selectedItems = listViewPersonnes.getSelectionModel().getSelectedItems();
		FXPersonne personne = (FXPersonne) selectedItems.get(0);
		// System.out.println("supprimer"+selectedItems);
		message = "Est-ce que vous voulez supprimer le utilisateur : " + personne.getNom() + " " + personne.getPrenom()
				+ "?";
		// if(Main.demanderConfirmation(message)){
		if (managerGui.demanderConfirmation(message)) {
			try {
				modelPersonne.supprimer(personne);
			} catch (ExceptionAppli e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				managerGui.afficherErreur(e);
			}
		}
	}

	@FXML
	private void doActualiser() {
		try {
			modelPersonne.actualiserListe();
		} catch (ExceptionAppli e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			managerGui.afficherErreur(e);
		}
	}

	@FXML
	private void gererClic(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				doModifier();
			}
		}
	}

	@Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {
		this.managerGui = managerGui;
		modelPersonne = managerGui.getModel(IModelPersonne.class);

		listViewPersonnes.setItems(modelPersonne.getPersonnes());
		listViewPersonnes.getSelectionModel().getSelectedItems().addListener((ListChangeListener<FXPersonne>) (c) -> {
			if (listViewPersonnes.getSelectionModel().getSelectedItem() != null) {
				Modifier.setDisable(false);
				Supprimer.setDisable(false);
			} else {
				Modifier.setDisable(true);
				Supprimer.setDisable(true);
			}
		});
	}
}
