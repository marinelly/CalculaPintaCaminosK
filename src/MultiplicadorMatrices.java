
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase desarrollada en las horas de refuerzo de Algoritmos y complejidad
 * Marzo 06 de 2015
 * @author Admin
 */
public class MultiplicadorMatrices {

    /**
     * @param args the command line arguments
     */
    int tamM;
    int[][] matAdy;
    ArrayList<String> caminos;

    /**
     * Método que carga la matriz de adyacencia.
     * Para esto se leer de un archivo txt la estructura de la matriz.
     * @return
     */
    public int[][] leerMatriz() {

        caminos = new ArrayList<String>();


        File file = new File("MatrizAdyacencia.txt");

        if(file.exists()){
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader (fr);
                String linea = br.readLine();
                tamM = Integer.parseInt(linea);
                matAdy = new int[tamM][tamM];

                linea = br.readLine();

                while(linea != null){
                    String[] posiciones = linea.split(";");
                    matAdy[Integer.parseInt(posiciones[0])][Integer.parseInt(posiciones[1])] = 1;
                    linea = br.readLine();
                }

            }catch(Exception ex){

            }
        }

//        tamM = 5;
//
//        matAdy = new int[5][5];
//        matAdy[0][1] = 1;
//        matAdy[0][3] = 1;
//        matAdy[1][3] = 1;
//        matAdy[1][4] = 1;
//        matAdy[2][0] = 1;
//        matAdy[2][1] = 1;
//        matAdy[2][3] = 1;
//        matAdy[3][0] = 1;
//        matAdy[3][1] = 1;
//        matAdy[3][4] = 1;
//        matAdy[4][0] = 1;
//        matAdy[4][3] = 1;

        return matAdy;
    }

    /**
     * Devuelve el resultado de multiplicar K veces una matriz por sí misma.
     * @param k
     * @return
     */
    public int[][] generarMatrizDeCaminos(int k) {

        int[][] mr;

        if (k == 1) {
            mr = matAdy;
        } else {
            mr = Multiplicador(matAdy, matAdy, 5, 5, 5);

            for (int i = 2; i < k; i++) {
                mr = Multiplicador(matAdy, mr, 5, 5, 5);
            }
        }
        return mr;
    }

    /**
     * Devuelve la multiplicación de dos matrices.
     * @param m1
     * @param m2
     * @param f1
     * @param cf
     * @param c2
     * @return
     */
    public int[][] Multiplicador(int[][] m1, int[][] m2, int f1, int cf, int c2) {
        int[][] m3 = new int[100][100];

        for (int i = 0; i < f1; i++) {
            for (int j = 0; j < c2; j++) {
                int s = 0;
                for (int k = 0; k < cf; k++) {
                    s = s + (m1[i][k] * m2[k][j]);
                }
                m3[i][j] = s;
            }
        }
        return m3;
    }

    /**
     * Recorriendo la matriz de adyacencia recursivamente, va creando los caminos entre los nodos inicio y fin
     * y los almacena en un listado
     * @param inicio
     * @param fin
     * @param actual
     * @param k
     * @param camino
     */
    public void calcularCaminos(int inicio, int fin, int actual, int k, String camino) {

        if (k > 1) {
            for (int j = 0; j < tamM; j++) {
                if (matAdy[actual][j] == 1) {
                    calcularCaminos(inicio, fin, j, k - 1, camino + "-" + j);
                }
            }
        } else {
            if (matAdy[actual][fin] == 1) {
                caminos.add(camino + "-" + fin);
            }
        }

    }

    /** Como tenemos un JFrame, ya este método no es necesario
     *
    public static void main(String[] args) {
        // TODO code application logic here
        MultiplicadorMatrices mm = new MultiplicadorMatrices();

        mm.tamM = 5;

        mm.matAdy = new int[5][5];
        mm.matAdy[0][1] = 1;
        mm.matAdy[0][3] = 1;
        mm.matAdy[1][3] = 1;
        mm.matAdy[1][4] = 1;
        mm.matAdy[2][0] = 1;
        mm.matAdy[2][1] = 1;
        mm.matAdy[2][3] = 1;
        mm.matAdy[3][0] = 1;
        mm.matAdy[3][1] = 1;
        mm.matAdy[3][4] = 1;
        mm.matAdy[4][0] = 1;
        mm.matAdy[4][3] = 1;

        int k = 3;

        int[][] mr;

        if (k == 1) {
            mr = mm.matAdy;
        } else {
            mr = mm.Multiplicador(mm.matAdy, mm.matAdy, 5, 5, 5);

            for (int i = 2; i < k; i++) {
                mr = mm.Multiplicador(mm.matAdy, mr, 5, 5, 5);
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(mr[i][j] + "-");
                }
                System.out.println();

            }

        }

        try {
            BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
            boolean sw;

            do {

                System.out.println("Digite inicio: ");
                int ini = Integer.parseInt(leer.readLine());

                System.out.println("Digite fin: ");
                int fin = Integer.parseInt(leer.readLine());

                mm.caminos = new ArrayList<String>();
                mm.calcularCaminos(ini, fin, ini, k, ini + "");

                for (int i = 0; i < mm.caminos.size(); i++) {
                    System.out.println(mm.caminos.get(i));
                }

                System.out.println("Desea calcular otro camino? (S/N)");
                sw = leer.readLine().equals("s") ? true : false;


            } while (sw);

        } catch (Exception e) {
        }
    }
     *
     */
}
