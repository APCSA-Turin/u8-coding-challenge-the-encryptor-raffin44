package com.example.project;

public class Encryptor {

    public static int determineColumns(int messageLen, int rows) {
        if (rows <= 0) return 0;
        return Math.max(1, (messageLen + rows - 1) / rows);
    }

    public static String[][] generateEncryptArray(String message, int rows) {
        int cols = determineColumns(message.length(), rows);
        String[][] grid = new String[rows][cols];

        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < message.length()) {
                    grid[r][c] = String.valueOf(message.charAt(index));
                } else {
                    grid[r][c] = "=";
                }
                index++;
            }
        }
        return grid;
    }

    public static String encryptMessage(String message, int rows) {
        if (message.isEmpty()) {
            StringBuilder placeholder = new StringBuilder();
            for (int i = 0; i < Math.max(1, rows); i++) {
                placeholder.append("=");
            }
            return placeholder.toString();
        }

        String[][] grid = generateEncryptArray(message, rows);
        int cols = determineColumns(message.length(), rows);

        StringBuilder encrypted = new StringBuilder();
        for (int c = cols - 1; c >= 0; c--) {
            for (int r = 0; r < rows; r++) {
                encrypted.append(grid[r][c]);
            }
        }
        return encrypted.toString();
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        if (rows <= 0 || encryptedMessage.isEmpty()) return "";
    
        int cols = determineColumns(encryptedMessage.length(), rows);
    
        if (cols * rows < encryptedMessage.length()) {
            System.out.println("Invalid row count for decryption.");
        }
    
        String[][] grid = new String[rows][cols];
        int index = 0;
    
        for (int c = cols - 1; c >= 0; c--) {
            for (int r = 0; r < rows; r++) {
                if (index < encryptedMessage.length()) {
                    grid[r][c] = String.valueOf(encryptedMessage.charAt(index));
                    index++;
                }
            }
        }
    
        String decrypted = "";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != null && !grid[r][c].equals("=")) {
                    decrypted += grid[r][c];  
                }
            }
        }
        return decrypted;
    }
    

    public static void main(String[] args) {
        String message = "Bnasshgasbkblleoa m!===eoJeit ee stlpy  li!===Lr m  erttaea arflte!==";
        int rows = 23;

        String encrypted = encryptMessage(message, rows);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decryptMessage(message, rows);
        System.out.println("Decrypted: " + decrypted);
    }
}
