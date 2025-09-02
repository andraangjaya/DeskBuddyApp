package com.demo.deskbuddy.repository;

import com.demo.deskbuddy.domain.SessionHistory;
import com.demo.deskbuddy.domain.Status;
import com.demo.deskbuddy.dto.SessionHistoryDTO;
import com.demo.deskbuddy.dto.SessionHistoryViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionHistoryRepository extends JpaRepository<SessionHistory, Long> {
    Optional<SessionHistory> findByStudentIdAndSession(Long student_id, Integer session);
    Optional<SessionHistory> findByStudentIdAndSessionAndSessionDateBetween(Long student_id, Integer session, Instant start, Instant end);
    Optional<SessionHistory> findByStudentIdAndTimeStartedIsNotNullAndTimeFinishedIsNull(Long student_id);
    List<SessionHistory> findAllByStatusAndTimeFinishedIsNull(Status status);

    @Query(value = """
    select
    	sh.session,
    	sh.time_started,
    	sh.time_finished,
    	sh.status,
    	s.first_name,
    	s.last_name,
    	count (d.id) as total_distraction
    from session_history sh
    inner join student s on s.id = sh.student_id
    left join distraction_history d on d.session_history_id = sh.id
    group by sh.session, sh.time_started, sh.time_finished, sh.status, s.first_name, s.last_name
    order by s.first_name, sh.session
    """, nativeQuery = true)
    List<SessionHistoryViewDTO> findAllSessionHistory();
}
