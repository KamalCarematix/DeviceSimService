package com.rpm.rest.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rpm.rest.dao.DaoRepository;
import com.rpm.rest.helper.Util;
import com.rpm.rest.model.DeviceEntity;

@RestController
public class DeviceSimController {
	private static final Logger LOGGER = LogManager.getLogger(com.rpm.rest.controller.DeviceSimController.class);

	@Autowired
	DaoRepository repository;

	@Value("${sim.api.service.url}")
	private String SIM_API_URL;

	@GetMapping("/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

	@PostMapping(value = { "/ddss/devices" }, produces = { "application/json" })
	public ResponseEntity<Object> deviceActiveDeactive(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DeviceEntity entity) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"command\" : ");

		System.out.println(entity.getSerailNumbers().toString());
		for (String serialNo : entity.getSerailNumbers()) {
			Optional<Integer> id = this.repository.getAllSimOfDevice(serialNo);
			LOGGER.info("Device data sim Model " + id);
			// call for other microservice which running on port 18080

			String sim = removeLastChar(this.repository.getSimOfDevice(id.get()));
			String simAction = "";
			String ddssStatus = "";
			if (entity.getAction().contains("Deactivate")) {
				sb.append("\"disable\"");
				sb.append("}");
				simAction = sb.toString();
				ddssStatus = "Y";
			} else {
				sb.append("\"enable\"");
				sb.append("}");
				simAction = sb.toString();
				ddssStatus = "N";
			}
			this.repository.updateDeviceStatus(id.get(), ddssStatus);
			System.out.println("update");
			System.out.println(simAction);
			String simResponse = Util.postAndGetJSON(SIM_API_URL + sim, simAction);
			System.out.println(simResponse);
			this.repository.updateDeviceSimStatus(id.get(), ddssStatus);
		}
		// call API which deactivet/active sim by passing listof sim
		// update table DVC_DEVICE_SIM_DETAILS in IS_SIM_SUSPENDED Y/N and
		// IS_DEVICE_ACTIVE Y/N and SIM_UPDATE_DATE with sysdate and
		// DEVICE_UPDATE_DATE with sysdate

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Response of sim API");
		map.put("status", HttpStatus.OK);

		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

	private String removeLastChar(String s) {
		// returns the string after removing the last character
		return s.substring(0, s.length() - 1);
	}
}
