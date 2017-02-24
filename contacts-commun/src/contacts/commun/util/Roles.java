package contacts.commun.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class Roles {
	
	// Champs statiques
	
	public static final String ADMINISTRATEUR	= "ADMINISTRATEUR";
	public static final String UTILISATEUR		= "UTILISATEUR";
	
	private static final List<Role>				roles = new ArrayList<>();
	private static final Map<String, String>	mapLibelles = new HashMap<>();

	
	// Méthode anonyme statique
	static {

		roles.add( new Role( ADMINISTRATEUR,	"Administrateur"	) );
		roles.add( new Role( UTILISATEUR,		"Utilisateur"		) );
		
		for( Role role : roles ) {
			mapLibelles.put( role.getId(), role.getLibelle()		);
		}
	}
	
	
	// Constructeur privé qui empêche l'instanciation de la classe
	private Roles() {
	}
	
	
	// Actions

	public static Role[] getRoles() {
		return roles.toArray( new Role[ roles.size() ] );
	}

	public static Set<String> getIds() {
		return mapLibelles.keySet();
	}
	
	public static String getLibelle( String role ) {
		return mapLibelles.get( role );
	}
	
	
	// Classe auxiliaire
	
	public static class Role {
		
		// Champs
		
		private final String		id;
		
		private final String		libelle;

		
		// Constructeur
		
		private Role(String id, String libelle) {
			super();
			this.id = id;
			this.libelle = libelle;
		}

		
		// Getters
		
		public String getId() {
			return id;
		}

		public String getLibelle() {
			return libelle;
		}
		
	}
	
}
