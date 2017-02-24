package contacts.commun.service;

import contacts.commun.dto.DtoCompte;
import contacts.commun.util.ExceptionAppli;


public interface IServiceConnexion {

	DtoCompte	sessionUtilisateurOuvrir(String pseudo, String motDePasse) throws ExceptionAppli;

	void		sessionUtilisateurFermer() throws ExceptionAppli;

}
