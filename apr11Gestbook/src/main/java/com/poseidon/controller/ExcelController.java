package com.poseidon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poseidon.entitiy.Iplog;
import com.poseidon.service.ExcelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ExcelController {

	private final ExcelService excelService;

	@GetMapping("/log")
	public String log(Model model) {
		List<Iplog> list = excelService.findAllByOrderByInoDesc();
		model.addAttribute("list", list);
		return "admin/log"; // http://localhost/log
	}

	@GetMapping("/excel")
	public ResponseEntity<byte[]> downloadExcel() {
		try {
			byte[] excelFile = excelService.createLogExcel();
			System.out.println(excelFile.toString());
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=log.xlsx");
			return new ResponseEntity<byte[]>(excelFile, headers, HttpStatus.OK);
		} catch (IOException e) {
			// e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/readExcel")
	public String upload() throws IOException {
		return "admin/upfile";
	}

	@PostMapping("/readExcel")
	public String read(@RequestParam("file") MultipartFile upFile, Model model) throws IOException {
		//엔티티 만들어주고 올게요. 나중에.
		List<Map<String, Object>> data = new ArrayList<>();
		
		Workbook workbook = new XSSFWorkbook(upFile.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Row titleRow = sheet.getRow(0);
		
		List<String> columnTitles = new ArrayList<String>(); //타이틀만 따로 뽑아서 저장하기
		for (Cell cell : titleRow) {
			columnTitles.add(cell.getStringCellValue());
		}		
		
		for (int i = 1; i < sheet.getLastRowNum() ; i++) {
			Row row = sheet.getRow(i);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			for (int j = 0; j < columnTitles.size(); j++) {
				Cell cell = row.getCell(j);
				Object value = null;
				
				if (cell != null) {
					//System.out.println(cell.getCellType());
					switch (cell.getCellType()) {
					case STRING:
						value = cell.getStringCellValue();
						break;
					case NUMERIC:
						break;
					case BOOLEAN:
						break;
					default:
						value = null;
					}
				} //end if
				dataMap.put(columnTitles.get(j), value);
			} // end for J
			data.add(dataMap);
		}// end for I
		System.out.println(data); //최종 출력
		model.addAttribute("data", data);
		return "admin/upfile";
	}
	
}
