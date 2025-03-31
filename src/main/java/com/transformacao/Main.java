package com.transformacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.transformacao.DataExporter.DataExporter;
import com.transformacao.FileUtils.FileUtils;
import com.transformacao.TextExtractor.TextExtractor;
import com.transformacao.models.Procedure;

public class Main {
    public static void main(String[] args) {
        Path inputDir = Paths.get("input");
        Path inputFile = inputDir.resolve("Anexo_I.pdf");
        String outputCsvName = "procedures";
        String zipName = "Teste_Micherenio";

        try {
            Files.createDirectories(inputDir);
            if (!Files.exists(inputFile)) {
                throw new RuntimeException("O arquivo " + inputFile + " não foi encontrado no diretório 'input/'");
            }

            String documentText = FileUtils.readPdfFile(inputFile.toString());
            TextExtractor extractor = new TextExtractor(documentText);
            DataExporter exporter = new DataExporter("output");

            List<Procedure> procedures = extractor.extractProcedures();
            System.out.println("Número de procedimentos extraídos: " + procedures.size());

            Path csvPath = exporter.exportToCsv(procedures, outputCsvName);
            Path zipPath = exporter.createZip(csvPath, zipName);

            System.out.println("CSV gerado em: " + csvPath);
            System.out.println("ZIP gerado em: " + zipPath);

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}