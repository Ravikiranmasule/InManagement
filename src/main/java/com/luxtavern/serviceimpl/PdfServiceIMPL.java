package com.luxtavern.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.luxtavern.service.PdfService;

@Service
public class PdfServiceIMPL implements PdfService {
	private Logger logger=LoggerFactory.getLogger(PdfServiceIMPL.class);

	@Override
	public ByteArrayInputStream createPdf() {
	logger.info("in create pdf method of pdfimpl");
	String title="Welcome to Luxtavern";
	String content="We are the best hotel in india."
			+ " Visit us as soon as possible";
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		
		Document document=new Document();
		PdfWriter.getInstance(document, outStream);
	
		
		HeaderFooter footer=new HeaderFooter(true,new Phrase(" www.luxtavern.com"));
				footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorderWidthBottom(0);
		document.setFooter(footer);
		
		document.open();
		
		Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
		Paragraph titlePara=new Paragraph(title,titleFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlePara);
		
		
		Font paraFont=FontFactory.getFont(FontFactory.HELVETICA,15);
		Paragraph paragraph=new Paragraph(content,paraFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.add(new Chunk("this data is added between paragraph"));
		document.add(paragraph);
		
		document.close();
		return new ByteArrayInputStream(outStream.toByteArray());
	}

}
