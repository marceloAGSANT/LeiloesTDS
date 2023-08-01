/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useSSL=false", "root", "root");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
     public int salvar(ProdutosDTO produto) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            
            status = st.executeUpdate();
            return status; // retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        //conn = new conectaDAO().connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
     public void desconectar() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }
     
   public List<ProdutosDTO> listar(String filtro) {
        String sql = "SELECT * from produtos";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));
                
               
                lista.add(produtos);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }
    }
   
   public List<ProdutosDTO> listarVendas1() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));
                
               
                lista.add(produtos);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
            return null;
        }
    }
        
}


    

