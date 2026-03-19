import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Fred extends JFrame {

    JButton[] casillas = new JButton[4];
    int[] secuencia = new int[5];
    Color[] colores = {
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.YELLOW
    };
    Random random = new Random();

    public Fred() {
        setTitle("Fred20");
        setDefaultCloseOperation(3);
        setSize(300, 300);
        setLayout(new GridLayout(2, 2));

        for (int i = 0; i < 4; i++) {
            final int indice = i;
            casillas[i] = new JButton("BTN " + (i + 1));
            casillas[i].setBackground(Color.LIGHT_GRAY);
            casillas[i].setOpaque(true);
            casillas[i].setBorderPainted(false);
            casillas[i].addActionListener(e -> encenderBoton(indice, 350));
            add(casillas[i]);
        }

        generarSecuencia();
        mostrarSecuencia();
    }

    public void generarSecuencia() {
        for (int i = 0; i < secuencia.length; i++) {
            secuencia[i] = random.nextInt(4);
        }

        for (int s : secuencia) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public void mostrarSecuencia() {
        for (JButton casilla : casillas) {
            casilla.setEnabled(false);
        }

        final int[] paso = {0};
        final boolean[] encendido = {false};

        Timer timer = new Timer(500, null);
        timer.addActionListener(e -> {
            if (paso[0] >= secuencia.length) {
                timer.stop();
                for (JButton casilla : casillas) {
                    casilla.setEnabled(true);
                    casilla.setBackground(Color.LIGHT_GRAY);
                }
                return;
            }

            int indice = secuencia[paso[0]];

            if (!encendido[0]) {
                casillas[indice].setBackground(colores[indice]);
                encendido[0] = true;
                timer.setDelay(1000);
            } else {
                casillas[indice].setBackground(Color.LIGHT_GRAY);
                encendido[0] = false;
                paso[0]++;
                timer.setDelay(500);
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public void encenderBoton(int indice, int duracion) {
        casillas[indice].setBackground(colores[indice]);

        Timer apagar = new Timer(duracion, e -> casillas[indice].setBackground(Color.LIGHT_GRAY));
        apagar.setRepeats(false);
        apagar.start();
    }

    public static void main(String[] args) {
        Fred f = new Fred();
        f.setVisible(true);
    }
}
