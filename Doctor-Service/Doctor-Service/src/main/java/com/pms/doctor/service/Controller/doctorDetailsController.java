package com.pms.doctor.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.doctor.service.Impl.DoctorPersonalDetailsImpl;
import com.pms.doctor.service.Impl.doctorDetailsImpl;
import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Models.DoctorPersonalDetails;

@RestController
@RequestMapping("/registerDoctor")
public class doctorDetailsController {
	@Autowired
	private doctorDetailsImpl doctorService;
	
	@Autowired
	private DoctorPersonalDetailsImpl doctorPersonalDetailsImpl;
	
	@GetMapping("/getDetails/{doctorId}")
	public ResponseEntity<DoctorPersonalDetails> getDetails(@PathVariable String doctorId)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorPersonalDetailsImpl.getDetails(doctorId));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Doctor> addDetails(@RequestBody Doctor doctorobj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addDetails(doctorobj));
	}
	@PutMapping("/update")
	public ResponseEntity<Doctor> updateDetails(@RequestBody Doctor doctorobj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDetails(doctorobj));
	}
	

}
