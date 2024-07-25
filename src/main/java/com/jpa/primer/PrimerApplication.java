package com.jpa.primer;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.primer.entities.Person;
import com.jpa.primer.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class PrimerApplication  implements CommandLineRunner{
	@Autowired
	private PersonRepository pr;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(PrimerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		

		Optional<Person> name = (Optional<Person>)pr.findByNameContaining("Sara");
		System.out.println(name);

		Optional<Person> nombre = pr.EncuentraNombre("Maritza");
		System.out.println(nombre);

		List<Person> last = (List<Person>)pr.findByLastnameStartingWith("Garcia");
		System.out.println(last);

		List<Person> apellido = pr.encuentraApellido("Velazco");
		System.out.println(apellido);

	}

	@Transactional
	public void create(){

		System.out.println("Ingrese el nombre: ");
		String name = sc.next();

		System.out.println("Ingrese el apellido: ");
		String apellido = sc.next();

		System.out.println("Ingrese el lenguaje de programacion: ");
		String lengua = sc.next();

		Person person = new Person(null,name,apellido,lengua);
		Person personNew = pr.save(person);
		System.out.println(personNew);

		pr.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update(){
		System.out.println("Ingrese el id de la persona: ");
		Long id = sc.nextLong();

		Optional<Person> optionalPerson = pr.findById(id);
		if(optionalPerson.isPresent()){
			Person personDB = optionalPerson.orElseThrow();

			System.out.println(personDB);

			System.out.println("Ingrese el nombre: ");
			String nombre = sc.next();
			personDB.setName(nombre);

			System.out.println("Ingrese el apellido: ");
			String apellido = sc.next();
			personDB.setLastname(apellido);

			System.out.println("Ingrese el lenguaje de programacion: ");
			String lenguaje = sc.next();
			personDB.setProgrammingLanguage(lenguaje);

			Person pU = pr.save(personDB);
			System.out.println(pU);
		}else{
			System.out.println("El usuario con id " + id + " no existe!!! ");
		}
	}

	@Transactional
	public void delete(){
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = sc.nextLong();

		pr.deleteById(id);
	}

	@Transactional
	public void list(){
		List<Person> persons = (List<Person>) pr.findAll();
		persons.stream().forEach(person -> System.out.println(person));
	}
}
