package org.example;

// klasa do łączenia się z API od OpenAI

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.List;

public class ChatGPTHelper {
    OpenAiService service;
    public ChatGPTHelper() {
        // token ze strony OpenAI
        service = new OpenAiService("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");
    }

    public String getTripScheduleIdea(List<String> parameters) {
        String allParams = String.join(",", parameters);
        String question = "Podam Ci parametry mojej podróży - miasto, ilośc dni i ilość pieniędzy: " + allParams + " Powiedz co mogę zobaczyć w danym mieście, z jakich atrakcji skorzystać za tę kwotę - ułóż plan na określoną ilość dni. ";

        // Skorzystanie z API Chat GPT - stworzenie "prośby o uzupełnienie, odpowiedź"
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();
        // Poprzez service uzyskujemy odpowiedź
        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
        // Serwis daje odpowiedź i wywołujemy getChoices dzieki czemu dostajemy liste odpowiedzi

        StringBuilder stringBuilder = new StringBuilder();

        choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append); // każda wiadomość będzie dodawana do StringBuildera
        // stworzyć ifa - jeżeli nie ma żadnych parametrów to - "Nie podałeś jeszcze żadnych parametrów podróży,
        // dodaj parametry, żeby uzyskać plan swojej wymarzonej wycieczki!
        return stringBuilder.toString();
    }
}
