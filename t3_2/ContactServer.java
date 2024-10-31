package com.software.t3_2;

import java.io.*;
import java.net.*;

import java.io.*;
import java.net.*;

public class ContactServer {
    private static ContactService contactService = new ContactService();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("服务器正在监听端口 12345...");

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String command;
                while ((command = input.readLine()) != null) {
                    if (command.startsWith("ADD")) {
                        String[] parts = command.split(";");
                        output.println(contactService.addContact(parts[1], parts[2], parts[3]));
                    } else if (command.equals("LIST")) {
                        output.println(contactService.listContacts());
                        output.println("END");
                    } else if (command.startsWith("DELETE")) {
                        String name = command.split(";")[1];
                        output.println(contactService.deleteContact(name));
                    } else {
                        output.println("未知命令。");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
