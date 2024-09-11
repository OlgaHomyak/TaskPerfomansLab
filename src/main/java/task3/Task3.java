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
            ObjectMapper objectMapper = new ObjectMapper(); // создание объекта для работы с json
            // чтение + парсинг файлов
            JsonNode testsRoot = objectMapper.readTree(new File(testsFilePath));
            JsonNode valuesRoot = objectMapper.readTree(new File(valuesFilePath));

            Map<Integer, String> valuesMap = new HashMap<>();
            for (JsonNode valueNode : valuesRoot.get("values")) { // получаем id и значения, заполняем ими мапы
                int id = valueNode.get("id").asInt();
                String value = valueNode.get("value").asText();
                valuesMap.put(id, value);
            }

            populateValues(testsRoot.get("tests"), valuesMap); // обработка json и заполнение value методом populateValues

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsRoot); // запись report
            System.out.println("Отчет успешно сгенерирован и сохранен в: " + reportFilePath); // + вывод результата
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // функция  проходит по узлам json из tests, добавляя соответствующие значения из мапа по их ид
    private static void populateValues(JsonNode testsNode, Map<Integer, String> valuesMap) {
        for (JsonNode testNode : testsNode) {
            int id = testNode.get("id").asInt(); // извлечение id
            String value = valuesMap.getOrDefault(id, ""); //  value по id, если такого нет, то ""
            if (testNode instanceof ObjectNode) {
                ((ObjectNode) testNode).put("value", value); // + узел
            }
            if (testNode.has("values")) { //
                populateValues(testNode.get("values"), valuesMap);
            }
        }
    }
}
        //src/main/java/task3/values.json
        //src/main/java/task3/tests.json
        //src/main/java/task3/report.json

