package com.example.bootJpa.controller;

import com.example.bootJpa.dao.AlienRepo;
import com.example.bootJpa.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class AlienController {

    @Autowired
    AlienRepo repo;

    @RequestMapping("/")
    public String home(){
        return "home.jsp";
    }

    @RequestMapping("/addAlien")
    public String addAlien(Alien alien){

        repo.save(alien);
        return "home.jsp";
    }

    @RequestMapping("/updateAlien")
    public String updateAlien(Alien alien){

        repo.save(alien);
        return "home.jsp";
    }

    @RequestMapping("/deleteAlien")
    public ModelAndView deleteAlien(@RequestParam int aid){

        ModelAndView mv = new ModelAndView("home.jsp");
        Alien alien = repo.findById(aid).orElse( new Alien());
        repo.delete(alien);
        return mv;
    }

    @RequestMapping("/getAlien")
    public ModelAndView addAlien(@RequestParam int aid){

        ModelAndView mv = new ModelAndView("show_details.jsp");
        Alien alien = repo.findById(aid).orElse( new Alien());
        mv.addObject(alien);
        return mv;

    }
//        System.out.println("java "+repo.findByTechSorted("java"));
//        System.out.println("GT "+repo.findByAidGreaterThan(aid));
//        System.out.println("getNameUsingId "+repo.findByAname("madhu"));

//        java [Alien{aid=104, aname='john4', tech='java'}, Alien{aid=106, aname='john6', tech='java'}, Alien{aid=101, aname='madhu1', tech='java'}]
//        GT [Alien{aid=102, aname='john2', tech='python'}, Alien{aid=103, aname='madhu3', tech='c'}, Alien{aid=104, aname='john4', tech='java'}, Alien{aid=105, aname='madhu5', tech='python'}, Alien{aid=106, aname='john6', tech='java'}]
//        getNameUsingId []
//        java [Alien{aid=104, aname='john4', tech='java'}, Alien{aid=106, aname='john6', tech='java'}, Alien{aid=101, aname='madhu', tech='java'}]
//        GT [Alien{aid=102, aname='john2', tech='python'}, Alien{aid=103, aname='madhu3', tech='c'}, Alien{aid=104, aname='john4', tech='java'}, Alien{aid=105, aname='madhu5', tech='python'}, Alien{aid=106, aname='john6', tech='java'}]
//        getNameUsingId [Alien{aid=101, aname='madhu', tech='java'}]

    //Rest Controller
    @GetMapping("/aliens")
    public List<Alien> getAliens(){
        return repo.findAll();
    }

//    http://localhost:8080/alien/101
//    @RequestMapping(path="/alien/{aid}", produces= {"application/xml"})
    @RequestMapping("/alien/{aid}")
//    @ResponseBody - instead of adding ResponseBody for all Rest methods, we can annotate with @RestController
    public Optional<Alien> getAlien(@PathVariable("aid") int aid){
        return repo.findById(aid);
    }


    //http://localhost:8080/alien  -- remember don't append slash while sending from client
    @PostMapping("/alien")
    public Alien add_Alien(@RequestBody Alien alien){
        repo.save(alien);
        return alien;
    }

    @PutMapping("/alien")
    public Alien saveorUpdateAlien(@RequestBody Alien alien){
        repo.save(alien);
        return alien;
    }

    @DeleteMapping("/alien/{aid}")
    public String delete_Alien(@PathVariable int aid){

        Alien a = repo.getOne(aid);
        repo.delete(a);
        return "deleted";
    }

}
