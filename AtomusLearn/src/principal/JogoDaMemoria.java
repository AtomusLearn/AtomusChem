package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class JogoDaMemoria {
    private JFrame frame;
    private JButton[] buttons;
    private ArrayList<ElementoQuimico> elementosQuimicos;
    private ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/principal/images/AtomusChem.png"));
    private ElementoQuimico firstSelection = null;
    private JButton firstButton = null;
    private int matchedPairs = 0;

    public JogoDaMemoria() {
        frame = new JFrame("Jogo da Memória - Elementos Químicos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700); 
        frame.setLayout(new GridLayout(4, 4)); 

        elementosQuimicos = new ArrayList<>();

       
        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaHidro.png")), 
                null,
                "Hidrogênio"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeHidro.png")),
                "Hidrogênio"));

        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaCarbo.png")), 
                null,
                "Carbono"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeCarbo.png")),
                "Carbono"));
        
        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaOx.png")), 
                null,
                "Oxigênio"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeOx.png")),
                "Oxigênio"));

        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaNitro.png")), 
                null,
                "Nitrogênio"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeNitro.png")),
                "Nitrogênio"));

        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaEnx.png")), 
                null,
                "Enxofre"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeEnx.png")),
                "Enxofre"));

        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaFos.png")), 
                null,
                "Fósforo"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeFos.png")),
                "Fósforo"));

        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaSe.png")), 
                null,
                "Selênio"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeSe.png")),
                "Selênio"));
        
        elementosQuimicos.add(new ElementoQuimico(
                new ImageIcon(getClass().getResource("/principal/images/siglaMag.png")), 
                null,
                "Magnésio"));

        elementosQuimicos.add(new ElementoQuimico(
                null, 
                new ImageIcon(getClass().getResource("/principal/images/nomeMag.png")),
                "Magnésio"));


        
        Collections.shuffle(elementosQuimicos);

        buttons = new JButton[elementosQuimicos.size()];

        for (int i = 0; i < elementosQuimicos.size(); i++) {
            buttons[i] = new JButton();
            buttons[i].setIcon(defaultIcon); 
            ElementoQuimico elemento = elementosQuimicos.get(i);
            buttons[i].putClientProperty("elemento", elemento);
            buttons[i].addActionListener(new ButtonClickListener());
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            ElementoQuimico elemento = (ElementoQuimico) clickedButton.getClientProperty("elemento");

            if (clickedButton.getIcon().equals(defaultIcon)) {
                clickedButton.setIcon(elemento.getImagem());

                if (firstSelection == null) {
                    firstSelection = elemento;
                    firstButton = clickedButton;
                } else {
                    if (firstSelection.getNome().equals(elemento.getNome()) && firstButton != clickedButton) {
                        matchedPairs++;

                        firstSelection = null;
                        firstButton = null;

                        if (matchedPairs == elementosQuimicos.size() / 2) {
                            JOptionPane.showMessageDialog(frame, "Você venceu!");
                            resetGame();
                        }
                    } else {
                        @SuppressWarnings("unused")
						Timer timer = new Timer(500, event -> {
                            clickedButton.setIcon(defaultIcon);
                            firstButton.setIcon(defaultIcon);
                            firstSelection = null;
                            firstButton = null;
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        }
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setIcon(defaultIcon);
        }
        firstSelection = null;
        firstButton = null;
        matchedPairs = 0;

        Collections.shuffle(elementosQuimicos);

        for (int i = 0; i < elementosQuimicos.size(); i++) {
            buttons[i].setIcon(defaultIcon);
            buttons[i].putClientProperty("elemento", elementosQuimicos.get(i));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JogoDaMemoria::new);
    }

    private class ElementoQuimico {
        private ImageIcon simboloImagem;
        private ImageIcon nomeImagem;
        private String nome;

        public ElementoQuimico(ImageIcon simboloImagem, ImageIcon nomeImagem, String nome) {
            this.simboloImagem = simboloImagem;
            this.nomeImagem = nomeImagem;
            this.nome = nome;
        }

        public ImageIcon getImagem() {
            return simboloImagem != null ? simboloImagem : nomeImagem;
        }

        public String getNome() {
            return nome;
        }
    }
}
