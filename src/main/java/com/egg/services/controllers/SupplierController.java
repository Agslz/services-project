package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.SupplierService;

//IMPORTANT
//ALL OF THE ROUTES/FILENAMES CAN (AND POSSIBBLY) BE CHANGED

@Controller
@RequestMapping("/supplier")
public class SupplierController implements CrudController<Supplier>{

	@Autowired
	private SupplierService supplierService;

	@Override
	@GetMapping("/")
	// If the getAllMethod is modified to throw an exception, add the catch
	//Surrounding the first two lines
	public String getAll(ModelMap model) {
		List<Supplier> suppliers = supplierService.getAll();
		model.put("suppliers", suppliers);
		return "suppliers-view";
	}

	@Override
	@GetMapping("/save")
	public String getForm(ModelMap model) {
		return "supplier-form";
	}

	@Override
	@PostMapping("/save")
	public String create(@Valid Supplier entity, ModelMap model) {
		try {
			supplierService.create(entity);
			model.put("success", "supplier added successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("supplierId", entity.getId());
			// Go to another page(optional)
		}

		return "redirect:/supplier";
	}
	
	@Override
	@GetMapping("modify/{id}")
	public String modify(@PathVariable Integer Id, ModelMap model) {
		try{
			Supplier supplier = supplierService.getById(Id);
			model.put("supplier", supplier);
		}catch (ServicesException se) {
			model.put("error", se.getMessage());
			return "suppliers-view";
		}
		return "supplier-form";
	}

	
	@Override
	@PostMapping("/modify")
	public String modify(@Valid Supplier supplier, ModelMap model) {
		try {
			supplierService.update(supplier);
			model.put("success", "supplier modified successfully");
		}catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("supplier", supplier);
			return "supplier-form";
		}
		return "redirect:/supplier";
	}
	
	@Override
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer Id, ModelMap model){
		try {
			supplierService.delete(Id);
			model.put("success", "supplier dissmissed successfully");
		}catch(ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/supplier";
	}
}
