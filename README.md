# Gestión de Empleados

Este proyecto es una aplicación de backend para la gestión de empleados de una empresa. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Borrar) utilizando JPA para interactuar con una base de datos.

## Video de prueba y foto del Menu ASCII
![image](https://github.com/user-attachments/assets/59b1e1ca-32c5-4635-9495-90ace46e3663)

[Video en YouTube](https://youtu.be/PcpvcGs00jw)

## Funcionalidades

- **Menú ASCII interactivo:** Proporciona un menú atractivo para la interacción del usuario.
- **Agregar un nuevo empleado:** Permite ingresar información sobre un nuevo empleado, incluyendo nombre, apellido, cargo, salario y fecha de inicio.
- **Listar empleados:** Visualiza la lista de todos los empleados en la base de datos.
- **Buscar empleados por cargo:** Busca empleados por su cargo y muestra una lista de los empleados que tienen ese cargo.
- **Actualizar información de un empleado:** Modifica la información de un empleado existente.
- **Eliminar un empleado:** Elimina un empleado de la base de datos.

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
Ejecuta el archivo [empleados.sql](https://github.com/kodkodmx/habobaAlberto_pruebatec1/blob/46ae5780609ee553a57a638aeabcbb6e5dc6c387/empleado.sql) para configurar las tablas necesarias.

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
- Se utiliza un menú ASCII para mejorar la experiencia del usuario.
- Se asume que los usuarios tienen permisos para acceder y modificar la base de datos.
- La validación de datos realizada asegura que los campos obligatorios no estén vacíos y sean validos.
- Sea asigna automaticamente por la base de datos un numero de identificacion ID a cada empledo que se registra. La cual se maneja como la clave principal (Primary Key) en la base de datos. El numero es unico e irrepetible.
- Se usa la clase BigDecimal en java y Decimal en la base de datos para evitar perdida de exactitud y esto se logra especificando los atributos de esa columna en especifico manualmente con la etiqueta @column.
- No se implementa la busqueda personalizada en la base de datos para cumplir con la consigna "Utiliza colecciones para gestionar los datos antes de interactuar con la base de datos."
- Se cumple con el extra de "La aplicación debe ser capaz de manejar errores y excepciones de manera apropiada, como la gestión de registros duplicados o la búsqueda de empleados que no existen, etc." ademas para la verificacion de formato correcto en salarios, fechas, ids y cargos. **Sin embargo no se usa try and catch en caso de empleado existente ya que eso no es un error o excepcion, sino mas bien es parte del flujo normal de la applicacion ya que en si no son duplicados porque el id lo maneja automaticamente la base de datos.**
- Se cumple con principios de programacion como No te repitas (DRY), Manténlo simple (KISS), No lo vas a necesitar (YAGNI), SOLID, Separación de responsabilidades, Encapsulamiento, Modularidad, Abstracción, Principio de Pareto (80/20), Desarrollo guiado por pruebas (TDD), Consistencia, Programación defensiva, No sobreingeniería, Optimización de código (cuando sea necesario), Refactorización, Principio de la menor sorpresa.
- En el comit fba23d6 "listo para pruebas finales" hay una version lista mucho mas simple con menos clases pero al tratar de hacerla mas profesional se complico todo un poco mas.

## Estructura de archivos

![image](https://github.com/user-attachments/assets/6a41a2e4-9261-4b0a-ad99-dcd2493d480c)

## Classes
1. Paquete com.softek.albertoHaboba_pruebatec1 es el paquete principal y contiene la clase principal:
   1. albertoHaboba_pruebatec1 (main) en donde corre un ciclo while que es el encargado de dirigir el flujo de la aplicacion mediante un switch anidado con 6 opciones.
2. Paquete logica tenemos:
   1. GestorEmpleados es la clase que concentra y administra todos los metodos para trabajar con la clase empleado.
   2. Clase Empleado es la clase o molde de los objetos que son usados en la aplicación la cual contiene todos sus atributos.
3. Paquete persistencia:
   1. EmpleadoJPAController es la clase donde estan definidos los metodos JPA que son los encargados de interactuar con la base de datos.
   2. ControladoraPersistencia es la clase que contiene los emtodos que cree para interactuar con el JPAController y la lagica de mi aplicación.
4. Paquete ui:
   1. CapturaDatos es la clase que implementa los formularios para que el usuario capture la informacion del crud y se penso asi ya que varios metodos repiten el mismo proceso de captura de datos para        realizar sus funciones.
   1. InterfazUsuario es el equivalente al front o la interfaz grafica que en este caso solo implementa en terminal un menu ascii interactivo con su logica para verificar que la opcion elegida sea valida.
5. persistencia.exceptions clase creada automaticamente por JPA con metodos propios.

## Metodos
- crearEmpleado llama a capturarEmpleado que a su ves llama 3 veces a capturarDatos, capturarSalario y a capturarFechaIngles para crear una instancia de Empleado que se lo pasa a la ControladoraPersistencia que jama a create de JPA que se conecta a la base de datos y realiza un INSERT.
- consultarTodos llama directo a la controladora que llama al JPA controller para un SELECT * en la base de datos.
- consultarPorCargo llama a capturaCargo y luego llama a traerTodos (SELECT *) y lo almacena en un List<Empleados> para despues verificar con un for/each si el cargo capturado coincide con el cargo de alguno de los Empleados y de ser asi lo almacena en un ArrayList que muestra al final sus valores.
- modificarEmpleado descarga con traerTodos y almacena en lista para luego llamar a capturarID y si coincide con algun Empleado en el ArrayList llama a capturarEmpeado y para luego se lo pasa a modificarEmpleado en la controladora que usa el metodo edit para reescribir la entrada con la ID coincidente con el nuevo Empleado
- borrarEmpleado llama a capturarID de CapturaDatos y luego se lo pasa a eliminarEmpleado de la controladora que llamam a destroy del JPA controller que usa un DROP en la base de datos.

## Manejo de Excepciones
La aplicación maneja errores y excepciones, como en la busquedad de registros duplicados y búsquedas de empleados inexistentes, para asegurar una experiencia de usuario fluida.
   
## Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue los pasos a continuación para contribuir:

## Haz un fork del proyecto.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad').
Sube tus cambios a la rama (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](https://github.com/kodkodmx/habobaAlberto_pruebatec1/blob/b7ca3724449356290610ba57fa8e9305bd59bf29/LICENSE) para más detalles.
