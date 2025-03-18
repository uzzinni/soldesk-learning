package com.poseidon.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileController {
	
	@Autowired
	private ResourceLoader resourceLoader; // 경로 얻어오기 위해서
	
	@GetMapping("/file")
	public String file() {
		return "file";
	}

	@PostMapping("/file")
	public String file(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("file name : " + file.getOriginalFilename());
		System.out.println("file size : " + file.getSize());
		System.out.println("디렉토리 확인 : " + System.getProperty("user.dir"));
		
		//저장할 디렉토리
		String classpath = resourceLoader.getResource("classpath:static/upload/").getFile().getAbsolutePath();
		System.out.println(classpath);
		File uploadDir = new File(classpath);
		// 만약에 저 경로가 없다면?
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		//저장할 파일명 -> UUID뽑아서 연결하기
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString()); // 5ca88c0b-249f-4e53-b7e5-03b28392ae51
		
		//년월일시분초 뽑아보기
		LocalDateTime ldt = LocalDateTime.now(); // 2025-03-14T20:00:38.580812400
		System.out.println(ldt.toString());
		String format = ldt.format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss"));
		System.out.println(format); // 20250314200242
		
		//저장 경로 : 디렉토리 + 파일명
		// C:\workspace-sts4\mar11\bin\main\static\ upload\파일명
		File saveFile = new File(uploadDir, format + uuid.toString() + file.getOriginalFilename());

		//전송
		file.transferTo(saveFile);
		
		return "file";
	}
	
	
	@ResponseBody
	@GetMapping("/download@{file}")
	public void down(@PathVariable("file") String file, HttpServletResponse response) throws IOException {
		System.out.println("들어온 파일명 : " + file);
		//경로 뽑아오기
		String classpath = resourceLoader.getResource("classpath:static/upload/").getFile().getAbsolutePath();
		File uploadDir = new File(classpath);
		
		File serverFile = new File(uploadDir, file);
		
		byte[] fileByte = FileCopyUtils.copyToByteArray(serverFile);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition","attachment; fileName=\"" + URLEncoder.encode(classpath + "/" + file, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
}















/*
 * 
 * 가나다.png
 * 
 */ 
