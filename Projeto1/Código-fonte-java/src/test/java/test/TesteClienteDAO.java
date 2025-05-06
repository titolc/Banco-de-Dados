package test;

import dao.ClienteDAO;
import modelo.Cliente;
import java.sql.SQLException;
import java.util.List;

public class TesteClienteDAO {
    public static void main(String[] args) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();

            // Adicionando um cliente
            Cliente cliente = new Cliente("João", "joao@email.com", "123456789");
            clienteDAO.adicionarCliente(cliente);

            // Listando clientes
            List<Cliente> clientes = clienteDAO.listarClientes();
            for (Cliente c : clientes) {
                System.out.println(c.getId() + ": " + c.getNome() + ", " + c.getEmail() + ", " + c.getTelefone());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
