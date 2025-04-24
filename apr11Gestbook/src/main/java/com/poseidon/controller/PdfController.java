package com.poseidon.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Controller
public class PdfController {

	//http://localhost/pdf를 실행하면 바로 화면에 출력하겠습니다.
	@ResponseBody
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> pdf() throws DocumentException, IOException{
		BaseFont baseFont = BaseFont.createFont("src/main/resources/static/font/NanumGothic.ttf", 
				BaseFont.IDENTITY_H, 
				BaseFont.EMBEDDED
				);
		Font font = new Font(baseFont);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.pdf\"");
		//여기 한글 문제가 발생합니다. 영어로 바꿔주세요 
		
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();
		
		Paragraph title = new Paragraph("회원 목록", new Font(baseFont, 20, Font.BOLD));
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title); //문서에 붙이기
		
		Paragraph solidLine = new Paragraph();
		solidLine.add(new LineSeparator());
		document.add(solidLine); // 문서에 붙이기
		
		// 여기에 본문 내용
		Paragraph bonmun = new Paragraph("\n\n본문 내용입니다.\n회원 목록은 아래와 같습니다.\n\n", font);
		document.add(bonmun);
		
		//table
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		int[] width = new int[]{1, 2, 2, 4};
		table.setWidths(width); //각각 너비 = 10 ?
		
		PdfPCell hcell;
		Font tableHeaderFontBold = new Font(baseFont, 12, Font.BOLD);
		hcell = new PdfPCell(    new Paragraph("번호", tableHeaderFontBold)   );
		hcell.setBackgroundColor(new BaseColor(255, 223, 186));
		hcell.setFixedHeight(20);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("이름", tableHeaderFontBold));
		hcell.setBackgroundColor(new BaseColor(255, 223, 186));
		hcell.setFixedHeight(20);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("전화번호", tableHeaderFontBold));
		hcell.setBackgroundColor(new BaseColor(255, 223, 186));
		hcell.setFixedHeight(20);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("주소", tableHeaderFontBold));
		hcell.setBackgroundColor(new BaseColor(255, 223, 186));
		hcell.setFixedHeight(20);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
		for (int i = 1; i < 11; i++) {
			PdfPCell cell;
			cell = new PdfPCell(new Phrase(i + "", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(20);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("홍길동" + i, font));
			cell.setPaddingLeft(5);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("010-5626-256" + i, font));
			cell.setPaddingRight(5);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("서울시 영등포구 여의도동 19길 " + i, font));
			cell.setPaddingLeft(5);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
		}
				
		//document에 붙이기
		document.add(table);
		
		//이미지
		Image image = Image.getInstance("src/main/resources/static/img/printer.png");
		//image.scaleToFit(50,80);
		image.scaleAbsolute(50, 80);
		//image.scalePercent(0, 0);
		document.add(image);

		//인터넷 경로에서 이미지 가져오기
		Image image2 = Image.getInstance("http://localhost/img/printer.png"); 
		document.add(image2);
		
		
		// 출력하기
		document.close();
		
		byte[] pdfData = out.toByteArray();
		
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(pdfData);
	}
	
}
