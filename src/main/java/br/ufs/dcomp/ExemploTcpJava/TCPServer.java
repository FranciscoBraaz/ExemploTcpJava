/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPServer{
    public static void main(String[] args){
        
        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            int finish = 0;
            
            while(finish == 0) {
                byte[] buf = new byte[20]; // buffer de recepção
                System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
                is.read(buf); // Operação bloqueante (aguardando chegada de dados)
                System.out.println("[OK] ]");
                
                String msg = new String(buf); // Mapeando vetor de bytes recebido para String
            
                System.out.println("  Mensagem recebida: "+ msg);
                
                if(msg.equals("FIM")) {
                    finish = 1;
                }
                
                System.out.println("Digite sua mensagem: ");
                String backMsg = sc.nextLine();
                if(backMsg.equals("FIM")) {
                    finish = 1;
                }
                
                byte[] bufBackMsg = backMsg.getBytes(); // Obtendo a respresntação em bytes da mensagem

                System.out.print("[ Enviando mensagem    ..............................  ");
                os.write(bufBackMsg);
                System.out.println("[OK] ]");
            }
            
             System.out.println("[ TERMINOU ]");
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}