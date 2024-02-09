package com.bootcamp.gestor;


import org.springframework.validation.BindingResult;

public class ErrorHandler {
	public String inputValidate(BindingResult bindingResult){
        StringBuilder errors = new StringBuilder(); // Usamos un StringBuilder para mayor eficiencia

        // recorremos todos los errores y los guardamos en nuestra variable
        bindingResult.getFieldErrors().forEach((error) -> {
            String campo = error.getField().toString();
            String errMsj = error.getDefaultMessage().toString();
            errors.append(campo).append(": ").append(errMsj).append("\n"); // Usamos append para concatenar al StringBuilder
        });

        // retornamos los errores (campo:mensaje)
        return errors.toString();
    }

}
