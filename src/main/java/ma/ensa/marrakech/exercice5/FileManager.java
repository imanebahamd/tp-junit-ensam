package ma.ensa.marrakech.exercice5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Le fichier n'existe pas: " + filePath);
        }

        if (!Files.isRegularFile(path)) {
            throw new IOException("Le chemin ne correspond pas à un fichier régulier: " + filePath);
        }

        if (!Files.isReadable(path)) {
            throw new IOException("Le fichier n'est pas accessible en lecture: " + filePath);
        }

        return Files.readString(path);
    }

    public String readFileWithEncoding(String filePath, String encoding) throws IOException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Le fichier n'existe pas: " + filePath);
        }

        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes, encoding);
    }
}