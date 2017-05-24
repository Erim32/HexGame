package launcher_jeu_hex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static launcher_jeu_hex.gui.URL_DEPOT;

/* Toutes les méthodes qui utilisent un acces internet.
 * @author Rémi Mesté
 */
public class Internet {

    /**
     * Recuperation de la taille d'un fichier web.
     *
     * @param url
     * @return boolean: taille du fichier pointé par url en b.
     */
    public static int recupererTailleAvecUrl(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            conn.disconnect();
        }
    }

    /**
     * Recuperation de la valeur de la version sur le manifest du dépot.
     *
     * @return String
     */
    public static String getVersionEnLigne() {
        String version = "0.0";
        String urlManfiest = URL_DEPOT + "manifest.txt";
        URL oracle;

        try {
            oracle = new URL(urlManfiest);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String ligne;
            while ((ligne = in.readLine()) != null) {
                //recuperation de la version
                if (ligne.toLowerCase().contains("version")) {
                    String temp[] = ligne.split(" ");
                    return temp[1];
                }
            }
            in.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        return version;
    }

    /**
     * Retourne si la machine dispose d'un acces internet.
     *
     * @return boolean
     */
    public static boolean accesInternet() {
        final String URL_SERVEUR_PING = "http://www.google.com";
        boolean internetOk = false;
        try {
            try {
                URL url = new URL(URL_SERVEUR_PING);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.connect();
                if (con.getResponseCode() == 200) {
                    System.out.println("[OK] Connexion internet.");
                    internetOk = true;
                }
            } catch (Exception exception) {
                System.out.println("[KO] Connexion internet.");
                internetOk = false;
            }
        } catch (Exception e) {
        }
        return internetOk;
    }
}
