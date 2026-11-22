package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class NotificacionDAO {

    public boolean insertar(Notificacion notificacion) {
        String sql = "INSERT INTO notificaciones (id_usuario, titulo, mensaje, tipo, fecha_envio, leida) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, notificacion.getIdUsuario());
            ps.setString(2, notificacion.getTitulo());
            ps.setString(3, notificacion.getMensaje());
            ps.setString(4, notificacion.getTipo());
            ps.setTimestamp(5, Timestamp.valueOf(notificacion.getFechaEnvio()));
            ps.setInt(6, notificacion.isLeida() ? 1 : 0);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Notificacion> listarTodos() {
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();
                notificacion.setIdNotificacion(rs.getInt("id_notificacion"));
                notificacion.setIdUsuario(rs.getInt("id_usuario"));
                notificacion.setTitulo(rs.getString("titulo"));
                notificacion.setMensaje(rs.getString("mensaje"));
                notificacion.setTipo(rs.getString("tipo"));
                if (rs.getTimestamp("fecha_envio") != null) {
                    notificacion.setFechaEnvio(rs.getTimestamp("fecha_envio").toLocalDateTime());
                }
                notificacion.setLeida(rs.getInt("leida") == 1);
                notificaciones.add(notificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificaciones;
    }

    public Notificacion buscarPorId(int idNotificacion) {
        String sql = "SELECT * FROM notificaciones WHERE id_notificacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNotificacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Notificacion notificacion = new Notificacion();
                    notificacion.setIdNotificacion(rs.getInt("id_notificacion"));
                    notificacion.setIdUsuario(rs.getInt("id_usuario"));
                    notificacion.setTitulo(rs.getString("titulo"));
                    notificacion.setMensaje(rs.getString("mensaje"));
                    notificacion.setTipo(rs.getString("tipo"));
                    if (rs.getTimestamp("fecha_envio") != null) {
                        notificacion.setFechaEnvio(rs.getTimestamp("fecha_envio").toLocalDateTime());
                    }
                    notificacion.setLeida(rs.getInt("leida") == 1);
                    return notificacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Notificacion notificacion) {
        String sql = "UPDATE notificaciones SET id_usuario = ?, titulo = ?, mensaje = ?, tipo = ?, fecha_envio = ?, leida = ? WHERE id_notificacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, notificacion.getIdUsuario());
            ps.setString(2, notificacion.getTitulo());
            ps.setString(3, notificacion.getMensaje());
            ps.setString(4, notificacion.getTipo());
            ps.setTimestamp(5, Timestamp.valueOf(notificacion.getFechaEnvio()));
            ps.setInt(6, notificacion.isLeida() ? 1 : 0);
            ps.setInt(7, notificacion.getIdNotificacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idNotificacion) {
        String sql = "DELETE FROM notificaciones WHERE id_notificacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNotificacion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
