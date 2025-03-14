/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Color;          //para manejar colores
import java.awt.Graphics;       //para dibujar en la pantalla
import javax.swing.JFrame;      //para crear ventanas
import javax.swing.JPanel;      //para crear paneles

/**
 *
 * @author chris
 */

        //definir la clase para dibujar el arbol
public class GraficarArbol extends JPanel { 
    private Proyecto1.Nodo raiz;        //almacena la raiz del arbol

        //constructor para la raiz del arbol
    public GraficarArbol(Proyecto1.Nodo raiz) {
        this.raiz = raiz;
    }

    @Override       //sobreescribir un metodo de la clase padre
    protected void paintComponent(Graphics g) {     //metodo para dibujar contenido
        super.paintComponent(g);    //llama al metodo de la clase para limpiar el panel
        
            //si la raiz no  es "nula" llama al metodo (dibujarNodo)
        if (raiz != null) {
            dibujarNodo(g, raiz, getWidth() / 2, 30, getWidth() / 4);
        }
    }

        
    private void dibujarNodo(Graphics g, Proyecto1.Nodo nodo, int x, int y, int offset) {
        g.setColor(Color.GRAY);             //establece color de fondo del nodo
        g.fillOval(x - 15, y - 15, 30, 30); //dibuja un circulo para el nodo
        g.setColor(Color.WHITE);            //color del texto
        g.drawString(nodo.valor, x - 5, y + 5); // escribe el valor del nodo
      
    //si el nodo tiene hijo izquierdo dibuja una linea hacia el y llama recursivamente a "dibujarNodo"    
        if (nodo.izquierdo != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - offset, y + 50); // dibuja la linea hacia el hijo izquierdo
            dibujarNodo(g, nodo.izquierdo, x - offset, y + 50, offset / 2); // llama recursivamente
        }

    //si el nodo tiene hijo derecho dibuja una linea hacia el y llama recursivamente a "dibujarNodo"    
        if (nodo.derecho != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + offset, y + 50); // dibuja la linea hacia el hijo derecho
            dibujarNodo(g, nodo.derecho, x + offset, y + 50, offset / 2); // llama recursivamente
        }
    }

        //metodo para mostrar el arbol
    public static void mostrarArbolGrafico(Proyecto1.Nodo raiz) {
        JFrame frame = new JFrame("ARBOL BINARIO");     //crea ventana 
        GraficarArbol panel = new GraficarArbol(raiz);  //crea un panel con el arbol
        
		frame.add(panel);       //agrega el panel a la ventana
		frame.setSize(800, 600);    //establece el tamaño de la ventana
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //cierra la aplicacion cuando se cierra la ventana
		frame.setVisible(true);     //hace visible la ventana
	}
}