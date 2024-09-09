package org.example.registro.de;
import java.util.HashMap;
import java.util.Map;
public class usuario {
    private String usuarioid;
    private String nombre;
    private String correo;
    private Map<String, Cuenta> cuentas;

    public usuario(String usuarioid, String nombre, String correo) {
        this.usuarioid = usuarioid;
        this.nombre = nombre;
        this.correo = correo;
        this.cuentas = new HashMap<>();
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public String getNombre() {
        return usuarioid;
    }

    public Map<String, Cuenta> getCuentas() {
    return cuentas;
    }

    public boolean agregarCuentas(Cuenta cuenta){
        if (cuentas.containsKey(cuenta.getCuentaId())) {
            return false;
        }
        cuentas.put(cuenta.getCuentaId(), cuenta);
        return true;
    }
    public Cuenta obtenerCuenta(String cuentaId){
        return cuentas.get(cuentaId);
    }
}
