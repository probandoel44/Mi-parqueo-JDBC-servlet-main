package miparqueo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

import miparqueo.controller.VehiculoController;
import miparqueo.model.Usuario;
import miparqueo.model.UsuarioDAO;
import miparqueo.model.Vehiculo;

@WebServlet(name = "VehiculoServlet", urlPatterns = {"/vehiculos"})
public class VehiculoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final VehiculoController controller = new VehiculoController();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Vehiculo> vehiculos = controller.listar();
        String content = buildVehiclePage(request, vehiculos, null);
        writeHtml(response, "Mi Parqueo", content);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setNombres("Usuario");
        usuario.setApellidos("Web");
        usuario.setCorreo("web@miparqueo.local");
        usuario.setTelefono("0000000000");
        usuario.setContrasenaHash("hash-web");
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setEstado("activo");

        boolean usuarioCreado = usuarioDAO.insertar(usuario);
        int idUsuario = usuarioCreado ? obtenerUltimoUsuarioId() : obtenerUsuarioPorCorreo("web@miparqueo.local");

        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String codigoQr = "QR-" + System.currentTimeMillis();

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdUsuario(idUsuario);
        vehiculo.setPlaca(placa);
        vehiculo.setTipoVehiculo("Automóvil");
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setColor("Sin definir");
        vehiculo.setCodigoQr(codigoQr);
        vehiculo.setActivo(true);

        boolean guardado = controller.guardar(vehiculo);
        String message = guardado ? "Vehículo guardado correctamente" : "No se pudo guardar el vehículo";
        String content = "<section class='card'>"
                + "<h1>" + escapeHtml(message) + "</h1>"
                + "<p><a href='" + request.getContextPath() + "/vehiculos'>Volver al formulario</a></p>"
                + "</section>";
        writeHtml(response, "Resultado", content);
    }

    private int obtenerUltimoUsuarioId() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        if (usuarios.isEmpty()) {
            return 1;
        }
        return usuarios.get(usuarios.size() - 1).getIdUsuario();
    }

    private int obtenerUsuarioPorCorreo(String correo) {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario usuario : usuarios) {
            if (correo.equals(usuario.getCorreo())) {
                return usuario.getIdUsuario();
            }
        }
        return 1;
    }

    private String buildVehiclePage(HttpServletRequest request, List<Vehiculo> vehiculos, String message) {
        StringBuilder html = new StringBuilder();
        html.append("<section class='card'>");
        html.append("<h1>Gestión de vehículos</h1>");
        if (message != null && !message.isEmpty()) {
            html.append("<p class='message'>").append(escapeHtml(message)).append("</p>");
        }
        html.append("<form method='post' action='" + request.getContextPath() + "/vehiculos'>");
        html.append("<label>Placa <input type='text' name='placa' required></label>");
        html.append("<label>Marca <input type='text' name='marca'></label>");
        html.append("<label>Modelo <input type='text' name='modelo'></label>");
        html.append("<button type='submit'>Guardar</button>");
        html.append("</form>");
        html.append("</section>");

        html.append("<section class='card'>");
        html.append("<h2>Vehículos registrados</h2>");
        html.append("<ul>");
        if (vehiculos.isEmpty()) {
            html.append("<li>No hay vehículos registrados aún.</li>");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                html.append("<li>").append(escapeHtml(vehiculo.getPlaca()))
                        .append(" - ")
                        .append(escapeHtml(vehiculo.getMarca()))
                        .append(" ")
                        .append(escapeHtml(vehiculo.getModelo()))
                        .append("</li>");
            }
        }
        html.append("</ul>");
        html.append("</section>");
        return html.toString();
    }

    private void writeHtml(HttpServletResponse response, String title, String content) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>" + escapeHtml(title) + "</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: #f4f7fb; color: #1f2937; margin: 0; padding: 24px; }");
        out.println(".card { background: white; border-radius: 10px; padding: 20px; margin-bottom: 16px; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }");
        out.println("form { display: flex; flex-direction: column; gap: 10px; max-width: 320px; }");
        out.println("input, button { padding: 8px; border-radius: 6px; border: 1px solid #cbd5e1; }");
        out.println("button { background: #2563eb; color: white; border: none; cursor: pointer; }");
        out.println(".message { color: #166534; font-weight: bold; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println(content);
        out.println("</body>");
        out.println("</html>");
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
