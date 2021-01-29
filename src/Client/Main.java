package Client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexi�n");

            InetSocketAddress addr = new InetSocketAddress("192.168.56.2", 5555);
            clienteSocket.connect(addr);
           /* InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();*/
            DataInputStream is = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream os = new DataOutputStream(clienteSocket.getOutputStream());

            System.out.println(is.readUTF());
            System.out.println("Por favor, Seleccione una opcion:");
            System.out.println("1: Suma");
            System.out.println("2: Resta");
            System.out.println("3: Multiplicacion");
            System.out.println("4: Division");
            System.out.print(">");
            int opcion = input.nextInt();
            os.writeInt(opcion);
            System.out.println("Por favor, Ingrese 5 Operandos:");
            for (int i = 0; i < 5; i++){
                System.out.print(">");
                os.writeInt(input.nextInt());
            }
            System.out.print("El resultado es: ");
            if(opcion==4){
                System.out.println(is.readFloat());
            }else{
                System.out.println(is.readInt());
            }


            System.out.println("Cerrando el socket cliente");

            os.close();
            is.close();
            clienteSocket.close();


            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}