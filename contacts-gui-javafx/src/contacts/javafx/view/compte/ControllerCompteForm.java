package contacts.javafx.view.compte;

import java.util.Collections;
import java.util.Comparator;

import contacts.commun.util.ExceptionAppli;
import contacts.commun.util.Roles;
import contacts.javafx.fxb.FXCompte;
import contacts.javafx.model.IModelCompte;
import contacts.javafx.view.EnumView;
import contacts.javafx.view.IController;
import contacts.javafx.view.IManagerGui;
import contacts.javafx.view.util.StringBindingId;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;


public class ControllerCompteForm implements IController {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldPseudo;
	@FXML
	private TextField			textFieldMotDePasse;
	@FXML
	private TextField			textFieldEmail;
	@FXML
	private ListView<ItemRole>	listViewGroupes;

	
	// Autres champs
	
	private IManagerGui			managerGui;
	private IModelCompte		modelCompte;
	private FXCompte 			compteVue;
	
	private final ObservableList<ItemRole> itemGroupes = FXCollections.observableArrayList();
	
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.CompteListe );;
	}
	
	@FXML
	private void doValider()  {
		try {
			modelCompte.validerMiseAJour();
			managerGui.showView( EnumView.CompteListe );;
		} catch (Exception e) {
			managerGui.afficherErreur(e);
		}
	}
	


	// Initialisation du Controller
	
    @Override
	public void setManagerGui(IManagerGui managerGui) throws ExceptionAppli {

		// Injection  de dépendances
		this.managerGui = managerGui;
		modelCompte = managerGui.getModel( IModelCompte.class );
		compteVue = modelCompte.getCompteVue();

		// Data binding
		textFieldId.textProperty().bind(new StringBindingId(compteVue.idProperty()));
		textFieldPseudo.textProperty().bindBidirectional( compteVue.pseudoProperty() );
		textFieldMotDePasse.textProperty().bindBidirectional( compteVue.motDePasseProperty() );
		textFieldEmail.textProperty().bindBidirectional( compteVue.emailProperty() );

		
		// Configuration de l'objet ListView

		// Data binding

		itemGroupes.clear();
    	for ( String role : Roles.getIds()  ) {
    		itemRoleAjouter( role, false);
    	}
		actualiserListeItemGroupes();    			

        listViewGroupes.setItems( itemGroupes );
    	
    	// De compteVue vers la liste
    	compteVue.getRoles().addListener(
        	(ListChangeListener<String>)	c -> {
    			c.next();
				for ( String role : c.getAddedSubList() ) {
					itemRoleChoisir(role, true );
				}
				for ( String role : c.getRemoved() ) {
					itemRoleChoisir(role, false );
				}
        });
    	
		
		// Affichage
        listViewGroupes.setCellFactory( CheckBoxListCell.forListView(
        		(ItemRole item) -> item.choisiProperty()
   		) );
        
	}

    
    
    // Méthodes auxiliaires
    
    private void actualiserListeItemGroupes() {
    	Collections.sort( itemGroupes,
    			(Comparator<ItemRole>) (i1, i2) -> {
    				return i1.getRole().toUpperCase().compareTo( i2.getRole().toUpperCase() );
    	} );
		for( ItemRole item : itemGroupes ) {
			item.setChoisi( compteVue.isInRole( item.getRole() ) );
		}
    }
	
    
    
    private ItemRole itemRoleRetrouver( String role ) {
    	if ( role != null ) {
    		for ( ItemRole item : itemGroupes ) {
    			if ( item.getRole().equals( role ) ) {
    				return item;
    			}
    		}
    	}
		return null;
    }

    private void itemRoleAjouter( String role, boolean choisi ) {
		ItemRole item = itemRoleRetrouver(role);
		if ( item == null ) {
			itemGroupes.add( new ItemRole(role, choisi) );
		}
    }
    
//    private void itemRoleSupprimer( EnumRole role ) {
//		ItemRole item = itemRoleRetrouver(role);
//		if ( item != null ) {
//			itemGroupes.remove(item);
//		}
//    }
    
    private void itemRoleChoisir( String role, boolean choisi ) {
		ItemRole item = itemRoleRetrouver(role);
		if ( item != null ) {
			item.setChoisi(choisi);;
		}
    }
	
	
	// Classe auxiliaire

	private class ItemRole {

		// Champs
		
		private final String			role;
		private final BooleanProperty	choisi;

		
		// Propriétés

		public String getRole() {
			return role;
		}
		
		public void setChoisi( boolean choisi ) {
			choisiProperty().set(choisi);
		}
		
		public BooleanProperty choisiProperty() {
			return choisi;
		}
		
		@Override
		public String toString() {
			return Roles.getLibelle( role );
		}
		

		// Constructeurs

		public ItemRole( String role, boolean present ) {
			this.role = role;
			this.choisi = new SimpleBooleanProperty( present );
        	// Binding de l'item vers compteVue 
    		choisi.addListener(
    			(ChangeListener<Boolean> ) ( obs, oldValue, newValue ) -> {
    				if ( newValue ) {
    					if( ! compteVue.getRoles().contains( role) ) {
        					compteVue.getRoles().add( role );
    					}
    				} else {
       					compteVue.getRoles().remove( role );
    				}
        		});
		}
		
	}
}
