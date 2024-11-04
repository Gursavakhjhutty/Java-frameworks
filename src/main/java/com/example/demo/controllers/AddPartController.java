package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Controller
public class AddPartController {
    @Autowired
    private PartService partService;
    @Autowired
    private OutsourcedPartService outsourcedPartService;
    @Autowired
    private InhousePartService inhousePartService;

    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partID") int theId,Model theModel){

        boolean inhouse=true;
        List<OutsourcedPart> outsourcedParts=outsourcedPartService.findAll();
        for(OutsourcedPart outsourcedPart:outsourcedParts) {
            if(outsourcedPart.getId()==theId)inhouse=false;
        }
        String formtype;
        if(inhouse){
            InhousePart inhousePart=inhousePartService.findById(theId);
            theModel.addAttribute("inhousepart",inhousePart);
            formtype="InhousePartForm";
        }
        else{
            OutsourcedPart outsourcedPart=outsourcedPartService.findById(theId);
            theModel.addAttribute("outsourcedpart",outsourcedPart);
            formtype="OutsourcedPartForm";
        }
        return formtype;
    }

    @GetMapping("/deletepart")
    public String deletePart(@Valid @RequestParam("partID") int theId,  Model theModel){
        Part part= partService.findById(theId);
        if(part.getProducts().isEmpty()){
            partService.deleteById(theId);
            return "confirmationdeletepart";
        }
        else{
            return "negativeerror";
        }
    }

    @PostMapping("/savePart")
    public String savePart(@Valid @ModelAttribute("part") Part part, BindingResult bindingResult, Model model) {
        // Validate inventory against min and max values
        if (part.getInv() < part.getMin() || part.getInv() > part.getMax()) {
            model.addAttribute("message", "Inventory must be between min (" + part.getMin() + ") and max (" + part.getMax() + ") values.");
            return part instanceof InhousePart ? "InhousePartForm" : "OutsourcedPartForm"; // Return to the correct form
        }

        // Save the part if validation passes
        partService.save(part);
        return "redirect:/mainscreen"; // Redirect after saving
    }
    @PostMapping("/addPart")
    public String addPart(@ModelAttribute Part part, Model model) {
        try {
            partService.save(part);
            return "redirect:/parts";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errormessage", e.getMessage());
            return "addPart";
        }
    }
}
