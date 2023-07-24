package org.example;

import java.io.IOException;
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

    public void addParameter(String parameter) throws IOException {
        HashSet<String> parametry = new HashSet<>(getAllParameters());

        if (!parametry.contains(parameter)){
            Files.writeString(parametersPath, System.lineSeparator() + parameter, StandardOpenOption.APPEND);
        } else {
            System.out.println("Masz już zapisany podany parametr");
        }
    }

    public void deleteParams(String parametr) throws IOException {

    }

};
