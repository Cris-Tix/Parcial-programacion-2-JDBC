package parcialJava.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parcialJava.model.Mascota;
import parcialJava.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAOImpl implements GenericDAO<Mascota> {

    private static final Logger logger = LogManager.getLogger(MascotaDAOImpl.class);

    @Override
    public void crear(Mascota mascota) {
        String sql = "INSERT INTO mascota (nombre, especie, idDuenio) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mascota.getNombre());
            pstmt.setString(2, mascota.getEspecie());
            pstmt.setInt(3, mascota.getIdDuenio());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al crear mascota", e);
        }
    }

    @Override
    public List<Mascota> listarTodos() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascota";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idMascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecie(rs.getString("especie"));
                m.setIdDuenio(rs.getInt("idDuenio"));
                lista.add(m);
            }

        } catch (SQLException e) {
            logger.error("Error al listar mascotas", e);
        }

        return lista;
    }

    @Override
    public Mascota buscarPorId(int id) {
        String sql = "SELECT * FROM mascota WHERE idMascota = ?";
        Mascota mascota = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota();
                    mascota.setIdMascota(rs.getInt("idMascota"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setEspecie(rs.getString("especie"));
                    mascota.setIdDuenio(rs.getInt("idDuenio"));
                }
            }

        } catch (SQLException e) {
            logger.error("Error al buscar mascota por ID", e);
        }

        return mascota;
    }

    @Override
    public void editar(Mascota mascota) {
        String sql = "UPDATE mascota SET nombre = ?, especie = ? WHERE idMascota = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mascota.getNombre());
            pstmt.setString(2, mascota.getEspecie());
            pstmt.setInt(3, mascota.getIdMascota());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al editar mascota", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM mascota WHERE idMascota = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error al eliminar mascota", e);
        }
    }
}