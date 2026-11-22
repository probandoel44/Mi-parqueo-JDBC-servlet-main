package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class FidelizacionDAO {

    public boolean insertar(Fidelizacion fidelizacion) {
        String sql = "INSERT INTO fidelizacion (id_usuario, puntos_acumulados, nivel, ultima_actualizacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fidelizacion.getIdUsuario());
            ps.setInt(2, fidelizacion.getPuntosAcumulados());
            ps.setString(3, fidelizacion.getNivel());
            ps.setTimestamp(4, Timestamp.valueOf(fidelizacion.getUltimaActualizacion()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Fidelizacion> listarTodos() {
        List<Fidelizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM fidelizacion";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fidelizacion fidelizacion = new Fidelizacion();
                fidelizacion.setIdFidelizacion(rs.getInt("id_fidelizacion"));
                fidelizacion.setIdUsuario(rs.getInt("id_usuario"));
                fidelizacion.setPuntosAcumulados(rs.getInt("puntos_acumulados"));
                fidelizacion.setNivel(rs.getString("nivel"));
                if (rs.getTimestamp("ultima_actualizacion") != null) {
                    fidelizacion.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion").toLocalDateTime());
                }
                lista.add(fidelizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Fidelizacion buscarPorId(int idFidelizacion) {
        String sql = "SELECT * FROM fidelizacion WHERE id_fidelizacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFidelizacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Fidelizacion fidelizacion = new Fidelizacion();
                    fidelizacion.setIdFidelizacion(rs.getInt("id_fidelizacion"));
                    fidelizacion.setIdUsuario(rs.getInt("id_usuario"));
                    fidelizacion.setPuntosAcumulados(rs.getInt("puntos_acumulados"));
                    fidelizacion.setNivel(rs.getString("nivel"));
                    if (rs.getTimestamp("ultima_actualizacion") != null) {
                        fidelizacion.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion").toLocalDateTime());
                    }
                    return fidelizacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Fidelizacion fidelizacion) {
        String sql = "UPDATE fidelizacion SET id_usuario = ?, puntos_acumulados = ?, nivel = ?, ultima_actualizacion = ? WHERE id_fidelizacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fidelizacion.getIdUsuario());
            ps.setInt(2, fidelizacion.getPuntosAcumulados());
            ps.setString(3, fidelizacion.getNivel());
            ps.setTimestamp(4, Timestamp.valueOf(fidelizacion.getUltimaActualizacion()));
            ps.setInt(5, fidelizacion.getIdFidelizacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idFidelizacion) {
        String sql = "DELETE FROM fidelizacion WHERE id_fidelizacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFidelizacion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
