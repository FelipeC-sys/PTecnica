PREGUNTAS:


11. ¿Cuál es la diferencia entre @RestController y @Controller en Spring Boot?

@Controller se utiliza cuando la aplicación devuelve vistas, por ejemplo páginas HTML usando Thymeleaf o JSP.

@RestController se utiliza para crear APIs REST. Los métodos devuelven directamente los datos en formato JSON, por lo que no es necesario usar la anotación @ResponseBody en cada método.

En este proyecto se utilizó @RestController porque la aplicación expone servicios REST para ser consumidos desde Postman.



12. ¿Por qué se usan DTOs en lugar de exponer directamente la entidad JPA?

Los DTO (Data Transfer Object) permiten enviar únicamente la información necesaria entre el cliente y la aplicación.

Esto ayuda a:

No exponer todos los atributos de la entidad.
Validar los datos que llegan desde el cliente.
Separar la lógica de la base de datos de la información que recibe la API.

Por esta razón se utilizaron TareaDTO y EstadoDTO.



13. ¿Qué ventaja tiene @PrePersist sobre asignar la fecha en el constructor de la entidad?

@PrePersist ejecuta automáticamente un método justo antes de guardar la entidad en la base de datos.

Su ventaja es que siempre garantiza que la fecha se asigne antes del primer guardado, sin importar desde dónde se cree el objeto.

Si se asignara únicamente en el constructor, podrían existir casos donde la fecha no se establezca correctamente.



14. ¿Qué diferencia hay entre spring.jpa.hibernate.ddl-auto=update y ddl-auto=create? ¿Cuál usarías en producción y por qué?

ddl-auto=create

Elimina todas las tablas y las vuelve a crear cada vez que inicia la aplicación.
Se utiliza normalmente para pruebas.

ddl-auto=update

Conserva la información existente.
Solo realiza los cambios necesarios en la estructura de la base de datos.

En producción utilizaría update, porque evita perder la información almacenada por los usuarios.



15. Si esta API fuera a producción con usuarios reales, menciona al menos 3 cambios que harías.

Si esta aplicación fuera utilizada por usuarios reales realizaría los siguientes cambios:

Implementar autenticación y autorización utilizando Spring Security y JWT para proteger los endpoints.
Registrar los errores y eventos de la aplicación mediante logs para facilitar el mantenimiento.
Configurar variables de entorno para ocultar información sensible como el usuario y la contraseña de la base de datos.