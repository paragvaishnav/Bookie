package com.bookie.rest.api.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileStroageService {
	
	 public String storeFile(MultipartFile file);
	 public Resource loadFileAsResource(String fileName);
}
