# bci-evaluacion
Microservicio para gestión de Usuarios. Dicho microservicio expone diferentes endpoints y utilizando el Protocolo Http GET, POST, PUT y DELETE
para gestionar la creación, modificación, consulta y borrado de usuarios.

# Ejecución del Microservicio
Para levantar el Microservicio es necesario ejecutar la clase "UserAppApplication.java" del proyecto, se recomienda utilizar un IDE.
Una vez que la aplicación se este ejecutando, se accede desde la url http://localhost:8080/swagger-ui/index.html

# ACCESO A BD H2
Para el acceso a la BD ingresar a la url: http://localhost:8080/h2-console/
 - User Name: sa
 - Password: (vacio)

# ENDPOINTS
1. Creación:
  - METODO= POST, URL=localhost:8080/users/ 
    body (JSON) = 
    ```
    {
    "name": "Enrique",
    "email": "a@dominio.cl",
    "password": "Secret1",
    "phones": [
    	{
    		"number": "1234567",
    		"citycode": "1",
    		"countrycode": "57"
    	},
    	{
    		"number": "7654321",
    		"citycode": "1",
    		"countrycode": "57"
    	}
    	]
     }
      ```
 2. Consulta:
  - METODO= GET, URL=localhost:8080/users/{id}
  donde id = id unico del usuario.
  
 3. Actualizar:
  - METODO= PUT, URL=localhost:8080/users/{id}
  body (JSON) =
  ```
  {
    "id": "6891628f-bf0b-4b5d-8f85-2a509f0839a7",
    "name": "Enrique Talavera",
    "email": "Enrique.j@correo.com",
    "password": "secret1",
    "phones": [
        {
        	"id": 1,
            "number": "44106104",
            "citycode": "9",
            "countrycode": "56"
        },
        {
        	"id": 2,
            "number": "7654321",
            "citycode": "1",
            "countrycode": "57"
        }
    ],
        "created": "2022-07-19T14:54:35.696+00:00",
    "modified": "2022-07-19T14:54:35.696+00:00",
    "last_login": "2022-07-19T14:54:35.696+00:00",
    "isactive": true
}
  ```
4. Borrar:
 - METODO= DELETE, URL=localhost:8080/users/{id}
 
# Validaciones
Para la validación de formato del correo y password se utiliza una expresión regular la cual se puede configurar desde el archivo application.properties.
las parametros disponibles:
 - email.patron: Actualmente solo acepta el formato: Caracteres especiales "-._+&" y cualquier digito. "@" nombre dominio
 - pass.patron: Actualmente acepta Primera letra en Mayuscula, 7 letras Minúsculas y 1 número.
