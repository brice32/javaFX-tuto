package contacts.javafx;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {

	static ModelPersonne modelPersonne;
	static Pane panePersonneListe;
	static Pane panePersonneForm;
	static Scene scene;
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			modelPersonne = new ModelPersonne();

			BorderPane root = new BorderPane();
			// Scene scene = new Scene(root,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ViewPersonneListe.fxml"));
			panePersonneListe = (Pane) loader.load();

			loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ViewPersonneForm.fxml"));
			panePersonneForm = (Pane) loader.load();

			scene = new Scene(panePersonneListe);
			primaryStage.setScene(scene);
			primaryStage.show();
			// Set person overview into the center of root layout.
			// rootLayout.setCenter(ProjetOverview);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// // Load person overview.
		// FXMLLoader loader = new FXMLLoader();
		// loader.setLocation(MainApp.class.getResource("view/UtilisateurOverview.fxml"));
		// AnchorPane ProjetOverview = (AnchorPane)loader.load();
		//
		// // Set person overview into the center of root layout.
		// rootLayout.setCenter(ProjetOverview);
		//
		// //give the controller access to the mian app
		// UtilisateurOverviewController controller = loader.getController();
		// controller.setMainApp(this);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static ModelPersonne getModelPersonne() {
		return modelPersonne;
	}

	public static void showViewPersonneListe() {
		scene.setRoot(panePersonneListe);
	}

	public static void showViewPersonneForm() {
		scene.setRoot(panePersonneForm);
	}

	public static void afficherMessage( String message, AlertType type ) {
		final Alert alert = new Alert( type );
		alert.initOwner( primaryStage );
		alert.setHeaderText( message );
		alert.showAndWait();
		}

	public static boolean demanderConfirmation( String message ) {
		final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner( primaryStage );
		alert.setHeaderText( message);
		final Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
		}
}
