package Server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args){
        try{
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("192.168.56.2",5555);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket= serverSocket.accept();

            System.out.println("Conexi�n recibida");

            DataInputStream is=new DataInputStream(newSocket.getInputStream());
            DataOutputStream os=new DataOutputStream(newSocket.getOutputStream());

            os.writeUTF("Bienvenido a la calculadora remota");

            int opcion = is.readInt();
            int operandos[] = new int[5];
            int resultado=0;
            float resultado_div=0;
            for(int i=0;i < 5; i++){
                operandos[i]=is.readInt();
            }

            switch (opcion){
                case 1:
                    for(int i=0;i < 5; i++){
                        resultado=resultado+operandos[i];
                    }
                    break;
                case 2:
                    int mayor=-1;
                    for(int i=0;i < 5; i++){
                        if(operandos[i] > mayor){
                            mayor=operandos[i];
                        }
                    }
                    resultado=mayor;
                    System.out.println("Mayor: " + resultado);
                    for(int i=0;i < 5; i++){
                        if(operandos[i]==mayor){
                            continue;
                        }
                        resultado=resultado-operandos[i];
                    }
                    break;
                case 3:
                    resultado=1;
                    for(int i=0;i < 5; i++){
                        resultado=resultado*operandos[i];
                    }
                    break;
                case 4:
                    resultado_div=operandos[0]/operandos[1];
                    for(int i=1;i < 5; i++){
                        resultado_div=resultado_div/operandos[i];
                        System.out.println("Resultado: " + resultado_div + " operando: " + operandos[i]);
                    }
                    break;
            }
            if(opcion == 4){
                os.writeFloat(resultado_div);
            }else {
                os.writeInt(resultado);
            }
            System.out.println("Cerrando el nuevo socket");

            is.close();
            os.close();
            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        }catch (IOException e) {
        }
    }
}