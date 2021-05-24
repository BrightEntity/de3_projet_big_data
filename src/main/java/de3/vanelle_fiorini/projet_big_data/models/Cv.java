package de3.vanelle_fiorini.projet_big_data.models;

public class Cv extends CSVable {
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String description;
    private String gender;

    public String toCSV() {
        return this.getId() + "," + this.getDescription() + "," + this.getGender();
    }

}
