package com.kei.cisco.service;

import com.kei.cisco.model.Trash;
import com.kei.cisco.model.User;

import java.util.List;

public interface TrashService {

	int create(Trash trash);

	Trash read(int id);

	List<Trash> read(String type);

	List<Trash> read();

	boolean update(Trash trash);

	boolean delete(int id);
	
}