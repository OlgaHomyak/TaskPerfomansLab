package task3;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Task3 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу tests.json:");
        String testsFilePath = scanner.nextLine();

        System.out.println("Введите путь к файлу values.json:");
        String valuesFilePath = scanner.nextLine();

        System.out.println("Введите путь для сохранения report.json:");
        String reportFilePath = scanner.nextLine();

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Чтение файлов
            JsonNode testsRoot = objectMapper.readTree(new File(testsFilePath));
            JsonNode valuesRoot = objectMapper.readTree(new File(valuesFilePath));

            // Создание мапы для id и value
            Map<Integer, String> valuesMap = new HashMap<>();
            for (JsonNode valueNode : valuesRoot.get("values")) {
                int id = valueNode.get("id").asInt();
                String value = valueNode.get("value").asText();
                valuesMap.put(id, value);
            }

            // Обработка JSON и заполнение value
            populateValues(testsRoot.get("tests"), valuesMap);

            // Запись результата в report.json
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsRoot);
            System.out.println("Отчет успешно сгенерирован и сохранен в: " + reportFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateValues(JsonNode testsNode, Map<Integer, String> valuesMap) {
        for (JsonNode testNode : testsNode) {
            int id = testNode.get("id").asInt();
            String value = valuesMap.getOrDefault(id, "");
            if (testNode instanceof ObjectNode) {
                ((ObjectNode) testNode).put("value", value);
            }
            if (testNode.has("values")) {
                populateValues(testNode.get("values"), valuesMap);
            }
        }
    }
}
        //src/main/java/task3/values.json
        //src/main/java/task3/tests.json
        //src/main/java/task3/report.json

