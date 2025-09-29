package com.example.wordy.controller.Model;


import com.example.wordy.model.ModelModel;
import com.example.wordy.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/models")
    public String getAllModels(Model model) {
        Iterable<ModelModel> modelModels = modelService.findAll();
        model.addAttribute("models", modelModels);
        model.addAttribute("model", new ModelModel());

        return "Model/models";
    }

    @PostMapping("/models/add")
    public String addModel(@ModelAttribute("model")
                           @Valid ModelModel modelModel,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Model/models";
        }

        modelService.save(modelModel);
        return "redirect:/models";
    }

    @PostMapping("/models/update")
    public String updateModel(@RequestParam int id,
                              @ModelAttribute("model")
                              @Valid ModelModel modelModel,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Model/models";
        }
        ModelModel existingModel = modelService.findById(id).orElseThrow();
        existingModel.setTitle(modelModel.getTitle());
        modelService.save(existingModel);
        return "redirect:/models";
    }

    @PostMapping("/models/delete")
    public String deleteModel(@RequestParam int id) {
        ModelModel modelModel = modelService.findById(id).orElseThrow();
        modelService.delete(modelModel);
        return "redirect:/models";
    }

    @PostMapping("/models/findById")
    public String findModelById(@RequestParam int id, Model model) {
        try {
            var modelData = modelService.findById(id).orElseThrow();
            model.addAttribute("model", modelData);
            return "Model/modelDetails";
        } catch (Exception e) {
            return "redirect:/models";
        }
    }

    @PostMapping("/models/findByTitle")
    public String findModelByTitle(@RequestParam String title, Model model) {
        Optional<ModelModel> modelModel = modelService.findByTitle(title);
        if (modelModel.isPresent()) {
            model.addAttribute("model", modelModel.get());
            return "Model/modelDetails";
        } else {
            return "redirect:/models";
        }
    }
}

