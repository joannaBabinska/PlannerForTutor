package com.babinska.plannerfortutor.filegenerator;

import com.babinska.plannerfortutor.common.FileFormat;
import com.babinska.plannerfortutor.student.dto.StudentsDownloadsView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class FilePdfContentGenerator implements FileContentGenerator {

    @Override
    public byte[] generateFile(List<StudentsDownloadsView> inputData) {
        try {
            Document document = new Document();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, stream);
            createDocument(inputData, document);
            return stream.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDocument(List<StudentsDownloadsView> inputData, Document document) throws DocumentException {
        document.open();
        PdfPTable table = new PdfPTable(5);
        addTableHeaders(table);
        addRows(table, inputData);
        document.add(table);
        document.close();
    }

    private void addRows(PdfPTable table, List<StudentsDownloadsView> inputData) {
        for (StudentsDownloadsView inputDatum : inputData) {
            table.addCell(inputDatum.getFirstname());
            table.addCell(inputDatum.getLastname());
            table.addCell(inputDatum.getEmail());
            table.addCell(inputDatum.getPhoneNumber());
            table.addCell(inputDatum.getSchoolClass());
        }
    }

    private void addTableHeaders(PdfPTable table) {
        Stream.of("firstname", "lastName", "email", "phone number", "school class")
                .forEach(columnTitle ->
                        {
                            PdfPCell header = new PdfPCell();
                            header.setBackgroundColor(BaseColor.PINK);
                            header.setBorderWidth(2);
                            header.setPhrase(new Phrase(columnTitle));
                            table.addCell(header);
                        }
                );
    }

    @Override
    public boolean supportsFormat(FileFormat fileFormat) {
        return fileFormat.equals(FileFormat.PDF);
    }
}
