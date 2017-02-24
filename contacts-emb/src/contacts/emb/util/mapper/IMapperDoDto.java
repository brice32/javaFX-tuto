package contacts.emb.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import contacts.commun.dto.DtoCompte;
import contacts.emb.dom.Compte;

 
@Mapper
public interface IMapperDoDto {  
	
	static final IMapperDoDto INSTANCE = Mappers.getMapper( IMapperDoDto.class );
	
	
	// Comptes
	
	Compte map( DtoCompte source );
	
	DtoCompte map( Compte source );
}
