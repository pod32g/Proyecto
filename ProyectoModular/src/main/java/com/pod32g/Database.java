package com.pod32g;

public interface Database {

    public void runInsertQuery(String query);
    public void close();

}
