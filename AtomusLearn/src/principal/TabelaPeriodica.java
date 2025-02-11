/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TabelaPeriodica extends JFrame {

    private JTextArea infoTextArea; // Área onde as informações serão exibidas
    private Map<String, Elemento> elementosInfo; // Mapa para armazenar informações sobre os elementos
    private JPanel panel; // Painel que contém os botões da tabela periódica
    private JButton ultimoBotaoClicado; // Referência ao último botão clicado

    public TabelaPeriodica() {
        // Configuração inicial da janela
        setTitle("Tabela Periódica Interativa");
        setSize(800, 600);  // Tamanho maior para uma visualização mais confortável
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o fechamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout()); // Define o layout da janela

        // Criando o JTextArea para mostrar as informações
        infoTextArea = new JTextArea(); // Cria uma área de texto
        infoTextArea.setEditable(false); // Define que o texto não será editável
        infoTextArea.setFont(new Font("Arial", Font.BOLD, 16)); // Definindo a fonte do texto
        infoTextArea.setBackground(new Color(240, 240, 240)); // Cor de fundo clara para o JTextArea
        JScrollPane scrollPane = new JScrollPane(infoTextArea); // Adiciona a área de texto dentro de um JScrollPane para permitir rolagem
        add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à janela

        // Painel com os botões representando os elementos
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5)); // 4 linhas e 5 colunas para a tabela
        panel.setBackground(new Color(75, 90, 228)); // Define a cor de fundo do painel
        add(panel, BorderLayout.NORTH); // Adiciona o painel de botões à parte superior da janela

        // Criando o mapa com informações dos elementos
        elementosInfo = new HashMap<>();
        criarElementos(); // Chama o método para adicionar os elementos

        // Adicionando botões para cada elemento com a mesma cor de grupo
        for (String simbolo : elementosInfo.keySet()) {
            JButton button = new JButton(simbolo);
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Definindo a fonte para os botões
            button.setPreferredSize(new Dimension(80, 80)); // Define o tamanho dos botões
            button.setBackground(new Color(75, 90, 228)); // Inicialmente o botão fica azul
            button.setForeground(Color.BLACK); // Define a cor da fonte do botão
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta para o botão
            button.setFocusPainted(false); // Remove o foco padrão do botão

            // Adiciona a ação de clique no botão
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Quando o botão do elemento for clicado, exibe as informações
                    Elemento elemento = elementosInfo.get(simbolo);
                    infoTextArea.setText(elemento.toString());
                    
                    // Se já houver um botão colorido, restaura a cor dele para branco
                    if (ultimoBotaoClicado != null) {
                        ultimoBotaoClicado.setBackground(new Color(75, 90, 228)); // Restaura a cor do último botão
                    }

                    // Muda a cor do botão clicado para a cor associada ao elemento
                    button.setBackground(getCorDoElemento(simbolo));

                    // Atualiza o último botão clicado
                    ultimoBotaoClicado = button;
                }
            });

            panel.add(button); // Adiciona o botão ao painel
        }
    }

    // Método para definir a cor do fundo do botão de acordo com o tipo do elemento
    private Color getCorDoElemento(String simbolo) {
        switch (simbolo) {
            case "H": case "Li": case "Na": case "K": case "Rb": case "Cs": case "Fr":
                return new Color(255, 87, 34); // Cor vibrante para metais alcalinos (Laranja-avermelhado)
            case "He": case "Ne": case "Ar": case "Kr": case "Xe": case "Rn":
                return new Color(0, 188, 212); // Cor vibrante para gases nobres (Ciano)
            case "B": case "Al": case "Ga": case "In": case "Tl":
                return new Color(255, 193, 7); // Cor vibrante para metais do grupo 13 (Amarelo)
            case "C": case "Si": case "Ge": case "Sn": case "Pb":
                return new Color(33, 33, 33); // Cor vibrante para metais do grupo 14 (Cinza escuro)
            case "N": case "P": case "As": case "Sb": case "Bi":
                return new Color(156, 39, 176); // Cor vibrante para semimetais (Roxo)
            case "Fe": case "Cu": case "Zn":
                return new Color(255, 87, 34); // Cor para metais de transição (Laranja-avermelhado)
            case "O": case "F": case "Cl": case "Br":
                return new Color(76, 175, 80); // Cor para halogênios (Verde)
            default:
                return new Color(33, 150, 243); // Cor padrão para outros elementos (Azul vibrante)
        }
    }

    // Método para criar os elementos
    private void criarElementos() {
        // Exemplo de como os elementos podem ser criados e armazenados
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
                // Criando e exibindo a janela
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
