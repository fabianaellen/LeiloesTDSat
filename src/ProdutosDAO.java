
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
conectaDAO conecta = new conectaDAO();
Connection conn = conecta.connectDB();
    public boolean cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";

        try {
            conectaDAO conecta = new conectaDAO();
            Connection conn = conecta.connectDB();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try {
            conectaDAO conecta = new conectaDAO();
            Connection conn = conecta.connectDB();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));

                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }
}

    
    
    
        


