
package q1;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminPalabrasSecretas {
    private List<String> palabrasSecretas;

    public AdminPalabrasSecretas() {
        palabrasSecretas = new ArrayList<>();
    }

    public void agregarPalabra(String palabra) {
        palabrasSecretas.add(palabra);
    }

    public String seleccionarPalabraAzar() {
        if (palabrasSecretas.isEmpty()) {
            System.out.println("No hay palabras secretas disponibles.");
            return "";
        }
        Random random = new Random();
        int indice = random.nextInt(palabrasSecretas.size());
        return palabrasSecretas.get(indice);
    }
}