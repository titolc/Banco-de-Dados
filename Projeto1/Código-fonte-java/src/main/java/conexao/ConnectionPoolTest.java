package conexao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    public static void main(String[] args) {
        // Configurações da pool
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/sistema_pedidos");
        config.setUsername("root");
        config.setPassword("22261800");
        config.setMaximumPoolSize(10); // Tamanho máximo da pool

        // Criando a pool de conexões
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            // Testando a conexão
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao obter conexão do pool.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar o DataSource.");
            e.printStackTrace();
        }
    }
}
