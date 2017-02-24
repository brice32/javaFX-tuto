package contacts.javafx.view;

public interface IManagerGui {

	void launch();

	<T> T getModel(Class<T> type);

	void showView(EnumView view);

	void reinit();

	void close();

	void execTask(Runnable runnable);

	void afficherMessage(String message);

	void afficherErreur(Throwable exception);

	void afficherErreur(String message);

	void afficherErreur(String message, Throwable exception);

	boolean demanderConfirmation(String message);

}