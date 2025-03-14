/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

import java.util.*;     //importa todas las clases del paquete 
import javax.swing.*;   //importa todas las clases del paquete para interfaces graficas

/**
 *
 * @author chris
 */

public class Proyecto1 {

    //clase interna para representar nodos del arbol de expresion
    
    static class Nodo {
        String valor;       //almacena el valor del nodo
        Nodo izquierdo;     //referencia nodo izquierdo
        Nodo derecho;       //refernacia nodo derecho

        Nodo(String valor) {    //constructor que inicia el nodo
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    // metodo principal
    public static void main(String[] args) {
        
    //muestra caja de dialogo para solicitar la expresion al usuario    
        String expresion = JOptionPane.showInputDialog("ingrese la expresion matematica:");
    
    //verifica si la expresion es nula o esta vacia, si es asi muestra ERROR y termina el programa    
        if (expresion == null || expresion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "expresion no valida.");
            return;
        }

    //llama al metodo "validarCaracteres" si hay caracteres no permitidos muestra ERROR    
        if (!validarCaracteres(expresion)) {
            JOptionPane.showMessageDialog(null, "caracteres no permitidos.");
            return;
        }

    //extrae las variables de la expresion    
        Set<String> variables = extraerVariables(expresion);
    //solicita los valores de las variables    
        Map<String, Double> valores = solicitarValores(variables);

        try {       //se usa "try" para prevenir que un error detenga el programa
            String postfija = convertirAPostfija(expresion);    //convertir la expresin infija a postfija
            Nodo arbol = construirArbol(postfija);              //construye arbol binario con la expresion postfija
            double resultado = evaluarPostfija(postfija, valores);  //evalua la expresion postfija y obtiene resultado

        //muestra el recorrido de los arboles
            String mensaje = "recorrido PreOrden: " + preorden(arbol) + "\n" +
                             "recorrido InOrden: " + inorden(arbol) + "\n" +
                             "recorrido PostOrden: " + postorden(arbol) + "\n" +
                             "resultado: " + resultado;

        //muestra el mensaje en una caja de texto
            JOptionPane.showMessageDialog(null, mensaje);

            //llama al metodo para mostrar el arbol grafico
            GraficarArbol.mostrarArbolGrafico(arbol);

    //"catch" se usa para verificar si hay excepciones y mostrar ERROR
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la expresion: " + e.getMessage());
        }
    }

            //validar caracteres permitidos
    private static boolean validarCaracteres(String expresion) {
        return expresion.matches("[a-zA-Z0-9+\\-*/^()\\s]+");
    }

            //extraer las variables de la expresion
    private static Set<String> extraerVariables(String expresion) {
        Set<String> variables = new HashSet<>();    //no permite elementos duplicados
        for (char c : expresion.toCharArray()) {    //recorre cada caracter de la expresion
            if (Character.isLetter(c)) {            //verifica si el caracter es una letra (no numeros ni simbolos)
                variables.add(String.valueOf(c));   //convierte el caracter a String para almacenar 
            }
        }
        return variables;   //retorna el conjunto de variables
    }

    //solicitar valores de variables mediante JOptionPane
    private static Map<String, Double> solicitarValores(Set<String> variables) {
        
            //crea un mapa para guardar variables con sus valores numericos (variable, valor)
        Map<String, Double> valores = new HashMap<>();
       
            //recorre cada variable del conjunto
        for (String var : variables) {
                //solicita el valor de la variable al usuario
            String input = JOptionPane.showInputDialog("valor de " + var + ":");
            try {
                    //convertir el string a double y lo guarda
                valores.put(var, Double.parseDouble(input));
           
    //si encuentra una excepcion (no es un numero), muestra ERROR        
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "valor no valido para " + var);
                System.exit(1);     //termina el programa 
            }
        }
        return valores;     //retorna el mapa con los valores
    }

    //convertir expresiaon infija a postfija
    private static String convertirAPostfija(String expresion) {
        StringBuilder postfija = new StringBuilder();   //almacenar la expresion postfija
        Stack<Character> pila = new Stack<>();          //pila para apilar operadores

            //eliminar espacios y convertir en arreglo
        for (char c : expresion.replaceAll("\\s", "").toCharArray()) {
           
                //verifica si es letra o digito
            if (Character.isLetterOrDigit(c)) {
                postfija.append(c);     //si es letra, agrega directamente al resultado
            } else if (c == '(') {      //si es "(" 
                pila.push(c);           //apila el "("
            } else if (c == ')') {      //si es ")"
                while (!pila.isEmpty() && pila.peek() != '(') {     //desapilar hasta encontrar "("
                    postfija.append(pila.pop());        //agrega operadores al resultado
                }
                pila.pop();         //eliminar "(" de la pila
            } else {            //si es un operador
                while (!pila.isEmpty() && precedencia(pila.peek()) >= precedencia(c)) {     //jerarquia de operadores
                    postfija.append(pila.pop());        //desapila operadores de mayor o igual precedencia
                }
                pila.push(c);           //apila el operador actual
            }
        }

        while (!pila.isEmpty()) {           //vacia la pila al final
            postfija.append(pila.pop());    //agrega operadores restantes al resultado
        }

        return postfija.toString();     //retorna la expresion "postfija"
    }

    // jerarquia de operadores
    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    //construir arbol desde la notacion postfija
    private static Nodo construirArbol(String postfija) {
        Stack<Nodo> pila = new Stack<>();           //pila nueva para nodos
        for (char c : postfija.toCharArray()) {     //itera cada caracter de la postfija
            if (Character.isLetterOrDigit(c)) {     //verifica si es letra o digito
                pila.push(new Nodo(String.valueOf(c)));     //crea un nodo y lo apila
            } else {        //si es un operador
                Nodo derecho = pila.pop();      //obtiene el operando derecho
                Nodo izquierdo = pila.pop();    //obtiene el operando izquierdo
                Nodo operador = new Nodo(String.valueOf(c));    //crea nodo para el operador
                operador.izquierdo = izquierdo;     //asigna hijo izquierdo
                operador.derecho = derecho;         //asigna hijo derecho
                pila.push(operador);                //apila el nodo operador
            }
        }
        return pila.pop();      //retorna la raiz del arbol
    }

    //evaluar expresion postfija
    private static double evaluarPostfija(String postfija, Map<String, Double> valores) {
        Stack<Double> pila = new Stack<>();     //pila para valores numericos
        
            //iterar cada caracter de la postfija
        for (char c : postfija.toCharArray()) { 
            
                //si es una variable
            if (Character.isLetter(c)) {        
                pila.push(valores.get(String.valueOf(c)));      //obtiene su valor del mapa
            
                //si es un digito
            } else if (Character.isDigit(c)) {                  
                pila.push(Double.parseDouble(String.valueOf(c)));   //convierte a double
            
                //si es un operador
            } else {
                double b = pila.pop();      //obtiene operando derecho
                double a = pila.pop();      //obtiene operando izquierdo
                switch (c) {                //efectua operacion para cada caso
                    case '+': pila.push(a + b); break;
                    case '-': pila.push(a - b); break;
                    case '*': pila.push(a * b); break;
                    case '/': pila.push(a / b); break;
                    case '^': pila.push(Math.pow(a, b)); break;
                }
            }
        }
        return pila.pop();      //retorna el resultado final
    }

                //RECORRIDOS DEL ARBOL
    
        //preORden      R,I,D
    private static String preorden(Nodo nodo) {
        if (nodo == null) return "";
        return nodo.valor + " " + preorden(nodo.izquierdo) + preorden(nodo.derecho);
    }

        //inOrden       I,R,D
    private static String inorden(Nodo nodo) {
        if (nodo == null) return "";
        return inorden(nodo.izquierdo) + nodo.valor + " " + inorden(nodo.derecho);
    }

        //postOrden     I,D,R
    private static String postorden(Nodo nodo) {
        if (nodo == null) return "";
        return postorden(nodo.izquierdo) + postorden(nodo.derecho) + nodo.valor + " ";
    }
}       
//FIN