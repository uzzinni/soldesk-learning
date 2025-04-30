package com.poseidon.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poseidon.entitiy.Excel;
import com.poseidon.entitiy.Iplog;
import com.poseidon.repository.ExcelRepository;
import com.poseidon.repository.IpTableReoisitory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExcelService {

	private final ExcelRepository excelRepository; // iplog 다룰 용도입니다. :: Iplog Entity
	private final IpTableReoisitory ipTableRepository; // excel로 입력하는 용도 :: Excel Entity

	// 국가 시작주소 끝주소 프리픽스(/24) 할당일자 :: 지금은 한글로 되어있습니다. -> 나중에 영어로 변경
	// 날짜는 2010.09.15로 구성되어 있습니다.
	public static Excel mapToEntity(Map<String, Object> map) {
		// 날짜는 아래처럼 고치겠습니다.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");

		Excel excel = Excel.builder().nation((String) map.get("국가")).startIP((String) map.get("시작주소"))
				.endIP((String) map.get("끝주소")).prefix((String) map.get("프리픽스(/24)"))
				.date(LocalDate.parse((String) map.get("할당일자"), dtf)).build();
		return excel;
	}

	public List<Iplog> findAllByOrderByInoDesc() {
		return excelRepository.findAllByOrderByInoDesc();
	}

	public byte[] createLogExcel() throws IOException {
		// 데이터 가져오기
		List<Iplog> list = excelRepository.findAllByOrderByInoDesc();

		// 날짜 형식 지정
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// 엑셀은 워크북 > 시트 > Row > Cell
		// 워크북 만들기
		Workbook workbook = new XSSFWorkbook();
		// sheet 만들기
		Sheet sheet = workbook.createSheet();
		// 너비지정
		sheet.setColumnWidth(1, 20 * 250);
		sheet.setColumnWidth(2, 20 * 250);
		sheet.setColumnWidth(3, 30 * 250);
		sheet.setColumnWidth(4, 20 * 250);
		// Row 만들기
		Row headerRow = sheet.createRow(0);
		// Cell 만들기
		Cell inoCell = headerRow.createCell(0); // A1 셀 = 번호 들어갈 셀
		inoCell.setCellValue("번호");

		Cell idataCell = headerRow.createCell(1); // B1 셀 = 내역이 들어갈 셀
		idataCell.setCellValue("data");

		Cell idateCell = headerRow.createCell(2); // C1 셀 = 날짜 들어갈 셀
		idateCell.setCellValue("날짜");

		Cell iipCell = headerRow.createCell(3); // D1 = 아이피 들어갈 셀
		iipCell.setCellValue("아이피");

		Cell iurlCell = headerRow.createCell(4); // E1 cell = URL 들어갈 셀
		iurlCell.setCellValue("URL");

		// 데이터 추가
		for (int i = 1; i < list.size(); i++) {
			System.out.println(i);
			Row row = sheet.createRow(i); // 실제 데이터가 저장될 Row
			Cell ino = row.createCell(0);
			ino.setCellValue(list.get(i).getIno());

			Cell data = row.createCell(1);
			data.setCellValue(list.get(i).getIdata());

			Cell idate = row.createCell(2); // 위에서 date라는 변수명을 사용해서요. ㅠㅠ
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

	// 업로드 파일을 List<Map<>>으로 변환하는 메소드
	public List<Map<String, Object>> readExcel(MultipartFile upFile) throws IOException {
		List<Map<String, Object>> data = new ArrayList<>();

		Workbook workbook = new XSSFWorkbook(upFile.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Row titleRow = sheet.getRow(0);

		List<String> columnTitles = new ArrayList<String>(); // 타이틀만 따로 뽑아서 저장하기
		for (Cell cell : titleRow) {
			columnTitles.add(cell.getStringCellValue());
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) { // sheet.getLastRowNum()
			Row row = sheet.getRow(i);
			Map<String, Object> dataMap = new HashMap<String, Object>();

			for (int j = 0; j < columnTitles.size(); j++) {
				Cell cell = row.getCell(j);
				Object value = null;

				if (cell != null) {
					// System.out.println(cell.getCellType());
					switch (cell.getCellType()) {
					case STRING:
						value = cell.getStringCellValue();
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) { // 날짜일 경우
							value = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						} else {
							double numericValue = cell.getNumericCellValue(); // 123456478914324654
							if (numericValue > 0) {
								LocalDate date = LocalDate.of(1900, 1, 1).plusDays((long) numericValue - 2);
								value = date;
							} else {
								value = numericValue; // 이게 숫자 저장
							}
						}
						break;
					case BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					default:
						value = null;
					}
				} // end if
				dataMap.put(columnTitles.get(j), value);
			} // end for J
			data.add(dataMap);
		} // end for I
		return data;
	}

	// 읽은 엑셀 파일을 데이터베이스에 저장하기
	public void readExcel(List<Map<String, Object>> data) {
		// Map -> Entity로 변경해야 합니다.
		// Map<String, Object> ele = data.get(0);
		// Excel excel = mapToEntity(ele);
		// System.out.println("변환된 결과 : " + excel);

		// 이제 list를 변환하겠습니다.
		// 1. for문 사용해서 변경하기
		/*
		 * List<Excel> list = new ArrayList<Excel>(); for (Map<String, Object> map :
		 * data) { list.add(mapToEntity(map)); }
		 * 
		 * System.out.println("데이터 사이즈 " + list.size()); //2279
		 */

		// 2. stream()으로 처리하기
		List<Excel> list = data.stream().map(ExcelService::mapToEntity).collect(Collectors.toList());
		System.out.println("데이터 사이즈 " + list.size()); // 2279

		// excelRepository.saveAll(list); 사용불가
		ipTableRepository.saveAll(list);
	}

}
