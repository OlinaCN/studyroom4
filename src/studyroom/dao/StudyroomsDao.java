package studyroom.dao;

import studyroom.bean.Studyrooms;

import java.util.List;

public interface StudyroomsDao {
    int addStudyrooms(Studyrooms studyrooms);
    int deleteById(long id);
    int updateStudyrooms(Studyrooms studyrooms);
    boolean checkById(long id);
    List<Studyrooms> getAllStudyrooms();
}
