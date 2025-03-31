package com.transformacao.TextExtractor;

import com.transformacao.models.Procedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractor {
    private String text;
    private Map<String, String> legendMap;

    public TextExtractor(String text) {
        this.text = text;
        this.legendMap = new HashMap<>();
        legendMap.put("OD", "Dental Coverage");
        legendMap.put("AMB", "Outpatient Coverage");
        legendMap.put("HCO", "Hospital with Obstetrics");
        legendMap.put("HSO", "Hospital without Obstetrics");
        legendMap.put("REF", "Reference Plan");
        legendMap.put("PAC", "High Complexity Procedure");
        legendMap.put("DUT", "Usage Guideline");
    }

    public List<Procedure> extractProcedures() {
        List<Procedure> procedures = new ArrayList<>();
        String pattern = "^(.*?)\\\\s+(?:\\\\d{3}/\\\\d{4})?\\\\s*(\\\\d{2}/\\\\d{2}/\\\\d{4})?\\\\s*([A-Za-z]{2,3})?\\\\s*([A-Za-z]{2,3})?\\\\s*([A-Za-z]{2,3})?\\\\s*([A-Za-z]{2,3})?\\\\s*([A-Za-z]{2,3})?\\\\s*([A-Za-z]{2,3})?\\\\s*([A-Za-z]{2,3})?";
        Pattern regex = Pattern.compile(pattern);

        String[] lines = text.split("\\n");
        Map<String, String> currentProcedure = null;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.contains("Legenda:") || line.contains("Rol de Procedimentos")) {
                continue;
            }

            Matcher matcher = regex.matcher(line);
            if (matcher.matches()) {
                if (currentProcedure != null) {
                    procedures.add(createProcedure(currentProcedure));
                }
                currentProcedure = new HashMap<>();
                currentProcedure.put("name", matcher.group(1).trim());
                currentProcedure.put("rn", matcher.group(2) != null ? matcher.group(2) : "");
                currentProcedure.put("effective_date", matcher.group(3) != null ? matcher.group(3) : "");
                currentProcedure.put("dental_coverage", matcher.group(4) != null ? matcher.group(4) : "");
                currentProcedure.put("outpatient_coverage", matcher.group(5) != null ? matcher.group(5) : "");
                currentProcedure.put("hospital_with_obstetrics", matcher.group(6) != null ? matcher.group(6) : "");
                currentProcedure.put("hospital_without_obstetrics", matcher.group(7) != null ? matcher.group(7) : "");
                currentProcedure.put("reference_plan", matcher.group(8) != null ? matcher.group(8) : "");
                currentProcedure.put("high_complexity", matcher.group(9) != null ? matcher.group(9) : "");
                currentProcedure.put("usage_guideline", "");
                currentProcedure.put("subgroup", "");
                currentProcedure.put("group", "");
                currentProcedure.put("chapter", "");
            }
        }

        if (currentProcedure != null) {
            procedures.add(createProcedure(currentProcedure));
        }

        return procedures;
    }

    private Procedure createProcedure(Map<String, String> data) {
        return new Procedure(
            data.get("name"),
            data.get("rn"),
            data.get("effective_date"),
            data.get("dental_coverage"),
            data.get("outpatient_coverage"),
            data.get("hospital_with_obstetrics"),
            data.get("hospital_without_obstetrics"),
            data.get("reference_plan"),
            data.get("high_complexity"),
            data.get("usage_guideline"),
            data.get("subgroup"),
            data.get("group"),
            data.get("chapter")
        );
    }
}
