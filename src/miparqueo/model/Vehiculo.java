package miparqueo.model;

public class Vehiculo {
    private int idVehiculo;
    private int idUsuario;
    private String placa;
    private String tipoVehiculo;
    private String marca;
    private String modelo;
    private String color;
    private String codigoQr;
    private boolean activo;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, int idUsuario, String placa, String tipoVehiculo, String marca, String modelo, String color, String codigoQr, boolean activo) {
        this.idVehiculo = idVehiculo;
        this.idUsuario = idUsuario;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.codigoQr = codigoQr;
        this.activo = activo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
