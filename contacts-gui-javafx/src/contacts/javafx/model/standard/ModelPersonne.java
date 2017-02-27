package contacts.javafx.model.standard;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import contacts.commun.dto.DtoCompte;
import contacts.commun.dto.DtoPersonne;
import contacts.commun.service.IServicePersonne;
import contacts.commun.util.ExceptionAppli;
import contacts.javafx.fxb.*;
import contacts.javafx.model.IModelPersonne;
import contacts.javafx.util.mapper.IMapperDtoFX;
import contacts.javafx.view.IManagerGui;

public class ModelPersonne implements IModelPersonne {

	private final ObservableList<FXPersonne> personnes = FXCollections.observableArrayList(p ->new Observable[]{ p.nomProperty(), p.prenomProperty()});

	private final FXPersonne personneVue= new FXPersonne(99, "XXXXX", "Xxxxx");

	private static FXPersonne personneCourant = new FXPersonne();

	private IManagerGui managerGui;


	private IServicePersonne servicePersonne;

	private IMapperDtoFX mapper;
//	public ModelPersonne(){
//		actualiserListe();
//	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#actualiserListe()
	 */
	@Override
	public void actualiserListe() throws ExceptionAppli{
		personnes.clear();

//		personnes.add(new FXPersonne(1,"DUBOIS","Jean"));
//		personnes.add(new FXPersonne(2,"DUPONT","Marie"));
//		personnes.add(new FXPersonne(3,"DURAND","Pierre"));
		for( DtoPersonne dto : servicePersonne.listerTout() ) {
		FXPersonne personne = mapper.map( dto );
			personnes.add(personne);
		}
	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#getPersonnes()
	 */
	@Override
	public ObservableList<FXPersonne> getPersonnes() {
		// TODO Auto-generated method stub
		return personnes;
	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#supprimer(contacts.javafx.fxb.FXPersonne)
	 */
	@Override
	public void supprimer(FXPersonne personne) throws ExceptionAppli{
		servicePersonne.supprimer(personne.getId());
		personnes.remove(personne);
	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#getPersonneVue()
	 */
	@Override
	public FXPersonne getPersonneVue(){
		return personneVue;
	}

	// Méthodes auxiliaires
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

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#preparerModifier(contacts.javafx.fxb.FXPersonne)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#preparerModifier()
	 */
	@Override
	public void preparerModifier(){
		personneCourant=new FXPersonne();
//		personnes.add(personneCourant);
		copierDonnees(personneCourant, personneVue);
	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#ValiderMiseAJour()
	 */
	@Override
	public void ValiderMiseAJour( ) throws ExceptionAppli{
		int dernierId;
		String message="";
		String Nom=personneVue.getNom();
		String Prenom=personneVue.getPrenom();
		if(Nom==null||Nom.length()==0){
			message+="Le nom de la personne ne doit pas être vide.\n";
		}else if(Nom.length()>=25){
			message+="La longueur du nom ne doit pas excéder 25 caractères.\n";
		}
		if(Prenom==null||Prenom.length()==0){
			message+="Le prenom de la personne ne doit pas être vide.\n";
		}else if(Prenom.length()>=25){
			message+="La longueur du prenom ne doit pas excéder 25 caractères..\n";
		}

		if(message.length()==0){
			copierDonnees(personneVue, personneCourant);
			if(!personnes.contains(personneCourant)){
				/*下面这些都是为了能够正确的赋上ID的值，结果神tm用mapper的方法就自动能弄好了*/
//				if(personnes.size()!=0){
//				dernierId=personnes.get(personnes.size()-1).getId();
//				personneCourant.setId(dernierId+1);
//				}
//				else{
//					personneCourant.setId(1);
//				}
//				personnes.add(personneCourant);
//				DtoPersonne dtopersonne = mapper.map(personneCourant);
//				servicePersonne.inserer(dtopersonne);
				int id = servicePersonne.inserer( mapper.map( personneVue ) );
				personneVue.setId( id );
				personnes.add(personneVue);
			}
		}else{
/*AlertType.ERROR
AlertType.WARNING
AlertType.INFORMATION*/
//			Main.afficherMessage(message, AlertType.ERROR);
			managerGui.afficherErreur( new Exception(message) );

		}
	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#ajouterTelephone()
	 */
	@Override
	public void ajouterTelephone(){
		personneVue.getTelephones().add(new FXTelephone());
	}

	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#supprimerTelephone(contacts.javafx.fxb.FXTelephone)
	 */
	@Override
	public void supprimerTelephone( FXTelephone telephone ){
		personneVue.getTelephones().remove(telephone);
	}

	// Initialisation
	/* (non-Javadoc)
	 * @see contacts.javafx.model.mock.IModelPersonne#refresh()
	 */
	@Override
	public void refresh() throws ExceptionAppli {
	 actualiserListe();
	}


	public void setServicePersonne(IServicePersonne servicePersonne) {
		this.servicePersonne = servicePersonne;
	}


	public void setMapper(IMapperDtoFX mapper) {
		this.mapper = mapper;
	}

}
