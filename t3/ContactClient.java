package com.software.t3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ContactClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server.");
            while (true) {
                System.out.println("选择指令: 1-联系人列表, 2-添加联系人 3-删除联系人, 4-退出");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        output.println("LIST");
                        String response;
                        while (!(response = input.readLine()).equals("END")) {
                            System.out.println(response);
                        }
                        break;

                    case 2:
                        System.out.print("输入名字: ");
                        String name = scanner.nextLine();
                        System.out.print("输入地址: ");
                        String address = scanner.nextLine();
                        System.out.print("输入手机号: ");
                        String phone = scanner.nextLine();
                        output.println("ADD;" + name + ";" + address + ";" + phone);
                        System.out.println(input.readLine());
                        break;

                    case 3:
                        System.out.print("输入删除名字: ");
                        String deleteName = scanner.nextLine();
                        output.println("DELETE;" + deleteName);
                        System.out.println(input.readLine());
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
