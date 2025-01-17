package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */

@Service
public class PartServiceImpl implements PartService{
        private PartRepository partRepository;

        @Autowired

    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        return (List<Part>) partRepository.findAll();
    }
    public List<Part> listAll(String keyword){
        if(keyword !=null){
            return partRepository.search(keyword);
        }
        return (List<Part>) partRepository.findAll();
    }
    @Override
    public Part findById(int theId) {
        Long theIdl=(long)theId;
        Optional<Part> result = partRepository.findById(theIdl);

        Part thePart = null;

        if (result.isPresent()) {
            thePart = result.get();
        }
        else {
            // we didn't find the part id
            throw new RuntimeException("Did not find part id - " + theId);
        }

        return thePart;
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl=(long)theId;
        partRepository.deleteById(theIdl);
    }

    @Override
    public void save(Part thePart) {
        try {
            validatePart(thePart);
            partRepository.save(thePart);
        } catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
        List<Part> existingParts = (List<Part>) partRepository.findAll();
        for (Part part : existingParts) {
            if (part.getName() != null && part.getName().equalsIgnoreCase(thePart.getName())) {
                thePart.setName(thePart.getName() + " (Multi-pack)");
                break;
            }
        }
        partRepository.save(thePart);
    }

    public void validatePart(Part part) throws IllegalArgumentException {
        if (part.getInv() < part.getMin()) {
            throw new IllegalArgumentException("Error: Inventory cannot be less than the minimum allowed.");
        }
        if (part.getInv() > part.getMax()) {
            throw new IllegalArgumentException("Error: Inventory cannot be greater than the maximum allowed.");
        }
        if (part.getMin() > part.getMax()) {
            throw new IllegalArgumentException("Error: Minimum cannot be greater than maximum.");
        }
    }


}
