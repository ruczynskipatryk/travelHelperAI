package org.example;

import java.io.*;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
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
        parametry.add(parameter);

        // Zapisanie zawartości zbioru do pliku "parametry.txt"
        Files.write(parametersPath, parametry, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }




    // Funkcja usuwająca przenosząca plik parametry.txt do katalogu roboczego, tam dokonanie zmiany
    // czyli wyczyszczenie pliku i następnie ponowne skopiowanie go do katalogu resources



    public void deleteParams() throws IOException {
        // Odczytanie ścieżki do pliku "parametry.txt" jako URL
        URL resourceUrl = ParametersManager.class.getClassLoader().getResource("parametry.txt");
        if (resourceUrl == null) {
            System.err.println("Nie znaleziono pliku zasobu: parametry.txt");
            return;
        }
        Path resourcePath;
        try {
            resourcePath = Paths.get(resourceUrl.toURI());
        } catch (URISyntaxException e) {
            System.err.println("Wystąpił błąd podczas pobierania ścieżki pliku: " + e.getMessage());
            return;
        }

        // Nadpisz zawartość pliku "parametry.txt" pustą zawartością
        FileWriter fileWriter = new FileWriter(resourcePath.toFile());
        fileWriter.write("");
        fileWriter.close();

        System.out.println("Parametry podróży zostały usunięte. Dodaj kolejne parametry na swoją kolejną podróż!");
    }







};
