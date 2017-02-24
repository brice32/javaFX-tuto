package contacts.javafx;

import javax.swing.JButton;

import contacts.javafx.fxb.FXPersonne;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ControllerPersonneListe {

	@FXML
	private ListView<FXPersonne> listViewPersonnes;

	private ModelPersonne modelPersonne;

	@FXML
	private Button Modifier;

	@FXML
	private Button Supprimer;

	@FXML
	private void initialize() {

		modelPersonne = Main.getModelPersonne();

	listViewPersonnes.setItems( modelPersonne.getPersonnes() );
	listViewPersonnes.getSelectionModel().getSelectedItems().addListener(
			(ListChangeListener<FXPersonne>) (c) -> {
				if( listViewPersonnes.getSelectionModel().getSelectedItem() != null ){
					Modifier.setDisable(false);
					Supprimer.setDisable(false);
				}
				else{
					Modifier.setDisable(true);
					Supprimer.setDisable(true);
				}
			});
	}

	@FXML
	private void doAjouter(){
		modelPersonne.preparerModifier();
		Main.showViewPersonneForm();
	}

	@FXML
	private void doModifier(){
		ObservableList<FXPersonne> selectedItems = listViewPersonnes.getSelectionModel().getSelectedItems();
		FXPersonne personne = (FXPersonne) selectedItems.get(0);
		modelPersonne.preparerModifier(personne);
		Main.showViewPersonneForm();
	}

	@FXML
	private void doSupprimer(){
		String message;
		ObservableList<FXPersonne> selectedItems = listViewPersonnes.getSelectionModel().getSelectedItems();
		FXPersonne personne = (FXPersonne) selectedItems.get(0);
//		System.out.println("supprimer"+selectedItems);
		message="Est-ce que vous voulez supprimer le utilisateur : "+personne.getNom()+" "+personne.getPrenom()+"?";
		if(Main.demanderConfirmation(message)){
		modelPersonne.supprimer(personne);
		}
	}

	@FXML
	private void doActualiser(){
		modelPersonne.actualiserListe();
	}

	@FXML
	private void gererClic( MouseEvent event ) {
			if ( event.getButton().equals(MouseButton.PRIMARY) ) {
				if (event.getClickCount() == 2) {
					doModifier();
				}
			}
	}
}
