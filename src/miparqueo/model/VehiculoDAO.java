package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class VehiculoDAO {

    public boolean insertar(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (id_usuario, placa, tipo_vehiculo, marca, modelo, color, codigo_qr, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehiculo.getIdUsuario());
            ps.setString(2, vehiculo.getPlaca());
            ps.setString(3, vehiculo.getTipoVehiculo());
            ps.setString(4, vehiculo.getMarca());
            ps.setString(5, vehiculo.getModelo());
            ps.setString(6, vehiculo.getColor());
            ps.setString(7, vehiculo.getCodigoQr());
            ps.setInt(8, vehiculo.isActivo() ? 1 : 0);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Vehiculo> listarTodos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                vehiculo.setIdUsuario(rs.getInt("id_usuario"));
                vehiculo.setPlaca(rs.getString("placa"));
                vehiculo.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setColor(rs.getString("color"));
                vehiculo.setCodigoQr(rs.getString("codigo_qr"));
                vehiculo.setActivo(rs.getInt("activo") == 1);
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    public Vehiculo buscarPorId(int idVehiculo) {
        String sql = "SELECT * FROM vehiculos WHERE id_vehiculo = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
                    vehiculo.setIdUsuario(rs.getInt("id_usuario"));
                    vehiculo.setPlaca(rs.getString("placa"));
                    vehiculo.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                    vehiculo.setMarca(rs.getString("marca"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setColor(rs.getString("color"));
                    vehiculo.setCodigoQr(rs.getString("codigo_qr"));
                    vehiculo.setActivo(rs.getInt("activo") == 1);
                    return vehiculo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET id_usuario = ?, placa = ?, tipo_vehiculo = ?, marca = ?, modelo = ?, color = ?, codigo_qr = ?, activo = ? WHERE id_vehiculo = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehiculo.getIdUsuario());
            ps.setString(2, vehiculo.getPlaca());
            ps.setString(3, vehiculo.getTipoVehiculo());
            ps.setString(4, vehiculo.getMarca());
            ps.setString(5, vehiculo.getModelo());
            ps.setString(6, vehiculo.getColor());
            ps.setString(7, vehiculo.getCodigoQr());
            ps.setInt(8, vehiculo.isActivo() ? 1 : 0);
            ps.setInt(9, vehiculo.getIdVehiculo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idVehiculo) {
        String sql = "DELETE FROM vehiculos WHERE id_vehiculo = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
