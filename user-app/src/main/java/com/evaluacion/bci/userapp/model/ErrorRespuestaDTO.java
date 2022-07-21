package com.evaluacion.bci.userapp.model;

public class ErrorRespuestaDTO {
    private int codigo;
    private String mensaje;

    protected ErrorRespuestaDTO(){

    }

    public ErrorRespuestaDTO (int codigo, String mensaje){
        super();
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
