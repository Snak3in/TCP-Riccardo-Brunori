import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author RiccardoBrunori
 * @version Feb 2023
 */
public class Client {
    private String nomeServer = "localhost";
    private int portaServer = 2000;
    private Socket mySocket;
    private BufferedReader tastiera;
    private String stringaUtente = null;
    private String stringaRicevutaDalServer;
    private DataOutputStream outVersoServer;
    private BufferedReader inDalServer;
    
    
    /**
     * Metodo che permette la connessione al server indicato da nomeServer e portaServer.
     * @exception UnknownHostEsception Errore che si verifica quando non viene trovato il nomeServer.
     * @exception IOException.
     * @return mySocket
     */
    public Socket connetti() {
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            
            mySocket = new Socket(nomeServer, portaServer); //data socket non connection socket
            System.out.println("Connessione effettuata!");
            
            outVersoServer = new DataOutputStream(mySocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (mySocket.getInputStream()));
        } catch (UnknownHostException e) { //se non riconosce nomeServer
            System.err.println(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return mySocket;
    }
    /**
     * Metodo che consente l'invio di una stringa.
     * @exception IOException.
     */
    public void scrivi(){
        try {
            System.out.println("Inserisci la stringa da trasmettere al server:\n");
            stringaUtente = tastiera.readLine();
            
            System.out.println("Invio... ");
            outVersoServer.writeBytes(stringaUtente+'\n');
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo che legge la stringa ricevuta come risposta dal server.
     * @exception IOException.
     */
    public void leggi(){
        try {
            stringaRicevutaDalServer = inDalServer.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Risposta ricevuta  \n"+stringaRicevutaDalServer);
    }
    /**
     * Metodo che chiude il collegamento con il server.
     * @exception IOException.
     */
    public void chiudi(){
        System.out.println("Chiusura connessione!");
        try {
            mySocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
    
}
