package de3.vanelle_fiorini.projet_big_data.models;

public class Label extends CSVable {
    private Integer Id;
    private String Category;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }


    @Override
    public String toCSV() {
        return this.getId() + "," + this.getCategory();
    }
}
