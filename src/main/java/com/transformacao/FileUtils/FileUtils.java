package com.transformacao.FileUtils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.IOException;

public class FileUtils {
    public static String readPdfFile(String filePath) throws IOException {
        try (PdfReader reader = new PdfReader(filePath);
             PdfDocument pdfDoc = new PdfDocument(reader)) {
            StringBuilder text = new StringBuilder();
            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                text.append(PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i)));
            }
            return text.toString();
        } catch (IOException e) {
            throw new IOException("Erro ao ler o PDF: " + e.getMessage());
        }
    }
}