package at.pixsearch.mvc.model;

/**
 * Created by michael on 08/06/15.
 */

public class UploadedFile {

    private Long id;
    private String name;
    private String location;
    private Long size;
    private String type;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Long getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }
}