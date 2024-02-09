
package q1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    private static ArrayList<String> palabrasPosibles = new ArrayList<>();
    private Random random = new Random();
    private boolean mensajeMostrado = false;

    public JuegoAhorcadoAzar() {
        
        if (palabrasPosibles.isEmpty()) {
          //  JOptionPane.showMessageDialog(null, "No hay palabras disponibles. Por favor, agregue palabras primero.");
        } else {
            
            this.palabraSecreta = inicializarPalabraSecreta();
            this.palabraActual = "_".repeat(palabraSecreta.length());
            this.intentos = 8;
        }
    }

    @Override
    public String inicializarPalabraSecreta() {
       
        if (palabrasPosibles.isEmpty()) {
            return "";
        }
        int indice = random.nextInt(palabrasPosibles.size());
        return palabrasPosibles.get(indice);
    }

    @Override
    public void jugar() {
        
        if ((palabraSecreta == null || palabraSecreta.isEmpty()) && !mensajeMostrado) {
            JOptionPane.showMessageDialog(null, "No hay palabras disponibles. Por favor, agregue palabras primero.");
            mensajeMostrado = true; 
            return; 
        }

        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        
        JPanel panel = new JPanel();
        panel.setSize(600,600);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        
        JLabel label = new JLabel("Ingrese su Letra");
        label.setBounds(150, 100, 100, 40);
        label.setForeground(Color.BLACK);
        
        JTextField text = new JTextField();
        text.setFont(new Font("Arial",0,22));
        text.setBounds(300, 100, 50, 50);
        
        panel.add(text);
        panel.add(label);
        
        JLabel titulo = new JLabel("Ahorcado Azar");
        titulo.setBounds(200, 30, 200, 50);
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Arial",0,28));
        
        panel.add(titulo);
        
        JButton boton = new JButton("verificar");
        boton.setBounds(200, 450 ,200,50 );
        
        JTextArea descripcion = new JTextArea();
        descripcion.setText(palabraActual);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setEditable(false);
        descripcion.setFont(new Font("Arial",0,22));
        
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBounds(100, 200, 400, 200);
        
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (text.getText().length() < 2) {
                    if (!text.getText().isEmpty()) {
                        if (verificarLetra(text.getText().charAt(0))) {
                            descripcion.setText(actualizarPalabraActual(text.getText().charAt(0)));
                            JOptionPane.showMessageDialog(null, "has acertado");
                            if (hasGanado()) {
                                JOptionPane.showMessageDialog(null, "Ganaste");
                                frame.dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "has fallado, te quedan " + intentos + " intentos");
                            if (intentos == 0) {
                                JOptionPane.showMessageDialog(null, "perdiste");
                                frame.dispose();
                            }
                        }
                        actualizarPalabraActual(text.getText().charAt(0));
                    } else {
                        JOptionPane.showMessageDialog(null, "necesita ingresar una letra");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Solo se puede ingresar una letra");
                }
            }
        });
        
        panel.add(scrollPane);
        panel.add(boton);

        JButton botonVolver = new JButton("Volver");
        botonVolver.setBounds(10, 10, 100, 30);
        botonVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame.dispose(); 
                
                Main_Ahorcado main = new Main_Ahorcado();
                main.setVisible(true);
            }
        });
        panel.add(botonVolver);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public String actualizarPalabraActual(char letra) {
        StringBuilder nuevaPalabra = new StringBuilder(palabraActual);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.toLowerCase().charAt(i) == letra) {
                nuevaPalabra.setCharAt(i, letra);
            }
        }
        palabraActual = nuevaPalabra.toString();
        return palabraActual;
    }

    @Override
    public boolean verificarLetra(char letra) {
        boolean letraCorrecta = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.toLowerCase().charAt(i) == letra) {
                letraCorrecta = true;
            }
        }
        if (!letraCorrecta) {
            intentos--;
        }
        return letraCorrecta;
    }

    @Override
    public boolean hasGanado() {
        return palabraSecreta.equals(palabraActual);
    }
 
    public static void agregarPalabras(String[] palabrasNuevas) {
        
        for (String palabra : palabrasNuevas) {
            palabrasPosibles.add(palabra.trim());
            
            System.out.println("Palabra agregada: " + palabra); 
        }
    }
}
