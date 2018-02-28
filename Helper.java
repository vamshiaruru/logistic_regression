package logistic_regression;
import java.util.*;

class Helper{
    public static double dotProduct(double[] w, double[] x){
        double product = 0;
        for(int i = 0; i < w.length; i++){
            product = product + w[i]*x[i];
        }
        return product;
    }
    public static double sigmoid(double[] w, double[] x){
        return sigmoid(dotProduct(w, x));
    }
    public static double sigmoid(double num){
        return 1/(1 + Math.exp(-1*num));
    }
    public static double[] gradient(double t, double[] w, double[] x){
        double[] graidents = new double[5];
        double sigma = sigmoid(w, x);
        for(int i = 0; i < w.length; i++){
            graidents[i] = (sigma - t)*x[i];
        }
        return graidents;
    }
    public static double ln(double t){
        return Math.log(t)/Math.log(2);
    }
    public static double error(double t, double y){
        if(t == y){
            return 0;
        }
        if(y == 0 && t==1){
            return error(t, 0.00001);
        }
        if(y == 1 && t == 0){
            return error(t, 0.99999);
        }
        return -1*(t*ln(y) + (1-t)*(ln(1-y)));
    }
    double sum(double[] array){
        double s = 0;
        for(int i = 0; i < array.length; i++){
            s = s + array[i];
        }
        return s;
    }
    void print(double[] array){
        for (int i = 0; i < array.length ; i++ ) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}