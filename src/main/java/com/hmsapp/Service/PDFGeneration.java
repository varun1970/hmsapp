package com.hmsapp.Service;
import com.hmsapp.Entity.Booking;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;
@Service
public class PDFGeneration {
    public void pdfGeneration(String path, Booking booking){
       try {
          // Step 1: Create a PdfWriter using ByteArrayOutputStream
          PdfWriter writer = new PdfWriter(path);

          // Step 2: Create a PdfDocument and attach the writer
          PdfDocument pdfDoc = new PdfDocument(writer);

          // Step 3: Create a layout Document object
          Document document = new Document(pdfDoc);

          // Step 4: Add a Paragraph
          document.add(new Paragraph("Generated PDF Content:"));

          // Step 5: Add a Table
          float[] columnWidths = {1, 2}; // Define column widths
          Table table = new Table(columnWidths);

          // Add header cells
          table.addCell("Name");
          table.addCell("Mobile Number");

          // Add data rows
          table.addCell(booking.getGuestName());
          table.addCell(booking.getMobile());

          // Add the table to the document
          document.add(table);
          // Step 6: Close the Document
          document.close();
       } catch (Exception e) {
          e.printStackTrace();
       }
    }
}
