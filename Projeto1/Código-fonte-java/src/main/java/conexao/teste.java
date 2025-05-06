package conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class teste {
    public static void main(String[] args) {
        try {
            Connection conn = Conexao.getConexao();
            System.out.println("Conectado com sucesso!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }
}
