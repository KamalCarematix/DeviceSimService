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
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rpm.rest.dao.DaoRepository;
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

	@GetMapping(value = { "/ddss/sim" }, produces = { "application/json" })
	public String getStatus(HttpServletRequest request, HttpServletResponse response, @RequestParam String serialNumber)
			throws Exception {
		System.out.println("in thats get call /ddss/sim");
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		Optional<Integer> id = this.repository.getAllSimOfDevice(serialNumber);
		LOGGER.info("Device data sim Model " + id);
		// call for other microservice which running on port 18080
		System.out.println(serialNumber);
		//restTemplate.getForObject("http://google.com", String.class);
		String sim = removeLastChar(this.repository.getSimOfDevice(id.get()));
		String simResponse = restTemplate.getForObject("http://127.0.0.1:18080/api/v0.1/sim/" + sim, String.class);
		return simResponse;

	}
	
	

	//Override timeouts in request factory
	private static SimpleClientHttpRequestFactory getClientHttpRequestFactory()
	{
	    SimpleClientHttpRequestFactory clientHttpRequestFactory
	                      = new SimpleClientHttpRequestFactory();
	    //Connect timeout
	    clientHttpRequestFactory.setConnectTimeout(300000);
	     
	    //Read timeout
	    clientHttpRequestFactory.setReadTimeout(300000);
	    return clientHttpRequestFactory;
	}

	@PostMapping(value = { "/ddss/sim" }, produces = { "application/json" })
	public ResponseEntity<Object> deviceActiveDeactive(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DeviceEntity entity) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"command\" : ");
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

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
			System.out.println("http://10.1.0.6:18080/api/v0.1/sim/" + sim);
			System.out.println(simAction);
			// String simResponse = Util.postAndGetJSON(, simAction);
			String simResponse = restTemplate.postForObject("http://10.1.0.6:18080/api/v0.1/sim/" + sim, simAction,
					String.class);
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
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		restTemplate.getForObject("http://google.com", String.class);

		
	}

	private String removeLastChar(String s) {
		// returns the string after removing the last character
		return s.substring(0, s.length() - 1);
	}
}
