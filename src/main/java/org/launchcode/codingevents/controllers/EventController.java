package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("events")
public class EventController {
//    private static List<String> events = new ArrayList<>();
    private static List<Map<String, String>> events = new ArrayList<>();

    @GetMapping
    public String displayEvents(Model model) {
        model.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }

    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, String eventDesc) {
        events.add(new HashMap<String, String>() {{ put("name", eventName); put("desc", eventDesc); }});
        return "redirect:/events";
    }
}
