package pdfGenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreatePdf {

    private String path;

    public CreatePdf(String filePath) {
        path = filePath;
    }

    public void createBillPDF(float silverPrice, float goldPrice, float platinumPrice, String month, String year) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk1 = new Chunk("Earnings:", font);
            Paragraph prg1 = new Paragraph();
            prg1.add(chunk1);

            Chunk titleChunk = new Chunk("Monthly Report: " + month + year, font);
            Paragraph prgSpace = new Paragraph();
            prgSpace.add("\n");

            document.add(titleChunk);
            document.add(prgSpace);
            document.add(prg1);
            document.add(prgSpace);

            ArrayList<String> header = new ArrayList<>();
            header.add("Silver Membership");
            header.add("Gold Membership");
            header.add("Platinum Membership");
            PdfPTable table = new PdfPTable(header.size());
            createTable(table, header);
            table.addCell(silverPrice + " RON");
            table.addCell(goldPrice + " RON");
            table.addCell(platinumPrice + " RON");
            Paragraph prg4 = new Paragraph();
            prg4.add(table);
            document.add(prg4);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createTable(PdfPTable table, ArrayList<String> header) {
        for (String headerCellContent : header) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setBorderWidth(2);
            headerCell.setPhrase(new Phrase(headerCellContent));
            table.addCell(headerCell);
        }
    }
}
