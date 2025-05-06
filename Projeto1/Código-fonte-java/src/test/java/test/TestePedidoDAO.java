package test;

import dao.PedidoDAO;
import modelo.Pedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.SQLException;

public class TestePedidoDAO {
    public static void main(String[] args) {
        try {
            PedidoDAO pedidoDAO = new PedidoDAO();

            // Criando um novo pedido (assumindo que o cliente_id 1 já existe)
            Pedido pedido = new Pedido(1, LocalDate.now(), new BigDecimal("150.00"));
            pedidoDAO.adicionarPedido(pedido);

            // Listando todos os pedidos
            pedidoDAO.listarPedidos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
