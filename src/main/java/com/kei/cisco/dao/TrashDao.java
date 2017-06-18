package com.kei.cisco.dao;

import com.kei.cisco.model.Class;
import com.kei.cisco.model.Trash;

import java.util.List;

public interface TrashDao {

    int create(Trash trash);

    Trash read(int id);

    List<Trash> read(String type);

    List<Trash> read();

    boolean update(Trash trash);

    boolean delete(int id);
}

