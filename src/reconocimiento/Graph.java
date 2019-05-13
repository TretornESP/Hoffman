/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reconocimiento;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author xabie
 */
public class Graph extends JPanel {
   
    private final int PIXELS;
    private int matrix[][];
    private Color defaultColor;
    
    public void click(int x, int y) {
        int ix = x/(this.getWidth()/PIXELS);
        int iy = y/(this.getHeight()/PIXELS); 
        
        if (ix < matrix.length && iy < matrix.length)
            matrix[ix][iy] *= -1;
    }
    
    private void paintGrid(Graphics2D g2d) {
        for (int i = 0; i < this.getWidth(); i+=(this.getWidth()/PIXELS)) {
            g2d.drawLine(i, 0, i, this.getHeight());
        }
        
        for (int i = 0; i < this.getHeight(); i+=(this.getHeight()/PIXELS)) {
            g2d.drawLine(0, i, this.getWidth(), i);
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]==1) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(i*(this.getWidth()/PIXELS), j*(this.getHeight()/PIXELS), this.getWidth()/PIXELS, this.getHeight()/PIXELS);
                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(i*(this.getWidth()/PIXELS)+1, j*(this.getHeight()/PIXELS)+1, this.getWidth()/PIXELS-2, this.getHeight()/PIXELS-2);
                }
            }
        }
        
    }
    
    @Override
    public void paint(Graphics e) {
        super.paint(e);    
        Graphics2D g2d = (Graphics2D) e;
    
        paintGrid(g2d);
    }
    
    public Graph(int PIXELS, int matrix[][]) {
        this.PIXELS = PIXELS;
        this.matrix = matrix;
    }
}
