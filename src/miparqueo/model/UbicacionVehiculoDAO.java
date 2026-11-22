package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class UbicacionVehiculoDAO {

    public boolean insertar(UbicacionVehiculo ubicacion) {
        String sql = "INSERT INTO ubicaciones_vehiculo (id_movimiento, latitud, longitud, nivel, zona, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ubicacion.getIdMovimiento());
            ps.setDouble(2, ubicacion.getLatitud());
            ps.setDouble(3, ubicacion.getLongitud());
            ps.setString(4, ubicacion.getNivel());
            ps.setString(5, ubicacion.getZona());
            ps.setTimestamp(6, Timestamp.valueOf(ubicacion.getFechaRegistro()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UbicacionVehiculo> listarTodos() {
        List<UbicacionVehiculo> ubicaciones = new ArrayList<>();
        String sql = "SELECT * FROM ubicaciones_vehiculo";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UbicacionVehiculo ubicacion = new UbicacionVehiculo();
                ubicacion.setIdUbicacion(rs.getInt("id_ubicacion"));
                ubicacion.setIdMovimiento(rs.getInt("id_movimiento"));
                ubicacion.setLatitud(rs.getDouble("latitud"));
                ubicacion.setLongitud(rs.getDouble("longitud"));
                ubicacion.setNivel(rs.getString("nivel"));
                ubicacion.setZona(rs.getString("zona"));
                if (rs.getTimestamp("fecha_registro") != null) {
                    ubicacion.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                }
                ubicaciones.add(ubicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ubicaciones;
    }

    public UbicacionVehiculo buscarPorId(int idUbicacion) {
        String sql = "SELECT * FROM ubicaciones_vehiculo WHERE id_ubicacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUbicacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UbicacionVehiculo ubicacion = new UbicacionVehiculo();
                    ubicacion.setIdUbicacion(rs.getInt("id_ubicacion"));
                    ubicacion.setIdMovimiento(rs.getInt("id_movimiento"));
                    ubicacion.setLatitud(rs.getDouble("latitud"));
                    ubicacion.setLongitud(rs.getDouble("longitud"));
                    ubicacion.setNivel(rs.getString("nivel"));
                    ubicacion.setZona(rs.getString("zona"));
                    if (rs.getTimestamp("fecha_registro") != null) {
                        ubicacion.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                    }
                    return ubicacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(UbicacionVehiculo ubicacion) {
        String sql = "UPDATE ubicaciones_vehiculo SET id_movimiento = ?, latitud = ?, longitud = ?, nivel = ?, zona = ?, fecha_registro = ? WHERE id_ubicacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ubicacion.getIdMovimiento());
            ps.setDouble(2, ubicacion.getLatitud());
            ps.setDouble(3, ubicacion.getLongitud());
            ps.setString(4, ubicacion.getNivel());
            ps.setString(5, ubicacion.getZona());
            ps.setTimestamp(6, Timestamp.valueOf(ubicacion.getFechaRegistro()));
            ps.setInt(7, ubicacion.getIdUbicacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idUbicacion) {
        String sql = "DELETE FROM ubicaciones_vehiculo WHERE id_ubicacion = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUbicacion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
