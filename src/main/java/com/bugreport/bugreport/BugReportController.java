package com.bugreport.bugreport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:6001", "http://localhost:6002", "http://localhost:6003" })
@RequestMapping(path = "/BugReports")
public class BugReportController {
    @Autowired
    BugReportService bugReportService;

    @PostMapping(path = "/add", consumes = "application/json")
    public @ResponseBody BugReportDto addNewBugReport(@RequestBody BugReportDto br) {
        return new BugReportDto(bugReportService.addNewBugReport(new BugReport(br)));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<BugReportDto> getAllBugReports(
            @RequestParam(required = false) Integer pageId,
            @RequestParam(required = false) String filter) {

        Iterable<BugReport> bugReports = bugReportService.GetAllBugReports(pageId, filter);
        
        List<BugReportDto> bugReportDtos = new ArrayList<BugReportDto>();
        for (BugReport bugReport : bugReports) {
            bugReportDtos.add(new BugReportDto(bugReport));
        }

        return bugReportDtos;
    }

    @GetMapping(path = "/get")
    public @ResponseBody BugReportDto getBugReportbyId(@RequestParam Integer id) {
        BugReport result = bugReportService.getBugReportbyId(id);
        if (result != null) {
            return new BugReportDto(result);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Found no bug report with id " + id);
        }
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody void deleteBugReportById(@RequestParam Integer id) {
        if (!bugReportService.deleteBugReportById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Found no bug report with id " + id);
        }
    }
}
