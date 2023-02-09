/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient{
    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("[ Conectando com o Servidor TCP    ..................  ");
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            int finish = 0;
            
            while(finish == 0) {
                System.out.println("Digite sua mensagem: ");
                String msg = sc.nextLine();
                if(msg.equals("FIM")) {
                    finish = 1;
                }
                byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                System.out.print("[ Enviando mensagem    ..............................  ");
                os.write(buf);
                System.out.println("[OK] ]");
                
                byte[] bufResp = new byte[20]; // buffer de recepção
                System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
                is.read(bufResp); // Operação bloqueante (aguardando chegada de dados)
                System.out.println("[OK] ]");
            
                String respMsg = new String(bufResp); // Mapeando vetor de bytes recebido para String
            
                System.out.println("  Mensagem recebida: "+ respMsg);
                
                if(respMsg.equals("FIM")) {
                    finish = 1;
                }
            }
            
            System.out.println("[ TERMINOU ]");
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}