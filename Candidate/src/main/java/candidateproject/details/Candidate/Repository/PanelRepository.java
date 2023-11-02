package candidateproject.details.Candidate.Repository;

import candidateproject.details.Candidate.Entity.Panel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PanelRepository extends JpaRepository<Panel, Integer> {
    Panel findByPanelEmail(String panelEmail);

    @Query(value = "select * from Panel where skills_id in (select skills_id from skills_table where skill = ?1)",
            nativeQuery = true)
    public List<Panel> findBySkillId(String skill);

    @Modifying
    @Query(value = "delete from panel where panel_email=:panelEmail", nativeQuery = true)
    Integer deleteByemail(String panelEmail);

    @Query(value = "select * from panel where panel_name=:panelName", nativeQuery = true)
    List<Panel> findByname(String panelName);

    @Query(value = "select * from panel where panel_name like :panelName%", nativeQuery = true)
    List<Panel> getbyname(String panelName);
}