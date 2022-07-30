# bci-evaluacion
Microservicio para gestión de Usuarios. Dicho microservicio expone diferentes endpoints y utilizando el Protocolo Http GET, POST, PUT y DELETE
para gestionar la creación, modificación, consulta y borrado de usuarios.

# Ejecución del Microservicio
Para levantar el Microservicio se abre el terminal de window y ejecutar el comando

```
java -cp {RUTA_CARPETA}\bci_evaluacion.jar com.evaluacion.bci.userapp.UserAppApplication
```

donde RUTA_CARPETA es la carpeta donde se descargo el proyecto.

Una vez que la aplicación se este ejecutando, se accede desde la url http://localhost:8080/swagger-ui/index.html

# Diagramas
Se agrega Diagrama de Componentes y Modelo ER de la solución.

# ACCESO A BD H2
Para el acceso a la BD ingresar a la url: http://localhost:8080/h2-console/
 - User Name: sa
 - Password: (vacio)

# Validaciones
Para la validación de formato del correo y password se utiliza una expresión regular la cual se puede configurar desde el archivo application.properties.
las parametros disponibles:
 - email.patron: Actualmente solo acepta el formato: Caracteres especiales "-._+&" y cualquier digito. "@" nombre dominio
 - pass.patron: Actualmente acepta Primera letra en Mayuscula, 7 letras Minúsculas y 1 número.
