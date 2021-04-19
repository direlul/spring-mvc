package ru.saburov.springmvc.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.saburov.springmvc.domain.Message;
import ru.saburov.springmvc.domain.User;
import ru.saburov.springmvc.domain.dto.MessageDto;
import ru.saburov.springmvc.repository.MessageRepository;
import ru.saburov.springmvc.service.MessageService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MainController {
    private final MessageRepository messageRepository;
    private final MessageService messageService;

    public MainController(MessageRepository messageRepository, MessageService messageService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @AuthenticationPrincipal User user
    ) {
        Page<MessageDto> page;
        model.addAttribute("message", new Message());

        page = messageService.messageList(pageable, filter, user);

        int[] bodyPager = messageService.getBodyPager(page);

        model.addAttribute("body", bodyPager);
        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid @ModelAttribute("message") Message message,
            Errors errors,
            @RequestParam MultipartFile file,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        message.setAuthor(user);

        if (!errors.hasErrors()) {
            messageService.saveFile(message, file);
            messageRepository.save(message);
        }

        Iterable<MessageDto> page = messageRepository.findAll(pageable, user);

        model.addAttribute("page", page);

        return "/main";
    }

}
