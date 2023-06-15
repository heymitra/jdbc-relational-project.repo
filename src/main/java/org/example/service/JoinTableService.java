package org.example.service;

import java.sql.SQLException;

public interface JoinTableService {
    // takeShare
    void takeShare(long shareholderId, long brandId);
    // sellShare
    void sellShare(long shareholderId, long brandId) throws SQLException;
    // change share
    void changeShare(long shareholderId, long oldBrandId, long newBrandId) throws SQLException;
    // load all
    void loadAll() throws SQLException;
}
