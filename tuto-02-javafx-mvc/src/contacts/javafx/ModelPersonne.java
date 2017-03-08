package contacts.javafx;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import contacts.javafx.fxb.*;

public class ModelPersonne {

	private final ObservableList<FXPersonne> personnes = FXCollections.observableArrayList(p ->new Observable[]{ p.nomProperty(), p.prenomProperty()});

	private final FXPersonne personneVue= new FXPersonne(99, "XXXXX", "Xxxxx");

	private static FXPersonne personneCourant = new FXPersonne();

	public ModelPersonne(){
		actualiserListe();
	}

	public void actualiserListe(){
		personnes.clear();

		personnes.add(new FXPersonne(1,"DUBOIS","Jean"));
		personnes.add(new FXPersonne(2,"DUPONT","Marie"));
		personnes.add(new FXPersonne(3,"DURAND","Pierre"));
	}

	public ObservableList<FXPersonne> getPersonnes() {
		// TODO Auto-generated method stub
		return personnes;
	}

	public void supprimer(FXPersonne personne){
		personnes.remove(personne);
	}

	public FXPersonne getPersonneVue(){
		return personneVue;
	}

	// M¨¦thodes auxiliaires
	private FXPersonne copierDonnees( FXPersonne source, FXPersonne cible ) {
	cible.setId( source.getId() );
	cible.setNom( source.getNom() );
	cible.setPrenom( source.getPrenom() );
	cible.getTelephones().clear();
	for(FXTelephone personne : source.getTelephones()){
		cible.getTelephones().add(personne);
	}
	return cible ;
	}

	public void preparerModifier( FXPersonne personne ){
		if(personne!=null){
		personneCourant=personne;
		copierDonnees(personne, personneVue);
		}
		else{
			personneCourant=new FXPersonne();
			copierDonnees(personneCourant, personneVue);
		}
	}

	public void preparerModifier(){
		personneCourant=new FXPersonne();
//		personnes.add(personneCourant);
		copierDonnees(personneCourant, personneVue);
	}

	public void ValiderMiseAJour( ){
		for(FXTelephone t:personneVue.getTelephones()){
			System.out.println(t.getId()+" "+t.getLibelle()+" "+t.getNumero());
		}
		int dernierId;
		String message="";
		String Nom=personneVue.getNom();
		String Prenom=personneVue.getPrenom();
		if(Nom==null||Nom.length()==0){
			message+="Le nom de la personne ne doit pas ¨ºtre vide.\n";
		}else if(Nom.length()>=25){
			message+="La longueur du nom ne doit pas exc¨¦der 25 caract¨¨res.\n";
		}
		if(Prenom==null||Prenom.length()==0){
			message+="Le prenom de la personne ne doit pas ¨ºtre vide.\n";
		}else if(Prenom.length()>=25){
			message+="La longueur du prenom ne doit pas exc¨¦der 25 caract¨¨res..\n";
		}

		if(message.length()==0){
			copierDonnees(personneVue, personneCourant);
			if(!personnes.contains(personneCourant)){
				if(personnes.size()!=0){
				dernierId=personnes.get(personnes.size()-1).getId();
				personneCourant.setId(dernierId+1);
				}
				else{
					personneCourant.setId(1);
				}
				personnes.add(personneCourant);
			}
		}else{
/*AlertType.ERROR
AlertType.WARNING
AlertType.INFORMATION*/
			Main.afficherMessage(message, AlertType.ERROR);
		}
	}

	public void ajouterTelephone(){
		personneVue.getTelephones().add(new FXTelephone());
	}

	public void supprimerTelephone( FXTelephone telephone ){
		personneVue.getTelephones().remove(telephone);
	}
}
