/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps.pkg6sem;
//

import Reconhecimento.reconhecimento;
import javafx.fxml.FXML;
import org.bytedeco.javacv.FrameGrabber;












/**
 *
 * @author Gabriel
 */

public class NewClass {
        @FXML
    public static void main(String args[]) throws FrameGrabber.Exception {
    
//    public static void gabriel() throws FrameGrabber.Exception  {
// FXMLController g = new FXMLController();
// g.rec();
 reconhecimento g = new reconhecimento();
 g.rec();
           System.out.print("\n gabriel");
                      }
}
