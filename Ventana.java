import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener {
    int ancho, alto;
    JTextField txtEdad;
    JButton btnAceptar;
    JPanel panelBotones;

    public Ventana() {
        ancho = 400;
        alto = 400;
        setTitle("Mi Ventana");
        setSize(ancho, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelBotones = new JPanel();
        txtEdad = new JTextField(5);
        btnAceptar = new JButton("Dame tu edad");

        btnAceptar.addActionListener(this);

        panelBotones.add(txtEdad);
        panelBotones.add(btnAceptar);

        add(panelBotones);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textoEdad = txtEdad.getText().trim();

        if (textoEdad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor escribe una edad.");
            return;
        }

        try {
            int edad = Integer.parseInt(textoEdad);

            if (edad < 0) {
                JOptionPane.showMessageDialog(this, "La edad no puede ser negativa.");
            } else if (edad >= 18) {
                JOptionPane.showMessageDialog(this, "Eres mayor de edad.");
            } else {
                JOptionPane.showMessageDialog(this, "Eres menor de edad.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa solo numeros.");
        }
    }
}
