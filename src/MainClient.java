/**
 * @author RiccardoBrunori
 * @version Feb 2023
 */
public class MainClient {
    
    /**
     * Metodo statico per l'avvio della classe.
     * @param args argomenti da linea di comando
     */
    public static void main(String args[]){
        Client client1 = new Client();
        client1.connetti();
        client1.scrivi();
        client1.leggi();
        client1.chiudi();
        
    }
    
}
