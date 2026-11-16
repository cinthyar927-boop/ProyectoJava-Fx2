package org.ejemplos.projectojavafx2;

/**
 * Cilindro permite calcular el area y el volumen del cilindro
 */
public class Cilindro {

    private double radio = 0;
    private double altura = 0;
    private double area = 0;
    private double volumen = 0;
    /**
     * Constructor que inicia el cilindro con un radio y una altura especificos,
     * calculando automaticamente su area y su volumen iniciales.
     * @param altura La altura del cilindro
     */

    public Cilindro(double radio, double altura) {
        this.radio = radio;
        this.altura = altura;
        this.area = calcularArea();
        this.volumen = calcularVolumen();
    }

    /**
     * establece un nuevo radio para el cilindro y actualiza
     * automaticamente tanto el area como el volumen
     * @param radio El nuevo radio a establecer
     */
    public void setRadio(double radio) {
        this.radio = radio;
        area = calcularArea();
        volumen = calcularVolumen();
    }

    /**
     * Obtiene el radio actual del cilindro
     * @return El radio en unidades lineales
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Establece una nueva altura para el cilindro y actualiza
     * automaticamente tanto el area como el volumen
     * @param altura la nueva altura a establecer
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Obtiene la altura actual del cilindro
     * @return la altura en unidades lineales
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Calcula el area total del cilindro utilizando la formula A=2πr(r+h)
     * @return El area total calculada
     */
    public double calcularArea() {
        //A=2πr(r+h)
        return 2*Math.PI*radio*(radio+altura);
    }

    /**
     * Calcula el volumen del cilindro utilizando la formula v=πhr^2
     * @return El volumen calculado
     */
    public double calcularVolumen(){
        //	v=πhr^2
    return Math.PI*altura*radio*radio;
    //returnn Math.PI*altura*Math.pow(radio,2);
}

    /**
     * Obtiene el area total calculada del cilindro
     * @return El area almacenada
     */
    public double getArea() {
        return area;
    }

    /**
     * Obtiene el volumen calculado del cilindro
     * @return El volumen almacenado
     */
    public double getVolumen() {
        return volumen;
    }


}