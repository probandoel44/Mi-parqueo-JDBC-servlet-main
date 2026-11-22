package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class ReservaDAO {

    public boolean insertar(Reserva reserva) {
        String sql = "INSERT INTO reservas (id_usuario, id_vehiculo, id_espacio, codigo_reserva, fecha_reserva, hora_inicio, hora_fin_estimada, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getIdUsuario());
            ps.setInt(2, reserva.getIdVehiculo());
            ps.setInt(3, reserva.getIdEspacio());
            ps.setString(4, reserva.getCodigoReserva());
            ps.setTimestamp(5, Timestamp.valueOf(reserva.getFechaReserva()));
            ps.setTimestamp(6, Timestamp.valueOf(reserva.getHoraInicio()));
            ps.setTimestamp(7, Timestamp.valueOf(reserva.getHoraFinEstimada()));
            ps.setString(8, reserva.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reserva> listarTodos() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("id_reserva"));
                reserva.setIdUsuario(rs.getInt("id_usuario"));
                reserva.setIdVehiculo(rs.getInt("id_vehiculo"));
                reserva.setIdEspacio(rs.getInt("id_espacio"));
                reserva.setCodigoReserva(rs.getString("codigo_reserva"));
                if (rs.getTimestamp("fecha_reserva") != null) {
                    reserva.setFechaReserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
                }
                if (rs.getTimestamp("hora_inicio") != null) {
                    reserva.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
                }
                if (rs.getTimestamp("hora_fin_estimada") != null) {
                    reserva.setHoraFinEstimada(rs.getTimestamp("hora_fin_estimada").toLocalDateTime());
                }
                reserva.setEstado(rs.getString("estado"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public Reserva buscarPorId(int idReserva) {
        String sql = "SELECT * FROM reservas WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setIdReserva(rs.getInt("id_reserva"));
                    reserva.setIdUsuario(rs.getInt("id_usuario"));
                    reserva.setIdVehiculo(rs.getInt("id_vehiculo"));
                    reserva.setIdEspacio(rs.getInt("id_espacio"));
                    reserva.setCodigoReserva(rs.getString("codigo_reserva"));
                    if (rs.getTimestamp("fecha_reserva") != null) {
                        reserva.setFechaReserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
                    }
                    if (rs.getTimestamp("hora_inicio") != null) {
                        reserva.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
                    }
                    if (rs.getTimestamp("hora_fin_estimada") != null) {
                        reserva.setHoraFinEstimada(rs.getTimestamp("hora_fin_estimada").toLocalDateTime());
                    }
                    reserva.setEstado(rs.getString("estado"));
                    return reserva;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Reserva reserva) {
        String sql = "UPDATE reservas SET id_usuario = ?, id_vehiculo = ?, id_espacio = ?, codigo_reserva = ?, fecha_reserva = ?, hora_inicio = ?, hora_fin_estimada = ?, estado = ? WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getIdUsuario());
            ps.setInt(2, reserva.getIdVehiculo());
            ps.setInt(3, reserva.getIdEspacio());
            ps.setString(4, reserva.getCodigoReserva());
            ps.setTimestamp(5, Timestamp.valueOf(reserva.getFechaReserva()));
            ps.setTimestamp(6, Timestamp.valueOf(reserva.getHoraInicio()));
            ps.setTimestamp(7, Timestamp.valueOf(reserva.getHoraFinEstimada()));
            ps.setString(8, reserva.getEstado());
            ps.setInt(9, reserva.getIdReserva());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idReserva) {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
