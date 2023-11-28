package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluator.IfStatementEvaluator;
import org.example.jsonmodel.Payload;
import org.example.translator.Translator;
import org.example.translator.basic.BasicComparisonConditionTranslator;
import org.example.translator.basic.BasicTranslator;
import org.example.translator.basic.BasicConditionTranslator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            final String defaultJson = "data/task.json";
            String pathToJson = args.length == 0 ? defaultJson : args[0];

            IfStatementEvaluator ifStatementEvaluator = new IfStatementEvaluator();
            Payload payload = loadPayloadFromJson(pathToJson);

            String translated = getBasicTranslation(payload);
            Object ifEvaluationResult = ifStatementEvaluator.evaluate(payload);

            printResult(translated, payload, ifEvaluationResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResult(String translated, Payload payload, Object ifEvaluationResult) {
        System.out.println("--------------------------------------------------");
        System.out.println("Translated json using Basic translator:");
        System.out.println(translated);
        System.out.println("--------------------------------------------------");
        System.out.println("Inputs:");
        System.out.println(payload.getInput());
        System.out.println("--------------------------------------------------");
        System.out.println("If evaluation result: " + ifEvaluationResult);
    }

    private static String getBasicTranslation(Payload payload) {
        BasicConditionTranslator basicConditionTranslator = new BasicConditionTranslator();
        Translator translator = new BasicTranslator(basicConditionTranslator);
        return translator.translate(payload.getStatement());
    }

    private static Payload loadPayloadFromJson(String pathToJson) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = readFileAsString(pathToJson);
        return objectMapper.readValue(jsonString, Payload.class);
    }

    private static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
