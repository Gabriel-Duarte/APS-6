/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.pkg6sem;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
// * @author Ray Sales
// */
//public class FXML1Controller implements Initializable {
//    private ImageView imgInfo;
//    private Label lblOla;
//    private TableView tbl_info;
//    
//    
//    @FXML 
//    private void preencherTabela() throws SQLException{
//        try{
//            Connection con = Conexao.getConexao();
//            Statement stm = con.createStatement();
//            String sql = "SELECT * FROM informacoes WHERE nivel_acesso = " + APS6SEM.nivel_acesso;
//            ResultSet rs = stm.executeQuery(sql);
//            ArrayList<String> dados = new ArrayList<>();
//            while(rs.next()){
//                tbl_info.set
//                /*dados.add(0,new Object[]{
//                    rs.getInt("id"),
//                    rs.getInt("agencia"),
//                    rs.getInt("conta"),
//                    rs.getFloat("saldo"),
//                    rs.getFloat("limiteEspecial")
//                });  */             
//            }
//            
//            tbl_info.setItems(observableArrayList());
//            con.close();
//            stm.close();     
//                } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,ex.getMessage());
//        }                                                         
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
