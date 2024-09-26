/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author samoano
 */
public class ConsultaFuncionDTO {
    private String dia;
    private int idSala;
    private int idPelicula;

    public ConsultaFuncionDTO() {
    }

    public ConsultaFuncionDTO(String dia, int idSala, int idPelicula) {
        this.dia = dia;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    
}
