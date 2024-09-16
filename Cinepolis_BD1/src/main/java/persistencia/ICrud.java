/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import java.util.List;

/**
 *
 * @author carli
 */
public interface ICrud <T> {
  // Crear una nueva entidad
   public void guardar(T entidad) throws PersistenciaException;

    // Leer una entidad por su identificador
   public T leerPorID(int id) throws PersistenciaException;

    // Leer todas las entidades
    public List<T> leerTodos() throws PersistenciaException;

    // Actualizar una entidad existente
    public void editar(T entidad) throws PersistenciaException;

    // Eliminar una entidad por su identificador
    public void eliminarPorID(int id) throws PersistenciaException;
}

