/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reconocimiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 *
 * @author xabie
 */
public class Algorithm {
    
    private static int THRESHOLD = 50;
    
    private int matrix[][];
    private int weight[][];
    private ArrayList<int[]> vectors;
    private ArrayList<String> strings;
    private int r[];
    private boolean unreliable;
    
    public void train(String data) {
        int vector[] = new int[matrix.length*matrix.length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                vector[i*matrix.length+j] = matrix[i][j];
            }
        }
        
        vectors.add(vector);
        strings.add(data);
        
        int tmp[][] = new int[matrix.length*matrix.length][matrix.length*matrix.length];
        
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                if (i==j) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = vector[i]*vector[j];
                }
            }
        }
        
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                weight[i][j] += tmp[i][j];
            }   
        }
        
        System.out.print("Training: ");
        for (Integer i: vector) {
            System.out.print(i+",");
        }
        System.out.println(" "+data);
    }
    
    private int[] getResult(int vector[]) {
        int result[] = new int[vector.length];       
        
        for (int j = 0; j < vector.length; j++) {
            for (int k = 0; k < vector.length; k++) {
                result[j] += vector[k] * weight[k][j];
            }
        }    
        
        return result;
    }
    
    private int[] normalice(int vector[]) {
        int result[] = new int[vector.length];
        
        for (int i = 0; i < vector.length; i++) {
                        
            if (vector[i] < 0) result[i]=-1;
            else result[i]=1;
        }
        
        return result;
    }
    
    private void printPartial(int partial[]) {
        System.out.print("  Result: ");
        for (Integer i: partial) {
            System.out.print(i+",");
        }
        System.out.println();
    }
    
    public void go() {
        unreliable = false;
        int coef = matrix.length*matrix.length;
        r = new int[coef];

        int vector[] = new int[coef];
        int last[] = new int[coef];
                
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, vector, i*matrix.length, matrix[i].length);
        }      
                     
        int it = 0;
        while(true) {
            
            int result[] = getResult(vector);
            int small[] = normalice(result);
            
            if (Arrays.equals(last, small)) {
                int n[] = normalice(result);
                System.arraycopy(n, 0, r, 0, n.length);
                break;
            }
            
            System.arraycopy(small, 0, last, 0, small.length);
            System.arraycopy(small, 0, vector, 0, small.length);
            it++;
        }       
        
        System.out.println("Result found after: " + it+ " iterations");
    }
    
    public void printWeights() {
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[i].length; j++) {
                System.out.print(" "+weight[i][j]);
            }
            System.out.println();
        }
    }
    
    public Algorithm(int[][] matrix) {
        this.vectors = new ArrayList<>();
        this.strings = new ArrayList<>();
        
        this.matrix = matrix;
        weight = new int[matrix.length*matrix.length][matrix.length*matrix.length];
        r = new int[matrix.length*matrix.length];
    }
    
    public int[][] getResult() {
        int dim = (int)Math.sqrt(r.length);
        int m[][] = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = r[i*dim+j];
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
        
        return m;
    }
    
    public String getResultAsString() {
        printPartial(r);
        
        int index = 0;    
        boolean found = false;
        
        for (int i = 0; i < vectors.size(); i++) {
            if (Arrays.equals(vectors.get(i), r)) {found = true; index = i;}
        }
        
        if (found) {
            return strings.get(index);
        } else {
            return "Error";
        }
    }
}
