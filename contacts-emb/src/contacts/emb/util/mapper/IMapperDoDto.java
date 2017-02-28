package contacts.emb.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import contacts.commun.dto.DtoCompte;
import contacts.commun.dto.DtoPersonne;
import contacts.commun.dto.DtoTelephone;
import contacts.emb.dom.Compte;
import contacts.emb.dom.Personne;
import contacts.emb.dom.Telephone;


@Mapper
public interface IMapperDoDto {

	static final IMapperDoDto INSTANCE = Mappers.getMapper( IMapperDoDto.class );


	// Comptes

	Compte map( DtoCompte source );

	DtoCompte map( Compte source );

	Personne map(DtoPersonne source );

	DtoPersonne map( Personne source );

	Telephone map( DtoTelephone source );

	DtoTelephone map( Telephone source );

}
