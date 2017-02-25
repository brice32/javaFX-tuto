package contacts.javafx.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import contacts.commun.dto.DtoCompte;
import contacts.commun.dto.DtoPersonne;
import contacts.commun.dto.DtoTelephone;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.fxb.FXTelephone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


@Mapper( uses=IMapperDtoFX.FactoryObsservableList.class  )
public interface IMapperDtoFX {

	IMapperDtoFX INSTANCE = Mappers.getMapper( IMapperDtoFX.class );


	// Compte

	FXCompte update( FXCompte source, @MappingTarget FXCompte target );

	FXCompte map( DtoCompte source );

	DtoCompte map( FXCompte source );

	FXPersonne map( DtoPersonne source);

	DtoPersonne map( FXPersonne source );

	FXTelephone map( DtoTelephone source );

	DtoTelephone map( FXTelephone source);

    // Classe auxiliaire

    public static class FactoryObsservableList {

    	ObservableList<String> createObsListString() {
    		return FXCollections.observableArrayList();
    	}

    	ObservableList<FXTelephone> createObsListFXTelephone() {
    		 return FXCollections.observableArrayList();
    	}
    }

}