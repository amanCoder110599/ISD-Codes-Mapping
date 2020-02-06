package com.example.demo;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

/**
 * @author Abhimanyu Singh
 * @author abhimanyusingh@hackerrank.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResultMatcher {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * @param jsonString string representing JSON array
     * @return parsed JSON array
     */
    private static List<JsonNode> parseJsonArray(String jsonString) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, new TypeReference<List<JsonNode>>() {});
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * @param jsonArray JSON array
     * @return sorted JSON array
     */
    private static List<JsonNode> sortJsonArray(List<JsonNode> jsonArray) {
        return jsonArray.stream().sorted(comparing(JsonNode::toString)).collect(toList());
    }

    /**
     * @param response JSON array
     * @param expected expected JSON array
     * @return boolean describing whether preliminary tests failed or succeeded
     */
    private static boolean preliminaryJsonArrayMatching(
            List<JsonNode> response, List<JsonNode> expected) {
        if (response == null || expected == null) {
            return false;
        }

        return response.size() == expected.size();
    }

    /**
     * @param response JSON array
     * @param expected expected JSON array
     * @param reportMismatch indicates whether to print the mismatch or not
     * @return boolean describing whether comparison failed or succeeded
     */
    private static boolean jsonArrayMatching(
            List<JsonNode> response, List<JsonNode> expected, boolean reportMismatch) {
        for (int i = 0; i < response.size(); i++) {
            JsonNode expectedJson = expected.get(i);
            JsonNode responseJson = response.get(i);

            if (!expectedJson.equals(responseJson)) {
                if (reportMismatch) {
                    System.out.println(
                            Color.RED
                                    + "Expected <"
                                    + expectedJson.toString()
                                    + "> but was <"
                                    + responseJson.toString()
                                    + ">"
                                    + " (at index "
                                    + i
                                    + ")."
                                    + Color.RESET);
                }

                return false;
            }
        }

        return true;
    }

    /**
     * @param responseString JSON array string
     * @param expectedString expected JSON array string
     * @param reportMismatch indicates whether to print the mismatch or not
     * @return boolean describing whether comparison ignoring order, failed or succeeded
     */
    public static boolean matchJsonArrayIgnoreOrder(
            String responseString, String expectedString, boolean reportMismatch) {
        List<JsonNode> response = ResultMatcher.parseJsonArray(responseString);
        List<JsonNode> expected = ResultMatcher.parseJsonArray(expectedString);

        boolean preliminary = ResultMatcher.preliminaryJsonArrayMatching(response, expected);

        if (!preliminary) {
            return false;
        }

        return ResultMatcher.jsonArrayMatching(
                ResultMatcher.sortJsonArray(response),
                ResultMatcher.sortJsonArray(expected),
                reportMismatch);
    }

    /**
     * @param responseString JSON array string
     * @param expectedString expected JSON array string
     * @param reportMismatch indicates whether to print the mismatch or not
     * @return boolean describing whether comparison failed or succeeded
     */
    public static boolean matchJsonArray(
            String responseString, String expectedString, boolean reportMismatch) {
        List<JsonNode> response = ResultMatcher.parseJsonArray(responseString);
        List<JsonNode> expected = ResultMatcher.parseJsonArray(expectedString);

        boolean preliminary = ResultMatcher.preliminaryJsonArrayMatching(response, expected);

        if (!preliminary) {
            return false;
        }

        return ResultMatcher.jsonArrayMatching(response, expected, reportMismatch);
    }

    /**
     * @param responseString JSON string
     * @param expectedString expected JSON string
     * @param reportMismatch indicates whether to print the mismatch or not
     * @return boolean describing whether comparison failed or succeeded
     */
    public static boolean matchJson(
            String responseString, String expectedString, boolean reportMismatch) {
        try {
            JsonNode response = OBJECT_MAPPER.readTree(responseString);
            JsonNode expected = OBJECT_MAPPER.readTree(expectedString);

            if (!response.equals(expected)) {
                if (reportMismatch) {
                    System.out.println(
                            Color.RED
                                    + "Expected <"
                                    + expectedString
                                    + "> but was <"
                                    + responseString
                                    + ">."
                                    + Color.RESET);
                }

                return false;
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}