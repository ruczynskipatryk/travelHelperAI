package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ParametersManager {
    private final Path parametersPath;
    public ParametersManager() throws URISyntaxException {
        ClassLoader classLoader = ParametersManager.class.getClassLoader();
        parametersPath = Paths.get(Objects.requireNonNull(classLoader.getResource("parametry.txt")).toURI());
    }
    public List<String> getAllParameters() throws IOException {

        return Files.readAllLines(parametersPath, StandardCharsets.UTF_8);
    }

    // Funkcja dodająca parametry do pliku parametry.txt

    public void addParameter(String parameter) throws IOException {
        HashSet<String> parametry = new HashSet<>(getAllParameters());

        if (!parametry.contains(parameter)){
            Files.writeString(parametersPath, System.lineSeparator() + parameter, StandardOpenOption.APPEND);
        } else {
            System.out.println("Masz już zapisany podany parametr");
        }
    }

    // Funkcja usuwająca wszystkie parametry z pliku parametry.txt

    public static void deleteParams(String parametry) {
        try {
            FileWriter fileWriter = new FileWriter(parametry);
            fileWriter.write(""); // Nadpisanie zawartości pliku pustym ciągiem znaków
            fileWriter.close();
            System.out.println("Zawartość pliku " + parametry + " została usunięta.");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas usuwania zawartości pliku: " + e.getMessage());
        }
    }


    };
