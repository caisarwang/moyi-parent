package com.dd.service;

import java.util.Map;

public interface AuthService {
    Map<String, Object> authentication(String username, String password);
}
