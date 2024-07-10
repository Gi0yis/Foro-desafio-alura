# Foro Desafío Alura
Este proyecto es un foro desarrollado como parte del desafío del programa Alura Latam. El foro permite a los usuarios registrarse, iniciar sesión, crear publicaciones y comentar en las publicaciones de otros usuarios. Está diseñado con un enfoque en la robustez y escalabilidad, utilizando tecnologías modernas y buenas prácticas de desarrollo.

---

### Tecnologías Utilizadas

- **Backend**: Java, Spring Boot
- **Base de Datos**: H2 Database (para desarrollo y pruebas)
- **Seguridad**: Spring Security
- **Autenticación y Autorización**: JWT (JSON Web Tokens)
- **Gestión de Dependencias**: Maven
- **Testing**: JUnit, Mockito

---

### Características

- **Registro de Usuario**: Permite a los usuarios registrarse proporcionando la información necesaria.
- **Inicio de Sesión**: Los usuarios pueden iniciar sesión utilizando sus credenciales.
- **Creación de Publicaciones**: Los usuarios autenticados pueden crear nuevas publicaciones.
- **Comentarios en Publicaciones**: Los usuarios pueden comentar en las publicaciones de otros usuarios.
- **Seguridad**: Implementación de autenticación y autorización utilizando JWT y Spring Security.

---

### Instalación y Ejecución

#### Prerrequisitos

- Java JDK 17 o superior
- Maven
- IDE (IntelliJ IDEA, Eclipse, etc.)

#### Pasos

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/Gi0yis/Foro-desafio-alura.git
   cd Foro-desafio-alura
   ```

2. **Construir el proyecto con Maven**

   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**

   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**

   La aplicación estará disponible en `http://localhost:9090`.

---

### Uso

1. **Registro de Usuario**
   - Endpoint: `POST /api/register`
   - Ejemplo de solicitud:
     ```json
     {
       "username": "nuevo_usuario",
       "password": "password123",
       "email": "usuario@example.com"
     }
     ```

2. **Inicio de Sesión**
   - Endpoint: `POST /api/login`
   - Ejemplo de solicitud:
     ```json
     {
       "username": "nuevo_usuario",
       "password": "password123"
     }
     ```
   - El servidor responderá con un token JWT que debe ser utilizado para autenticar las solicitudes subsecuentes.

3. **Creación de Publicaciones**
   - Endpoint: `POST /api/topic`
   - Ejemplo de solicitud:
     ```json
     {
       "title": "Título de la publicación",
       "content": "Contenido de la publicación"
     }
     ```
   - Requiere autenticación (incluir el token JWT en el encabezado de la solicitud).
