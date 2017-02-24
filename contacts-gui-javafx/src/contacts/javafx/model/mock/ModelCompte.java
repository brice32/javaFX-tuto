package contacts.javafx.model.mock;


import static contacts.javafx.model.EnumModeVue.CREER;
import static contacts.javafx.model.EnumModeVue.MODIFIER;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.EnumModeVue;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.util.mapper.IMapperDtoFX;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModelCompte implements IModelCompte {


	// DonnÃ©es pour les vues

	private final ObservableList<FXCompte> comptes = FXCollections.observableArrayList(
			c ->  new Observable[] { c.pseudoProperty()  }
		);

	private final FXCompte         compteVue = new FXCompte();


	// Objet courant

	private FXCompte               compteCourant;
    private EnumModeVue            modeVue;


	// Autres champs
    private  Map<Integer, FXCompte>	mapComptes;
	private IMapperDtoFX			mapper;


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

	public void setDonnees(Donnees donnees) {
		mapComptes = donnees.getMapComptes();
//		actualiserListe();
	}

	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}


	// Actualisations

	@Override
	public void actualiserListe()  {
        comptes.clear();
        for ( FXCompte compte : mapComptes.values() ) {
        	comptes.add( compte );
        }
        trierListe();
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
	public void validerMiseAJour()   {

		// Effectue la mise Ã  jour
        if ( modeVue == CREER ) {
            if (mapComptes.isEmpty()) {
            	compteVue.setId(1);
            } else {
            	compteVue.setId(Collections.max(mapComptes.keySet()) + 1);
            }
            compteCourant = mapper.update( compteVue, new FXCompte() );
			mapComptes.put( compteCourant.getId(), compteCourant );
			comptes.add( compteCourant );
		}
        if ( modeVue == MODIFIER ) {
			mapper.update( compteVue, compteCourant );
		}

        // Trie la liste
        trierListe();
	}


	@Override
	public void supprimer( FXCompte compte )   {
		mapComptes.remove( compte.getId() );
		comptes.remove( compte );
	}


	// Initialisaiton

	public void refresh() throws Exception {
		actualiserListe();
	}


    // MÃ©thodes auxiliaires

    private void trierListe() {
		FXCollections.sort( comptes,
            (Comparator<FXCompte>) ( item1, item2) -> {
                return ( item1.pseudoProperty().get().toUpperCase().compareTo(item2.pseudoProperty().get().toUpperCase()));
		});
    }

}
