package parcialJava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseUtil {

    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);
    private static final String DB_URL = "jdbc:h2:F:/2025/profeNaveda/parcial/parcialTest";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Error al cargar el driver de H2", e);
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void crearTablasSiNoExisten() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Duenio (
                idDuenio INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100),
                celDuenio VARCHAR(20)
            );

            CREATE TABLE IF NOT EXISTS Mascota (
                idMascota INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100),
                especie VARCHAR(50),
                idDuenio INT,
                FOREIGN KEY (idDuenio) REFERENCES Duenio(idDuenio)
            );
        """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            logger.info("Tablas creadas o verificadas correctamente.");
        } catch (SQLException e) {
            logger.error("Error al crear las tablas", e);
        }
    }
}