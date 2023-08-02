package org.example;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;


// klasa do łączenia się z API od OpenAI
public class ChatGPTHelper {
    OpenAiService service;
    public ChatGPTHelper() {
        // token ze strony OpenAI
        service = new OpenAiService("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", Duration.ofSeconds(30));
        // drugi arguemnt - jeżeli nie będzie odpowiedzi ze strona chata w 30 sekund
    }

    public String getTripScheduleIdea(List<String> parameters) {
        String allParams = String.join(",", parameters);
        String question = "Podam Ci parametry mojej podróży: " + allParams + " Ułóż plan na określoną ilość dni.";

        return askChatGPT(question);
    }

    public String getTownsRecommendation(List<String> parameters){
        String allParams = String.join(", ", parameters);
        String question = "Poleć mi 3 miasta warte odwiedzenia, takie, które nie znajdują się na liście moich parametrów." + allParams;

        return askChatGPT(question);
    }

    private String askChatGPT(String question) {
        // Skorzystanie z API Chat GPT - stworzenie "prośby o uzupełnienie, odpowiedź"
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();
        // Poprzez service uzyskujemy odpowiedź
        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
        // Serwis daje odpowiedź i wywołujemy getChoices dzieki czemu dostajemy liste odpowiedzi

        StringBuilder stringBuilder = new StringBuilder();

        // mapowanie wiadomości, które otrzymamy
        choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append); // każda wiadomość będzie dodawana do StringBuildera
        return stringBuilder.toString();
    }
}
