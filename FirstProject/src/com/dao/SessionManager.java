package com.dao;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private Map<String, Object> session;

    public SessionManager() {
    	session = new HashMap<>();
    }

    public void setAttribute(String login, Object value) {
    	session.put(login, value);
    }

    public Object getAttribute(String login) {
        return session.get(login);
    }

    public void removeAttribute(String login) {
    	session.remove(login);
    }
    
    public boolean isLoggedIn() {
        return session.get("member_id") != null;
    }
}
