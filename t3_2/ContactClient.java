package com.software.t3_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ContactClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("已连接到服务器。");
            while (true) {
                System.out.println("请选择操作: 1-列出联系人, 2-添加联系人, 3-删除联系人, 4-退出");
                int choice = scanner.nextInt();
                scanner.nextLine();  // 消耗换行符

                switch (choice) {
                    case 1:
                        output.println("LIST");
                        String response;
                        while (!(response = input.readLine()).equals("END")) {
                            System.out.println(response);
                        }
                        break;

                    case 2:
                        System.out.print("请输入姓名: ");
                        String name = scanner.nextLine();
                        System.out.print("请输入地址: ");
                        String address = scanner.nextLine();
                        System.out.print("请输入电话: ");
                        String phone = scanner.nextLine();
                        output.println("ADD;" + name + ";" + address + ";" + phone);
                        System.out.println(input.readLine());
                        break;

                    case 3:
                        System.out.print("请输入要删除的联系人姓名: ");
                        String deleteName = scanner.nextLine();
                        output.println("DELETE;" + deleteName);
                        System.out.println(input.readLine());
                        break;

                    case 4:
                        System.out.println("正在退出...");
                        return;

                    default:
                        System.out.println("无效选择。");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}