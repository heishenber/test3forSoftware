package com.software.t3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class Contact {
    String name;
    String address;
    String phone;

    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Phone: " + phone;
    }
}

public class ContactServer {
    private static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345...");

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
                        contacts.add(new Contact(parts[1], parts[2], parts[3]));
                        output.println("Contact added.");
                    } else if (command.equals("LIST")) {
                        for (Contact contact : contacts) {
                            output.println(contact.toString());
                        }
                        output.println("END");
                    } else if (command.startsWith("DELETE")) {
                        String name = command.split(";")[1];
                        contacts.removeIf(contact -> contact.name.equals(name));
                        output.println("Contact deleted.");
                    } else {
                        output.println("Unknown command.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
