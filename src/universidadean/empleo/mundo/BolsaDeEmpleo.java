/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import java.util.ArrayList;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o más con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList<Aspirante> aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo() {
        aspirantes = new ArrayList<>();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public ArrayList<Aspirante> darAspirantes() {
        ArrayList<Aspirante> copia = new ArrayList<>(aspirantes);
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     *
     * @param nombreA           El nombre del aspirante - nombreA != null
     * @param profesionA        La profesión del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA Años de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El teléfono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retornó true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        int aspiranteBuscado = buscarAspirante(nombreA);
        boolean agregado = false;
        if (aspiranteBuscado == -1) {
            Aspirante nuevoAspirante = new Aspirante(nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.add(nuevoAspirante);
            agregado = true;
        }

        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes está ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        int i, j;
        Aspirante aux;
        for (i = 0; i < aspirantes.size() - 1; i++) {
            for (j = 0; j < aspirantes.size() - i - 1; j++) {
                if (aspirantes.get(j+1).darNombre().charAt(0) < aspirantes.get(j).darNombre().charAt(0))//compara si nombre siguiente es menor que actual
                {
                    aux = aspirantes.get(j+1);//guarda en la variable aux el nombre que es menor
                    aspirantes.set(j+1,aspirantes.get(j));//intercambia la posición
                    aspirantes.set(j,aux);//intercambia la posición el nombre menor guardado en aux se pone de primeros en la posición j

                }
            }
        }

    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selección <br>
     * <b>post: </b>La lista de aspirantes está ordenada por edad
     */
    public void ordenarPorEdad() {

        int i, j,  pos;
        Aspirante menor,tmp;
        for (i = 0; i <  aspirantes.size() - 1; i++) {      // el bucle for va desde 0 hasta el tamañno del arreglo menos 1
            menor = aspirantes.get(i);                       // la variable menor es igual a aspirantes en la primera posición
            pos = i;                            // y guardamos la posición de menor
            for (j = i + 1; j <  aspirantes.size(); j++){ // hacemos otro bucle for que busca en el resto del arreglo
                if (aspirantes.get(j).darEdad() < menor.darEdad()) {           // si el arreglo en posición j es menor a menor
                    menor = aspirantes.get(j);             // menor es igual al arreglo en la posición j
                    pos = j;//actualizamos su posición
                }
            }
            if (pos != i){                      // si hay alguno menor se intercambia
                tmp = aspirantes.get(i);
                aspirantes.set(i,aspirantes.get(pos));
                aspirantes.set(pos,tmp);
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por profesión usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesión
     */
    public void ordenarPorProfesion() {
        int i, j;
        Aspirante aux;
        for (i = 0; i < aspirantes.size() - 1; i++) {
            for (j = 0; j < aspirantes.size() - i - 1; j++) {
                if (aspirantes.get(j+1).darProfesion().charAt(0) < aspirantes.get(j).darProfesion().charAt(0)) // compara si profesion siguiente es menor a profesión actual
                {
                    aux = aspirantes.get(j+1); //guarda en la variable aux la profesión que es menor
                    aspirantes.set(j+1,aspirantes.get(j)); //intercambia la posición
                    aspirantes.set(j,aux);//intercambia la posición posición menor guardada en aux se pone de primeros en la posición j

                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por años de experiencia usando el algoritmo de inserción <br>
     * <b>post: </b>La lista de aspirantes está ordenada por años de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        int p, j; //creamos p que es la posición actual
        Aspirante aux;//creamos la varible aux
        for (p = 1; p < aspirantes.size(); p++){ // creamos bucle for que va desde p=1 hasta el tamaño del arreglo
            aux = aspirantes.get(p);           // en aux guardamos el elemento de la posición actual y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux.darAniosExperiencia() < aspirantes.get(j).darAniosExperiencia())){ // mientras queden posiciones y el
                // valor de aux sea menor que los valores a la izquierda
                aspirantes.set(j+1,aspirantes.get(j));
               // cambiamos los números
                j--;               // y comparamos con todos los números a la izquierda
            }
            aspirantes.set(j+1,aux);
                  // colocamos aux en su sitio
        }

    }

    /**
     * Busca un Aspirante según su nombre y retorna la posición en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retornó la posición donde se encuentra un aspirante con el nombre dado. Si no se encuentra ningún aspirante con ese nombre se retornó -1.
     */
    public int buscarAspirante(String nombre) {
        int posicion = -1;

        for(int i=0;i<aspirantes.size();i++){
            if(aspirantes.get(i).darNombre().equalsIgnoreCase(nombre))
                return i;
        }

        return posicion;
    }

    /**
     * Busca un aspirante utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retornó la posición del aspirante con el nombre dado. Si el aspirante no existe se retornó -1.
     */
    public int buscarBinarioPorNombre(String nombre) {
        int posicion = -1;
        int ini = 0;
        int fin = aspirantes.size() - 1;


        while (ini <= fin) {
            posicion = (ini+fin) / 2;
            if ( aspirantes.get(posicion).darNombre().equalsIgnoreCase(nombre) )
                return posicion;
            else if ( aspirantes.get(posicion).darNombre().charAt(0) < nombre.charAt(0) ) {
                ini = posicion+1;
            } else {
                fin = posicion-1;
            }
        }

        return posicion;
    }


    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante más joven. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMasJoven() {
        int posicion = -1;
        Aspirante menor=aspirantes.get(0);
        for(int i=1;i<aspirantes.size();i++){
            if(aspirantes.get(i).darEdad()<menor.darEdad()){
                menor=aspirantes.get(i);
            }
        }
        for(int i=0;i<aspirantes.size();i++){
            if(menor.darEdad()==aspirantes.get(i).darEdad())return i;
        }

        return posicion;
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante con más edad. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorEdad() {
        int posicion = -1;

        Aspirante mayor=aspirantes.get(0);
        for(int i=1;i<aspirantes.size();i++){
            if(aspirantes.get(i).darEdad()>mayor.darEdad()){
                mayor=aspirantes.get(i);
            }
        }
        for(int i=0;i<aspirantes.size();i++){
            if(mayor.darEdad()==aspirantes.get(i).darEdad())return i;
        }

        return posicion;
    }

    /**
     * Busca el aspirante con más años de experiencia en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorExperiencia() {
        int posicion = -1;

        Aspirante mayor=aspirantes.get(0);
        for(int i=1;i<aspirantes.size();i++){
            if(aspirantes.get(i).darAniosExperiencia()>mayor.darAniosExperiencia()){
                mayor=aspirantes.get(i);
            }
        }
        for(int i=0;i<aspirantes.size();i++){
            if(mayor.darAniosExperiencia()==aspirantes.get(i).darAniosExperiencia())return i;
        }

        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se eliminó el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retornó true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) {
        boolean contratado = false;
        for(int i=0;i<aspirantes.size();i++){
            if(aspirantes.get(i).darNombre().equalsIgnoreCase(nombre))
                return true;
        }
        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos años de experiencia <br>
     * son menores a la cantidad de años especificada <br>
     *
     * @param aniosExperiencia La cantidad de años con relación a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) {
        int eliminados = 0;
        ArrayList<Aspirante> aspirantes2=new ArrayList<>();
        int cont = 0;

        for(int i=0;i<aspirantes.size();i++){
            if(aspirantes.get(i).darAniosExperiencia()<=aniosExperiencia){
                eliminados+=1;
            }
            else{
                aspirantes2.add(aspirantes.get(i));
            }

        }
        aspirantes.clear();
        aspirantes=aspirantes2;


        return eliminados;
    }

}
