package contacts.commun.service;

import contacts.commun.dto.DtoCompte;
import contacts.commun.util.ExceptionAppli;


public interface IServiceAuthentification {

	DtoCompte 	verifierAuthentification(String pseudo, String motDePasse) throws ExceptionAppli;

}