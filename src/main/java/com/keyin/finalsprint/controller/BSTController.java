package com.keyin.finalsprint.controller;


import com.keyin.finalsprint.model.BSTRecord;
import com.keyin.finalsprint.model.BinarySearchTree;
import com.keyin.finalsprint.repository.BSTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BSTController {

    @Autowired
    private BSTRepository repository;

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        BinarySearchTree bst = new BinarySearchTree();
        for (int number : numberList) {
            bst.insert(number);
        }
        String jsonTreeStructure = bst.toJson();

        BSTRecord record = new BSTRecord();
        record.setInputNumbers(numbers);
        record.setTreeStructure(jsonTreeStructure);
        repository.save(record);

        model.addAttribute("treeStructure", jsonTreeStructure);
        return "process-numbers";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<BSTRecord> records = repository.findAll();
        model.addAttribute("records", records);
        return "previous-trees";
    }
}
