/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author carli
 */
public class FiltroFuncionesDTO {
    private String dia;
    private int idSala;

    public FiltroFuncionesDTO() {
    }

    public FiltroFuncionesDTO(String Dia, int idSala) {
        this.dia = Dia;
        this.idSala = idSala;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String Dia) {
        this.dia = Dia;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }
    
    
}
