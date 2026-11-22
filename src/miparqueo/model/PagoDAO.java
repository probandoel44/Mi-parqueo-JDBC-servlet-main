package miparqueo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import miparqueo.connection.ConexionBD;

public class PagoDAO {

    public boolean insertar(Pago pago) {
        String sql = "INSERT INTO pagos (id_reserva, monto, metodo_pago, estado_pago, fecha_pago, referencia_transaccion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pago.getIdReserva());
            ps.setDouble(2, pago.getMonto());
            ps.setString(3, pago.getMetodoPago());
            ps.setString(4, pago.getEstadoPago());
            ps.setTimestamp(5, Timestamp.valueOf(pago.getFechaPago()));
            ps.setString(6, pago.getReferenciaTransaccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pago> listarTodos() {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pagos";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdReserva(rs.getInt("id_reserva"));
                pago.setMonto(rs.getDouble("monto"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setEstadoPago(rs.getString("estado_pago"));
                if (rs.getTimestamp("fecha_pago") != null) {
                    pago.setFechaPago(rs.getTimestamp("fecha_pago").toLocalDateTime());
                }
                pago.setReferenciaTransaccion(rs.getString("referencia_transaccion"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }

    public Pago buscarPorId(int idPago) {
        String sql = "SELECT * FROM pagos WHERE id_pago = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pago pago = new Pago();
                    pago.setIdPago(rs.getInt("id_pago"));
                    pago.setIdReserva(rs.getInt("id_reserva"));
                    pago.setMonto(rs.getDouble("monto"));
                    pago.setMetodoPago(rs.getString("metodo_pago"));
                    pago.setEstadoPago(rs.getString("estado_pago"));
                    if (rs.getTimestamp("fecha_pago") != null) {
                        pago.setFechaPago(rs.getTimestamp("fecha_pago").toLocalDateTime());
                    }
                    pago.setReferenciaTransaccion(rs.getString("referencia_transaccion"));
                    return pago;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Pago pago) {
        String sql = "UPDATE pagos SET id_reserva = ?, monto = ?, metodo_pago = ?, estado_pago = ?, fecha_pago = ?, referencia_transaccion = ? WHERE id_pago = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pago.getIdReserva());
            ps.setDouble(2, pago.getMonto());
            ps.setString(3, pago.getMetodoPago());
            ps.setString(4, pago.getEstadoPago());
            ps.setTimestamp(5, Timestamp.valueOf(pago.getFechaPago()));
            ps.setString(6, pago.getReferenciaTransaccion());
            ps.setInt(7, pago.getIdPago());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idPago) {
        String sql = "DELETE FROM pagos WHERE id_pago = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
