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
        String pattern = "^(.+?)\\s+(AMB|OD|HCO|HSO|REF|PAC|DUT)(?:\\s+(AMB|OD|HCO|HSO|REF|PAC|DUT))*.*$";
        Pattern regex = Pattern.compile(pattern);

        String[] lines = text.split("\\n");
        Map<String, String> currentProcedure = null;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.contains("Legenda:") || line.contains("Rol de Procedimentos") ||
                line.equals("PROCEDIMENTOS DIAGNÓSTICOS E") || line.equals("TERAPÊUTICOS")) {
                continue;
            }

            Matcher matcher = regex.matcher(line);
            if (matcher.matches()) {
                System.out.println("Linha correspondida: " + line);
                if (currentProcedure != null) {
                    procedures.add(createProcedure(currentProcedure));
                }
                currentProcedure = new HashMap<>();
                String name = matcher.group(1).trim();
                currentProcedure.put("name", name);
                currentProcedure.put("rn", "");
                currentProcedure.put("effective_date", "");

                String[] parts = line.split("\\s+");
                for (String part : parts) {
                    if (legendMap.containsKey(part)) {
                        switch (part) {
                            case "OD": currentProcedure.put("dental_coverage", "OD"); break;
                            case "AMB": currentProcedure.put("outpatient_coverage", "AMB"); break;
                            case "HCO": currentProcedure.put("hospital_with_obstetrics", "HCO"); break;
                            case "HSO": currentProcedure.put("hospital_without_obstetrics", "HSO"); break;
                            case "REF": currentProcedure.put("reference_plan", "REF"); break;
                            case "PAC": currentProcedure.put("high_complexity", "PAC"); break;
                            case "DUT": currentProcedure.put("usage_guideline", "DUT"); break;
                        }
                    }
                }
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