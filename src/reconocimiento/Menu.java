/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reconocimiento;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author xabie
 */
public class Menu extends JPanel{
    private JButton go;
    private JButton train;
    private JTextField input;
    private JLabel result;
    private int matrix[][];
    
    private Algorithm alg;
    
    private void updateMatrix(int[][] m) {
        matrix = m;
    }
    public Menu(int[][] matrix) {
        this.matrix = matrix;
        
        input = new JTextField(10);
        train = new JButton("Train");
        
        alg = new Algorithm(matrix);
        
        go = new JButton("GO");
        result = new JLabel("Result...");
        this.add(go);
        this.add(result);
        this.add(input);
        this.add(train);

        go.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent me) {
              
             alg.go();
             result.setText(alg.getResultAsString());
             updateMatrix(alg.getResult());
           } 
        });
        
        train.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent me) {
              
               if (!input.getText().equals(""))
                   alg.train(input.getText());
               
           } 
        });
    }
}
