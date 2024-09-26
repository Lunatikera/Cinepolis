    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author samoano
 */
public class SalaEntidad {

    private int id;
    private String nombre;
    private int numeroAsiento;
    private int duracionLimpieza;
    private int idSucursal;
    private boolean estaEliminada;

    public SalaEntidad() {
    }

    public SalaEntidad(int id, String nombre, int numeroAsiento, int duracionLimpieza, int iDsucursal) {
        this.id = id;
        this.nombre = nombre;
        this.numeroAsiento = numeroAsiento;
        this.duracionLimpieza = duracionLimpieza;
        this.idSucursal = iDsucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public int getDuracionLimpieza() {
        return duracionLimpieza;
    }

    public boolean isEstaEliminada() {
        return estaEliminada;
    }

    public void setEstaEliminada(boolean estaEliminada) {
        this.estaEliminada = estaEliminada;
    }

    public void setDuracionLimpieza(int duracionLimpieza) {
        this.duracionLimpieza = duracionLimpieza;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

  
}
