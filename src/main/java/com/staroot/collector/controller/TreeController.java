package com.staroot.collector.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.staroot.collector.dao.OrganizationRepository;
import com.staroot.collector.dao.ServerInfoRepository;
import com.staroot.collector.dao.WasInstanceRepository;
import com.staroot.collector.domain.Organization;
import com.staroot.collector.domain.WasInstance;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tree")
public class TreeController {
    private static final Logger log = LoggerFactory.getLogger(TreeController.class);

    @Autowired
    OrganizationRepository organizationRepository;
    @GetMapping("/getData")
    public List<Map<String, Object>> index(Model model) {
        //makeSampleTreeData();

        List<Organization> allOrganizations = organizationRepository.findAll();
        List<Organization> rootOrganizations = new ArrayList<>();
        Map<String, Organization> organizationMap = new HashMap<>();

        // 맵에 모든 조직을 넣음
        for (Organization organization : allOrganizations) {
            organizationMap.put(organization.getOrgCd(), organization);
        }

        // 상하관계를 반영하여 트리 구성
        for (Organization organization : allOrganizations) {
            log.info(organization.getOrgCd()+"/"+organization.getOrgNm()+"/"+organization.getUpOrgCd());
            String parentOrgCd = organization.getUpOrgCd();
            if (parentOrgCd == null || "".equals(parentOrgCd)) {
                log.info("==================================================ROOT======");
                rootOrganizations.add(organization); // 상위 조직인 경우 루트로 추가
            } else {
                Organization parentOrganization = organizationMap.get(parentOrgCd);
                if (parentOrganization != null) {
                    parentOrganization.addChild(organization);
                }
            }
        }
        log.info(rootOrganizations.toString());

        // JSON으로 변환하여 반환
        return convertToJson(rootOrganizations);
    }
    private List<Map<String, Object>> convertToJson(List<Organization> organizations) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Organization organization : organizations) {
            Map<String, Object> orgMap = new HashMap<>();
            orgMap.put("orgCd", organization.getOrgCd());
            orgMap.put("orgNm", organization.getOrgNm());
            orgMap.put("upOrgCd", organization.getUpOrgCd());
            orgMap.put("sq", organization.getSq());
            orgMap.put("mgrId", organization.getMgrId());
            orgMap.put("mgrNm", organization.getMgrNm());
            log.info("json converting::"+organization.getOrgCd()+"/"+organization.getOrgNm());
            List<Map<String, Object>> children = convertToJson(organization.getChildren());
            if (!children.isEmpty()) {
                orgMap.put("children", children);
            }
            result.add(orgMap);
        }
        return result;
    }
    @GetMapping("/makeData")
    public void makeSampleTreeData() {
        Organization rootOrg = new Organization();
        rootOrg.setOrgCd("ROOT");
        rootOrg.setOrgNm("Root Organization");
        rootOrg.setUpOrgCd("");
        rootOrg.setSq("0");
        rootOrg.setMgrId("staroot");
        rootOrg.setMgrNm("Hansolo");
        organizationRepository.save(rootOrg);

        // Add 10 test organizations
        for (int i = 1; i <= 5; i++) {
            Organization testOrg = new Organization();
            testOrg.setOrgCd("TEST-1" + i);
            testOrg.setOrgNm("Test(1) Organization " + i);
            testOrg.setUpOrgCd("ROOT"); // Set up_org_cd to ROOT for all test organizations
            testOrg.setSq(Integer.toString(i));
            testOrg.setMgrId("staroot"+i);
            testOrg.setMgrNm("Hansolo"+i);
            organizationRepository.save(testOrg);
        }
        for (int i = 1; i <= 5; i++) {
            Organization testOrg = new Organization();
            testOrg.setOrgCd("TEST-2" + i);
            testOrg.setOrgNm("Test(2) Organization " + i);
            testOrg.setUpOrgCd("TEST-1"+i); // Set up_org_cd to ROOT for all test organizations
            testOrg.setSq(Integer.toString(i));
            testOrg.setMgrId("staroot"+i);
            testOrg.setMgrNm("Hansolo"+i);
            organizationRepository.save(testOrg);
        }
        for (int i = 1; i <= 5; i++) {
            Organization testOrg = new Organization();
            testOrg.setOrgCd("TEST-3" + i);
            testOrg.setOrgNm("Test(3) Organization " + i);
            testOrg.setUpOrgCd("TEST-2"+i); // Set up_org_cd to ROOT for all test organizations
            testOrg.setSq(Integer.toString(i));
            testOrg.setMgrId("staroot"+i);
            testOrg.setMgrNm("Hansolo"+i);
            organizationRepository.save(testOrg);
        }
        for (int i = 1; i <= 5; i++) {
            Organization testOrg = new Organization();
            testOrg.setOrgCd("TEST-4" + i);
            testOrg.setOrgNm("Test(4) Organization " + i);
            testOrg.setUpOrgCd("TEST-3"+i); // Set up_org_cd to ROOT for all test organizations
            testOrg.setSq(Integer.toString(i));
            testOrg.setMgrId("staroot"+i);
            testOrg.setMgrNm("Hansolo"+i);
            organizationRepository.save(testOrg);
        }
        for (int i = 1; i <= 5; i++) {
            Organization testOrg = new Organization();
            testOrg.setOrgCd("TEST-5" + i);
            testOrg.setOrgNm("Test(5) Organization " + i);
            testOrg.setUpOrgCd("TEST-4"+i); // Set up_org_cd to ROOT for all test organizations
            testOrg.setSq(Integer.toString(i));
            testOrg.setMgrId("staroot"+i);
            testOrg.setMgrNm("Hansolo"+i);
            organizationRepository.save(testOrg);
        }

    }
    /*
    postMapping("/save")
    public ResponseEntity<String> saveChanges(@RequestBody List<Organization> orgList) {
        try {
            // Save changes to the database
            organizationRepository.saveAll(orgList);
            return ResponseEntity.ok("Changes saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save changes!");
        }
    }
    */
    @PostMapping("/save")
    public ResponseEntity<String> saveChanges(@RequestBody JsonNode modifiedTreeData) {
        try {
            // Iterate through the modified tree data and save changes to the database
            log.info("modifiedTreeData.toString() :: "+modifiedTreeData.toString());
            saveTreeData(modifiedTreeData, null); // Assuming there's no parent node
            return ResponseEntity.ok("Changes saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save changes!");
        }
    }

    private void saveTreeData(JsonNode treeData, Organization parent) {
        //log.info("saveTreeData() called :: "+treeData.toString()+"/"+parent);
        log.info("treeData.size() ::"+treeData.size());
        for (JsonNode node : treeData) {
            Organization organization = new Organization();
            organization.setOrgCd(node.get("id").asText());
            //organization.setOrgNm("okok");
            organization.setOrgNm(node.get("name").asText());
            if(parent != null){
                organization.setUpOrgCd(parent.getOrgCd());
            }else if(node.get("upOrgCd").asText() != null && !"".equals(node.get("upOrgCd").asText())){
                organization.setUpOrgCd(node.get("upOrgCd").asText());
            }else{
                //organization.setUpOrgCd(null);
                organization.setUpOrgCd("");
            }
            organization.setSq(node.get("sq").asText());
            organization.setMgrId(node.get("mgrId").asText());
            organization.setMgrNm(node.get("mgrNm").asText());
            log.info("Check Data ::" + node.get("id").asText()+"/"+node.get("name").asText());
            organizationRepository.save(organization);

            if (node.has("children")) {
                saveTreeData(node.get("children"), organization);
            }
        }
    }
}
