import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class gimnasio {
    static Scanner scanner = new Scanner(System.in);
    static final int PRECIO_MENSUAL = 70000;
    static final int PRECIO_SEMANAL = 20000;
    static final int PRECIO_DIARIO = 6000;
    static final double DEVOLUCION_MENSUAL = 10000;
    static final double DEVOLUCION_SEMANAL = 3000;

    private static List<Entrada> entradasRegistradas = new ArrayList<>();

    public static void main(String[] args) {
        elBloque();
    }

    public static void elBloque() {
        int opcion;
        while (true) {
            System.out.println("\n=== SISTEMA DE GIMNASIO ===");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Consultar precios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcion) {
                    case 1:
                        registrarEntrada();
                        break;
                    case 2:
                        registrarSalida();
                        break;
                    case 3:
                        consultarPrecios();
                        break;
                    case 4:
                        System.out.println("¡Gracias por usar el sistema!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un número.");
                scanner.next();
                scanner.nextLine(); 
            }
        }
    }

    public static void registrarEntrada() {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        int id = obtenerIdCliente();
        if (idYaExiste(id)) {
            System.out.println("Entrada ya registrada para este cliente.");
            return;
        }
        String tipoMembresia = seleccionarTipoMembresia();
        System.out.print("Ingrese fecha de entrada: ");
        String fechaEntrada = obtenerFecha();
        Entrada entrada = new Entrada(nombre, id, tipoMembresia, fechaEntrada);
        entradasRegistradas.add(entrada);
      System.out.println("Entrada registrada con éxito!");
    }

    public static void registrarSalida() {
        int id = obtenerIdCliente();
        Entrada entradaEncontrada = encontrarEntrada(id);
        if (entradaEncontrada != null) {
            System.out.print("Ingrese fecha de salida: ");
            String fechaSalida = obtenerFecha();
            if (validarPeriodoTiempoMembresia(entradaEncontrada.getTipoMembresia(), entradaEncontrada.getFechaEntrada(), fechaSalida)) {
                int devolucion = 0;
                switch (entradaEncontrada.getTipoMembresia()) {
                    case "mensual":
                        devolucion = (int) DEVOLUCION_MENSUAL;
                        break;
                    case "semanal":
                        devolucion = (int) DEVOLUCION_SEMANAL;
                        break;
                }
                System.out.println("Devolución calculada con éxito: $ " + devolucion);
                System.out.println("Salida registrada con éxito!");
            } else {
                System.out.println("Error: la fecha de salida es posterior al período de tiempo de la membresía.");
            }
        } else {
            System.out.println("No se encontró una entrada registrada con ese ID.");
        }
    }
    public static void consultarPrecios() {
    System.out.println("Precios del gimnasio:");
    System.out.println("Mensual: $ " + PRECIO_MENSUAL);
    System.out.println("Semanal: $ " + PRECIO_SEMANAL);
    System.out.println("Diario: $ " + PRECIO_DIARIO);
}

private static int obtenerIdCliente() {
    while (true) {
        try {
            System.out.print("Ingrese ID del cliente: ");
            String idString = scanner.nextLine();
            if (idString.matches("\\d{8,10}")) {
                int id = Integer.parseInt(idString);
                if (id > 0) {
                    return id;
                } else {
                    System.out.println("ID inválido. Debe ser un número entero positivo");
                }
            } else {
                System.out.println("ID inválido. Debe tener entre 8 y 10 dígitos numéricos");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error al ingresar ID. Debe ser un número entero");
        }
    }
}

private static String seleccionarTipoMembresia() {
    String[] opciones = {"mensual", "semanal", "diaria"};
    System.out.println("Seleccione tipo de membresía:");
    for (int i = 0; i < opciones.length; i++) {
        System.out.println((i + 1) + ". " + opciones[i].substring(0, 1).toUpperCase() + opciones[i].substring(1));
    }
    while (true) {
        System.out.print("Ingrese el número de la opción: ");
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            if (opcion >= 1 && opcion <= opciones.length) {
                System.out.println("Membresía " + opciones[opcion - 1] + " seleccionada.");
                return opciones[opcion - 1];
            } else {
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: ingrese un número.");
            scanner.next(); 
            scanner.nextLine(); 
        }
    }
}

private static String obtenerFecha() {
    while (true) {
        System.out.print("Ingrese fecha (dd/MM/yyyy): ");
        String fecha = scanner.nextLine();
        String[] partes = fecha.split("/");
        if (partes.length != 3) {
            System.out.println("Fecha inválida. Debe tener formato dd/MM/yyyy.");
            continue;
        }
        String dia = partes[0];
        String mes = partes[1];
        String año = partes[2];
        if (!dia.matches("\\d{2}") || !mes.matches("\\d{2}") || !año.matches("\\d{4}")) {
            System.out.println("Fecha inválida. Día, mes y año deben ser números.");
            continue;
        }
        int diaInt = Integer.parseInt(dia);
        int mesInt = Integer.parseInt(mes);
        int añoInt = Integer.parseInt(año);
        if (diaInt < 1 || diaInt > 31 || mesInt < 1 || mesInt > 12 || añoInt < 2000) {
            System.out.println("Fecha inválida. Día, mes o año no válido.");
            continue;
        }
        if ((mesInt == 2 && diaInt > 28) || (mesInt == 4 && diaInt > 30) || (mesInt == 6 && diaInt > 30) || (mesInt == 9 && diaInt > 30) || (mesInt == 11 && diaInt > 30)) {
            System.out.println("Fecha inválida. Día no válido para el mes.");
            continue;
        }
        return fecha;
    }
}

private static Entrada encontrarEntrada(int id) {
    for (Entrada entrada : entradasRegistradas) {
        if (entrada.getId() == id) {
            return entrada;
        }
    }
    return null;
}

private static boolean idYaExiste(int id) {
    for (Entrada entrada : entradasRegistradas) {
        if (entrada.getId() == id) {
            return true;
        }
    }
    return false;
}

private static boolean validarPeriodoTiempoMembresia(String tipoMembresia, String fechaEntrada, String fechaSalida) {
    int diasRestantes = calcularDiasRestantes(fechaEntrada, fechaSalida);
    switch (tipoMembresia) {
        case "mensual":
            if (diasRestantes > 30) {
                return false;
            }
            break;
        case "semanal":
            if (diasRestantes > 7) {
                return false;
            }
            break;
        case "diaria":
            if (diasRestantes > 1) {
                return false;
            }
            break;
    }
    return true;
}

private static int calcularDiasRestantes(String fechaEntrada, String fechaSalida) {
    String[] partesEntrada = fechaEntrada.split("/");
    String[] partesSalida = fechaSalida.split("/");
    int diaEntrada = Integer.parseInt(partesEntrada[0]);
    int mesEntrada = Integer.parseInt(partesEntrada[1]);
    int anioEntrada = Integer.parseInt(partesEntrada[2]);
    int diaSalida = Integer.parseInt(partesSalida[0]);
    int mesSalida = Integer.parseInt(partesSalida[1]);
    int anioSalida = Integer.parseInt(partesSalida[2]);
    int diasRestantes = 0;
    if (anioSalida > anioEntrada) {
        diasRestantes += (anioSalida - anioEntrada) * 365;
    } else if (anioSalida < anioEntrada) {
        diasRestantes -= (anioEntrada - anioSalida) * 365;
    }
    if (mesSalida > mesEntrada) {
        diasRestantes += (mesSalida - mesEntrada) * 30;
    } else if (mesSalida < mesEntrada) {
        diasRestantes -= (mesEntrada - mesSalida) * 30;
    }
    diasRestantes += diaSalida - diaEntrada;
    return diasRestantes;
}

        static class Entrada {
    private String nombre;
    private int id;
    private String tipoMembresia;
    private String fechaEntrada;
            
       public Entrada(String nombre, int id, String tipoMembresia, String fechaEntrada) {
        this.nombre = nombre;
        this.id = id;
        this.tipoMembresia = tipoMembresia;
        this.fechaEntrada = fechaEntrada;
  

      }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }
            
}
}
