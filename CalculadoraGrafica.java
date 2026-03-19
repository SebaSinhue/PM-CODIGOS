import java.awt.Font;
import java.awt.GridLayout;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CalculadoraGrafica extends JFrame {

    JTextField num1, num2;
    JButton btnSumar, btnRestar, btnMultiplicar, btnDividir;
    JLabel resultado;
    Font fuente = new Font("Arial", Font.BOLD, 60);

    public CalculadoraGrafica() {
        setTitle("casio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        num1 = new JTextField(10);
        num1.setFont(fuente);
        num2 = new JTextField(10);
        num2.setFont(fuente);

        btnSumar = new JButton("+");
        btnRestar = new JButton("-");
        btnMultiplicar = new JButton("x");
        btnDividir = new JButton("/");

        JPanel pOpciones = new JPanel();
        pOpciones.add(btnSumar);
        pOpciones.add(btnRestar);
        pOpciones.add(btnMultiplicar);
        pOpciones.add(btnDividir);

        resultado = new JLabel();
        resultado.setFont(fuente);

        add(num1);
        add(num2);
        add(pOpciones);
        add(resultado);

        BiConsumer<JButton, DoubleBinaryOperator> configurarOperacion = (boton, operacion) ->
            boton.addActionListener(e -> {
                try {
                    double n1 = Double.parseDouble(num1.getText());
                    double n2 = Double.parseDouble(num2.getText());

                    if (boton == btnDividir && n2 == 0) {
                        resultado.setText("Error");
                        return;
                    }

                    double res = operacion.applyAsDouble(n1, n2);
                    resultado.setText(String.valueOf(res));
                } catch (NumberFormatException ex) {
                    resultado.setText("Dato invalido");
                }
            });

        configurarOperacion.accept(btnSumar, (a, b) -> a + b);
        configurarOperacion.accept(btnRestar, (a, b) -> a - b);
        configurarOperacion.accept(btnMultiplicar, (a, b) -> a * b);
        configurarOperacion.accept(btnDividir, (a, b) -> a / b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGrafica().setVisible(true));
    }
}
