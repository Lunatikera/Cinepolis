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
    private int idSucursal;
    private int idPelicula;

    public ConsultaFuncionDTO() {
    }

    public ConsultaFuncionDTO(String dia, int idSucursal, int idPelicula) {
        this.dia = dia;
        this.idSucursal = idSucursal;
        this.idPelicula = idPelicula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getIdSala() {
        return idSucursal;
    }

    public void setIdSala(int idSala) {
        this.idSucursal = idSala;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    @Override
    public String toString() {
        return "ConsultaFuncionDTO{" + "dia=" + dia + ", idSucursal=" + idSucursal + ", idPelicula=" + idPelicula + '}';
    }
    
    
}
