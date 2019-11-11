/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.pkg6sem;







import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_PLAIN;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*; 

import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;






/**
 * FXML Controller class
 *
 * @author Ray Sales
 */
public class FXMLController implements Initializable {
    private ImageView imgLogin;
    private Label lblIdentificacao;
   
   
    private Pane paneLogin;
    private AnchorPane paneMestre;
    private Button btn;
      private boolean reco; 
    private Desktop desktop = Desktop.getDesktop();
    
        @FXML
    private TextField txt1;
        @FXML
    private TextField txt;
    
    @FXML
    private Button btn1;
    
    @FXML
   protected void login(ActionEvent event) throws IOException {
    String log =txt.getText();
        int senh = Integer.parseInt(txt1.getText());
//        System.out.printf(log);
//        System.out.printf(txt1.getText());
//        if (txt.getText().trim().equals("")) {
//
//            JOptionPane.showMessageDialog(null, "campo em branco");
//
//        } else {
            boolean status = checkLogin(log, senh);

            if (status == true) {
//                reconhecimento r1 = new reconhecimento();
//              r1.reconhece();
    rec1();
       
System.out.printf("passou");
//         if (reco==true){
//             System.out.printf("\n sou foda");
//         }
//                tipo(log, senh);
//                if (cont.equals("user")) {
//                    MenuUser tl = new MenuUser();
//                    tl.setVisible(true);
//                    this.dispose();
//
//                } else {
//
//                    Menu tl = new Menu();
//                    tl.setVisible(true);
//                    this.dispose();
              }
//            } else {
//                JOptionPane.showMessageDialog(null, "Login ou senha invalidos");
//            }


        }
    @FXML
 public  void rec1 ()throws FrameGrabber.Exception {
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        String[] pessoas = {"", "lucas", "Gabriel"};
        camera.start();
        
        CascadeClassifier detectorFace = new CascadeClassifier("src\\recursos\\haarcascade_frontalface_alt.xml");
        
      
      
      FaceRecognizer reconhecedor = FisherFaceRecognizer.create();
      reconhecedor.read("src\\recursos\\classificadorFisherFaces.yml");

       boolean status = false; 
        CanvasFrame cFrame = new CanvasFrame("Reconhecimento", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        Mat imagemColorida = new Mat();
        
        while ((frameCapturado = camera.grab()) != null) {
            imagemColorida = converteMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorida, imagemCinza, COLOR_BGRA2GRAY);
            RectVector facesDetectadas = new RectVector();
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 2, 0, new Size(100,100), new Size(500,500));
            for (int i = 0; i < facesDetectadas.size(); i++) {
                Rect dadosFace = facesDetectadas.get(i);
                rectangle(imagemColorida, dadosFace, new Scalar(0,255,0,0));
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                
                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);
                
                System.out.println("w="+faceCapturada.size(0)+"  /  h="+faceCapturada.size(1));
                if ((faceCapturada.size(0) == 160) || (faceCapturada.size(1) == 160)){
                    continue;
                }  
                resize(faceCapturada, faceCapturada, new Size(160,160));
                //Size tamanho = new Size(faceCapturada); 
                reconhecedor.predict(faceCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);
                String nome;
                if (predicao == -1 || confianca.get(0)>390) {
                    nome = "Desconhecido";
                    
                } else {
                    nome = pessoas[predicao] + " - " + confianca.get(0);
             status = true;
        
                }
                
                System.out.println(confianca.get(0));
                
                int x = Math.max(dadosFace.tl().x() - 10, 0);
                int y = Math.max(dadosFace.tl().y() - 10, 0);
                putText(imagemColorida, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0,255,0,0));
            }
            if (cFrame.isVisible()) {
                cFrame.showImage(frameCapturado);
            }
              if(status == true){
              cFrame.dispose();
             camera.stop();
        }
        
        
     
         
      
}
      
      
        
     
         
        
 }
   public boolean checkLogin(String login, int senha) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM acesso WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setInt(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {

                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return check;

    }
    
   @FXML
   protected void testarAction(ActionEvent event) throws IOException {
         
//       final FileChooser fileChooser = new FileChooser();
//        File file = fileChooser.showOpenDialog(APS6SEM.stage1);
//                try {
//            desktop.open(file);
//        } catch (IOException ex) {
//            Logger.getLogger(
//                    FileChooserSample.class.getName()).log(
//                    Level.SEVERE, null, ex
//            );
//        }
    }

   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
