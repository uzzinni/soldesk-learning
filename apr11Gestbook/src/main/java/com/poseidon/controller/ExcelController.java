package com.poseidon.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		//서비스로 이동해주세요.
		List<Map<String, Object>> data = excelService.readExcel(upFile); // upfile을 열어서 리스트로
		
		//System.out.println(data); //최종 출력
		excelService.readExcel(data); //서비스 통해서 JPA로 데이터베이스에 저장하기
		model.addAttribute("data", data); // 화면에 출력
		return "admin/upfile";
	}
	
}
