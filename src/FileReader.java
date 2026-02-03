import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReader {

    public ArrayList<String> readFilesContents(String filename) {
        String path = "./resources/" + filename;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочесть файл с отчетом. Возможно файл отсутствует в директории");
            return new ArrayList<>();
        }
    }
}
