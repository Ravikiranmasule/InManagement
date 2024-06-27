package com.luxtavern.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.luxtavern.dao.CategoryRepository;
import com.luxtavern.entity.Category;
import com.luxtavern.service.PdfService;

@Service
public class PdfServiceIMPL implements PdfService {
    private Logger logger = LoggerFactory.getLogger(PdfServiceIMPL.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ByteArrayInputStream createPdf() {
        logger.info("in create pdf method of PdfServiceIMPL");
        
        String uploadDirectory=System.getProperty("user.dir")+"/src/main/webapp/images";
        
        List<Category> allCategories = categoryRepository.findAll();

        String title = "Welcome to Luxtavern";
        String content = "We are the best hotel in India. Visit us as soon as possible.";

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4, 20, 20, 30, 30);
        PdfWriter.getInstance(document, outStream);

        HeaderFooter footer = new HeaderFooter(new Phrase("www.luxtavern.com"), false);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorder(Rectangle.NO_BORDER);
        document.setFooter(footer);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        titlePara.setSpacingAfter(20);
        document.add(titlePara);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        Stream.of("Id", "Title", "Image", "Description").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(headerTitle, headFont));
            table.addCell(header);
        });

        for (Category category : allCategories) {
            PdfPCell idCell = new PdfPCell(new Phrase(category.getCategoryId().toString()));
            idCell.setPadding(5);
            idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(idCell);

            PdfPCell titleCell = new PdfPCell(new Phrase(category.getCategoryTitle()));
            titleCell.setPadding(5);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(titleCell);

            try {
                Path imagePath = Paths.get(uploadDirectory, category.getCategoryImage());
                if (Files.exists(imagePath)) {
                    Image image = Image.getInstance(imagePath.toAbsolutePath().toString());
                    image.scaleToFit(50, 50); // Adjust size as needed
                    PdfPCell imageCell = new PdfPCell(image);
                    imageCell.setPadding(5);
                    imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(imageCell);
                } else {
                    PdfPCell noImageCell = new PdfPCell(new Phrase("No Image"));
                    noImageCell.setPadding(5);
                    noImageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    noImageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(noImageCell);
                }
            } catch (Exception e) {
                PdfPCell errorCell = new PdfPCell(new Phrase("Image not available"));
                errorCell.setPadding(5);
                errorCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                errorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(errorCell);
            }

            PdfPCell descriptionCell = new PdfPCell(new Phrase(category.getCategoryDescription()));
            descriptionCell.setPadding(5);
            descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(descriptionCell);
        }
        document.add(table);

        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA, 15);
        Paragraph paragraph = new Paragraph(content, paraFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(20);
        document.add(paragraph);

        document.close();
        return new ByteArrayInputStream(outStream.toByteArray());
    }
}