
package q1;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main_Ahorcado extends JFrame{
    public Main_Ahorcado(){
        setSize(600,500);
        setLocationRelativeTo(null);
        setBackground(Color.DARK_GRAY);
        menu();
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void menu(){
        JLabel label = new JLabel("Menu de juego");
        label.setBounds(200, 10, 200, 30);
        label.setFont(new Font("Arial BLACK", 0, 24));
        label.setForeground(Color.BLACK);
        
        JPanel menu = new JPanel();
        menu.setSize(700, 600);
        menu.setLayout(null);
        menu.setBackground(Color.LIGHT_GRAY);
        
        JButton botonAhorcadoFijo = new JButton("Ahorcado Fijo");
        botonAhorcadoFijo.setBounds(200, 100 ,200,50 );
        botonAhorcadoFijo.setBackground(Color.WHITE);
        botonAhorcadoFijo.setForeground(Color.BLACK);
        botonAhorcadoFijo.setFont(new Font("Arial", Font.BOLD, 16));
        botonAhorcadoFijo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JuegoAhorcadoFijo fijo = new JuegoAhorcadoFijo("palabra");
                fijo.jugar();
            }
        });
        
        JButton botonAhorcadoAzar = new JButton("Ahorcado Azar");
        botonAhorcadoAzar.setBounds(200, 200 ,200,50 );
        botonAhorcadoAzar.setBackground(Color.WHITE);
        botonAhorcadoAzar.setForeground(Color.BLACK);
        botonAhorcadoAzar.setFont(new Font("Arial", Font.BOLD, 16));
        botonAhorcadoAzar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JuegoAhorcadoAzar azar = new JuegoAhorcadoAzar();
                azar.jugar();
            }
        });
        
        JButton botonAgregarPalabrasAzar = new JButton("Agregar palabras azar");
        botonAgregarPalabrasAzar.setBounds(200, 300, 200, 50);
        botonAgregarPalabrasAzar.setBackground(Color.WHITE);
        botonAgregarPalabrasAzar.setForeground(Color.BLACK);
        botonAgregarPalabrasAzar.setFont(new Font("Arial", Font.BOLD, 16));
        botonAgregarPalabrasAzar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String palabras = JOptionPane.showInputDialog(null, "Ingrese las palabras separadas por comas:");
                if (palabras != null && !palabras.isEmpty()) {
                    JuegoAhorcadoAzar.agregarPalabras(palabras.split(","));
                    JOptionPane.showMessageDialog(null, "Palabras agregadas exitosamente.");
                }
            }
        });
        
        JButton salir =new JButton("Salir");
        salir.setBounds(20, 20, 80, 40);
        salir.setBackground(Color.WHITE);
        salir.setForeground(Color.BLACK);
        salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
             System.exit(0);
                
            }
        });
        menu.add(salir);
        menu.add(label);
        menu.add(botonAhorcadoFijo);
        menu.add(botonAhorcadoAzar);
        menu.add(botonAgregarPalabrasAzar);
        
        add(menu);
    }
    
    public static void main(String[] args) {
        Main_Ahorcado m = new Main_Ahorcado();
        m.setVisible(true);
    }
}
