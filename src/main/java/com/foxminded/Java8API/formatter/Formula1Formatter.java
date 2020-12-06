package com.foxminded.Java8API.formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Formula1Formatter implements Formatter {
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("mm:ss.SSS");
    private static final String LINE_BREAK = "\n";
    private static final String TAB = " ";
    private static final String DASH = "-";

    private final Map<String, Calendar> calculatedMap;

    public Formula1Formatter(Map<String, Calendar> calculatedMap) {
        this.calculatedMap = calculatedMap;
    }

    @Override
    public String format() {
        StringBuilder report = new StringBuilder();

        calculatedMap.entrySet().stream().limit(15).forEach(e ->
                report.append(compare(e.getKey()))
                        .append(TAB).append(FORMAT_TIME.format(e.getValue().getTime()))
                        .append(LINE_BREAK));

        for (int i = 0; i < getLength(report); i++) {
            report.append(DASH);
        }

        report.append(LINE_BREAK);

        calculatedMap.entrySet().stream().skip(15).forEach(e ->
                report.append(compare(e.getKey()))
                        .append(TAB).append(FORMAT_TIME.format(e.getValue().getTime()))
                        .append(LINE_BREAK));

        return report.toString();
    }

    private String compare(String racerAbbreviation) {
        Map<String, String> abbreviations = new HashMap<>();
        String result = null;

        abbreviations.put("DRR", "Daniel Ricciardo   | RED BULL RACING TAG HEUER   |");
        abbreviations.put("SVF", "Sebastian Vettel   | FERRARI                     |");
        abbreviations.put("LHM", "Lewis Hamilton     | MERCEDES                    |");
        abbreviations.put("KRF", "Kimi Raikkonen     | FERRARI                     |");
        abbreviations.put("VBM", "Valtteri Bottas    | MERCEDES                    |");
        abbreviations.put("EOF", "Esteban Ocon       | FORCE INDIA MERCEDES        |");
        abbreviations.put("FAM", "Fernando Alonso    | MCLAREN RENAULT             |");
        abbreviations.put("CSR", "Carlos Sainz       | RENAULT                     |");
        abbreviations.put("SPF", "Sergio Perez       | FORCE INDIA MERCEDES        |");
        abbreviations.put("PGS", "Pierre Gasly       | SCUDERIA TORO ROSSO HONDA   |");
        abbreviations.put("NHR", "Nico Hulkenberg    | RENAULT                     |");
        abbreviations.put("SVM", "Stoffel Vandoorne  | MCLAREN RENAULT             |");
        abbreviations.put("SSW", "Sergey Sirotkin    | WILLIAMS MERCEDES           |");
        abbreviations.put("CLS", "Charles Leclerc    | SAUBER FERRARI              |");
        abbreviations.put("RGH", "Romain Grosjean    | HAAS FERRARI                |");
        abbreviations.put("BHS", "Brendon Hartley    | SCUDERIA TORO ROSSO HONDA   |");
        abbreviations.put("MES", "Marcus Ericsson    | SAUBER FERRARI              |");
        abbreviations.put("LSW", "Lance Stroll       | WILLIAMS MERCEDES           |");
        abbreviations.put("KMH", "Kevin Magnussen    | HAAS FERRARI                |");

        for (Map.Entry<String, String> pair : abbreviations.entrySet()) {
            String abbreviation = pair.getKey();
            String name = pair.getValue();

            if (abbreviation.equals(racerAbbreviation)) {
                result = name;
            }
        }

        return result;
    }

    private int getLength(StringBuilder stringBuilder) {
        return stringBuilder.substring(0, 60).length();
    }
}
