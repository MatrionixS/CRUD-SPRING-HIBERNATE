package ua.rusyn.hibernate_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.rusyn.hibernate_mvc.dao.PersonDAO;
import ua.rusyn.hibernate_mvc.model.Person;

@Controller
@RequestMapping("/")
public class PersonController {

    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping
    public String homePage(Model model){
        model.addAttribute("people",personDAO.findAll());
        return "home";
    }
    @GetMapping("/{id}")
    public String personPage(@PathVariable("id")int id,Model model){
        model.addAttribute("person",personDAO.find(id));
        return "person";
    }
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id")int id,Model model){
        model.addAttribute("person",personDAO.find(id));
        return "edit";
    }
    @PatchMapping ("/{id}/edit")
    public String edit(@PathVariable("id")int id, @ModelAttribute("person") Person person){
        personDAO.update(id,person);
        return "redirect:/";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/";
    }
    @GetMapping("/create")
    public String createForm(@ModelAttribute("person") Person person){
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/";
    }
}
