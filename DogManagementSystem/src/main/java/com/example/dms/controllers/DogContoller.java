package com.example.dms.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dms.models.Dog;
import com.example.dms.models.Trainer;
import com.example.dms.repositories.DogRepository;
import com.example.dms.repositories.TrainerRepository;

@Controller
public class DogContoller {
	
//	@RequestMapping("dogHome")
//	public String home() {
//		return "home";
//	}
	
	ModelAndView mav = new ModelAndView();
	
	@Autowired
	DogRepository dogRepo;
	
	@Autowired
	TrainerRepository trainerRepo;
	
	@RequestMapping("dogHome")
	public ModelAndView home() {
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("add")
	public ModelAndView add() {
		mav.setViewName("addNewDog");
		Iterable<Trainer>trainerList = trainerRepo.findAll();
		mav.addObject("trainers", trainerList);
		return mav;
	}
	
	
	@RequestMapping("addTrainer")
    public ModelAndView addTrainer() {
            mav.setViewName("addNewTrainer");
            return mav;
    }
	
	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog, @RequestParam("trainerId") int id) {
        Trainer trainer = trainerRepo.findById(id).orElse(new Trainer());
        dog.setTrainer(trainer);
        dogRepo.save(dog);
        mav.setViewName("home");
        return mav;
}
    
    @RequestMapping("trainerAdded")
    public ModelAndView addNewTrainer(Trainer trainer) {
            trainerRepo.save(trainer);
            mav.setViewName("home");
            return mav;
    }
	
	
	@RequestMapping("viewModifyDelete")
	public ModelAndView viewModifyEdit(){
		mav.setViewName("viewDogs");
		Iterable<Dog>dogList =  dogRepo.findAll();
		mav.addObject("dogs", dogList);
		return mav;
		
	}
	
	@RequestMapping("editDog")
	public ModelAndView viewModifyEdit(Dog dog){
		mav.setViewName("editDog");
		dogRepo.save(dog);	
		return mav;
		
	}
	
	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog){
//		Optional<Dog> dogFound = dogRepo.findById(dog.getId());	
//		if(dogFound.isPresent()) {
//			dogRepo.delete(dog);
//		}
//		return home();
		
//		List<Dog>dogs = dogRepo.findByName(dog.getName());
//		
//		for(Dog dog1:dogs) {
//			dogRepo.delete(dog1);
//		}
//		
//		return home();
		Dog d = dogRepo.findById(dog.getId()).orElse(new Dog());
		dogRepo.delete(d);
		return home();
	}
	
	
	@RequestMapping("search")
	public ModelAndView searchById(int id) {
		Dog d = dogRepo.findById(id).orElse(new Dog());
		mav.addObject(d);
		mav.setViewName("searchResults");
		return mav;
	}
}
