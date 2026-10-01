import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface gráfica em Swing, seguindo o protótipo "Batalla por la Tierra Media"
 * do enunciado: permite criar os exércitos de Heróis e Bestas, reordenar/remover
 * personagens e lançar a batalha, mostrando o relato num painel de texto.
 */
public class Janela extends JFrame {

    private final JTextField nomeHeroiTexto = new JTextField();
    private final JComboBox<String> tipoHeroiCombo = new JComboBox<>(new String[]{"Elfo", "Hobbit", "Humano"});
    private final JTextField vidaHeroiTexto = new JTextField();
    private final JTextField armaduraHeroiTexto = new JTextField();

    private final JTextField nomeBestaTexto = new JTextField();
    private final JComboBox<String> tipoBestaCombo = new JComboBox<>(new String[]{"Orc", "Troll"});
    private final JTextField vidaBestaTexto = new JTextField();
    private final JTextField armaduraBestaTexto = new JTextField();

    private final DefaultListModel<Heroi> modeloHerois = new DefaultListModel<>();
    private final DefaultListModel<Besta> modeloBestas = new DefaultListModel<>();
    private final JList<Heroi> listaHerois = new JList<>(modeloHerois);
    private final JList<Besta> listaBestas = new JList<>(modeloBestas);

    private final JTextArea logArea = new JTextArea();
    private final JButton lutarBotao = new JButton("LUTAR!");

    public Janela() {
        super("Batalha pela Terra-Média");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel exercitosPainel = new JPanel(new GridLayout(1, 2, 10, 10));
        exercitosPainel.add(criarPainelFormulario("Heróis", nomeHeroiTexto, tipoHeroiCombo,
                vidaHeroiTexto, armaduraHeroiTexto, this::adicionarHeroi));
        exercitosPainel.add(criarPainelFormulario("Bestas", nomeBestaTexto, tipoBestaCombo,
                vidaBestaTexto, armaduraBestaTexto, this::adicionarBesta));

        JPanel lutaListasPainel = new JPanel(new GridLayout(1, 2, 10, 10));
        lutaListasPainel.add(criarPainelLista("Heróis", listaHerois, modeloHerois));
        lutaListasPainel.add(criarPainelLista("Bestas", listaBestas, modeloBestas));

        lutarBotao.setFont(lutarBotao.getFont().deriveFont(Font.BOLD, 16f));
        lutarBotao.addActionListener(e -> lutar());
        JPanel lutarPainel = new JPanel();
        lutarPainel.add(lutarBotao);

        JPanel lutaPainel = new JPanel(new BorderLayout(5, 5));
        lutaPainel.setBorder(BorderFactory.createTitledBorder("Luta"));
        lutaPainel.add(lutaListasPainel, BorderLayout.CENTER);
        lutaPainel.add(lutarPainel, BorderLayout.SOUTH);

        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setPreferredSize(new Dimension(880, 250));

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.add(lutaPainel, BorderLayout.NORTH);
        centro.add(logScroll, BorderLayout.CENTER);

        add(exercitosPainel, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        setMinimumSize(new Dimension(900, 750));
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelFormulario(String titulo, JTextField nome, JComboBox<String> tipo,
                                          JTextField vida, JTextField armadura, Runnable aoAdicionar) {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder(titulo));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        int linha = 0;
        adicionarLinha(painel, c, linha++, "Nome:", nome);
        adicionarLinha(painel, c, linha++, "Tipo:", tipo);
        adicionarLinha(painel, c, linha++, "Vida:", vida);
        adicionarLinha(painel, c, linha++, "Armadura:", armadura);

        JButton adicionarBotao = new JButton("Adicionar");
        adicionarBotao.addActionListener(e -> aoAdicionar.run());
        c.gridx = 0;
        c.gridy = linha;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        painel.add(adicionarBotao, c);

        return painel;
    }

    private void adicionarLinha(JPanel painel, GridBagConstraints c, int linha, String rotulo, JComponent campo) {
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = linha;
        c.weightx = 0;
        painel.add(new JLabel(rotulo), c);
        c.gridx = 1;
        c.weightx = 1;
        painel.add(campo, c);
    }

    private <T extends Personagem> JPanel criarPainelLista(String titulo, JList<T> lista, DefaultListModel<T> modelo) {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(BorderFactory.createTitledBorder(titulo));

        lista.setVisibleRowCount(6);
        painel.add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton subirBotao = new JButton("Subir");
        JButton descerBotao = new JButton("Descer");
        JButton eliminarBotao = new JButton("Eliminar");
        subirBotao.addActionListener(e -> mover(lista, modelo, -1));
        descerBotao.addActionListener(e -> mover(lista, modelo, 1));
        eliminarBotao.addActionListener(e -> eliminar(lista, modelo));

        JPanel botoesPainel = new JPanel();
        botoesPainel.add(subirBotao);
        botoesPainel.add(descerBotao);
        botoesPainel.add(eliminarBotao);
        painel.add(botoesPainel, BorderLayout.SOUTH);

        return painel;
    }

    private <T> void mover(JList<T> lista, DefaultListModel<T> modelo, int delta) {
        int index = lista.getSelectedIndex();
        int novoIndex = index + delta;
        if (index < 0 || novoIndex < 0 || novoIndex >= modelo.size()) {
            return;
        }
        T item = modelo.remove(index);
        modelo.add(novoIndex, item);
        lista.setSelectedIndex(novoIndex);
    }

    private <T> void eliminar(JList<T> lista, DefaultListModel<T> modelo) {
        int index = lista.getSelectedIndex();
        if (index >= 0) {
            modelo.remove(index);
        }
    }

    private void adicionarHeroi() {
        String nome = nomeHeroiTexto.getText().trim();
        if (nome.isEmpty()) {
            mostrarErro("Indique o nome do herói.");
            return;
        }
        Integer vida = lerInteiroPositivo(vidaHeroiTexto.getText(), "Vida");
        Integer armadura = lerInteiroPositivo(armaduraHeroiTexto.getText(), "Armadura");
        if (vida == null || armadura == null) {
            return;
        }

        String tipo = (String) tipoHeroiCombo.getSelectedItem();
        Heroi heroi = switch (tipo) {
            case "Elfo" -> new Elfo(nome, vida, armadura);
            case "Hobbit" -> new Hobbit(nome, vida, armadura);
            default -> new Humano(nome, vida, armadura);
        };
        modeloHerois.addElement(heroi);
        limparCampos(nomeHeroiTexto, vidaHeroiTexto, armaduraHeroiTexto);
    }

    private void adicionarBesta() {
        String nome = nomeBestaTexto.getText().trim();
        if (nome.isEmpty()) {
            mostrarErro("Indique o nome da besta.");
            return;
        }
        Integer vida = lerInteiroPositivo(vidaBestaTexto.getText(), "Vida");
        Integer armadura = lerInteiroPositivo(armaduraBestaTexto.getText(), "Armadura");
        if (vida == null || armadura == null) {
            return;
        }

        String tipo = (String) tipoBestaCombo.getSelectedItem();
        Besta besta = "Orc".equals(tipo) ? new Orc(nome, vida, armadura) : new Troll(nome, vida, armadura);
        modeloBestas.addElement(besta);
        limparCampos(nomeBestaTexto, vidaBestaTexto, armaduraBestaTexto);
    }

    private Integer lerInteiroPositivo(String texto, String campo) {
        try {
            int valor = Integer.parseInt(texto.trim());
            if (valor <= 0) {
                mostrarErro(campo + " tem de ser um número inteiro positivo.");
                return null;
            }
            return valor;
        } catch (NumberFormatException ex) {
            mostrarErro(campo + " tem de ser um número inteiro.");
            return null;
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Dados inválidos", JOptionPane.WARNING_MESSAGE);
    }

    private void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    private void lutar() {
        if (modeloHerois.isEmpty() || modeloBestas.isEmpty()) {
            mostrarErro("É preciso pelo menos um herói e uma besta para lutar.");
            return;
        }

        List<Heroi> herois = new ArrayList<>();
        for (int i = 0; i < modeloHerois.size(); i++) {
            herois.add(modeloHerois.get(i));
        }
        List<Besta> bestas = new ArrayList<>();
        for (int i = 0; i < modeloBestas.size(); i++) {
            bestas.add(modeloBestas.get(i));
        }

        logArea.append("=== Nova batalha ===\n");
        lutarBotao.setEnabled(false);

        SwingWorker<Void, String> tarefa = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                Batalha batalha = new Batalha(herois, bestas, this::publish);
                batalha.lutar();
                return null;
            }

            @Override
            protected void process(List<String> linhas) {
                for (String linha : linhas) {
                    logArea.append(linha + "\n");
                }
                logArea.setCaretPosition(logArea.getDocument().getLength());
            }

            @Override
            protected void done() {
                lutarBotao.setEnabled(true);
            }
        };
        tarefa.execute();
    }
}
