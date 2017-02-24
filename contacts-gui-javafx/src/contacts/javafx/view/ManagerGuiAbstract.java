package contacts.javafx.view;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import contacts.commun.util.ExceptionAnomalie;
import contacts.commun.util.ExceptionAutorisation;
import contacts.commun.util.ExceptionValidation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public abstract class ManagerGuiAbstract implements IManagerGui  {


	// Constants
	private static final KeyCodeCombination KCC_ENTER = new KeyCodeCombination( KeyCode.ENTER );
	private static final KeyCodeCombination KCC_ESCAPE = new KeyCodeCombination( KeyCode.ESCAPE );


	// Logger
	private static final Logger logger = Logger.getLogger( ManagerGuiAbstract.class.getName() );


	// Champs

	private final Set<Object>		models = new HashSet<>();

	private Stage					stage;
	private Scene					scene;
    private BorderPane				panePrincipal;
	private EnumView				viewPrecedente;


	// Initialisations

	@Override
	public void launch() {
		ApplicationJavaFX.launch(this);
	}


	private void start( Stage stage ) throws Exception {

			// Configure la scene
			String path ="systeme/PanePrincipal.fxml" ;
			FXMLLoader loader = new FXMLLoader(getClass().getResource( path ));
			panePrincipal = loader.load();
			IController controller = loader.getController();
			controller.setManagerGui( this );
			scene = new Scene( panePrincipal );

			// Configure le stage
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setMinHeight(300);
			stage.setMinWidth(400);
			stage.setTitle("Gestion de contacts");
			stage.getIcons().add(new Image(getClass().getResource("icone.png").toExternalForm()));
			this.stage = stage;

			// Choisit la vue Ã  afficher
			showView( EnumView.Connexion);

			// Affiche le stage
			stage.show();
	}


	// Actions

	@Override
	public <T> T getModel( Class<T> type ) {
		T model = getModelFromContext(type);
		// Lors de la premiÃ¨re utilisation aprÃ¨s la connexion de l'utilisateur,
		// appelle la mÃ©thode refresh() si elle existe
		if ( ! models.contains(model) ) {
			try {
				Method method = model.getClass().getMethod( "refresh" );
				method.invoke(model);
			} catch (RuntimeException e) {
				throw e;
			} catch (NoSuchMethodException e) {
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			models.add(model);
		}
		return model;
	}

	protected abstract <T> T getModelFromContext( Class<T> type );


	// Affichage des vues

	@Override
	public void showView( EnumView view ) {

		try {

			Pane pane = view.getPane();

			if( pane == null ) {

				// Charge le panneau
				FXMLLoader loader = new FXMLLoader(getClass().getResource( view.getPathn() ));
				pane = loader.load();

				// Injecte les dÃ©pandances
				IController controller = loader.getController();
				controller.setManagerGui( this );

				// Enregistre le panneau dans la vue
				view.setPane( pane );
			}
			// Affiche la vue
			panePrincipal.setCenter(pane);

			// GÃ¨re les boutons par dÃ©faut
			if ( viewPrecedente != null ) {
				if ( viewPrecedente.getRunnableEnter() == null ) {
					viewPrecedente.setRunnableEnter(scene.getAccelerators().get( KCC_ENTER ) );
				}
				if ( viewPrecedente.getRunnableEscape() == null ) {
					viewPrecedente.setRunnableCancel( scene.getAccelerators().get( KCC_ESCAPE ) );
				}
			}
			if ( view.getRunnableEnter() != null ) {
				scene.getAccelerators().put( KCC_ENTER, view.getRunnableEnter() );
			}
			if ( view.getRunnableEscape() != null ) {
				scene.getAccelerators().put( KCC_ESCAPE, view.getRunnableEscape() );
			}
			viewPrecedente = view;

		} catch (Exception e) {
			afficherErreur(e);
		}
	}

	@Override
	public void reinit() {
		for ( EnumView view : EnumView.values() ) {
			view.setPane(null);
		}
		models.clear();
	}

	@Override
	public void close() {
		stage.close();
	}


	// Actions gÃ©nÃ©rales

	@Override
	public void execTask( Runnable runnable ) {

		final EventHandler<InputEvent> inputEventConsumer = (event) ->	event.consume() ;
		stage.addEventFilter(InputEvent.ANY, inputEventConsumer);
		stage.getScene().setCursor( Cursor.WAIT );

		Timeline timeline = new Timeline(  new KeyFrame(
	        Duration.ONE,
            new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
					runnable.run();
		            stage.removeEventFilter(InputEvent.ANY, inputEventConsumer);
		            stage.getScene().setCursor(Cursor.DEFAULT);
	              }
	         }));
		timeline.play();

	}

	@Override
	public void afficherMessage( String message ) {
		final Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initOwner(stage);
		alert.setHeaderText(message);
		alert.showAndWait();
	}

	@Override
	public boolean demanderConfirmation( String message ) {
		final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner( stage );
		alert.setHeaderText( message);
		final Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}


	@Override
	public void afficherErreur( Throwable exception ) {
		afficherErreur( null, exception );
	}

	@Override
	public void afficherErreur( String message ) {
		afficherErreur( message, null );
	}

	@Override
	public void afficherErreur( String message, Throwable exception ) {

		String messageDefaut = null;

		if ( exception != null ) {

			if ( exception instanceof ExceptionValidation ) {
				messageDefaut = exception.getMessage();
			} else if ( exception instanceof ExceptionAutorisation ) {
				messageDefaut = "Action non autoriÃ©sÃ© !";
				logger.log(Level.FINEST, exception.getMessage(), exception );
			} else if ( exception.getClass().getName().equals( "javax.ejb.EJBAccessException") ) {
				messageDefaut = "EJB : Action non autoriÃ©sÃ© !";
				logger.log(Level.FINEST, exception.getMessage(), exception );
			} else if ( exception instanceof ExceptionAnomalie ) {
				messageDefaut = "Echec du traitement demandÃ©";
				logger.log(Level.FINEST, exception.getMessage(), exception );
			} else if ( exception.getClass().getName().equals( "javax.ejb.EJBException")
					|| exception.getClass().getName().equals( "javax.ejb.EJBTransactionRolledbackException") ) {
				messageDefaut = "EJB : Echec du traitement demandÃ©";
				logger.log(Level.FINEST, exception.getMessage(), exception );
			} else if ( exception instanceof RuntimeException ) {
				logger.log(Level.SEVERE, exception.getMessage(), exception );
			} else {
				messageDefaut = exception.getMessage();
				logger.log(Level.SEVERE, exception.getMessage(), exception );
			}

			if (message == null ) {
				if ( messageDefaut == null ) {
					message = "Ecec du traitement demandÃ©.";
				} else {
					message = messageDefaut;
				}
			}
		}

		final Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initOwner(stage);
		alert.setHeaderText( message );
		alert.showAndWait();
	}


	// Classes auxiliaires


	public static class ApplicationJavaFX extends Application {

		private static ManagerGuiAbstract	managerGui;

		public static void launch( ManagerGuiAbstract managerGui ) {
			ApplicationJavaFX.managerGui = managerGui;
			launch();
		}

		@Override
		public void start(Stage stage) throws Exception {
			managerGui.start(stage);
		}
	}


	public static interface RunnableWithException {
		void run() throws Exception;
	}

}
