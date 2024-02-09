package com.bootcamp.gestor;


import org.springframework.validation.BindingResult;

public class ErrorHandler {
    
    public String validacionInputs(BindingResult bindingResult) {
        
        //creo una variable (key, value) para guardar los errores
        String errors = "";
        
        //recorro todos los errores y los guardo en mi variable
        bindingResult.getFieldErrors().forEach((error) -> {
            String campo = error.getField();
            String errMsj = error.getDefaultMessage();
            errors.concat(campo+": "+errMsj+"\n");
        });
        
        //retorno los errores (campo:mensaje)
        return errors;
        
    }

}
