package com.example.ecommerce_clothes.util;

import com.example.ecommerce_clothes.Model.Employee;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionUtil {

    HttpSession session;
    public void set( Object value) {
        session.setAttribute("account", value);
    }

    public Employee get() {
        return (Employee) session.getAttribute("account");
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
}
