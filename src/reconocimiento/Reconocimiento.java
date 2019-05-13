/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reconocimiento;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author xabie
 */
public class Reconocimiento {

    private int matrix[][];
    
    public void paint() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
               System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    public Reconocimiento(int size) {
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = -1;
            }
        }
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    public static void main(String[] args) {
        int SIZE = 800;
        int DOTS = 100;
        
        JFrame frame = new JFrame("IMG RECON");
        frame.setSize(SIZE, SIZE);
        
        Reconocimiento r = new Reconocimiento(DOTS);
        
        Graph g = new Graph(DOTS, r.getMatrix());
        g.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent me) {
              // System.out.println(me.getX()+","+me.getY());
               g.click(me.getX(), me.getY());
           } 
        });
        
        frame.add(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JFrame menu = new JFrame("Menu");
        Menu m = new Menu(r.getMatrix());
        m.setSize(100, 100);
        
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(m);
        menu.setVisible(true);
        menu.pack();
        
        while (true) {g.repaint();}
    }
    
}
