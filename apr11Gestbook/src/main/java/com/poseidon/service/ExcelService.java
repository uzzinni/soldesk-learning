package com.poseidon.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.poseidon.entitiy.Iplog;
import com.poseidon.repository.ExcelRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExcelService {

	private final ExcelRepository excelRepository;
	
	public List<Iplog> findAllByOrderByInoDesc(){
		return excelRepository.findAllByOrderByInoDesc();
	}
	
	public byte[] createLogExcel() throws IOException {
		//데이터 가져오기
		List<Iplog> list = excelRepository.findAllByOrderByInoDesc();
		
		//날짜 형식 지정
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//엑셀은 워크북 > 시트 > Row > Cell
		//워크북 만들기
		Workbook workbook = new XSSFWorkbook();
		//sheet 만들기
		Sheet sheet = workbook.createSheet();
		//너비지정
		sheet.setColumnWidth(1, 20*250);
		sheet.setColumnWidth(2, 20*250);
		sheet.setColumnWidth(3, 30*250);
		sheet.setColumnWidth(4, 20*250);
		//Row 만들기
		Row headerRow = sheet.createRow(0);
		// Cell 만들기
		Cell inoCell = headerRow.createCell(0); // A1 셀 = 번호 들어갈 셀
		inoCell.setCellValue("번호");
		
		Cell idataCell = headerRow.createCell(1); //B1 셀 = 내역이 들어갈 셀
		idataCell.setCellValue("data");
		
		Cell idateCell = headerRow.createCell(2); //C1 셀 = 날짜 들어갈 셀
		idateCell.setCellValue("날짜");
		
		Cell iipCell = headerRow.createCell(3); //D1 = 아이피 들어갈 셀
		iipCell.setCellValue("아이피");
		
		Cell iurlCell = headerRow.createCell(4); //E1 cell =  URL 들어갈 셀
		iurlCell.setCellValue("URL");
		
		//데이터 추가
		for(int i = 1; i < list.size(); i++) {
			System.out.println(i);
			Row row = sheet.createRow(i); // 실제 데이터가 저장될 Row
			Cell ino = row.createCell(0);
			ino.setCellValue(list.get(i).getIno());
			
			Cell data = row.createCell(1);
			data.setCellValue(list.get(i).getIdata());
			
			Cell idate = row.createCell(2); //위에서 date라는 변수명을 사용해서요. ㅠㅠ
			idate.setCellValue(list.get(i).getIdate().format(date));
			
			Cell ip = row.createCell(3);
			ip.setCellValue(list.get(i).getIip());
			
			Cell url = row.createCell(4);
			url.setCellValue(list.get(i).getIurl());
		}
		
		// 데이터를 바이트 배열로 변환
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
			System.out.println("성공");
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
		return null;
				
	}
	
}
