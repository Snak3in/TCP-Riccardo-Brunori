import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author RiccardoBrunori
 * @version Feb 2023
 */
class Server {
    private ServerSocket server = null;
    private Socket client = null;
    private String stringaRicevuta = null;
    private String stringaNuova = null;
    private BufferedReader inputDaClient;
    private DataOutputStream outputPerClient;
    
    /**
     * Metodo che crea un ServerSocket associato ad una porta, si mette in ascolto aspettando un client che si colleghi ed infine avvenuta la connessione
     * viene chiusa per permettere la connessione con nuovi client.
     * @exception IOException.
     * @return client
     */
    public Socket attendi(){
        try{
            System.out.println("Server partito");            
            server = new ServerSocket(2000);
            client = server.accept();
            server.close();
            
            outputPerClient= new DataOutputStream(client.getOutputStream());
        } catch(IOException e) {System.err.println(e.getMessage());}
        System.out.println("Connessione avvenuta");
        return client;
    }
    
    /**
     * Metodo che consente la lettura di una stringa ricevuta dal client e la sua scrittura a schermo.
     * @exception IOException.
     */
    public void leggi(){
        try {
            inputDaClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
            System.out.println("Scrivi una frase dal client e la cambierò.");
            stringaRicevuta = inputDaClient.readLine();
            System.out.println("Stringa ricevuta dal client: "+stringaRicevuta);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo che consente la modifica della stringa ricevuta dal client che poi viene rimandata.
     * @exception IOException.
     */
    public void scrivi(){
        stringaNuova = stringaRicevuta + ":)";
        System.out.println("Invio il nuovo messaggio al client");
        try {
            outputPerClient.writeBytes(stringaNuova+'\n');
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo che consente la chiusura del collegamento con il client.
     * @exception IOException.
     */
    public void chiudi(){
        System.out.println("Disconnessione dal client");
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}