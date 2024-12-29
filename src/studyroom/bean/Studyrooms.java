package studyroom.bean;

public class Studyrooms {
    private long id;
    private boolean status;

    public Studyrooms() {
    }

    public Studyrooms(long id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public Studyrooms(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Studyrooms{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
