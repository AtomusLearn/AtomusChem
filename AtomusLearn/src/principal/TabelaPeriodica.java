
package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TabelaPeriodica extends JFrame {

    private JTextArea infoTextArea; 
    private Map<String, Elemento> elementosInfo; 
    private JPanel panel; 
    private JButton ultimoBotaoClicado; 

    public TabelaPeriodica() {
        
        setTitle("Tabela Periódica Interativa");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout()); 

        
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false); 
        infoTextArea.setFont(new Font("Arial", Font.BOLD, 16)); 
        infoTextArea.setBackground(new Color(240, 240, 240)); 
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        add(scrollPane, BorderLayout.CENTER); 

        
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5)); 
        panel.setBackground(new Color(75, 90, 228)); 
        add(panel, BorderLayout.NORTH);

       
        elementosInfo = new HashMap<>();
        criarElementos(); 

        
        for (String simbolo : elementosInfo.keySet()) {
            JButton button = new JButton(simbolo);
            button.setFont(new Font("Arial", Font.BOLD, 14)); 
            button.setPreferredSize(new Dimension(80, 80));
            button.setBackground(new Color(75, 90, 228)); 
            button.setForeground(Color.BLACK); 
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
            button.setFocusPainted(false); 
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                    Elemento elemento = elementosInfo.get(simbolo);
                    infoTextArea.setText(elemento.toString());
                    
                    
                    if (ultimoBotaoClicado != null) {
                        ultimoBotaoClicado.setBackground(new Color(75, 90, 228)); 

                    button.setBackground(getCorDoElemento(simbolo));

                   
                    ultimoBotaoClicado = button;
                }
                }});

            panel.add(button); 
        }
    }

   
    private Color getCorDoElemento(String simbolo) {
        switch (simbolo) {
            case "H": case "Li": case "Na": case "K": case "Rb": case "Cs": case "Fr":
                return new Color(255, 87, 34); 
            case "He": case "Ne": case "Ar": case "Kr": case "Xe": case "Rn":
                return new Color(0, 188, 212); 
            case "B": case "Al": case "Ga": case "In": case "Tl":
                return new Color(255, 193, 7);
            case "C": case "Si": case "Ge": case "Sn": case "Pb":
                return new Color(33, 33, 33);
            case "N": case "P": case "As": case "Sb": case "Bi":
                return new Color(156, 39, 176); 
            case "Fe": case "Cu": case "Zn":
                return new Color(255, 87, 34); 
            case "O": case "F": case "Cl": case "Br":
                return new Color(76, 175, 80); 
            default:
                return new Color(33, 150, 243); 
        }
    }

    
    private void criarElementos() {
       
        elementosInfo.put("H", new Elemento("Hidrogênio", "H", 1, "É o elemento mais leve e abundante no universo, usado em combustíveis e formação da água."));
        elementosInfo.put("He", new Elemento("Hélio", "He", 2, "Gás nobre, utilizado em balões e sistemas de resfriamento."));
        elementosInfo.put("Li", new Elemento("Lítio", "Li", 3, "Usado em baterias recarregáveis."));
        elementosInfo.put("Be", new Elemento("Berílio", "Be", 4, "Usado em ligas metálicas e materiais para naves espaciais."));
        elementosInfo.put("B", new Elemento("Boro", "B", 5, "Usado em fibras de vidro e outros materiais de alta resistência."));
        elementosInfo.put("C", new Elemento("Carbono", "C", 6, "Elemento essencial para a vida, presente em todos os compostos orgânicos."));
        elementosInfo.put("N", new Elemento("Nitrogênio", "N", 7, "Gás que compõe a maior parte da atmosfera terrestre."));
        elementosInfo.put("O", new Elemento("Oxigênio", "O", 8, "Essencial para a respiração de muitos organismos vivos."));
        elementosInfo.put("F", new Elemento("Flúor", "F", 9, "Usado em cremes dentais e na produção de Teflon."));
        elementosInfo.put("Ne", new Elemento("Neônio", "Ne", 10, "Gás nobre usado em sinais luminosos."));
        elementosInfo.put("Na", new Elemento("Sódio", "Na", 11, "Usado em compostos como o sal de cozinha (NaCl)."));
        elementosInfo.put("Mg", new Elemento("Magnésio", "Mg", 12, "Usado em ligas metálicas e na indústria automobilística."));
        elementosInfo.put("Al", new Elemento("Alumínio", "Al", 13, "Elemento leve e resistente, usado na fabricação de embalagens e estruturas."));
        elementosInfo.put("Si", new Elemento("Silício", "Si", 14, "Usado em dispositivos eletrônicos e em painéis solares."));
        elementosInfo.put("P", new Elemento("Fósforo", "P", 15, "Usado em fertilizantes e na fabricação de fósforos."));
        elementosInfo.put("S", new Elemento("Enxofre", "S", 16, "Usado na fabricação de ácidos e produtos farmacêuticos."));
        elementosInfo.put("Cl", new Elemento("Cloro", "Cl", 17, "Usado como desinfetante e em processos industriais."));
        elementosInfo.put("Ar", new Elemento("Argônio", "Ar", 18, "Gás nobre usado em lâmpadas fluorescentes."));
        elementosInfo.put("K", new Elemento("Potássio", "K", 19, "Essencial para a função celular em organismos vivos."));
        elementosInfo.put("Ca", new Elemento("Cálcio", "Ca", 20, "Importante para ossos e dentes, usado também na construção civil."));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               
                TabelaPeriodica tabela = new TabelaPeriodica();
                tabela.setVisible(true);
            }
        });
    }
}

class Elemento {
    private String nome;
    private String simbolo;
    private int numeroAtomico;
    private String descricao;

    public Elemento(String nome, String simbolo, int numeroAtomico, String descricao) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.numeroAtomico = numeroAtomico;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Símbolo: " + simbolo + "\n" +
               "Número Atômico: " + numeroAtomico + "\n" +
               "Descrição: " + descricao;
    }
}
