package com.klef.dev.controller;

import com.klef.dev.model.Donor;
import com.klef.dev.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor")
@CrossOrigin
public class DonorController {
	
	@GetMapping("/")
	public String home() {
		return "Welcome to Project";
	}

    @Autowired
    private DonorService donorService;

    @PostMapping("/add")
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.addDonor(donor);
    }

    @PutMapping("/update")
    public Donor updateDonor(@RequestBody Donor donor) {
        return donorService.updateDonor(donor);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDonor(@PathVariable int id) {
        donorService.deleteDonor(id);
        return "Donor deleted successfully!";
    }

    @GetMapping("/{id}")
    public Donor getDonor(@PathVariable int id) {
        return donorService.getDonorById(id);
    }

    @GetMapping("/all")
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/search")
    public List<Donor> searchDonors(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false, name = "blood_group") String bloodGroup
    ) {
        List<Donor> allDonors = donorService.getAllDonors();
        return allDonors.stream()
                .filter(d -> (id == null || d.getId() == id) &&
                             (name == null || d.getName().equalsIgnoreCase(name)) &&
                             (age == null || d.getAge() == age) &&
                             (bloodGroup == null || d.getBloodGroup().equalsIgnoreCase(bloodGroup)))
                .toList();
    }
}
