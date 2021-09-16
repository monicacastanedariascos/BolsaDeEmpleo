/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnolog�a de la Informaci�n y Comunicaciones
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
 * En el vector de aspirantes no hay dos o m�s con el mismo nombre
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
    // M�todos
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
     * @param profesionA        La profesi�n del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA A�os de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El tel�fono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retorn� true si el aspirante fue adicionado o false de lo contrario
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
     * <b>post: </b>La lista de aspirantes est� ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        int i, j;
        Aspirante aux;
        for (i = 0; i < aspirantes.size() - 1; i++) {
            for (j = 0; j < aspirantes.size() - i - 1; j++) {
                if (aspirantes.get(j+1).darNombre().charAt(0) < aspirantes.get(j).darNombre().charAt(0))//compara si nombre siguiente es menor que actual
                {
                    aux = aspirantes.get(j+1);//guarda en la variable aux el nombre que es menor
                    aspirantes.set(j+1,aspirantes.get(j));//intercambia la posici�n
                    aspirantes.set(j,aux);//intercambia la posici�n el nombre menor guardado en aux se pone de primeros en la posici�n j

                }
            }
        }

    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selecci�n <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por edad
     */
    public void ordenarPorEdad() {

        int i, j,  pos;
        Aspirante menor,tmp;
        for (i = 0; i <  aspirantes.size() - 1; i++) {      // el bucle for va desde 0 hasta el tama�no del arreglo menos 1
            menor = aspirantes.get(i);                       // la variable menor es igual a aspirantes en la primera posici�n
            pos = i;                            // y guardamos la posici�n de menor
            for (j = i + 1; j <  aspirantes.size(); j++){ // hacemos otro bucle for que busca en el resto del arreglo
                if (aspirantes.get(j).darEdad() < menor.darEdad()) {           // si el arreglo en posici�n j es menor a menor
                    menor = aspirantes.get(j);             // menor es igual al arreglo en la posici�n j
                    pos = j;//actualizamos su posici�n
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
     * Organiza la lista de aspirantes por profesi�n usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesi�n
     */
    public void ordenarPorProfesion() {
        int i, j;
        Aspirante aux;
        for (i = 0; i < aspirantes.size() - 1; i++) {
            for (j = 0; j < aspirantes.size() - i - 1; j++) {
                if (aspirantes.get(j+1).darProfesion().charAt(0) < aspirantes.get(j).darProfesion().charAt(0)) // compara si profesion siguiente es menor a profesi�n actual
                {
                    aux = aspirantes.get(j+1); //guarda en la variable aux la profesi�n que es menor
                    aspirantes.set(j+1,aspirantes.get(j)); //intercambia la posici�n
                    aspirantes.set(j,aux);//intercambia la posici�n posici�n menor guardada en aux se pone de primeros en la posici�n j

                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por a�os de experiencia usando el algoritmo de inserci�n <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por a�os de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        int p, j; //creamos p que es la posici�n actual
        Aspirante aux;//creamos la varible aux
        for (p = 1; p < aspirantes.size(); p++){ // creamos bucle for que va desde p=1 hasta el tama�o del arreglo
            aux = aspirantes.get(p);           // en aux guardamos el elemento de la posici�n actual y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux.darAniosExperiencia() < aspirantes.get(j).darAniosExperiencia())){ // mientras queden posiciones y el
                // valor de aux sea menor que los valores a la izquierda
                aspirantes.set(j+1,aspirantes.get(j));
               // cambiamos los n�meros
                j--;               // y comparamos con todos los n�meros a la izquierda
            }
            aspirantes.set(j+1,aux);
                  // colocamos aux en su sitio
        }

    }

    /**
     * Busca un Aspirante seg�n su nombre y retorna la posici�n en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retorn� la posici�n donde se encuentra un aspirante con el nombre dado. Si no se encuentra ning�n aspirante con ese nombre se retorn� -1.
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
     * Busca un aspirante utilizando una b�squeda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retorn� la posici�n del aspirante con el nombre dado. Si el aspirante no existe se retorn� -1.
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
     * @return Se retorn� la posici�n donde se encuentra el aspirante m�s joven. Si no hay aspirantes en la bolsa se retorn� -1
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
     * @return Se retorn� la posici�n donde se encuentra el aspirante con m�s edad. Si no hay aspirantes en la bolsa se retorn� -1
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
     * Busca el aspirante con m�s a�os de experiencia en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retorn� -1
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
     * <b>post: </b>Se elimin� el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retorn� true si el aspirante estaba registrado en la bolsa o false de lo contrario
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
     * Elimina todos los aspirantes de la bolsa cuyos a�os de experiencia <br>
     * son menores a la cantidad de a�os especificada <br>
     *
     * @param aniosExperiencia La cantidad de a�os con relaci�n a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
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
