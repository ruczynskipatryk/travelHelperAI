package org.example;

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
            System.out.println("4. Poleć mi 3 miasta, które mogę odwiedzić.");
            System.out.println("5. Zamknij program");
            System.out.println();
            System.out.println("Wybierz opcje: ");

            // wybór użytkownika
            int choice = Integer.parseInt(scanner.nextLine());
            ParametersManager parametersManager = new ParametersManager();

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
                    System.out.println("Parametry podróży zostały wyzerowane.");
                    
                    parametersManager.deleteParams();
                }
                case 4 -> {
                    System.out.println("3 miasta polecene do odwiedzenia przez Travel Helper'a: ");}
                case 5 -> {
                    System.out.println("Przyjemniej podróży, do zobaczenia kolejnym razem!");
                    System.exit(0);
                }
            }


            System.out.println("Prawidłowe podanie parametrów do stworzenia Twojego dnia wygląda w następujący sposóp: ");
            System.out.println("[Nazwa Miasta], [Ilość dni (maksymalnie 10) ], [Ilość pieniędzy przeznaczona na atrakcje]");
            System.out.println("Przykład: Madryt, 5, 500 zł");
        }

    }
}