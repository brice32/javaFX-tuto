package contacts.emb.service.mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import contacts.commun.service.IContextService;
import contacts.commun.service.IServiceAuthentification;
import contacts.emb.util.securite.IManagerSecurite;
import contacts.emb.util.securite.ManagerSecurite;


public class ContextService implements IContextService  {
	
	
	// Champs
	
	private final List<Object>		beans = new ArrayList<>();
	
	private final Donnees			donnees;
	
	private IManagerSecurite		managerSecurite;
	
	
	// Constructeur
	
	public ContextService( ManagerSecurite managerSecurite ) {
		donnees = new Donnees();
		this.managerSecurite = managerSecurite;
		managerSecurite.setServiceAuthentification( getService( IServiceAuthentification.class ) );
		beans.add( managerSecurite );
	}
	
	public ContextService() {
		this( new ManagerSecurite() );
	}
	
	
	// Actions
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getService( Class<T> type ) {
		
		// Recherche dans la liste
		Object bean = null;
		for ( Object obj : beans ) {
			if ( type.isAssignableFrom( obj.getClass() ) ) {
				bean = obj;
				break;
			}
		}
		
		// Si pas trouvé dans la liste
		if ( bean == null ) {
			try {

				// Détermine le type à instancier
				Class<T> typeImpl;
				String nomImpl = type.getSimpleName().substring(1);
				String nomPackage = this.getClass().getPackage().getName();
				nomImpl = nomPackage + "." + nomImpl;
				typeImpl =  (Class<T>) Class.forName( nomImpl );
				Constructor<T> constructor = typeImpl.getConstructor(new Class[] {});

				// Instancie l'objet
				bean = constructor.newInstance( new Object[] {} ) ;

				// Injecte les dépendances
				for( Method method : typeImpl.getDeclaredMethods() ) {
					if ( method.getParameterCount() == 1 ) {
						Class typeProp = method.getParameterTypes()[0];
						if ( method.getName().startsWith( "setService" ) ) {
							method.invoke( bean, getService(typeProp) );
						} else if ( method.getName().equals( "setDonnees" ) ) {
							method.invoke( bean, donnees );
						} else if ( method.getName().equals( "setManagerSecurite" ) ) {
							method.invoke( bean, managerSecurite );
						}
					}
				}

				// Ajoute l'objet à la liste
				beans.add(bean);
						
			} catch ( RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return (T) bean;
	}

}
