package logistic_regression;
import java.util.*;

class Classifier{
    ArrayList<double[]> trainingData;
    ArrayList<double[]> X = new ArrayList<double[]>();
    double[] t;
    double[] w = new double[5];
    Classifier(){
        trainingData = Reader.read("train.txt");
        Random rand = new Random();
        for (int i = 0; i < w.length ; i++ ) {
            w[i] = 1;
        }
        buildData();
    }
    void buildData(){
        t = new double[trainingData.size()];
        int k = 0;
        for (double[] data: trainingData ) {
            double[] x = new double[5];
            x[0] = (double)1;
            int j = 1;
            for (int i = 0; i < data.length - 1; i++ ) {
                x[j++] = data[i];
            }
            X.add(x);
            t[k++] = data[data.length - 1];
        }
    }
    double predict(double[] x){
        return Helper.sigmoid(w, x);
    }
    void logistic_regression(){
        double step_size = 0.0001;
        double[] glob_gradients = {0,0,0,0,0}; 
        for (int i = 0; i < t.length; i++) {
            double[] x = X.get(i);
            double tn = t[i];
            double[] gradients = Helper.gradient(tn, w, x);
            for (int j = 0; j < gradients.length ; j++ ) {
                glob_gradients[j] += gradients[j];
            }
        }
        for (int i = 0; i < w.length ; i++) {
            w[i] = w[i] - step_size * glob_gradients[i];
        }
    }
    double measureLogError(){
        double error = 0;
        for (int i = 0; i < t.length ; i++) {
            double[] x = X.get(i);
            double y = predict(x);
            error += Helper.error(t[i], y);
        }
        return error;
    }
    void measureError(){
        int truePositives = 0;
        int trueNegatives = 0;
        int falsePositives = 0;
        int falseNegatives = 0;        
        ArrayList<double[]> testdata = Reader.read("test.txt");
        for (double[] y : testdata) {
            double[] z = new double[5];
            z[0] = 1;
            for (int i = 1; i < y.length - 1 ; i++ ) {
                z[i] = y[i];
            }
            double yn = predict(z);
            if(yn <= 0.5){
                yn = 0;
            }else{
                yn = 1;
            }
            double tn = y[y.length - 1];
            if(tn == 1){
                if(yn == 1){
                    truePositives++;
                }else{
                    falseNegatives++;
                }
            }else{
                if(yn == 0){
                    trueNegatives++;
                }else{
                    falsePositives++;
                }
            }
        }
        double precision = ((double)truePositives / (truePositives + falsePositives));
        double recall = ((double)truePositives / (truePositives + falseNegatives));
        System.out.println("True Positives  :" + truePositives);
        System.out.println("True Negatives  :" + trueNegatives);
        System.out.println("False Positives :" + falsePositives);
        System.out.println("False Negatives :" + falseNegatives);
        System.out.println("Precision       :" + precision);
        System.out.println("Recall          :" + recall);
    }
    public static void main(String[] args) {
        Classifier c = new Classifier();
        double error = c.measureLogError();
        while(error > 28.4){
            c.logistic_regression();
            error = c.measureLogError();
        }
        c.measureError();
    }
}
