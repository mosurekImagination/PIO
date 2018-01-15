import java.util.ArrayList;

/**
 * Klasa realizująca połączenie się z obiektami modelu klienta i przechowywaniem ich.
 */
public class KlienciRepository {

	public 	KlienciRepository() {
	}

	/**
	 * Zwraca pobraną z bazy danych listę klientów
	 */
	public ArrayList<Klient> getKlienci() {
    	Context context = new Context();
    	context.openDB();
    	KlienciContext kc = new KlienciContext(context);
    	ArrayList<Klient> listaKlientow= kc.getKlientZBazy();
    	context.closeDB();
        return listaKlientow;
    }
}