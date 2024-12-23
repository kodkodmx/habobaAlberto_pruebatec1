# Gestión de Empleados

Este proyecto es una aplicación de backend para la gestión de empleados de una empresa. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Borrar) utilizando JPA para interactuar con una base de datos.

## Funcionalidades

- **Agregar un nuevo empleado:** Permite ingresar información sobre un nuevo empleado, incluyendo nombre, apellido, cargo, salario y fecha de inicio.
- **Listar empleados:** Visualiza la lista de todos los empleados en la base de datos.
- **Actualizar información de un empleado:** Modifica la información de un empleado existente.
- **Eliminar un empleado:** Elimina un empleado de la base de datos.
- **Buscar empleados por cargo:** Busca empleados por su cargo y muestra una lista de los empleados que tienen ese cargo.
- **Menú ASCII interactivo:** Proporciona un menú atractivo para la interacción del usuario.

## Requisitos Técnicos

- **Lenguaje:** Java
- **Persistencia:** JPA (Java Persistence API)
- **Base de Datos:** Nombre de la base de datos: `empleados`
- **Control de Versiones:** GitHub (nombre del repositorio: `albertoHaboba_pruebatec1`)

## Configuración y Ejecución

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/kodkodmx/albertoHaboba_pruebatec1.git
   cd albertoHaboba_pruebatec1
   ```
## Configurar la base de datos:

Asegúrate de tener una base de datos llamada empleados.
Ejecuta el archivo empleados.sql para configurar las tablas necesarias.

## Compilar el proyecto:

Si estás usando Maven:
  ```bash
  mvn clean install
  ```
Si estás usando Gradle:
  ```bash
  gradle build
  ```          
## Ejecutar la aplicación:
Puedes ejecutar la aplicación desde tu IDE favorito o desde la línea de comandos:
 ```bash
  java -cp target/tu-archivo-jar.jar com.softek.habobaalberto_pruebatec1.HabobaAlberto_pruebatec1
  ```
## Supuestos
Se asume que los usuarios tienen permisos para acceder y modificar la base de datos.
La validación de datos asegura que los campos obligatorios no estén vacíos.
Se utiliza un menú ASCII para mejorar la experiencia del usuario.

## Manejo de Excepciones
La aplicación maneja errores y excepciones, como registros duplicados y búsquedas de empleados inexistentes, para asegurar una experiencia de usuario fluida.

## Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue los pasos a continuación para contribuir:

## Haz un fork del proyecto.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad').
Sube tus cambios a la rama (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.

##Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.



### Notas

- **Personalización:** Asegúrate de personalizar el contenido del `README.md` para que refleje con precisión tu proyecto, especialmente en las secciones de configuración y ejecución.
- **Enlaces:** Si tienes un repositorio en GitHub, actualiza los enlaces de clonación y contribución.
- **Licencia:** Incluye un archivo de licencia si es necesario, y asegúrate de que el `README.md` lo mencione.

