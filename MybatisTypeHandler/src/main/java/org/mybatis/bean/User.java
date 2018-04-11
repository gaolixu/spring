package org.mybatis.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Created by mybatis on 17-1-13.
 */
//@Alias("user")
public class User {
    private Long id;
    private String username;
    private String password;
    private Date regTime;
    private String address;

   

	public User(Long id, String username, String password, Date regTime, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.regTime = regTime;
		this.address = address;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regTime=" + regTime +
                 ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
