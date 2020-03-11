package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date birthday;

	private String name;

	private boolean isAdmin;

	private User[] subordinates;

	private int rating;

	public User(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"birthday=" + birthday +
				", name='" + name + '\'' +
				", isAdmin=" + isAdmin +
				", subordinates=" + Arrays.toString(subordinates) +
				", rating=" + rating +
				'}';
	}
}
