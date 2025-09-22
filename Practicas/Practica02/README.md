#Práctica 2

Aplicación web sencilla desarrollada en **Java (Servlets y JSP)** siguiendo el patrón **MVC**, con almacenamiento en **SQLite**.  

## Objetivo
Simular una librería en línea donde el usuario pueda:
- Registrar libros (título, autor, precio).
- Listar libros almacenados.
- Buscar libros por título o autor.

## Estructura del proyecto
```
src/main/java/
 ├── model/
 │    └── Book.java
 ├── dao/
 │    └── BookDAO.java
 └── controller/
      └── BookServlet.java

src/main/webapp/
 ├── libreria.jsp
 └── WEB-INF/
      └── web.xm
```

## Tecnologías
- **Java 8+**
- **JSP / Servlets**
- **Tomcat 9**
- **SQLite** con dependencia Maven:
  ```xml
  <dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.46.0.0</version>
  </dependency>
  ```

## Ejecución
1. Importar el proyecto en NetBeans.  
2. Asegurarse de tener configurado **Tomcat** como servidor.  
3. Ejecutar el proyecto (Clean & Build → Run).  
4. Abrir en el navegador:  
   ```
   http://localhost:8080/<contexto>/books
   ```
   (El contexto depende del nombre del proyecto en NetBeans).

## 📌 Notas
- La base de datos `books.db` se crea automáticamente en `WEB-INF` al iniciar la aplicación.  
- Cada redeploy del proyecto puede reiniciar la base de datos.
- El formulario valida campos vacíos y precio numérico.  
