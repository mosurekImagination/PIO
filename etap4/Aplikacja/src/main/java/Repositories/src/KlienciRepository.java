import java.util.ArrayList;

/**
 * Klasa realizujaca polaczenie sie z obiektami modelu klienta i przechowywaniem ich.
 */
public class KlienciRepository {

	public 	KlienciRepository() {
	}

	/**
	 * Zwraca pobrana z bazy danych liste klientow
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