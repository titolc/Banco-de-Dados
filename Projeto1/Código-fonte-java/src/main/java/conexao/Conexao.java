package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_pedidos";
    private static final String USUARIO = "root"; // substitua se seu usuário for outro
    private static final String SENHA = "22261800";       // coloque a senha do seu MySQL aqui

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
