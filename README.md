# Proyecto1
Recorrido de Arbol Binario
Ejemplo:
Expresion:
a+b-(c-b)+c

2. Recorridos del árbol:
- *Preorden*: + + a b - c b e
- *Inorden*: a + b - (c - b) + e
- *Postorden*: a b + c b - e + -

3. Evaluación con notación postfija:
- Se evalúa la expresión usando la notación postfija.

4. Visualización del árbol gráfico:
- Un diagrama del árbol de expresión será mostrado en la interfaz.

La aplicación debe ser capaz de:

1. Validar los caracteres permitidos para evitar excepciones.
2. Solicitar los valores de las variables presentes en la expresión matemática.
3. Mostrar los recorridos del árbol de expresión.
4. Evaluar la expresión utilizando la notación polaca o postfija.
5. Visualizar el árbol de forma gráfica.

## Requisitos

- La expresión matemática debe contener operaciones de suma, resta, multiplicación, división, potencias y raíces.
- Los caracteres válidos incluyen números, operadores matemáticos y paréntesis.
- Si la expresión contiene variables, la aplicación solicitará los valores para dichas variables.

## Flujo de Trabajo

1. *Entrada de la Expresión:*
   - El usuario ingresa una expresión matemática en una caja de texto.
   - Ejemplo: a + b - (c - b) + e

2. *Validación de Caracteres:*
   - La aplicación valida que solo se utilicen caracteres permitidos, tales como operadores (+, -, *, /, ^, √) y variables (a, b, c, etc.).

3. *Solicitud de Valores para Variables:*
   - Si la expresión contiene variables, la aplicación solicita los valores para cada una de ellas.
   - Ejemplo de las solicitudes:
     
     a = ?
     b = ?
     c = ?
     d = ?
     e = ?
     

4. *Construcción del Árbol de Expresión:*
   - La expresión es convertida en un árbol de expresión binaria, donde cada nodo es una operación o un operando.

5. *Recorridos del Árbol:*
   - Se realizan los siguientes recorridos del árbol de expresión:
     - *Preorden*
     - *Inorden*
     - *Postorden*
   - El recorrido *Postorden* será utilizado para evaluar la expresión.

6. *Evaluación de la Expresión:*
   - La expresión es evaluada utilizando el recorrido *Postorden* (también conocido como notación postfija o polaca inversa). 
   - El resultado es mostrado al usuario.

7. *Visualización Gráfica del Árbol:*
   - La aplicación debe mostrar una representación gráfica del árbol de expresión.

## Resultados Esperados

La aplicación mostrará los siguientes resultados:

1. *Recorridos del árbol de expresión:*
   - Se visualizan los tres recorridos (Preorden, Inorden y Postorden).

2. *Resultado de la notación polaca (Postfija):*
   - El resultado de la evaluación de la expresión utilizando la notación polaca inversa.

3. *Árbol de Expresión Gráfico:*
   - Se debe mostrar el árbol de expresión en una representación visual.

## Integrantes
- Melannie Rosse Lorenzana Ajanel 7690-23-21245
- Christian Emanuel Garcia Figueroa 7690-23-23196
- Carlos Guillermo Ardon Garrido 7690-23-21855
