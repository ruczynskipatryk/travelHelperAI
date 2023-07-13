package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ParametersManager {
    public List<String> getAllParameters() throws IOException, URISyntaxException {
        ClassLoader classLoader = ParametersManager.class.getClassLoader();
        Path parametersPath = Paths.get(Objects.requireNonNull(classLoader.getResource("parametry.txt")).toURI());

        return Files.readAllLines(parametersPath, StandardCharsets.UTF_8);
}};
