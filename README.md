# 🐾 Peluquería Canina PANLULU - Sistema CRUD

Este sistema está diseñado para la gestión de una peluquería canina, permitiendo la **carga, edición, visualización y eliminación de dueños y mascotas**. Utiliza una base de datos H2 en modo local y opera mediante comandos SQL ejecutados a través de JDBC.

Actualmente el sistema **cumple con las funcionalidades CRUD** básicas (Crear, Leer, Actualizar y Eliminar) y se encuentra en desarrollo una futura funcionalidad para **agendar turnos**.

## ⚙️ Tecnologías utilizadas

- Java 17
- JDBC
- Base de datos H2 (embedded)
- DAO Pattern
- log4j (para logging)
- Gradle (para manejo de dependencias y ejecución)

## 📂 Funcionalidades

- Crear dueño o mascota
- Listar todos los dueños o mascotas
- Editar datos de un dueño o mascota
- Eliminar dueño o mascota
- Registro de acciones mediante `log4j` en consola y archivo `logs/app.log`


## 🗃️ Script de creación de base de datos (H2)

```sql
CREATE TABLE DUENIO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(50)
);

CREATE TABLE MASCOTA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    especie VARCHAR(100),
    idDuenio INT,
    FOREIGN KEY (idDuenio) REFERENCES DUENIO(id)
);
```

## 🚧 Próximas mejoras

- Agendamiento de turnos
- Interfaz gráfica (GUI)
- Validaciones avanzadas y filtros de búsqueda

---

### 👤 Autor
Cristian Gomez

### 📄 Licencia
Cristian Panlulu