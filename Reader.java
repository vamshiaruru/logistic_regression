package logistic_regression;

import java.util.Scanner;
import java.io.*;
import java.util.*;

class Reader{
    public static ArrayList<double[]> read(String filename){
        ArrayList<double[]> data = new ArrayList<double[]>();
        try{
            Scanner in = new Scanner(new FileReader(filename));
            while(in.hasNext()){
                String[] row = in.next().split(",");
                double[] attr = new double[5];
                int j = 0;
                for (int i = 0; i < row.length ; i++) {
                    attr[j++] = Double.parseDouble(row[i]);
                }
                data.add(attr);
            }
            return data;
        }
        catch(FileNotFoundException e){
            return data;
        }
    }
}