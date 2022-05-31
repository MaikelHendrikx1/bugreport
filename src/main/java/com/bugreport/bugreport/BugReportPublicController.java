package com.bugreport.bugreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/bugreports")
public class BugReportPublicController {
    @Autowired
    BugReportService bugReportService;

    @PostMapping(path = "/add", consumes = "application/json")
    public @ResponseBody BugReportDto addNewBugReport(@RequestBody BugReportDto br, @RequestParam String key) {

        // create a client connected to the Buggerpage microservice
        WebClient client = WebClient.create("http://localhost:6001");

        Boolean keyVerified = client.get()
                .uri("/BuggerPages/verifyKey?pageId={pi}&key={key}", br.pageId, key)
                .retrieve()
                .toEntity(Boolean.class)
                .block()
                .getBody();

        if (keyVerified) {
            return new BugReportDto(bugReportService.addNewBugReport(new BugReport(br)));
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Supplied the incorrect key.");
        }
    }
}
