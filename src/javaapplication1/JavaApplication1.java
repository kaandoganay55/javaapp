package javaapplication1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javaapplication1.FileSearchTask;

public class JavaApplication1 {
    public static void main(String[] args) {
                    System.out.print("Arama yapılacak dizini girin: ");

        try ( // Kullanıcıdan arama yapılacak dizini alıyoruz
                Scanner scanner = new Scanner(System.in)) {
            System.out.print("Arama yapılacak dizini girin: ");
            String directoryPath = scanner.nextLine(); // Dizin yolu
            File directory = new File(directoryPath);
            
            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("Geçersiz dizin yolu!");
                return;
            }
            
            // Kullanıcıdan aranacak dosya sayısını alıyoruz
            System.out.print("Kaç dosya aramak istiyorsunuz? ");
            int numFiles = scanner.nextInt();
            scanner.nextLine(); // Sonraki input için newline'ı temizle
            
            // Kullanıcıdan dosya isimlerini alıyoruz
            ArrayList<String> fileNames = new ArrayList<>();
            for (int i = 0; i < numFiles; i++) {
                System.out.print("Aranacak " + (i + 1) + ". dosyanın ismini girin: ");
                String fileName = scanner.nextLine();
                fileNames.add(fileName);
            }
            
            // ExecutorService ile thread havuzu oluşturuluyor (istediğiniz kadar thread ile çalışabilir)
            ExecutorService executor = Executors.newFixedThreadPool(5); // Örneğin 5 thread'lik bir havuz
            
            // Her dosya için bir arama thread'i başlat
            for (String fileName : fileNames) {
                executor.submit(new FileSearchTask(directory, fileName));
            }
            
            // ExecutorService bitene kadar bekleyelim
            executor.shutdown();
            
            // Threadlerin bitmesini bekle (istendiği kadar dosya arandıktan sonra)
            while (!executor.isTerminated()) {
                // Başka işlemler yapılabilir veya beklenebilir
            }
            
            System.out.println("Arama tamamlandı.");
        }
    }
}


