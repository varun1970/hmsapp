package com.hmsapp.Service;

import com.hmsapp.Entity.Booking;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PDFGeneration {

   public void pdfGeneration(String path, Booking booking) {
      try {
         // Step 1: Create the HTML content with CSS
         String htmlContent = generateHtmlContentWithCss(booking);

         // Step 2: Define the output file
         File pdfFile = new File(path);
         FileOutputStream outputStream = new FileOutputStream(pdfFile);

         // Step 3: Use HtmlConverter to convert HTML to PDF
         ConverterProperties converterProperties = new ConverterProperties();
         HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties);

         System.out.println("PDF created successfully at: " + path);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private String generateHtmlContentWithCss(Booking booking) {
      // Define inline CSS styles
      String css = """
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                        margin: 20px;
                        color: #555;
                        background-color: #f9f9f9;
                    }
                    h1 {
                        text-align: center;
                        color: #333;
                        font-size: 24px;
                        margin-bottom: 20px;
                    }
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-top: 20px;
                        background-color: #fff;
                    }
                    th, td {
                        border: 1px solid #ddd;
                        padding: 12px;
                        text-align: left;
                        font-size: 14px;
                    }
                    th {
                        background-color: #f4f4f4;
                        color: #333;
                        font-weight: bold;
                    }
                    tr:nth-child(even) {
                        background-color: #f9f9f9;
                    }
                    tr:hover {
                        background-color: #eaeaea;
                    }
                    .container {
                        width: 80%;
                        margin: 0 auto;
                    }
                    .header {
                        font-size: 16px;
                        color: #333;
                        font-weight: bold;
                        margin-bottom: 10px;
                    }
                    .footer {
                        font-size: 12px;
                        color: #999;
                        margin-top: 30px;
                        text-align: center;
                    }
                    .info {
                        margin-top: 20px;
                        font-size: 14px;
                        color: #333;
                    }
                    .info p {
                        margin: 5px 0;
                    }
                </style>
                """ ;

      // Generate HTML content
      String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    %s
                </head>
                <body>
                    <div class="container">
                        <h1>Booking Details</h1>
                        <div class="header">Guest Information:</div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Mobile Number</th>
                                    <th>Number of Guests</th>
                                    <th>Place</th>
                                    <th>Country</th>
                                    <th>City</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>%s</td>
                                    <td>%s</td>
                                    <td>%d</td>
                                    <td>%s</td>
                                    <td>%s</td>
                                    <td>%s</td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="info">
                            <p><strong>You have booked a hotel near:</strong> %s</p>
                            <p><strong>Customer Service Number:</strong> 8904418145</p>
                            <p><strong>Email:</strong> <a href="mailto:vb05122001@gmail.com">vb05122001@gmail.com</a></p>
                        </div>
                    </div>
                    <div class="footer">
                        <p><strong>Disclaimer:</strong> This email is auto-generated. Please do not reply to this email.</p>
                    </div>
                </body>
                </html>
                """ ;

      // Populate booking details into the HTML template
      return String.format(
              html,
              css,
              booking.getGuestName(),
              booking.getMobile(),
              booking.getProperty().getNoOfGuest(),
              booking.getProperty().getName(),
              booking.getProperty().getCountry().getName(),
              booking.getProperty().getCity().getName(),
              booking.getProperty().getName() // Assuming the place is the hotel name; you can modify this as per your requirement
      );
   }
}
