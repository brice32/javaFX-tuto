package contacts.javafx.util.mapper;

import contacts.commun.dto.DtoCompte;
import contacts.commun.dto.DtoPersonne;
import contacts.commun.dto.DtoTelephone;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.fxb.FXPersonne;
import contacts.javafx.fxb.FXTelephone;
import contacts.javafx.util.mapper.IMapperDtoFX.FactoryObsservableList;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-02-27T20:00:22+0100",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class IMapperDtoFXImpl implements IMapperDtoFX {

    private final FactoryObsservableList factoryObsservableList = new FactoryObsservableList();

    @Override
    public FXCompte update(FXCompte source, FXCompte target) {
        if ( source == null ) {
            return null;
        }

        target.setEmail( source.getEmail() );
        target.setId( source.getId() );
        target.setMotDePasse( source.getMotDePasse() );
        target.setPseudo( source.getPseudo() );
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

        fXCompte.setEmail( source.getEmail() );
        fXCompte.setId( source.getId() );
        fXCompte.setMotDePasse( source.getMotDePasse() );
        fXCompte.setPseudo( source.getPseudo() );
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

    @Override
    public FXPersonne map(DtoPersonne source) {
        if ( source == null ) {
            return null;
        }

        FXPersonne fXPersonne = new FXPersonne();

        fXPersonne.setId( source.getId() );
        fXPersonne.setNom( source.getNom() );
        fXPersonne.setPrenom( source.getPrenom() );
        if ( fXPersonne.getTelephones() != null ) {
            ObservableList<FXTelephone> observableList = dtoTelephoneListToFXTelephoneObservableList( source.getTelephones() );
            if ( observableList != null ) {
                fXPersonne.getTelephones().addAll( observableList );
            }
        }

        return fXPersonne;
    }

    @Override
    public DtoPersonne map(FXPersonne source) {
        if ( source == null ) {
            return null;
        }

        DtoPersonne dtoPersonne = new DtoPersonne();

        dtoPersonne.setId( source.getId() );
        dtoPersonne.setNom( source.getNom() );
        dtoPersonne.setPrenom( source.getPrenom() );
        List<DtoTelephone> list = fXTelephoneObservableListToDtoTelephoneList( source.getTelephones() );
        if ( list != null ) {
            dtoPersonne.setTelephones( list );
        }

        return dtoPersonne;
    }

    @Override
    public FXTelephone map(DtoTelephone source) {
        if ( source == null ) {
            return null;
        }

        FXTelephone fXTelephone_ = new FXTelephone();

        fXTelephone_.setId( source.getId() );
        fXTelephone_.setLibelle( source.getLibelle() );
        fXTelephone_.setNumero( source.getNumero() );

        return fXTelephone_;
    }

    @Override
    public DtoTelephone map(FXTelephone source) {
        if ( source == null ) {
            return null;
        }

        DtoTelephone dtoTelephone_ = new DtoTelephone();

        dtoTelephone_.setId( source.getId() );
        dtoTelephone_.setLibelle( source.getLibelle() );
        dtoTelephone_.setNumero( source.getNumero() );

        return dtoTelephone_;
    }

    @Override
    public FXPersonne update(FXPersonne source, FXPersonne cible) {
        if ( source == null ) {
            return null;
        }

        cible.setId( source.getId() );
        cible.setNom( source.getNom() );
        cible.setPrenom( source.getPrenom() );
        if ( cible.getTelephones() != null ) {
            cible.getTelephones().clear();
            ObservableList<FXTelephone> observableList = duplicate( source.getTelephones() );
            if ( observableList != null ) {
                cible.getTelephones().addAll( observableList );
            }
        }

        return cible;
    }

    @Override
    public FXTelephone duplicate(FXTelephone source) {
        if ( source == null ) {
            return null;
        }

        FXTelephone fXTelephone = new FXTelephone();

        fXTelephone.setId( source.getId() );
        fXTelephone.setLibelle( source.getLibelle() );
        fXTelephone.setNumero( source.getNumero() );

        return fXTelephone;
    }

    @Override
    public ObservableList<FXTelephone> duplicate(ObservableList<FXTelephone> source) {
        if ( source == null ) {
            return null;
        }

        ObservableList<FXTelephone> observableList____ = factoryObsservableList.createObsListFXTelephone();
        for ( FXTelephone fXTelephone : source ) {
            observableList____.add( duplicate( fXTelephone ) );
        }

        return observableList____;
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

    protected ObservableList<FXTelephone> dtoTelephoneListToFXTelephoneObservableList(List<DtoTelephone> list) {
        if ( list == null ) {
            return null;
        }

        ObservableList<FXTelephone> observableList = factoryObsservableList.createObsListFXTelephone();
        for ( DtoTelephone dtoTelephone : list ) {
            observableList.add( map( dtoTelephone ) );
        }

        return observableList;
    }

    protected List<DtoTelephone> fXTelephoneObservableListToDtoTelephoneList(ObservableList<FXTelephone> observableList) {
        if ( observableList == null ) {
            return null;
        }

        List<DtoTelephone> list = new ArrayList<DtoTelephone>();
        for ( FXTelephone fXTelephone : observableList ) {
            list.add( map( fXTelephone ) );
        }

        return list;
    }
}
