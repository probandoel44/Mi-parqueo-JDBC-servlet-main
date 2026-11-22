package miparqueo.controller;

import java.util.List;

import miparqueo.model.Vehiculo;
import miparqueo.model.VehiculoDAO;

public class VehiculoController {
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();

    public boolean guardar(Vehiculo vehiculo) {
        return vehiculoDAO.insertar(vehiculo);
    }

    public List<Vehiculo> listar() {
        return vehiculoDAO.listarTodos();
    }

    public boolean eliminar(int id) {
        return vehiculoDAO.eliminar(id);
    }
}
