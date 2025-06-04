package parcialJava.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parcialJava.model.Duenio;
import parcialJava.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuenioDAOImpl implements GenericDAO<Duenio> {

    private static final Logger logger = LogManager.getLogger(DuenioDAOImpl.class);

    @Override
    public void crear(Duenio duenio) {
        String sql = "INSERT INTO duenio (nombre, celDuenio) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, duenio.getNombre());
            pstmt.setString(2, duenio.getCelDuenio());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al crear dueño", e);
        }
    }

    @Override
    public List<Duenio> listarTodos() {
        List<Duenio> lista = new ArrayList<>();
        String sql = "SELECT * FROM duenio";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Duenio d = new Duenio();
                d.setIdDuenio(rs.getInt("idDuenio"));
                d.setNombre(rs.getString("nombre"));
                d.setCelDuenio(rs.getString("celDuenio"));
                lista.add(d);
            }

        } catch (SQLException e) {
            logger.error("Error al listar dueños", e);
        }

        return lista;
    }

    @Override
    public Duenio buscarPorId(int id) {
        String sql = "SELECT * FROM duenio WHERE idDuenio = ?";
        Duenio duenio = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    duenio = new Duenio();
                    duenio.setIdDuenio(rs.getInt("idDuenio"));
                    duenio.setNombre(rs.getString("nombre"));
                    duenio.setCelDuenio(rs.getString("celDuenio"));
                }
            }

        } catch (SQLException e) {
            logger.error("Error al buscar dueño por ID", e);
        }

        return duenio;
    }

    @Override
    public void editar(Duenio duenio) {
        String sql = "UPDATE duenio SET nombre = ?, celDuenio = ? WHERE idDuenio = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, duenio.getNombre());
            pstmt.setString(2, duenio.getCelDuenio());
            pstmt.setInt(3, duenio.getIdDuenio());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al editar dueño", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM duenio WHERE idDuenio = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al eliminar dueño", e);
        }
    }
}