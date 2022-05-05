package com.example.lab_02.controller;

import com.example.lab_02.model.Calculator;
import com.example.lab_02.model.Validator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CalculateServlet", value = "/calculate")
public class CalculateServlet extends HttpServlet {
    Calculator calculator = new Calculator();
    Validator validator = new Validator();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> names = List.of("a", "b", "c", "d");
        List<String> params = names.stream().map(request::getParameter).toList();

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i), params.get(i));
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(172800);

        map.forEach(session::setAttribute);

        if (validator.isNumeric(params)) {
            List<Double> list = params.stream().map(Double::parseDouble).toList();
            double result = calculator.calculateTask(list.get(0), list.get(1), list.get(2), list.get(3));
            if (Double.isNaN(result))
                response.sendRedirect("view/nan-message.jsp");
            else {
                request.setAttribute("result", result);
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/answer.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("view/error-message.jsp");
        }
    }
}
