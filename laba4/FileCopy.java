import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void copyWithManualClose(String sourcePath, String destPath) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(sourcePath);   // может выбросить FileNotFoundException (подкласс IOException)
            out = new FileOutputStream(destPath);   // тоже может выбросить FileNotFoundException
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Копирование завершено (manual close).");
        } catch (IOException ex) {
            System.err.println("Ошибка при открытии/чтении/записи файла: " + ex.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    System.err.println("Ошибка при закрытии входного потока: " + ex.getMessage());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    System.err.println("Ошибка при закрытии выходного потока: " + ex.getMessage());
                }
            }
        }
    }

    public static void copyWithTryWithResources(String sourcePath, String destPath) {
        try (FileInputStream in = new FileInputStream(sourcePath);
             FileOutputStream out = new FileOutputStream(destPath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Копирование завершено (try-with-resources).");
        } catch (IOException ex) {
            System.err.println("Ошибка при копировании файла: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String source = "source.txt";
        String dest1 = "dest_manual.txt";
        String dest2 = "dest_twr.txt";
        copyWithManualClose(source, dest1);
        copyWithTryWithResources(source, dest2);
    }
}