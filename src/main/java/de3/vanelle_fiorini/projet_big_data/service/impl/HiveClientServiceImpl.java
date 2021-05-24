package de3.vanelle_fiorini.projet_big_data.service.impl;

import de3.vanelle_fiorini.projet_big_data.models.CSVable;
import de3.vanelle_fiorini.projet_big_data.models.Categorie;
import de3.vanelle_fiorini.projet_big_data.models.Cv;
import de3.vanelle_fiorini.projet_big_data.models.Label;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.awt.geom.RectangularShape;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HiveClientServiceImpl {
    @Autowired
    private DataSource hiveDataSource;

    public List<Cv> getCvsFromHive(Integer limit) throws SQLException {
        PreparedStatement stmt = hiveDataSource.getConnection().prepareStatement("SELECT * FROM cv LIMIT ?");
        stmt.setInt(1, limit);
        ResultSet resultSet = stmt.executeQuery();
        List<Cv> cvs = new ArrayList<>();
        while (resultSet.next()) {
            Cv c = new Cv();
            c.setId(resultSet.getInt(1));
            c.setDescription(resultSet.getString(2));
            c.setGender(resultSet.getString(3));
            cvs.add(c);
        }

        return cvs;
    }

    public List<Categorie> getCategoriesFromHive() throws SQLException {
        PreparedStatement stmt = hiveDataSource.getConnection().prepareStatement("SELECT * FROM categorie");
        ResultSet resultSet = stmt.executeQuery();
        List<Categorie> categories = new ArrayList<>();
        while (resultSet.next()) {
            Categorie c = new Categorie();
            c.setCategorieID(resultSet.getInt(1));
            c.setCategorieName(resultSet.getString(2));
            categories.add(c);
        }

        return categories;
    }

    public List<Label> getLabelsFromHive() throws SQLException {
        PreparedStatement stmt = hiveDataSource.getConnection().prepareStatement("SELECT * FROM label");
        ResultSet resultSet = stmt.executeQuery();
        List<Label> labels = new ArrayList<>();
        while (resultSet.next()) {
            Label l = new Label();
            l.setId(resultSet.getInt(1));
            l.setCategory(resultSet.getString(2));
            labels.add(l);
        }

        return labels;
    }
}
