/*package PROYECTO_GRUPO_3;
import java.util.InputMismatchException;
import java.util.Scanner;
public class PruebaSistemaDeReciclaje {
    public static void main (String[] args){
        Scanner entrada = new Scanner(System.in);
        SistemaDeReciclaje.cargarUsuariosDesdeArchivo();
        SistemaDeReciclaje.cargarGestoresDesdeArchivo();
        String ID,contraseña;
        int opc1,opc2,opc3,n;
        SistemaDeReciclaje Sistema = new SistemaDeReciclaje();
        System.out.println("+----------------------------------------------------------------+");
        System.out.println("+   BIENVENIDO A ESTE NUEVO SISTEMA DE RECOLECCION DE RESIDUOS   +");
        System.out.println("+                  CON RECOMPENSA MONETARIA                      +");
        System.out.println("+----------------------------------------------------------------+");
        do{
            System.out.println("\n1. Usuario");
            System.out.println("2. Gestor");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opcion: ");
            opc1 = entrada.nextInt();
            switch(opc1){
                case 1: do{
                            System.out.println("\n1. Registrarse");
                            System.out.println("2. Iniciar Sesion");
                            System.out.println("3. Salir");
                            System.out.print("Ingrese una opcion: ");
                            opc2 = entrada.nextInt();
                            entrada.nextLine();
                            switch(opc2){
                                case 1: System.out.println("--------------------------------");
                                        System.out.println("REGISTRO DE SESION:");
                                        try{
                                        Sistema.registrarUsuario();}
                                        catch(InputMismatchException e){
                                            System.out.println("Se ingreso un tipo de dato erroneo: "+e.getMessage());
                                        }
                                        break;
                                case 2: if (Sistema.tamaño("usuarios")==0) {
                                            System.out.println("Primero debe registrarse.");
                                            break;
                                        }
                                        System.out.println("--------------------------------");
                                        System.out.println("INICIO DE SESION:");
                                        System.out.print("Ingrese su ID: ");
                                        ID = entrada.nextLine();
                                        System.out.print("Ingrese su contraseña: ");
                                        contraseña = entrada.nextLine();
                                        if(Sistema.iniciar(ID,contraseña,"usuarios")){
                                            System.out.println("Inicio de sesión correcto.");
                                            System.out.println("--------------------------------");
                                            System.out.println(Sistema.bienvenida(ID,contraseña,"usuarios"));
                                            System.out.println("--------------------------------");
                                            do{
                                                System.out.println("\nMENU DE OPCIONES:");
                                                System.out.println("1. Mostrar datos personales");
                                                System.out.println("2. Mostrar saldo en la cuenta");
                                                System.out.println("3. Agregar productos reciclables a un contenedor");
                                                System.out.println("4. Mostrar estado de un contenedor");
                                                System.out.println("5. Actualizar datos personales");
                                                System.out.println("6. Actualizar ID o contraseña");
                                                System.out.println("7. Eliminar cuenta");
                                                System.out.println("8. Salir");
                                                System.out.print("Ingrese una opción: ");
                                                n = entrada.nextInt();
                                                switch(n){
                                                    case 1: System.out.println(Sistema.datosUsuario(ID,contraseña)); break;
                                                    case 2: System.out.println("EL SALDO ACTUAL DE USTED ES: ");
                                                            System.out.println(Sistema.mostrarSaldo(ID,contraseña));
                                                            break;
                                                    case 3: Sistema.agregar_1(ID,contraseña); break;
                                                    case 4: Sistema.estadoContenedores(); break;
                                                    case 5: Sistema.actualizar_1(ID,contraseña); break;
                                                    case 6: Sistema.actualizar_2(ID,contraseña); opc2=3; break;
                                                    case 7: Sistema.eliminar(ID,contraseña); opc2=3; break;
                                                    case 8: System.out.println("Saliendo de la cuenta..."); opc2=3; break;
                                                    default: System.out.println("Opción no valida");
                                                }
                                            }while(n!=6 && n!=7 && n!=8);
                                            break;
                                        }
                                        else{
                                            System.out.println("ID o contraseña incorrectos.");
                                        }
                                        break;
                                case 3: System.out.println("Saliendo..."); break;
                                default: System.out.println("Opción no valida.");
                            }
                        }while(opc2!=3);
                        break;
                case 2: do{
                            System.out.println("\n1. Registrarse");
                            System.out.println("2. Iniciar Sesion");
                            System.out.println("3. Salir");
                            System.out.print("Ingrese una opción: ");
                            opc3 = entrada.nextInt();
                            entrada.nextLine();
                            switch(opc3){
                                case 1: System.out.println("--------------------------------");
                                        System.out.println("REGISTRO DE SESION:");
                                        try{
                                        Sistema.registrarGestor();}
                                        catch(InputMismatchException e){
                                            System.out.println("Se ingreso un tipo de dato erroneo: "+e.getMessage());
                                        }
                                        break;
                                case 2: if (Sistema.tamaño("gestores")==0) {
                                            System.out.println("Primero debe registrarse.");
                                            break;
                                        }
                                        System.out.println("--------------------------------");
                                        System.out.println("INICIO DE SESION:");
                                        System.out.print("Ingrese su ID: ");
                                        ID = entrada.nextLine();
                                        System.out.print("Ingrese su contraseña: ");
                                        contraseña = entrada.nextLine();
                                        if(Sistema.iniciar(ID,contraseña,"gestores")){
                                            System.out.println("Inicio de sesión correcto.");
                                            System.out.println("--------------------------------");
                                            System.out.println(Sistema.bienvenida(ID,contraseña,"gestores"));
                                            System.out.println("--------------------------------");
                                            do{
                                                System.out.println("\nMENU DE OPCIONES:");
                                                System.out.println("1. Mostrar datos personales");
                                                System.out.println("2. Mostrar salario actual");
                                                System.out.println("3. Buscar usuarios atendidos");
                                                System.out.println("4. Salir");
                                                System.out.print("Ingrese una opción: ");
                                                n = entrada.nextInt();
                                                switch(n){
                                                    case 1: System.out.println(Sistema.datosGestor(ID,contraseña)); break;
                                                    case 2: System.out.println("EL SALARIO ACTUAL DE USTED ES: ");
                                                            System.out.println(Sistema.mostrarSalario(ID,contraseña));
                                                            break;
                                                    case 3: Sistema.buscarUsuario(ID, contraseña); break;
                                                    case 4: System.out.println("Saliendo de la cuenta..."); opc3=3; break;
                                                    default: System.out.println("Opción no valida");
                                                }
                                            }while(n!=4);
                                            break;
                                        }
                                        else{
                                            System.out.println("ID o contraseña incorrectos.");
                                        }
                                        break;
                                case 3: System.out.println("Saliendo..."); break;
                                default: System.out.println("Opción no valida.");
                            }
                        }while(opc3!=3);
                        break;
                case 3: System.out.println("Saliendo del programa..."); break;
                default: System.out.println("Opción no valida.");
            }
        }while(opc1!=3);
        entrada.close();
    }
}*/