/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author carli
 */
public class PeliculasSucursalFiltroDTO {
    private int pagina = 1;
    public static final int LIMITE = 5;

    public PeliculasSucursalFiltroDTO() {
    }
   public PeliculasSucursalFiltroDTO(int pagina) {
       this.pagina=pagina;
    }
    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int RegresarOFFSETMySQL() {
        if (pagina <= 1) {
            return 0;
        }

        if (pagina == 2) {
            return LIMITE;
        }

        return ((int) (LIMITE * (pagina - 1)));
    }
    
    
    public void sumarPagina(){
       this.pagina++;  
    }
    
     public void restarPagina(){
        this.pagina--;
    }

  

}
