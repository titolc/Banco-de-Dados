package view;

import dao.ClienteDAO;
import dao.PedidoDAO;
import modelo.Cliente;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;

public class PedidoCadastroGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<Cliente> comboClientes;
    private JComboBox<Pedido> comboPedidos; // ComboBox para selecionar pedidos
    private JTextField txtData;        // formato: dd/MM/yyyy
    private JTextField txtValor;
    private JTextArea areaLista;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PedidoCadastroGUI() {
        setTitle("Cadastro de Pedidos");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 5, 5));

        painelCampos.add(new JLabel("Cliente:"));
        comboClientes = new JComboBox<>();
        carregarClientes();  // Preenche o comboBox com os clientes
        painelCampos.add(comboClientes);

        painelCampos.add(new JLabel("Data (dd/MM/yyyy):"));
        txtData = new JTextField(sdf.format(new Date())); // data atual
        painelCampos.add(txtData);

        painelCampos.add(new JLabel("Valor total:"));
        txtValor = new JTextField();
        painelCampos.add(txtValor);

        // Botão para cadastrar pedido
        JButton btnCadastrar = new JButton("Cadastrar Pedido");
        btnCadastrar.addActionListener(evt -> cadastrarPedido());
        painelCampos.add(btnCadastrar);

        // ComboBox para selecionar um pedido
        painelCampos.add(new JLabel("Selecione Pedido:"));
        comboPedidos = new JComboBox<>();
        carregarPedidos(); // Preenche o comboBox com pedidos
        painelCampos.add(comboPedidos);

        // Botão para atualizar pedido
        JButton btnAtualizar = new JButton("Atualizar Pedido");
        btnAtualizar.addActionListener(evt -> atualizarPedido());
        painelCampos.add(btnAtualizar);

        // Botão para excluir pedido
        JButton btnExcluir = new JButton("Excluir Pedido");
        btnExcluir.addActionListener(evt -> excluirPedido());
        painelCampos.add(btnExcluir);

        // Botão para listar pedidos
        JButton btnListar = new JButton("Listar Pedidos");
        btnListar.addActionListener(evt -> listarPedidos());
        painelCampos.add(btnListar);

        add(painelCampos, BorderLayout.NORTH);

        areaLista = new JTextArea();
        areaLista.setEditable(false);
        add(new JScrollPane(areaLista), BorderLayout.CENTER);
    }

    private void carregarClientes() {
        try {
            ClienteDAO cdao = new ClienteDAO();
            List<Cliente> clientes = cdao.listarClientes();
            comboClientes.removeAllItems();
            for (Cliente c : clientes) {
                comboClientes.addItem(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar clientes: " + e.getMessage());
        }
    }

    private void carregarPedidos() {
        try {
            PedidoDAO pdao = new PedidoDAO();
            List<Pedido> pedidos = pdao.listarPedidos();
            comboPedidos.removeAllItems();
            for (Pedido p : pedidos) {
                comboPedidos.addItem(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar pedidos: " + e.getMessage());
        }
    }

    private void cadastrarPedido() {
        Cliente clienteSelecionado = (Cliente) comboClientes.getSelectedItem();
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }

        Date data;
        try {
            data = sdf.parse(txtData.getText().trim());
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use dd/MM/yyyy.");
            return;
        }

        LocalDate dataLocal = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        double valor;
        try {
            valor = Double.parseDouble(txtValor.getText().trim().replace(',', '.'));
            if (valor <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(this, "Valor total deve ser um número positivo.");
            return;
        }

        BigDecimal valorTotal = BigDecimal.valueOf(valor);

        Pedido pedido = new Pedido(clienteSelecionado.getId(), dataLocal, valorTotal);

        PedidoDAO pdao = new PedidoDAO();
        try {
            pdao.adicionarPedido(pedido);
            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso!");
            txtValor.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar pedido: " + e.getMessage());
        }
    }

    private void atualizarPedido() {
        Pedido pedidoSelecionado = (Pedido) comboPedidos.getSelectedItem();
        if (pedidoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para editar.");
            return;
        }

        // Atualiza os dados do pedido selecionado
        Cliente clienteSelecionado = (Cliente) comboClientes.getSelectedItem();
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }

        Date data;
        try {
            data = sdf.parse(txtData.getText().trim());
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use dd/MM/yyyy.");
            return;
        }

        LocalDate dataLocal = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        double valor;
        try {
            valor = Double.parseDouble(txtValor.getText().trim().replace(',', '.'));
            if (valor <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(this, "Valor total deve ser um número positivo.");
            return;
        }

        BigDecimal valorTotal = BigDecimal.valueOf(valor);

        pedidoSelecionado.setClienteId(clienteSelecionado.getId());
        pedidoSelecionado.setDataPedido(dataLocal);
        pedidoSelecionado.setValorTotal(valorTotal);

        PedidoDAO pdao = new PedidoDAO();
        try {
            pdao.atualizarPedido(pedidoSelecionado);
            JOptionPane.showMessageDialog(this, "Pedido atualizado com sucesso!");
            carregarPedidos(); // Atualiza a lista de pedidos no combo
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao atualizar pedido: " + e.getMessage());
        }
    }

    private void excluirPedido() {
        Pedido pedidoSelecionado = (Pedido) comboPedidos.getSelectedItem();
        if (pedidoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para excluir.");
            return;
        }

        PedidoDAO pdao = new PedidoDAO();
        try {
            pdao.excluirPedido(pedidoSelecionado.getId());
            JOptionPane.showMessageDialog(this, "Pedido excluído com sucesso!");
            carregarPedidos(); // Atualiza a lista de pedidos no combo
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao excluir pedido: " + e.getMessage());
        }
    }

    private void listarPedidos() {
        PedidoDAO pdao = new PedidoDAO();
        try {
            areaLista.setText(pdao.obterPedidosFormatados());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao listar pedidos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PedidoCadastroGUI().setVisible(true));
    }
}

