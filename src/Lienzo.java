
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase desarrollada en las horas de refuerzo de Algoritmos y complejidad
 * Marzo 06 de 2015
 * @author Admin
 */
public class Lienzo extends Canvas {


    /**
     * Matriz donde se almacena la matriz de adyacencia
     */
    int[][] matAdy;

    /*
     * Contiene el camino a pintar en el formato 0-1-2
     */
    String camino;

    /*
     * Número de nodos
     */
    int tamM;

    public Lienzo() {
        camino = "";
    }


    /**
     * Dibuja un grafo primero pintando las aristas, luego si existe un camino, y por último los nodos
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);

        for (int i = 0; i < tamM; i++) {
            for (int j = 0; j < tamM; j++) {

                if (matAdy[i][j] == 1) {
                    //Las lineas no podemos ubicarlas exactamente en el mismo punto de los circulos
                    //Si no que toca rodarlar un poco para que queden en el centro de ellos.
                    g.drawLine(getX(i) + 10, getY(i) + 5, getX(j) + 10, getY(j) + 5);
                }
            }
        }

        //Si existe un camino, lo pintamos
        if (!camino.equals("")) {
            String[] nodos = camino.split("-");
            g.setColor(Color.red);
            for(int i = 0; i< nodos.length - 1; i++){
                int nodo1 = Integer.parseInt(nodos[i]);
                int nodo2 = Integer.parseInt(nodos[i+1]);
                //Igual q con el paso anterior, rodamos también un poco las lineas
                g.drawLine(getX(nodo1) + 10, getY(nodo1) + 5, getX(nodo2) + 10, getY(nodo2) + 5);
            } 
        }

        //Se pintan los círculos que representan los nodos, y sus nombres
        for (int i = 0; i < tamM; i++) {
            g.setColor(Color.BLUE);

            //Pintamos nodos
            g.fillOval(getX(i), getY(i), 20, 20);

            //Pintamos nombre del nodo
            g.setColor(Color.white);
            g.drawString(i + "", getX(i) + 5, getY(i) + 15);
        }

    }

    /**
     * Devuelve la posición X del nodo, dependiendo si es par o impar
     * @param nodo
     * @return
     */
    public int getX(int nodo) {
        if (nodo % 2 == 0) {
            return 50;
        } else {
            return 200;
        }
    }

    /**
     * Devuelve la posición Y del nodo.
     * @param nodo
     * @return
     */
    public int getY(int nodo) {
        return nodo * 30;
    }

}
