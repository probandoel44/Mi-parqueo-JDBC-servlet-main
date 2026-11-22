package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class ParqueaderoDAO {

    public boolean insertar(Parqueadero parqueadero) {
        String sql = "INSERT INTO parqueaderos (nombre, direccion, ciudad, latitud, longitud, total_espacios, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, parqueadero.getNombre());
            ps.setString(2, parqueadero.getDireccion());
            ps.setString(3, parqueadero.getCiudad());
            ps.setDouble(4, parqueadero.getLatitud());
            ps.setDouble(5, parqueadero.getLongitud());
            ps.setInt(6, parqueadero.getTotalEspacios());
            ps.setString(7, parqueadero.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Parqueadero> listarTodos() {
        List<Parqueadero> parques = new ArrayList<>();
        String sql = "SELECT * FROM parqueaderos";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Parqueadero parqueadero = new Parqueadero();
                parqueadero.setIdParqueadero(rs.getInt("id_parqueadero"));
                parqueadero.setNombre(rs.getString("nombre"));
                parqueadero.setDireccion(rs.getString("direccion"));
                parqueadero.setCiudad(rs.getString("ciudad"));
                parqueadero.setLatitud(rs.getDouble("latitud"));
                parqueadero.setLongitud(rs.getDouble("longitud"));
                parqueadero.setTotalEspacios(rs.getInt("total_espacios"));
                parqueadero.setEstado(rs.getString("estado"));
                parques.add(parqueadero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parques;
    }

    public Parqueadero buscarPorId(int idParqueadero) {
        String sql = "SELECT * FROM parqueaderos WHERE id_parqueadero = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idParqueadero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Parqueadero parqueadero = new Parqueadero();
                    parqueadero.setIdParqueadero(rs.getInt("id_parqueadero"));
                    parqueadero.setNombre(rs.getString("nombre"));
                    parqueadero.setDireccion(rs.getString("direccion"));
                    parqueadero.setCiudad(rs.getString("ciudad"));
                    parqueadero.setLatitud(rs.getDouble("latitud"));
                    parqueadero.setLongitud(rs.getDouble("longitud"));
                    parqueadero.setTotalEspacios(rs.getInt("total_espacios"));
                    parqueadero.setEstado(rs.getString("estado"));
                    return parqueadero;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Parqueadero parqueadero) {
        String sql = "UPDATE parqueaderos SET nombre = ?, direccion = ?, ciudad = ?, latitud = ?, longitud = ?, total_espacios = ?, estado = ? WHERE id_parqueadero = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, parqueadero.getNombre());
            ps.setString(2, parqueadero.getDireccion());
            ps.setString(3, parqueadero.getCiudad());
            ps.setDouble(4, parqueadero.getLatitud());
            ps.setDouble(5, parqueadero.getLongitud());
            ps.setInt(6, parqueadero.getTotalEspacios());
            ps.setString(7, parqueadero.getEstado());
            ps.setInt(8, parqueadero.getIdParqueadero());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idParqueadero) {
        String sql = "DELETE FROM parqueaderos WHERE id_parqueadero = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idParqueadero);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
