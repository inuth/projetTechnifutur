package be.spring.bootProject.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.spring.bootProject.entities.Product;
import be.spring.bootProject.repositories.ProductRepository;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	private ProductRepository productRepo;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("products", productRepo.findAll());
		
		List<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
		model.addAttribute("roles", roles);
		return "product/products";
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Integer productId, Model model) {
		Product product = productRepo.findById(productId).orElse(null);
		model.addAttribute("product", product);
		return "/product/details";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer productId, Model model) {
		Product product = productRepo.findById(productId).orElse(null);
		model.addAttribute("product", product);
		return "/product/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer productId, @Valid @ModelAttribute("product") Product product, BindingResult br)
	{
		if (br.hasErrors()) {
			return "/product/edit";
		}
		Product prod = productRepo.findById(product.getId()).orElse(null);
		System.out.println(prod == null);
		prod.setDescription(product.getDescription());
		// Enlevé pour l'exercice selenium
		//prod.setName(product.getName());
		prod.setValue(product.getValue());
		productRepo.save(prod);
		return "redirect:/product";
	}
	
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("product", new Product());
		return "/product/create";
	}
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("product") Product product, BindingResult br)
	{
		if (br.hasErrors()) {
			return "/product/create";
		}
		productRepo.save(product);
		return "redirect:/product";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id)
	{
		// retirer l'Ã©lÃ©ment de la liste
		productRepo.deleteById(id);
		return "redirect:/product";
	}
}








