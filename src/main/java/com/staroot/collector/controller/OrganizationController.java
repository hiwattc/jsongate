package com.staroot.collector.controller;

import com.staroot.collector.dao.ServerInfoRepository;
import com.staroot.collector.dao.WasInstanceRepository;
import com.staroot.collector.domain.WasInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/org")
public class OrganizationController {
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    TreeController treeController;

    @GetMapping("/tree")
    public String showTree(Model model) {
        log.info("tree called!");
        return "tree";
    }
    @GetMapping("/makeData")
    public String makeSampleData(Model model) {
        treeController.makeSampleTreeData();
        return "redirect:/org/tree";
    }

}
