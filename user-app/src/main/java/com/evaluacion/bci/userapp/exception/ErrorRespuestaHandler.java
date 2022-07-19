package com.evaluacion.bci.userapp.exception;

public class ErrorRespuestaHandler extends Exception{
    private static final long serialVersionUID = 1L;
    private String mensaje;

   
    public ErrorRespuestaHandler (String mensaje){
        super(mensaje);
        this.mensaje = mensaje;
    }
    
    public ErrorRespuestaHandler (){
        super();
    }

    public String getMensaje() {
        return this.mensaje;
    }

}
