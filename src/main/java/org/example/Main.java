package org.example;

import com.theokanning.openai.service.OpenAiService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in); // Wczytanie danych od użytkownika

        // pętla pytająca o opcje wyboru użytkownika
        while(true) {
            System.out.println("1. Wyświetl aktualne parametry podrózy");
            System.out.println("2. Dodaj parametry podróży (instrukcja poniżej)");
            System.out.println("3. Usuń wszystkie parametry podróży");
            System.out.println("4. Podaj mi plan podróży na wybrane przeze mnie miasto.");
            System.out.println("5. Poleć mi 3 miasta, które mogę odwiedzić.");
            System.out.println("6. Koniec planowania na dziś - zamknij program.");
            System.out.println();
            System.out.println("Wybierz opcje: ");

            // wybór użytkownika
            int choice = Integer.parseInt(scanner.nextLine());
            ParametersManager parametersManager = new ParametersManager();
            ChatGPTHelper chatGPTHelper = new ChatGPTHelper();

            switch (choice){
                case 1 -> {
                    System.out.println("Aktualne parametry podróży: ");
                    parametersManager.getAllParameters().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Dodaj parametry podróży: ");
                    String parameter = scanner.nextLine();
                    parametersManager.addParameter(parameter);
                }
                case 3 -> {
                    try {
                        parametersManager.deleteParams();
                    } catch (IOException e) {
                        System.err.println("Wystąpił błąd podczas usuwania zawartości pliku: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Oto Twój plan podróży stworzony przez Twojego Travel Helper'a: ");
                    String tripScheduleIdea = chatGPTHelper.getTripScheduleIdea(parametersManager.getAllParameters());

                    // if na wypadek jeżeli ktoś nie podałby parametrów podróży
                    if (tripScheduleIdea.isEmpty()){
                        System.out.println("Podaj parametry podróży, żeby uzyskać plan swojej wymarzonej wycieczki!");
                    }
                    else {
                        System.out.println(tripScheduleIdea);
                    }
                }
                case 5 -> {
                    System.out.println("3 miasta polecene do odwiedzenia przez Travel Helper'a: ");
                    String townsRecommendation = chatGPTHelper.getTownsRecommendation(parametersManager.getAllParameters());

                    System.out.println(townsRecommendation);
                }
                case 6 -> {
                    System.out.println("Przyjemniej podróży, do zobaczenia kolejnym razem!");
                    System.exit(0);
                }
            }


            System.out.println("Prawidłowe podanie parametrów do stworzenia planu Twojego wyjazdu wygląda następująco: ");
            System.out.println("Nazwa Miasta | Ilość dni (maksymalnie 10) | Ilość pieniędzy przeznaczona na atrakcje");
            System.out.println("Przykład: Madryt 3 dni 500 zł");
        }

    }
}