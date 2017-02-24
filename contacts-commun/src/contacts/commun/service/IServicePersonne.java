package contacts.commun.service;

import java.util.List;

import contacts.commun.dto.DtoPersonne;
import contacts.commun.util.ExceptionAppli;


public interface IServicePersonne {
	
	int				inserer( DtoPersonne dtoPersonne ) throws ExceptionAppli;
	
	void			modifier( DtoPersonne dtoPersonne ) throws ExceptionAppli;
	
	void			supprimer( int idPersonne ) throws ExceptionAppli;
	
	DtoPersonne 	retrouver( int idPersonne ) throws ExceptionAppli;
	
	List<DtoPersonne> listerTout() throws ExceptionAppli;
	

}
