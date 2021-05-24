package de3.vanelle_fiorini.projet_big_data.models;

public class Categorie extends CSVable {
    private String categorieName;
    private Integer categorieID;


    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public Integer getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(Integer categorieID) {
        this.categorieID = categorieID;
    }

    @Override
    public String toCSV() {
        return this.categorieName + "," + this.categorieID;
    }
}
