package de3.vanelle_fiorini.projet_big_data.service.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HiveClientServiceImpl {
    private SparkSession spark;

    @Autowired
    public HiveClientServiceImpl(SparkSession sparkSession) {
        this.spark = sparkSession;
    }

    public void getDataFromHive() {
        spark.sql("USE projet_big_data");
        spark.sql("SELECT * FROM label LIMIT 100").show();
    }
}
