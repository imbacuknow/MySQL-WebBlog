package com.cpe.mysql.webblog;

import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.entity.Tag;
import com.cpe.mysql.webblog.entity.User;
import com.cpe.mysql.webblog.repository.RoleRepository;
import com.cpe.mysql.webblog.repository.TagRepository;
import com.cpe.mysql.webblog.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class WebBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBlogApplication.class, args);
	}

//	@Bean
//	ApplicationRunner init(TagRepository tagRepository, UserRepository userRepository, RoleRepository roleRepository) {
//		return args -> {
//
//			//create role
//			Role role1 = new Role("Newbie");
//			Role role2 = new Role("Experienced");
//			Role role3 = new Role("Admin");
//			Stream.of(role1, role2, role3).forEach((role -> {
//				roleRepository.save(role);
//				System.out.println(role);
//			}));
//
//			//create tag
//			Tag tag1 = new Tag("Stock", "orange");
//			Tag tag2 = new Tag("News", "indigo");
//			Tag tag3 = new Tag("Mutual Funds", "teal");
//			Stream.of(tag1, tag2, tag3).forEach((tag -> {
//				tagRepository.save(tag);
//				System.out.println(tag);
//			}));
//
//			// set date time
//			Timestamp time1 = new Timestamp(System.currentTimeMillis());
//			Date date1 = new Date(time1.getTime());
//			//create user
//			User usr1 = new User("admin1", "admin1", "IAM ADMIN1", "admin1@gmail.com", date1, role3, role3.getRoleOfUser());
//
//			// set date time
//			Timestamp time3 = new Timestamp(System.currentTimeMillis());
//			Date date3 = new Date(time3.getTime());
//			//create user
//			User usr3 = new User("admin2", "admin2", "IAM ADMIN2", "admin2@gmail.com", date3, role3, role3.getRoleOfUser());
//
//			// set date time
//			Timestamp time2 = new Timestamp(System.currentTimeMillis());
//			Date date2 = new Date(time2.getTime());
//			//create user
//			User usr2 = new User("imbac", "imbac", "Bac Natthawut", "imbac@gmail.com", date2, role2, role2.getRoleOfUser());
//
//			// set date time
//			Timestamp time4 = new Timestamp(System.currentTimeMillis());
//			Date date4 = new Date(time4.getTime());
//			//create user
//			User usr4 = new User("newbac", "newbac", "newBac Natthawut", "newbac@gmail.com", date4, role1, role1.getRoleOfUser());
//
//			Stream.of(usr1, usr2, usr3, usr4).forEach((usr -> {
//				userRepository.save(usr);
//				System.out.println(usr);
//			}));
//		};
//	}
}
