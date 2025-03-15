import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
         * Agregue validaciones para evitar saldos negativos al crear una cuenta.
         * Verifique que no se repita el número de cuenta al registrar un nuevo cliente.
         * Organice el submenú de operaciones en un metodo aparte para que el flujo sea más claro.
         * Al depositar o retirar, ahora se impide ingresar montos negativos o retirar más de lo disponible.
         * Al seleccionar "Salir", el programa ahora finaliza correctamente sin continuar ejecutando.
         * Cerre el Scanner al final como buena práctica de programación.
         */

        Scanner teclado = new Scanner(System.in);
        int op;
        double monto;
        boolean estado = true;

        List<cuenta_bancaria> lstBanco = new ArrayList<>();

        while (estado) {
            System.out.println("""
                    ---------------------------
                    1. Crear un cliente
                    2. Mostrar todas las cuentas
                    3. Mostrar información de una cuenta específica
                    4. Operaciones (Depositar/Retirar)
                    5. Salir
                    ---------------------------""");

            op = teclado.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("Ingrese nombre del titular:");
                    String titular = teclado.next();

                    System.out.println("Ingrese saldo inicial:");
                    double saldo = teclado.nextDouble();

                    while (saldo < 0) {
                        System.out.println("El saldo inicial no puede ser negativo. Ingrese nuevamente:");
                        saldo = teclado.nextDouble();
                    }

                    System.out.println("Ingrese número de cuenta:");
                    String numeroCuenta = teclado.next();

                    boolean existe = false;
                    for (cuenta_bancaria c : lstBanco) {
                        if (c.getNumerocuenta().equalsIgnoreCase(numeroCuenta)) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        System.out.println("Ya existe una cuenta con ese número. No se puede crear.");
                    } else {
                        cuenta_bancaria nuevaCuenta = new cuenta_bancaria(titular, saldo, numeroCuenta);
                        lstBanco.add(nuevaCuenta);
                        System.out.println("Cuenta creada exitosamente.\n");
                    }
                }

                case 2 -> {
                    if (lstBanco.isEmpty()) {
                        System.out.println("No hay cuentas registradas.");
                    } else {
                        for (cuenta_bancaria cu : lstBanco) {
                            System.out.println(cu);
                        }
                    }
                }

                case 3 -> {
                    System.out.println("Ingrese el número de cuenta:");
                    String buscarCuenta = teclado.next();

                    boolean encontrada = false;
                    for (cuenta_bancaria cu : lstBanco) {
                        if (cu.getNumerocuenta().equalsIgnoreCase(buscarCuenta)) {
                            cu.mostrarInfo();
                            encontrada = true;
                            break;
                        }
                    }
                    if (!encontrada) {
                        System.out.println("Cuenta no encontrada.");
                    }
                }

                case 4 -> {
                    if (!lstBanco.isEmpty()) {
                        System.out.println("Ingrese el número de cuenta para operar:");
                        String operacionCuenta = teclado.next();

                        cuenta_bancaria cuentaSeleccionada = null;
                        for (cuenta_bancaria cu : lstBanco) {
                            if (cu.getNumerocuenta().equalsIgnoreCase(operacionCuenta)) {
                                cuentaSeleccionada = cu;
                                break;
                            }
                        }

                        if (cuentaSeleccionada == null) {
                            System.out.println("Cuenta no encontrada.");
                        } else {
                            mostrarSubMenu(teclado, cuentaSeleccionada);
                        }
                    } else {
                        System.out.println("No hay cuentas disponibles.");
                    }
                }

                case 5 -> {
                    System.out.println("Gracias por usar el sistema bancario. ¡Hasta pronto!");
                    estado = false;
                }

                default -> System.out.println("Opción no válida.");
            }
        }

        teclado.close(); // Cerrar scanner
    }

    public static void mostrarSubMenu(Scanner teclado, cuenta_bancaria cuenta) {
        boolean subMenuActivo = true;
        while (subMenuActivo) {
            System.out.println("""
                    --- Operaciones ---
                    1. Depositar dinero
                    2. Retirar dinero
                    3. Mostrar información de la cuenta
                    4. Volver al menú principal
                    -------------------""");
            int subOp = teclado.nextInt();
            double monto;

            switch (subOp) {
                case 1 -> {
                    System.out.println("¿Cuánto desea depositar?");
                    monto = teclado.nextDouble();
                    cuenta.depositarDinero(monto);
                }
                case 2 -> {
                    System.out.println("¿Cuánto desea retirar?");
                    monto = teclado.nextDouble();
                    cuenta.retirarDinero(monto);
                }
                case 3 -> cuenta.mostrarInfo();
                case 4 -> subMenuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
