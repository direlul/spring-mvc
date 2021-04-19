package ru.saburov.springmvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.saburov.springmvc.domain.Message;
import ru.saburov.springmvc.domain.User;
import ru.saburov.springmvc.domain.dto.MessageDto;

public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("select new ru.saburov.springmvc.domain.dto.MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "group by m")
    Page<MessageDto> findAll(Pageable pageable, @Param("user") User author);

    @Query("select new ru.saburov.springmvc.domain.dto.MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.tag =:tag " +
            "group by m")
    Page<MessageDto> findByTag(@Param("tag") String tag, @Param("user") User user, Pageable pageable);
    @Query("select new ru.saburov.springmvc.domain.dto.MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.author =:author " +
            "group by m")
    Page<MessageDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);
}
