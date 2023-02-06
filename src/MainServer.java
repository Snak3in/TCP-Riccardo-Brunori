/**
 * @author RiccardoBrunori
 * @version Feb 2023
 */
public class MainServer {
     /**
     * Metodo statico per l'avvio della classe.
     * @param args argomenti da linea di comando
     */
    public static void main(String args[]){
        Server server1 = new Server();
        while(true){
            server1.attendi();
            server1.leggi();
            server1.scrivi();
            server1.chiudi();
        }
    }
}
