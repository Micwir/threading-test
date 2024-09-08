package com.demo.servlet;

import com.demo.runnable.TestRunner;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test-servlet")
public class TestServlet extends HttpServlet {

    @Resource
    ManagedExecutorService executor;

    @Inject
    TestRunner runner;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet called");

        runner.setNumber1(23);
        runner.setNumber2(34);

        if (executor == null) {
            System.out.println("Executor is null");
            resp.getWriter().println("Executor is null, check configuration.");
            return;
        } else {
            System.out.println("Executor is not null, proceeding with task.");
        }

        if (runner == null) {
            System.out.println("Runner is null");
            resp.getWriter().println("Runner is null, check configuration.");
            return;
        } else {
            System.out.println("Runner is not null, proceeding with task.");
        }

        if (runner.getTestObject() == null) {
            System.out.println("TestObject is null");
            resp.getWriter().println("TestObject is null, check configuration.");
            return;
        } else {
            System.out.println("TestObject is not null, proceeding with task.");
        }

        try {
            executor.runAsync(runner);
            System.out.println("Task submitted to executor");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error occurred: " + e.getMessage());
        }

        resp.getOutputStream().println("Hello World");
    }
}

