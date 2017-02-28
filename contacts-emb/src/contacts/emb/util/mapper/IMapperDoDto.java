package contacts.emb.util.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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

	@Mapping( target="personne", ignore=true )
	Telephone map( DtoTelephone source );


	DtoTelephone map( Telephone source );

	@AfterMapping
	default void ajouterRefPersonne( @MappingTarget Personne personne ) {
	 for ( Telephone telephone : personne.getTelephones() ) {
	 telephone.setPersonne( personne );
	 }
	}
}
