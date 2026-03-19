import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gato01 extends JFrame implements ActionListener {
    JButton botones[] = new JButton[9];
    boolean turnoX;
    Font fuente = new Font("Arial", Font.BOLD, 50);
    JButton btnReiniciar;
    JPanel panelJuego, panelOpciones;

    public Gato01() {
        setSize(400, 400);
        setTitle("Juego Gato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelJuego = new JPanel(new GridLayout(3, 3));
        panelOpciones = new JPanel();

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            panelJuego.add(botones[i]);
        }

        btnReiniciar = new JButton("Reiniciar Juego");
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        panelOpciones.add(btnReiniciar);
        add(panelJuego, BorderLayout.CENTER);
        add(panelOpciones, BorderLayout.SOUTH);

        reiniciarJuego();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String letra;

        if (turnoX) {
            letra = "X";
            turnoX = false;
        } else {
            letra = "O";
            turnoX = true;
        }

        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                botones[i].setText(letra);
                botones[i].setEnabled(false);
                validarResultado();
                break;
            }
        }
    }

    private void validarResultado() {
        int[][] combinaciones = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int i = 0; i < combinaciones.length; i++) {
            String valor1 = botones[combinaciones[i][0]].getText();
            String valor2 = botones[combinaciones[i][1]].getText();
            String valor3 = botones[combinaciones[i][2]].getText();

            if (!valor1.isEmpty() && valor1.equals(valor2) && valor1.equals(valor3)) {
                desactivarTablero();
                JOptionPane.showMessageDialog(this, "Ha ganado el jugador " + valor1);
                return;
            }
        }

        if (tableroLleno()) {
            JOptionPane.showMessageDialog(this, "La partida termino en empate.");
        }
    }

    private boolean tableroLleno() {
        for (int i = 0; i < botones.length; i++) {
            if (botones[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void desactivarTablero() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
        }
    }

    private void reiniciarJuego() {
        turnoX = true;
        for (int i = 0; i < botones.length; i++) {
            botones[i].setText("");
            botones[i].setEnabled(true);
        }
    }

    public static void main(String[] args) {
        Gato01 ventana = new Gato01();
        ventana.setVisible(true);
    }
}
