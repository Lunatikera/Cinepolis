/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.TicketDTO;
import java.util.List;

/**
 *
 * @author carli
 */
public interface ITicketBO {
        public List<TicketDTO> obtenerVentasFuncionesPorCliente(int clienteId, int limit, int pagina) throws NegocioException ;

}
