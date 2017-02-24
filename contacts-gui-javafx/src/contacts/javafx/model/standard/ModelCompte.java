package contacts.javafx.model.standard;


import static contacts.javafx.model.EnumModeVue.CREER;
import static contacts.javafx.model.EnumModeVue.MODIFIER;

import java.util.Comparator;

import contacts.commun.dto.DtoCompte;
import contacts.commun.service.IServiceCompte;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.util.mapper.IMapperDtoFX;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModelCompte implements IModelCompte {
	
	
	// Données observables 
	
	private final ObservableList<FXCompte> comptes = FXCollections.observableArrayList( 
			c ->  new Observable[] { c.pseudoProperty()  } 
		);
	
	private final FXCompte      compteVue = new FXCompte();
	
	
	// Objet courant

	private FXCompte            compteCourant;
    private EnumModeVue			modeVue;
	
	
	// Autres champs
	private IMapperDtoFX		mapper;
	private IServiceCompte		serviceCompte;
	
	
	// Getters
	
	@Override
	public ObservableList<FXCompte> getComptes() {
		return comptes;
	}

	@Override
	public FXCompte getCompteVue() {
		return compteVue;
	}
	
	
	// Injecteurs
	
	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}
	
	public void setServiceCompte(IServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}
	
	
	// Actualisations
	
	@Override
	public void actualiserListe() throws ExceptionAppli {
		
		// Prépare la récupération de l'objet courant
		int idCourant = 0;
		if( compteCourant != null ) {
			idCourant = compteCourant.getId();
		}
		
		// Actualise la liste des comptes
		comptes.clear();
		for( DtoCompte dto : serviceCompte.listerTout() ) {
			FXCompte compte = mapper.map( dto );
			comptes.add( compte );
			if( compte.getId() == idCourant ) {
				compteCourant = compte;
			}
		}
 	}
	
	
	// Actions
	
	@Override
	public void preparerAjouter() {
        modeVue = CREER;
		mapper.update( new FXCompte(), compteVue );		
	}
	
	@Override
	public void preparerModifier( FXCompte compte ) {
        modeVue = MODIFIER;
		compteCourant = compte;
		mapper.update( compte, compteVue );	
	}
	
	
	@Override
	public void validerMiseAJour() throws ExceptionAppli  {

		// Crée un objet contenant le données pour la mise à jour
		DtoCompte dto = mapper.map( compteVue );
		
		// Effectue la mise à jour
        if ( modeVue == CREER ) {
			int id = serviceCompte.inserer(dto);
			compteVue.setId(id);
            compteCourant = mapper.update(compteVue, new FXCompte() );
			comptes.add( compteCourant );
		}
        if ( modeVue == MODIFIER ) {
			serviceCompte.modifier(dto);
			mapper.update( compteVue, compteCourant );		
		}

        // Trie la liste
        trierListe();
	}
	
	
	@Override
	public void supprimer( FXCompte compte ) throws ExceptionAppli  {
		serviceCompte.supprimer( compte.getId() );
		comptes.remove( compte );
	}
	
	
	// Initialisaiton
	
	public void refresh() throws ExceptionAppli {
		actualiserListe();
	}
    
    
    // Méthodes auxiliaires
    
    private void trierListe() {
		FXCollections.sort( comptes,
            (Comparator<FXCompte>) ( c1, c2) -> {
                return ( c1.pseudoProperty().get().toUpperCase().compareTo(c2.pseudoProperty().get().toUpperCase()));
		});
    }

}
