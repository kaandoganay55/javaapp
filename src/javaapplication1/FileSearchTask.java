/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.io.File;

public class FileSearchTask implements Runnable {
    private File directory;          // Arama yapılacak dizin
    private String fileNameToSearch; // Aranacak dosyanın ismi

    // Constructor: Dizin ve aranacak dosya adı
    public FileSearchTask(File directory, String fileNameToSearch) {
        this.directory = directory;
        this.fileNameToSearch = fileNameToSearch;
    }

    @Override
    public void run() {
        search(directory); // Arama işlemini başlat
    }

    // Arama metodunu gerçekleştiren fonksiyon
    private void search(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();  // Dizindeki tüm dosya ve alt dizinleri al
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Eğer alt dizin varsa, burada da arama yap
                        search(file); // Rekürsif olarak alt dizinlerde arama yapılır
                    } else if (file.getName().equals(fileNameToSearch)) {
                        // Dosya bulunduğunda ekrana yazdır
                        System.out.println("Found file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
