package ru.saburov.springmvc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.saburov.springmvc.controller.ControllerUtils;
import ru.saburov.springmvc.domain.Message;
import ru.saburov.springmvc.domain.User;
import ru.saburov.springmvc.domain.dto.MessageDto;
import ru.saburov.springmvc.repository.MessageRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {

    @Value("${upload.path}")
    private String uploadPath;

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }
    }

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepository.findByTag(filter, user, pageable);
        } else {
            return messageRepository.findAll(pageable, user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepository.findByUser(pageable, author, currentUser);
    }

    public int[] getBodyPager(Page<MessageDto> page) {
        int[] body;

        if (page.getTotalPages() > 7) {
            int totalPages = page.getTotalPages();
            int pageNumber = page.getNumber() + 1;
            int[] head = (pageNumber > 4) ? new int[] {1, -1} : new int[] {1, 2, 3};
            int[] bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ?
                    new int[] {pageNumber - 2, pageNumber - 1} : new int[] {};
            int[] bodyCenter = (pageNumber > 3 && pageNumber < totalPages - 2) ?
                    new int[] {pageNumber} : new int[] {};
            int[] bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ?
                    new int[] {pageNumber + 1, pageNumber + 2} : new int[] {};
            int[] tail = (pageNumber < totalPages - 3) ?
                    new int[] { - 1, totalPages} : new int[] {totalPages - 2, totalPages - 1, totalPages};
            body = ControllerUtils.merge(head, bodyBefore, bodyCenter, bodyAfter, tail);

        } else {
            body = new int[page.getTotalPages()];
            for (int i = 0; i < page.getTotalPages(); i++) {
                body[i] = 1 + i;
            }
        }

        return body;
    }
}


