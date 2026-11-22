package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class EspacioDAO {

    public boolean insertar(Espacio espacio) {
        String sql = "INSERT INTO espacios (id_parqueadero, codigo_espacio, tipo_espacio, estado, nivel, zona) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, espacio.getIdParqueadero());
            ps.setString(2, espacio.getCodigoEspacio());
            ps.setString(3, espacio.getTipoEspacio());
            ps.setString(4, espacio.getEstado());
            ps.setString(5, espacio.getNivel());
            ps.setString(6, espacio.getZona());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Espacio> listarTodos() {
        List<Espacio> espacios = new ArrayList<>();
        String sql = "SELECT * FROM espacios";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Espacio espacio = new Espacio();
                espacio.setIdEspacio(rs.getInt("id_espacio"));
                espacio.setIdParqueadero(rs.getInt("id_parqueadero"));
                espacio.setCodigoEspacio(rs.getString("codigo_espacio"));
                espacio.setTipoEspacio(rs.getString("tipo_espacio"));
                espacio.setEstado(rs.getString("estado"));
                espacio.setNivel(rs.getString("nivel"));
                espacio.setZona(rs.getString("zona"));
                espacios.add(espacio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return espacios;
    }

    public Espacio buscarPorId(int idEspacio) {
        String sql = "SELECT * FROM espacios WHERE id_espacio = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEspacio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Espacio espacio = new Espacio();
                    espacio.setIdEspacio(rs.getInt("id_espacio"));
                    espacio.setIdParqueadero(rs.getInt("id_parqueadero"));
                    espacio.setCodigoEspacio(rs.getString("codigo_espacio"));
                    espacio.setTipoEspacio(rs.getString("tipo_espacio"));
                    espacio.setEstado(rs.getString("estado"));
                    espacio.setNivel(rs.getString("nivel"));
                    espacio.setZona(rs.getString("zona"));
                    return espacio;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Espacio espacio) {
        String sql = "UPDATE espacios SET id_parqueadero = ?, codigo_espacio = ?, tipo_espacio = ?, estado = ?, nivel = ?, zona = ? WHERE id_espacio = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, espacio.getIdParqueadero());
            ps.setString(2, espacio.getCodigoEspacio());
            ps.setString(3, espacio.getTipoEspacio());
            ps.setString(4, espacio.getEstado());
            ps.setString(5, espacio.getNivel());
            ps.setString(6, espacio.getZona());
            ps.setInt(7, espacio.getIdEspacio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idEspacio) {
        String sql = "DELETE FROM espacios WHERE id_espacio = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEspacio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
