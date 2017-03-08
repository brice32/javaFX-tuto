package contacts.emb.util.mapper;

import contacts.commun.dto.DtoCompte;
import contacts.commun.dto.DtoPersonne;
import contacts.commun.dto.DtoTelephone;
import contacts.emb.dom.Compte;
import contacts.emb.dom.Personne;
import contacts.emb.dom.Telephone;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-03-01T16:36:12+0100",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class IMapperDoDtoImpl implements IMapperDoDto {

    @Override
    public Compte map(DtoCompte source) {
        if ( source == null ) {
            return null;
        }

        Compte compte = new Compte();

        compte.setId( source.getId() );
        compte.setPseudo( source.getPseudo() );
        compte.setMotDePasse( source.getMotDePasse() );
        compte.setEmail( source.getEmail() );
        List<String> list = source.getRoles();
        if ( list != null ) {
            compte.setRoles(       new ArrayList<String>( list )
            );
        }

        return compte;
    }

    @Override
    public DtoCompte map(Compte source) {
        if ( source == null ) {
            return null;
        }

        DtoCompte dtoCompte = new DtoCompte();

        dtoCompte.setEmail( source.getEmail() );
        dtoCompte.setId( source.getId() );
        dtoCompte.setMotDePasse( source.getMotDePasse() );
        dtoCompte.setPseudo( source.getPseudo() );
        List<String> list = source.getRoles();
        if ( list != null ) {
            dtoCompte.setRoles(       new ArrayList<String>( list )
            );
        }

        return dtoCompte;
    }

    @Override
    public Personne map(DtoPersonne source) {
        if ( source == null ) {
            return null;
        }

        Personne personne = new Personne();

        personne.setId( source.getId() );
        personne.setNom( source.getNom() );
        personne.setPrenom( source.getPrenom() );
        List<Telephone> list = dtoTelephoneListToTelephoneList( source.getTelephones() );
        if ( list != null ) {
            personne.setTelephones( list );
        }

        ajouterRefPersonne( personne );

        return personne;
    }

    @Override
    public DtoPersonne map(Personne source) {
        if ( source == null ) {
            return null;
        }

        DtoPersonne dtoPersonne = new DtoPersonne();

        dtoPersonne.setId( source.getId() );
        dtoPersonne.setNom( source.getNom() );
        dtoPersonne.setPrenom( source.getPrenom() );
        List<DtoTelephone> list = telephoneListToDtoTelephoneList( source.getTelephones() );
        if ( list != null ) {
            dtoPersonne.setTelephones( list );
        }

        return dtoPersonne;
    }

    @Override
    public Telephone map(DtoTelephone source) {
        if ( source == null ) {
            return null;
        }

        Telephone telephone_ = new Telephone();

        telephone_.setId( source.getId() );
        telephone_.setLibelle( source.getLibelle() );
        telephone_.setNumero( source.getNumero() );

        return telephone_;
    }

    @Override
    public DtoTelephone map(Telephone source) {
        if ( source == null ) {
            return null;
        }

        DtoTelephone dtoTelephone_ = new DtoTelephone();

        dtoTelephone_.setId( source.getId() );
        dtoTelephone_.setLibelle( source.getLibelle() );
        dtoTelephone_.setNumero( source.getNumero() );

        return dtoTelephone_;
    }

    protected List<Telephone> dtoTelephoneListToTelephoneList(List<DtoTelephone> list) {
        if ( list == null ) {
            return null;
        }

        List<Telephone> list_ = new ArrayList<Telephone>();
        for ( DtoTelephone dtoTelephone : list ) {
            list_.add( map( dtoTelephone ) );
        }

        return list_;
    }

    protected List<DtoTelephone> telephoneListToDtoTelephoneList(List<Telephone> list) {
        if ( list == null ) {
            return null;
        }

        List<DtoTelephone> list_ = new ArrayList<DtoTelephone>();
        for ( Telephone telephone : list ) {
            list_.add( map( telephone ) );
        }

        return list_;
    }
}
