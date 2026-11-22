package miparqueo.usage;

import java.time.LocalDateTime;
import java.util.List;

import miparqueo.model.Espacio;
import miparqueo.model.EspacioDAO;
import miparqueo.model.Fidelizacion;
import miparqueo.model.FidelizacionDAO;
import miparqueo.model.IngresoSalida;
import miparqueo.model.IngresoSalidaDAO;
import miparqueo.model.Notificacion;
import miparqueo.model.NotificacionDAO;
import miparqueo.model.Pago;
import miparqueo.model.PagoDAO;
import miparqueo.model.Parqueadero;
import miparqueo.model.ParqueaderoDAO;
import miparqueo.model.Reserva;
import miparqueo.model.ReservaDAO;
import miparqueo.model.Usuario;
import miparqueo.model.UsuarioDAO;
import miparqueo.model.UbicacionVehiculo;
import miparqueo.model.UbicacionVehiculoDAO;
import miparqueo.model.Vehiculo;
import miparqueo.model.VehiculoDAO;

public class DemoCRUD {
    public static void main(String[] args) {
        ejecutarDemoVehiculo();
        ejecutarDemoUsuario();
    }

    private static void ejecutarDemoVehiculo() {
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        Vehiculo vehiculoNuevo = new Vehiculo();
        vehiculoNuevo.setIdUsuario(1);
        vehiculoNuevo.setPlaca("FHY-2024");
        vehiculoNuevo.setTipoVehiculo("Automóvil");
        vehiculoNuevo.setMarca("Chevrolet");
        vehiculoNuevo.setModelo("Spark");
        vehiculoNuevo.setColor("Rojo");
        vehiculoNuevo.setCodigoQr("QR12345");
        vehiculoNuevo.setActivo(true);

        boolean creado = vehiculoDAO.insertar(vehiculoNuevo);
        System.out.println("Vehículo creado: " + creado);

        List<Vehiculo> vehiculos = vehiculoDAO.listarTodos();
        System.out.println("Vehículos en la base:");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("- " + vehiculo.getPlaca() + " / " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        }

        if (!vehiculos.isEmpty()) {
            Vehiculo vehiculoExistente = vehiculos.get(0);
            vehiculoExistente.setColor("Azul");
            boolean actualizado = vehiculoDAO.actualizar(vehiculoExistente);
            System.out.println("Vehículo actualizado: " + actualizado);

            boolean eliminado = vehiculoDAO.eliminar(vehiculoExistente.getIdVehiculo());
            System.out.println("Vehículo eliminado: " + eliminado);
        }
    }

    private static void ejecutarDemoUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombres("María");
        usuarioNuevo.setApellidos("Pérez");
        usuarioNuevo.setCorreo("maria.perez@example.com");
        usuarioNuevo.setTelefono("3216549870");
        usuarioNuevo.setContrasenaHash("hash123");
        usuarioNuevo.setFechaRegistro(LocalDateTime.now());
        usuarioNuevo.setEstado("activo");

        boolean creado = usuarioDAO.insertar(usuarioNuevo);
        System.out.println("Usuario creado: " + creado);

        List<Usuario> usuarios = usuarioDAO.listarTodos();
        System.out.println("Usuarios en la base:");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNombres() + " " + usuario.getApellidos() + " / " + usuario.getCorreo());
        }

        if (!usuarios.isEmpty()) {
            Usuario usuarioExistente = usuarios.get(0);
            usuarioExistente.setTelefono("3001234567");
            boolean actualizado = usuarioDAO.actualizar(usuarioExistente);
            System.out.println("Usuario actualizado: " + actualizado);

            boolean eliminado = usuarioDAO.eliminar(usuarioExistente.getIdUsuario());
            System.out.println("Usuario eliminado: " + eliminado);
        }
    }
}
