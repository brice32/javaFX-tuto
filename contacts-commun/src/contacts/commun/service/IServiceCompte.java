package contacts.commun.service;

import java.util.List;

import contacts.commun.dto.DtoCompte;
import contacts.commun.util.ExceptionAppli;


public interface IServiceCompte {
	
	int				inserer(DtoCompte dtoCompte) throws ExceptionAppli;

	void			modifier(DtoCompte dtoCompte) throws ExceptionAppli; 

	void			supprimer(int idCompte) throws ExceptionAppli;

	DtoCompte 		retrouver(int idCompte) throws ExceptionAppli;

	List<DtoCompte>	listerTout() throws ExceptionAppli;

}
