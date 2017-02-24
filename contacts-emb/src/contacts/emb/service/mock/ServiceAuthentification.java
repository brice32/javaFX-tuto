package contacts.emb.service.mock;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import contacts.commun.dto.DtoCompte;
import contacts.commun.service.IServiceAuthentification;
import contacts.commun.util.ExceptionAnomalie;
import contacts.commun.util.ExceptionAppli;

public class ServiceAuthentification implements IServiceAuthentification {

	
	// Logger
	private static final Logger	logger = Logger.getLogger(ServiceAuthentification.class.getName());
	
	
	// Champs

    private  Map<Integer, DtoCompte>  mapComptes;
	
	
	// Injecteurs
	
	public void setDonnees(Donnees donnees) {
		mapComptes = donnees.getMapComptes();
	}
	
	
	@Override
	public DtoCompte verifierAuthentification(String pseudo, String motDePasse) throws ExceptionAppli {
		
		DtoCompte	compteConnecte = null;

		try {
	        for (DtoCompte compte : mapComptes.values()) {
	            if (compte.getPseudo().equals(pseudo)) {
	                if (compte.getMotDePasse().equals(motDePasse)) {
	                	compteConnecte = compte;
	                }
	                break;
	            }
	        }
			return compteConnecte;
		} catch ( RuntimeException e) {
			logger.log( Level.SEVERE, e.getMessage(), e );
			throw new ExceptionAnomalie(e);
		}
	}

}
