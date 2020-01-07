/*
 *******************************************************
 * PROJECT: StatProject
 * AUTHOR: Andrew Seamon
 * DESCRIPTION: Prints out table of Normal Curve areas
 *              generated dynamically through user input
 *******************************************************
 */
package statproject;
import java.text.*;
import java.util.*;
import java.io.*;
public class StatProject {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("How large should the table be? ");
        double range = input.nextInt();
        System.out.println();
        range += .1;
        double[] arr = numGen(range);
        printTable(range, arr);
    }
    public static double[] numGen(double range) {
        double increment = 0;
        int rangeCounter = (((int) (range / .01)));
        double[] arr = new double[rangeCounter + 1];
        int x = 0;
        while (increment < range - .01) {
            if (increment == 0.0) {
                arr[x] = 0;
                increment = increment + 0.01;
                x++;
            } else {
                double a = (1 / (Math.sqrt(2 * (Math.PI)))) * (Math.exp(-.5 * (Math.pow((increment - 0.01), 2))));
                double b = (1 / (Math.sqrt(2 * (Math.PI)))) * (Math.exp(-.5 * (Math.pow(increment, 2))));
                double c = (.01 * ((a + b) / 2));
                double d = arr[x - 1] + c;
                arr[x] = d;
                increment = increment + 0.01;
                x++;
            }
        }
        return arr;
    }
    public static void printTable(double range, double[] arr) throws IOException {
        File file = new File(System.getProperty("user.home") + "/Desktop/Seamon_NormalCurve.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!file.exists())
            file.createNewFile();
        String col1 = "-----";
        String seperator = "----------------";
        String cellSep = "|";
        String corner = "+";
        range = range / .1;
        double x = 0;
        double z = 0.0;
        int a = 0;
        DecimalFormat f = new DecimalFormat("0.00000000000000");
        DecimalFormat f1 = new DecimalFormat("0.0");
        DecimalFormat f0 = new DecimalFormat(".00");
        bw.write("\t\t\t\t\t\t\t\t\t\tNORMAL CURVE AREA\n");
        while (x < range + 1) {
            bw.append(corner + col1);
            for (int i = 0; i < 10; ++i)
                bw.append(corner + seperator);
            bw.append(corner + "\n");
            if (x == 0) {
                double count = 0.0;
                bw.append(cellSep + "  Z  ");
                for (int i = 0; i < 10; ++i) {
                    bw.append(cellSep + "      " + f0.format(count) + "       ");
                    count += .01;
                }
                bw.append(cellSep + "\n");
            } else {
                for (int y = 0; y <= 10; y++) {
                    if (y == 0) {
                        bw.append(cellSep);
                        bw.append(" " + f1.format(z) + " ");
                        bw.append(cellSep);
                        z += 0.1;
                    } else {
                        bw.append(f.format(arr[a]));
                        bw.append(cellSep);
                        a++;
                    }
                }
            }
            x++;
            if (x != 1)
                bw.append("\n");
        }
        bw.append(corner + col1);
        for (int i = 0; i < 10; ++i)
            bw.append(corner + seperator);
        bw.append(corner);
        bw.close();
    }
}