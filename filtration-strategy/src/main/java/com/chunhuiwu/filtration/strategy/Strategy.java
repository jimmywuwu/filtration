package com.chunhuiwu.filtration.strategy;
import com.chunhuiwu.filtration.data_source.DataSource;

import java.util.HashMap;
import java.util.Map;

public abstract class Strategy {
    protected Map<String, DataSource> datasource;

    public void setDatasource(Map datasource) {
        this.datasource = datasource;
    }

    public abstract int signal();
}
