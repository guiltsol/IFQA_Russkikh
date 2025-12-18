package hw_5.model;

import lombok.Data;

@Data
public class Location {

    public String name;
    public String url;

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
