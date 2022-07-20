package com.evaluacion.bci.userapp.exception;

public class ErrorRespuestaHandler extends Exception {
    private static final long serialVersionUID = 1L;
    private int codigo;
    private String mensaje;

   
    public ErrorRespuestaHandler (String mensaje, int codigo){
        super(mensaje);
        this.mensaje = mensaje;
        this.codigo = codigo;
    }
    
    public ErrorRespuestaHandler (){
        super();
    }

    public String getMensaje() {
        return this.mensaje;
    }


    public int getCodigo() {
        return this.codigo;
    }

}
