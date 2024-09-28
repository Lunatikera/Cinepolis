/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */
public class FuncionDTO {
    private int id;
    private String dia;
    private Timestamp hora;
    private Timestamp horaFinal;
    private int idSala;
    private int idPelicula;

    public FuncionDTO(int id, String dia, Timestamp hora, Timestamp horaFinal, int idSala, int idPelicula) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.horaFinal = horaFinal;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
    }

    public FuncionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public Timestamp getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Timestamp horaFinal) {
        this.horaFinal = horaFinal;
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
