package parcialJava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parcialJava.dao.DuenioDAOImpl;
import parcialJava.dao.MascotaDAOImpl;
import parcialJava.model.Duenio;
import parcialJava.model.Mascota;
import parcialJava.util.DatabaseUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        DatabaseUtil.crearTablasSiNoExisten();

        Scanner scanner = new Scanner(System.in);
        DuenioDAOImpl duenioDAO = new DuenioDAOImpl();
        MascotaDAOImpl mascotaDAO = new MascotaDAOImpl();

        logger.info("***********************************************");
        logger.info("*        Bienvenido a peluquería canina       *");
        logger.info("*                   PANLULU                   *");
        logger.info("***********************************************");
        boolean continuar = true;

        while (continuar) {
            logger.info("\n===== MENÚ PRINCIPAL =====");
            logger.info("1. Agregar nuevo dueño");
            logger.info("2. Listar todos los dueños");
            logger.info("3. Agregar mascota a un dueño");
            logger.info("4. Listar todas las mascotas");
            logger.info("5. Editar dueño");
            logger.info("6. Editar mascota");
            logger.info("7. Eliminar dueño");
            logger.info("8. Eliminar mascota");
            logger.info("9. Salir");
            logger.info("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    logger.info("Ingrese nombre del dueño:");
                    String nombre = scanner.nextLine();

                    logger.info("Ingrese número de celular:");
                    String celDuenio = scanner.nextLine();

                    Duenio nuevoDuenio = new Duenio();
                    nuevoDuenio.setNombre(nombre);
                    nuevoDuenio.setCelDuenio(celDuenio);

                    duenioDAO.crear(nuevoDuenio);
                    logger.info("✔ Dueño agregado correctamente.");
                }

                case "2" -> {
                    List<Duenio> duenios = duenioDAO.listarTodos();
                    if (duenios.isEmpty()) {
                        logger.info("No hay dueños registrados.");
                    } else {
                        logger.info("\nLista de dueños:");
                        for (Duenio d : duenios) {
                            logger.info("ID: {} | Nombre: {} | Cel: {}", d.getIdDuenio(), d.getNombre(), d.getCelDuenio());
                        }
                    }
                }

                case "3" -> {
                    List<Duenio> dueniosDisponibles = duenioDAO.listarTodos();
                    if (dueniosDisponibles.isEmpty()) {
                        logger.info("No hay dueños registrados. Cree uno primero.");
                        break;
                    }

                    logger.info("\nSeleccione el ID del dueño para agregar una mascota:");
                    for (Duenio d : dueniosDisponibles) {
                        logger.info("ID: {} | Nombre: {}", d.getIdDuenio(), d.getNombre());
                    }

                    int idDuenio;
                    try {
                        idDuenio = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        logger.warn("ID inválido.");
                        break;
                    }

                    if (!dueniosDisponibles.stream().anyMatch(d -> d.getIdDuenio() == idDuenio)) {
                        logger.warn("No se encontró un dueño con ese ID.");
                        break;
                    }

                    logger.info("Ingrese nombre de la mascota:");
                    String nombreMascota = scanner.nextLine();

                    logger.info("Ingrese especie de la mascota:");
                    String especieMascota = scanner.nextLine();

                    Mascota nuevaMascota = new Mascota();
                    nuevaMascota.setNombre(nombreMascota);
                    nuevaMascota.setEspecie(especieMascota);
                    nuevaMascota.setIdDuenio(idDuenio);

                    mascotaDAO.crear(nuevaMascota);
                    logger.info("✔ Mascota agregada correctamente.");
                }

                case "4" -> {
                    List<Mascota> mascotas = mascotaDAO.listarTodos();
                    if (mascotas.isEmpty()) {
                        logger.info("No hay mascotas registradas.");
                    } else {
                        logger.info("\nLista de mascotas:");
                        for (Mascota m : mascotas) {
                            logger.info("ID: {} | Nombre: {} | Especie: {} | ID Dueño: {}", m.getIdMascota(), m.getNombre(), m.getEspecie(), m.getIdDuenio());
                        }
                    }
                }

                case "5" -> {
                    logger.info("Ingrese ID del dueño a editar:");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        logger.warn("ID inválido.");
                        break;
                    }

                    Duenio duenio = duenioDAO.buscarPorId(id);
                    if (duenio == null) {
                        logger.warn("Dueño no encontrado.");
                        break;
                    }

                    logger.info("Nuevo nombre (actual: {}):", duenio.getNombre());
                    duenio.setNombre(scanner.nextLine());

                    logger.info("Nuevo celular (actual: {}):", duenio.getCelDuenio());
                    duenio.setCelDuenio(scanner.nextLine());

                    duenioDAO.editar(duenio);
                    logger.info("✔ Dueño editado correctamente.");
                }

                case "6" -> {
                    logger.info("Ingrese ID de la mascota a editar:");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        logger.warn("ID inválido.");
                        break;
                    }

                    Mascota mascota = mascotaDAO.buscarPorId(id);
                    if (mascota == null) {
                        logger.warn("Mascota no encontrada.");
                        break;
                    }

                    logger.info("Nuevo nombre (actual: {}):", mascota.getNombre());
                    mascota.setNombre(scanner.nextLine());

                    logger.info("Nueva especie (actual: {}):", mascota.getEspecie());
                    mascota.setEspecie(scanner.nextLine());

                    mascotaDAO.editar(mascota);
                    logger.info("✔ Mascota editada correctamente.");
                }

                case "7" -> {
                    logger.info("Ingrese ID del dueño a eliminar:");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        logger.warn("ID inválido.");
                        break;
                    }

                    duenioDAO.eliminar(id);
                    logger.info("✔ Dueño eliminado si existía.");
                }

                case "8" -> {
                    logger.info("Ingrese ID de la mascota a eliminar:");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        logger.warn("ID inválido.");
                        break;
                    }

                    mascotaDAO.eliminar(id);
                    logger.info("✔ Mascota eliminada si existía.");
                }

                case "9" -> {
                    continuar = false;
                    logger.info("Saliendo del programa...");
                }

                default -> logger.warn("Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}