package be.spring.bootProject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import be.spring.bootProject.entities.Product;
import be.spring.bootProject.entities.Role;
import be.spring.bootProject.entities.User;
import be.spring.bootProject.repositories.ProductRepository;
import be.spring.bootProject.repositories.RoleRepository;
import be.spring.bootProject.repositories.UserRepository;

@SpringBootApplication
public class ProjectWithSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectWithSecurityApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private ProductRepository productRepo;
	
	@EventListener(ApplicationReadyEvent.class)
	public void initData() throws IOException, NoSuchAlgorithmException {
		initRole();
		initUser();		
		initProduct();
	}
	
	private void initRole() {
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		roleRepo.save(admin);
		Role member = new Role();
		member.setName("ROLE_MEMBER");
		roleRepo.save(member);
	}
	
	private void initUser() throws NoSuchAlgorithmException {
		User admin = new User();
		admin.setLogin("admin123");
		admin.setPassword(passwordEncoder.encode("admin123"));
		admin.getRoles().add(roleRepo.findById(1).orElse(null));
		admin.getRoles().add(roleRepo.findById(2).orElse(null));
		userRepo.save(admin);
		
		User member = new User();
		member.setLogin("member123");
		member.setPassword(passwordEncoder.encode("member123"));
		member.getRoles().add(roleRepo.findById(2).orElse(null));
		userRepo.save(member);
	}
	
	private void initProduct() {
		productRepo.save(new Product("sushi saumon", 2.2, "délicieux sushi au saumon"));
		productRepo.save(new Product("sushi thon", 2.4, "délicieux sushi au thon"));
		productRepo.save(new Product("chirashi mixte", 14.7, "plat MEGA BON"));
		productRepo.save(new Product("maki concombre (6)", 3.5, "petit mais bon et croustillant !"));
		productRepo.save(new Product("temaki saumon", 5, "plat en forme de cornet"));
		productRepo.save(new Product("sashimi saumon (15)", 15.2, "tranches de saumon parfaitement découpées"));		
	}
	
}
