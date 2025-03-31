package com.transformacao.DataExporter;

import com.opencsv.CSVWriter;
import com.transformacao.models.Procedure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DataExporter {
    private Path outputDir;
    private String[] fieldNames = {
        "Procedure Name", "RN", "Effective Date", "Dental Coverage",
        "Outpatient Coverage", "Hospital with Obstetrics",
        "Hospital without Obstetrics", "Reference Plan",
        "High Complexity Procedure", "Usage Guideline",
        "Subgroup", "Group", "Chapter"
    };

    public DataExporter(String outputDir) {
        this.outputDir = Paths.get(outputDir);
        try {
            Files.createDirectories(this.outputDir);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar diretório de saída: " + e.getMessage());
        }
    }

    public Path exportToCsv(List<Procedure> procedures, String filename) throws IOException {
        Path csvPath = outputDir.resolve(filename + ".csv");
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvPath.toFile()))) {
            writer.writeNext(fieldNames);

            for (Procedure procedure : procedures) {
                String[] row = {
                    procedure.getName(),
                    procedure.getRn(),
                    procedure.getEffectiveDate(),
                    procedure.getDentalCoverage(),
                    procedure.getOutpatientCoverage(),
                    procedure.getHospitalWithObstetrics(),
                    procedure.getHospitalWithoutObstetrics(),
                    procedure.getReferencePlan(),
                    procedure.getHighComplexity(),
                    procedure.getUsageGuideline(),
                    procedure.getSubgroup(),
                    procedure.getGroup(),
                    procedure.getChapter()
                };
                writer.writeNext(row);
            }
        }
        return csvPath;
    }

    public Path createZip(Path csvPath, String zipName) throws IOException {
        Path zipPath = outputDir.resolve(zipName + ".zip");
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            File fileToZip = csvPath.toFile();
            zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
            Files.copy(fileToZip.toPath(), zipOut);
            zipOut.closeEntry();
        }
        return zipPath;
    }
}
