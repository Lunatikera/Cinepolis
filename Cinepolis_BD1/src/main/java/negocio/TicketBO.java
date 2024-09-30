/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.TicketDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ITicketDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class TicketBO implements ITicketBO {
    private ITicketDAO ticketDAO;

    public TicketBO(ITicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public List<TicketDTO> obtenerVentasFuncionesPorCliente(int clienteId, int limite, int pagina) throws NegocioException {
  try {
            // Calculamos el offset para la paginación
           int offset=utilerias.Herramientas.RegresarOFFSETMySQL(limite, pagina);
            // Llamamos al método del DAO para obtener las ventas paginadas
            return ticketDAO.obtenerVentasFuncionesPorCliente(clienteId, limite, offset);
        } catch (PersistenciaException e) {
            // Capturamos cualquier excepción y lanzamos una excepción de negocio
            throw new NegocioException("Error al obtener las ventas y funciones para el cliente con ID " + clienteId, e);
        }
    }
}
    

