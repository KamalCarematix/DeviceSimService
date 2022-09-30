package com.rpm.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rpm.rest.dao.DaoRepository;
import com.rpm.rest.model.DeviceEntity;

@RestController
public class DeviceSimController {
	private static final Logger LOGGER = LogManager.getLogger(com.rpm.rest.controller.DeviceSimController.class);

	@Autowired
	DaoRepository repository;

	@GetMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
	
	@PostMapping(value = { "/ddss/devices" }, produces = { "application/json" })
	public ResponseEntity<Object> deviceActiveDeactive(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DeviceEntity entity) throws Exception {
		for (String serialNo : entity.getSerailNumbers()) {
			List<String> ddse = this.repository.getAllSimOfDevice(serialNo);
			LOGGER.info("Device data sim Model " + ddse.toString());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Response of sim API");
		map.put("status", HttpStatus.OK);

		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}
}
