package ru.saburov.springmvc.repository;

import org.springframework.data.repository.CrudRepository;
import ru.saburov.springmvc.domain.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
