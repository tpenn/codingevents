package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    @GetMapping
    public String displayEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            EventData.add(newEvent);
            return "redirect:/events";
        }

        model.addAttribute("title", "Create Event");
        model.addAttribute("name", newEvent.getName());
        model.addAttribute("description", newEvent.getDescription());
        model.addAttribute("contactEmail", newEvent.getContactEmail());
        return "events/create";

    }

    @GetMapping("delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:/events";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventEdit = EventData.getById(eventId);
        model.addAttribute("event", eventEdit);
        model.addAttribute("title", "Edit Event " + eventEdit.getName() + " (id=" + eventEdit.getId() + ")");

        return "events/edit";

    }

    @PostMapping("edit")
    public String editEvent(int eventId, String name, String description) {
        Event eventEdit = EventData.getById(eventId);

        eventEdit.setName(name);
        eventEdit.setDescription(description);
        return "redirect:/events";
    }
}
