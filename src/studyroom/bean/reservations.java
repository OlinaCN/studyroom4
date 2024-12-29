package studyroom.bean;

public class reservations {
    private long id;
    private String user_name;
    private long studyroom_id;

    public reservations() {
    }

    public reservations(String user_name, long studyroom_id) {
        this.user_name = user_name;
        this.studyroom_id = studyroom_id;
    }

    public reservations(long id, String user_name, long studyroom_id) {
        this.id = id;
        this.user_name = user_name;
        this.studyroom_id = studyroom_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getStudyroom_id() {
        return studyroom_id;
    }

    public void setStudyroom_id(long studyroom_id) {
        this.studyroom_id = studyroom_id;
    }

    @Override
    public String toString() {
        return "reservations{" +
                "id=" + id +
                ", user_name=" + user_name +
                ", studyroom_id=" + studyroom_id +
                '}';
    }
}
