/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author carli
 */
public class TablaPeliculaSucursalDTO {
    private int idPelicula;
    private String titulo;
    private int duracionMinutos;
    private String pais;
    private String clasificacion;

    public TablaPeliculaSucursalDTO(int idPelicula, String titulo, int duracionMinutos, String pais, String clasificacion) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.pais = pais;
        this.clasificacion = clasificacion;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    
}
