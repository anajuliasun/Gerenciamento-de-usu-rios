
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.gerenciamento.model.Usuario;

public class MySQLRepository {
        private static MySQLRepository instance;
        private Connection connection;
        
        private MySQLRepository()
        {
            conectarEinicializarBanco();
        }
        
        public static MySQLRepository getInstance()
        {
            if (instance==null)
            {
                instance = new MySQLRepository();
            }
            return instance;
        }
        
        private void conectarEinicializarBanco()
        {
            try
            {
                String urlRaiz = "jdbc:mysql://localhost:3307/?allowPublicKeyRetrieval=true&useSSL=false";
                Connection connTemp = DriverManager.getConnection(urlRaiz, "root", "minhasenhasecreta");
                Statement stmtTemp = connTemp.createStatement();
                stmtTemp.executeUpdate("CREATE DATABASE IF NOT EXISTS app_db");
                connTemp.close();
                
                String urlDB = "jdbc:mysql://localhost:3307/app_db?allowPublicKeyRetrieval=true&useSSL=false";
                this.connection = DriverManager.getConnection(urlDB, "root", "minhasenhasecreta");
                
                String createTable = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY," 
                    + "nome VARCHAR(100) NOT NULL," 
                    + "email VARCHAR(100) UNIQUE NOT NULL," 
                    + "telefone VARCHAR(20)," 
                    + "senha VARCHAR(100) NOT NULL)";
                Statement stmt = connection.createStatement();
                stmt.execute(createTable);
            }
            catch (SQLException e)
            {
            System.err.println("Erro Crítico de Conexão: " + e.getMessage());
            }

        }
        
        public void adicionarUsuario(Usuario u)
        {
            String sql = "INSERT INTO usuarios (nome, email, telefone, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, u.getNome());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getTelefone());
            pst.setString(4, u.getSenha());
            pst.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao adicionar: " + e.getMessage());
        }
        }
        
        public List<Usuario> listarUsuarios()
        {
            List<Usuario> lista = new ArrayList<>();
            String sql = "SELECT * FROM usuarios";
            try (Statement st = connection.createStatement(); ResultSet rs =st.executeQuery(sql))
            {
                while (rs.next())
                {
                    Usuario u = new Usuario(
                    rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("senha")
                    );
                    lista.add(u);
                }
            }
            catch(SQLException e)
            {
                System.err.println("Erro ao listar: " + e.getMessage());
            }
            return lista;
        }
        
        public void atualizar(String nome, String telefone, String email, String senha, int id) throws Exception
        {
            var sql = "UPDATE usuarios SET nome = ?, telefone = ?, email = ?, senha = ? WHERE id =?";
            try(var stmt = connection.prepareStatement(sql))
            {
                stmt.setString(1, nome);
                stmt.setString(2, telefone);
                stmt.setString(3, email);
                stmt.setString(4, senha);
                stmt.setInt(5, id);
                stmt.executeUpdate();
            } catch(Exception e) {System.out.println(e.getMessage());}
        }
        
        public void deletar(Integer id) throws Exception
        {
            var sql = "DELETE FROM usuarios WHERE id = ?";
            try (var stmt=connection.prepareStatement(sql))
            {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (Exception e) {System.out.println(e.getMessage());}
        }
}
