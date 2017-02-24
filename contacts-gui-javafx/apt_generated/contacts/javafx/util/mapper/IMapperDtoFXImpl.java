package contacts.javafx.util.mapper;

import contacts.commun.dto.DtoCompte;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.util.mapper.IMapperDtoFX.FactoryObsservableList;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-02-23T22:37:39+0100",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class IMapperDtoFXImpl implements IMapperDtoFX {

    private final FactoryObsservableList factoryObsservableList = new FactoryObsservableList();

    @Override
    public FXCompte update(FXCompte source, FXCompte target) {
        if ( source == null ) {
            return null;
        }

        target.setId( source.getId() );
        target.setPseudo( source.getPseudo() );
        target.setMotDePasse( source.getMotDePasse() );
        target.setEmail( source.getEmail() );
        if ( target.getRoles() != null ) {
            target.getRoles().clear();
            ObservableList<String> observableList = source.getRoles();
            if ( observableList != null ) {
                target.getRoles().addAll( observableList );
            }
        }

        return target;
    }

    @Override
    public FXCompte map(DtoCompte source) {
        if ( source == null ) {
            return null;
        }

        FXCompte fXCompte = new FXCompte();

        fXCompte.setId( source.getId() );
        fXCompte.setPseudo( source.getPseudo() );
        fXCompte.setMotDePasse( source.getMotDePasse() );
        fXCompte.setEmail( source.getEmail() );
        if ( fXCompte.getRoles() != null ) {
            ObservableList<String> observableList = stringListToStringObservableList( source.getRoles() );
            if ( observableList != null ) {
                fXCompte.getRoles().addAll( observableList );
            }
        }

        return fXCompte;
    }

    @Override
    public DtoCompte map(FXCompte source) {
        if ( source == null ) {
            return null;
        }

        DtoCompte dtoCompte = new DtoCompte();

        dtoCompte.setEmail( source.getEmail() );
        dtoCompte.setId( source.getId() );
        dtoCompte.setMotDePasse( source.getMotDePasse() );
        dtoCompte.setPseudo( source.getPseudo() );
        ObservableList<String> observableList = source.getRoles();
        if ( observableList != null ) {
            dtoCompte.setRoles(       new ArrayList<String>( observableList )
            );
        }

        return dtoCompte;
    }

    protected ObservableList<String> stringListToStringObservableList(List<String> list) {
        if ( list == null ) {
            return null;
        }

        ObservableList<String> observableList = factoryObsservableList.createObsListString();
        for ( String string : list ) {
            observableList.add( string );
        }

        return observableList;
    }
}
