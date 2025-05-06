package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Pedido;
import conexao.Conexao;

public class PedidoDAO {

    // Método para atualizar um pedido existente
    public void atualizarPedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedidos SET cliente_id = ?, data_pedido = ?, valor_total = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getClienteId());
            stmt.setDate(2, Date.valueOf(pedido.getDataPedido()));
            stmt.setBigDecimal(3, pedido.getValorTotal());
            stmt.setInt(4, pedido.getId());

            stmt.executeUpdate();
        }
    }

 // Método para adicionar um pedido
    public void adicionarPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente_id, data_pedido, valor_total) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getClienteId());
            stmt.setDate(2, Date.valueOf(pedido.getDataPedido()));
            stmt.setBigDecimal(3, pedido.getValorTotal());

            stmt.executeUpdate();
        }
    }

    
    // Método para excluir um pedido pelo ID
    public void excluirPedido(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    // Método que retorna os pedidos em formato de texto, incluindo nome do cliente
    public String obterPedidosFormatados() throws SQLException {
        StringBuilder sb = new StringBuilder();
        String sql = """
            SELECT p.id, c.nome AS cliente, p.data_pedido, p.valor_total
            FROM pedidos p
            JOIN clientes c ON c.id = p.cliente_id
            ORDER BY p.id
            """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" | Cliente: ").append(rs.getString("cliente"))
                  .append(" | Data: ").append(rs.getDate("data_pedido"))
                  .append(" | Valor: ").append(rs.getBigDecimal("valor_total"))
                  .append("\n");
            }
        }
        return sb.toString();
    }

    // Método para listar todos os pedidos (em formato de objeto Pedido)
    public List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = """
            SELECT p.id, c.id AS cliente_id, p.data_pedido, p.valor_total
            FROM pedidos p
            JOIN clientes c ON c.id = p.cliente_id
            ORDER BY p.id
            """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setClienteId(rs.getInt("cliente_id"));
                pedido.setDataPedido(rs.getDate("data_pedido").toLocalDate());
                pedido.setValorTotal(rs.getBigDecimal("valor_total"));

                pedidos.add(pedido);
            }
        }
        return pedidos;
    }
}

