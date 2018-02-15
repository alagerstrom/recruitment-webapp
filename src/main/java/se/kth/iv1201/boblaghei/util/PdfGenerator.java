package se.kth.iv1201.boblaghei.util;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import se.kth.iv1201.boblaghei.entity.Application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {


    public static ByteArrayInputStream generateApplicationPdf(Application application) {

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter pdfWriter = new PdfWriter(out);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            PageSize pageSize = PageSize.A4;
            Document document = new Document(pdfDocument, pageSize);
            document.add(headLine("Application", 30, 30));
            document.add(headLine("Info", 20, 10));
            document.add(applicantInfoTable(application).setMarginBottom(20));
            document.add(headLine("Competences", 20, 10));
            document.add(competencesTable(application).setMarginBottom(20));
            document.add(headLine("Availabilities", 20, 10));
            document.add(availabilitiesTable(application).setMarginBottom(20));

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }


    private static Paragraph headLine(String text, int fontSize, int bottomMargin) {
        return new Paragraph(text).setFontSize(fontSize).setMarginBottom(bottomMargin);
    }

    private static Table applicantInfoTable(Application application) throws IOException {

        PdfFont headerFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(4);
        table.setWidthPercent(80);

        table.addHeaderCell(new Cell().add(new Paragraph("First name").setFont(headerFont).setFontSize(12)));
        table.addHeaderCell(new Cell().add(new Paragraph("Last name").setFont(headerFont).setFontSize(12)));
        table.addHeaderCell(new Cell().add(new Paragraph("Created").setFont(headerFont).setFontSize(12)));
        table.addHeaderCell(new Cell().add(new Paragraph("Status").setFont(headerFont).setFontSize(12)));

        table.addCell(new Cell().add(new Paragraph(application.getPerson().getFirstName())));
        table.addCell(new Cell().add(new Paragraph(application.getPerson().getLastName())));
        table.addCell(new Cell().add(new Paragraph(DateUtil.formatAsDateString(application.getCreated()))));
        table.addCell(new Cell().add(new Paragraph(application.getStatus().getName().toLowerCase())));


        return table;
    }

    private static Table competencesTable(Application application) throws IOException {
        PdfFont headerFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(2);
        table.setWidthPercent(80);

        table.addHeaderCell(new Cell().add(new Paragraph("Competence").setFont(headerFont).setFontSize(12)));
        table.addHeaderCell(new Cell().add(new Paragraph("Years of experience").setFont(headerFont).setFontSize(12)));

        application.getCompetenceProfiles().forEach(profile -> {
            table.addCell(new Cell().add(new Paragraph(profile.getCompetence().getName()).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(Double.toString(profile.getYearsOfExperience())).setFontSize(10)));
        });

        return table;
    }

    private static Table availabilitiesTable(Application application) throws IOException {
        PdfFont headerFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(2);
        table.setWidthPercent(80);

        table.addHeaderCell(new Cell().add(new Paragraph("From").setFont(headerFont).setFontSize(12)));
        table.addHeaderCell(new Cell().add(new Paragraph("To").setFont(headerFont).setFontSize(12)));

        application.getAvailabilities().forEach(availability -> {
            table.addCell(new Cell().add(new Paragraph(DateUtil.formatAsDateString(availability.getFromDate())).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(DateUtil.formatAsDateString(availability.getToDate())).setFontSize(10)));
        });

        return table;
    }
}
