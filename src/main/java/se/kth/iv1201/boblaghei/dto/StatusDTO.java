package se.kth.iv1201.boblaghei.dto;

/**
 * DTO representing entity class <code>Status</code>.
 */
public class StatusDTO {

    private long id;
    private String name;

    public StatusDTO() {
    }

    public StatusDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StatusDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
