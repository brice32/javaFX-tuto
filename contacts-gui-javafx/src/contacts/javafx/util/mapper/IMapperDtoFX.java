package contacts.javafx.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import contacts.commun.dto.DtoCompte;
import contacts.javafx.fxb.FXCompte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
   

@Mapper( uses=IMapperDtoFX.FactoryObsservableList.class  )
public interface IMapperDtoFX { 
	
	IMapperDtoFX INSTANCE = Mappers.getMapper( IMapperDtoFX.class );
	
	
	// Compte
	
	FXCompte update( FXCompte source, @MappingTarget FXCompte target );
	
	FXCompte map( DtoCompte source );
	
	DtoCompte map( FXCompte source );
	
	
    // Classe auxiliaire
    
    public static class FactoryObsservableList {

    	ObservableList<String> createObsListString() {
    		return FXCollections.observableArrayList();
    	}

    }
    
}