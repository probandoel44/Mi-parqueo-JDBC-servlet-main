package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class IngresoSalidaDAO {

    public boolean insertar(IngresoSalida movimiento) {
        String sql = "INSERT INTO ingresos_salidas (id_reserva, id_vehiculo, hora_ingreso, hora_salida, metodo_acceso, observaciones) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movimiento.getIdReserva());
            ps.setInt(2, movimiento.getIdVehiculo());
            ps.setTimestamp(3, Timestamp.valueOf(movimiento.getHoraIngreso()));
            ps.setTimestamp(4, movimiento.getHoraSalida() != null ? Timestamp.valueOf(movimiento.getHoraSalida()) : null);
            ps.setString(5, movimiento.getMetodoAcceso());
            ps.setString(6, movimiento.getObservaciones());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<IngresoSalida> listarTodos() {
        List<IngresoSalida> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingresos_salidas";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                IngresoSalida movimiento = new IngresoSalida();
                movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
                movimiento.setIdReserva(rs.getInt("id_reserva"));
                movimiento.setIdVehiculo(rs.getInt("id_vehiculo"));
                movimiento.setHoraIngreso(rs.getTimestamp("hora_ingreso").toLocalDateTime());
                if (rs.getTimestamp("hora_salida") != null) {
                    movimiento.setHoraSalida(rs.getTimestamp("hora_salida").toLocalDateTime());
                }
                movimiento.setMetodoAcceso(rs.getString("metodo_acceso"));
                movimiento.setObservaciones(rs.getString("observaciones"));
                lista.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public IngresoSalida buscarPorId(int idMovimiento) {
        String sql = "SELECT * FROM ingresos_salidas WHERE id_movimiento = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMovimiento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IngresoSalida movimiento = new IngresoSalida();
                    movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
                    movimiento.setIdReserva(rs.getInt("id_reserva"));
                    movimiento.setIdVehiculo(rs.getInt("id_vehiculo"));
                    movimiento.setHoraIngreso(rs.getTimestamp("hora_ingreso").toLocalDateTime());
                    if (rs.getTimestamp("hora_salida") != null) {
                        movimiento.setHoraSalida(rs.getTimestamp("hora_salida").toLocalDateTime());
                    }
                    movimiento.setMetodoAcceso(rs.getString("metodo_acceso"));
                    movimiento.setObservaciones(rs.getString("observaciones"));
                    return movimiento;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(IngresoSalida movimiento) {
        String sql = "UPDATE ingresos_salidas SET id_reserva = ?, id_vehiculo = ?, hora_ingreso = ?, hora_salida = ?, metodo_acceso = ?, observaciones = ? WHERE id_movimiento = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movimiento.getIdReserva());
            ps.setInt(2, movimiento.getIdVehiculo());
            ps.setTimestamp(3, Timestamp.valueOf(movimiento.getHoraIngreso()));
            ps.setTimestamp(4, movimiento.getHoraSalida() != null ? Timestamp.valueOf(movimiento.getHoraSalida()) : null);
            ps.setString(5, movimiento.getMetodoAcceso());
            ps.setString(6, movimiento.getObservaciones());
            ps.setInt(7, movimiento.getIdMovimiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idMovimiento) {
        String sql = "DELETE FROM ingresos_salidas WHERE id_movimiento = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMovimiento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
