package view;

import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ClienteCadastroGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtNome, txtEmail, txtTelefone;
    private JTextArea areaLista;
    private JComboBox<Cliente> comboClientes;

    public ClienteCadastroGUI() {
        setTitle("Cadastro de Clientes");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de campos
        JPanel painelCampos = new JPanel(new GridLayout(5, 2));

        painelCampos.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCampos.add(txtNome);

        painelCampos.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelCampos.add(txtEmail);

        painelCampos.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelCampos.add(txtTelefone);

        // Botão para cadastrar cliente
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarCliente());
        painelCampos.add(btnCadastrar);

        // ComboBox para selecionar um cliente para edição
        painelCampos.add(new JLabel("Selecione Cliente:"));
        comboClientes = new JComboBox<>();
        carregarClientes();
        painelCampos.add(comboClientes);

        // Botão para atualizar cliente
        JButton btnAtualizar = new JButton("Atualizar Cliente");
        btnAtualizar.addActionListener(e -> atualizarCliente());
        painelCampos.add(btnAtualizar);

        // Botão para listar clientes
        JButton btnListar = new JButton("Listar Clientes");
        btnListar.addActionListener(e -> listarClientes());
        painelCampos.add(btnListar);

        add(painelCampos, BorderLayout.NORTH);

        // Área para exibir lista de clientes
        areaLista = new JTextArea();
        areaLista.setEditable(false);
        add(new JScrollPane(areaLista), BorderLayout.CENTER);
    }

    // Método para cadastrar cliente
    private void cadastrarCliente() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        if (!email.contains("@") || nome.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
            return;
        }

        Cliente cliente = new Cliente(nome, email, telefone);
        ClienteDAO dao = new ClienteDAO();

        try {
            dao.adicionarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            txtNome.setText("");
            txtEmail.setText("");
            txtTelefone.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage());
        }
    }

    // Método para listar clientes
    private void listarClientes() {
        ClienteDAO dao = new ClienteDAO();
        try {
            List<Cliente> clientes = dao.listarClientes();
            areaLista.setText("");
            for (Cliente c : clientes) {
                areaLista.append(c.getId() + " - " + c.getNome() + " - " + c.getEmail() + " - " + c.getTelefone() + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar clientes: " + ex.getMessage());
        }
    }

    // Método para carregar os clientes na ComboBox
    private void carregarClientes() {
        try {
            ClienteDAO cdao = new ClienteDAO();
            List<Cliente> clientes = cdao.listarClientes();
            // Limpa os itens da ComboBox
            comboClientes.removeAllItems();
            // Adiciona cada cliente ao combo (o toString de Cliente será exibido)
            for (Cliente c : clientes) {
                comboClientes.addItem(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar clientes: " + e.getMessage());
        }
    }

    // Método para atualizar cliente
    private void atualizarCliente() {
        Cliente clienteSelecionado = (Cliente) comboClientes.getSelectedItem();
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
            return;
        }

        // Atualiza os dados do cliente selecionado
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        if (!email.contains("@") || nome.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
            return;
        }

        clienteSelecionado.setNome(nome);
        clienteSelecionado.setEmail(email);
        clienteSelecionado.setTelefone(telefone);

        ClienteDAO dao = new ClienteDAO();
        try {
            dao.atualizarCliente(clienteSelecionado);
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            // Limpa os campos de entrada
            txtNome.setText("");
            txtEmail.setText("");
            txtTelefone.setText("");
            carregarClientes(); // Atualiza a lista de clientes no combo
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + ex.getMessage());
        }
    }

    // Método main para testar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteCadastroGUI tela = new ClienteCadastroGUI();
            tela.setVisible(true);
        });
    }
}
