/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author carli
 */
public class PeliculaDTO {

    private int id;
    private String titulo;
    private String sinopsis;
    private String pais;
    private String link_trailer;
    private int duracion;
    private String cartel;
    private String clasificacion;
    private String clasificacionDescripcion;

    public PeliculaDTO() {
    }

    public PeliculaDTO(int id, String titulo, String sinopsis, String pais, String link_trailer, int duracion, String cartel, String clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.pais = pais;
        this.link_trailer = link_trailer;
        this.duracion = duracion;
        this.cartel = cartel;
        this.clasificacion = clasificacion;
    }

    public PeliculaDTO(String titulo, String sinopsis, String pais, String link_trailer, int duracion, String cartel, String clasificacion) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.pais = pais;
        this.link_trailer = link_trailer;
        this.duracion = duracion;
        this.cartel = cartel;
        this.clasificacion = clasificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLink_trailer() {
        return link_trailer;
    }

    public void setLink_trailer(String link_trailer) {
        this.link_trailer = link_trailer;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getClasificacionDescripcion() {
        return clasificacionDescripcion;
    }

    public void setClasificacionDescripcion(String clasificacionDescripcion) {
        this.clasificacionDescripcion = clasificacionDescripcion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PeliculaDTO{");
        sb.append("id=").append(id);
        sb.append(", titulo=").append(titulo);
        sb.append(", sinopsis=").append(sinopsis);
        sb.append(", pais=").append(pais);
        sb.append(", link_trailer=").append(link_trailer);
        sb.append(", duracion=").append(duracion);
        sb.append(", cartel=").append(cartel);
        sb.append(", clasificacion=").append(clasificacion);
        sb.append('}');
        return sb.toString();
    }

}
