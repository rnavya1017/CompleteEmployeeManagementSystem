package com.example.CompleteEmpManagementSystem.aop;

import com.example.CompleteEmpManagementSystem.model.AuditLog;
import com.example.CompleteEmpManagementSystem.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditLogAspect {

    private final AuditLogRepository auditLogRepository;

    private final HttpServletRequest request;


    public AuditLogAspect(
            AuditLogRepository auditLogRepository,
            HttpServletRequest request) {

        this.auditLogRepository = auditLogRepository;
        this.request = request;
    }


    // This advice runs around controller methods.
    //
    // It executes:
    //
    // 1. Before controller method
    // 2. Controller method
    // 3. After controller method
    //
    // execution(* controller..*(..))
    // means all methods inside controller package.


    @Around(
            "execution(* com.example.CompleteEmpManagementSystem.controller..*(..))"
    )
    public Object logApiRequest(
            ProceedingJoinPoint joinPoint) throws Throwable {


        // Start time


        long startTime = System.currentTimeMillis();


        // ------------------------------------------------------
        // Get logged-in user
        // ------------------------------------------------------

        String username = getCurrentUsername();


        // ------------------------------------------------------
        // Controller method name
        // Example:
        //
        // createEmployee
        // updateDepartment
        // getAllUsers
        // ------------------------------------------------------

        String methodName =
                joinPoint.getSignature().getName();


        // ------------------------------------------------------
        // HTTP method
        //
        // GET / POST / PUT / DELETE
        // ------------------------------------------------------

        String httpMethod =
                request.getMethod();


        // ------------------------------------------------------
        // Request URI
        //
        // Example:
        //
        // /api/employees
        // /api/users/5/roles
        // ------------------------------------------------------

        String requestUri =
                request.getRequestURI();


        // ------------------------------------------------------
        // Action
        //
        // We'll create a readable action such as:
        //
        // GET_createEmployee
        // POST_createSalary
        // PUT_updateRoles
        // ------------------------------------------------------

        String action =
                httpMethod + "_" + methodName;


        Object result;

        try {

            // ==================================================
            // Execute the actual controller method
            // ==================================================

            result = joinPoint.proceed();


            // ==================================================
            // Controller successfully completed
            // ==================================================

            int status = getResponseStatus(result);


            saveAuditLog(
                    username,
                    action,
                    httpMethod,
                    requestUri,
                    startTime,
                    status
            );


            return result;

        } catch (Throwable exception) {

            // ==================================================
            // Controller threw an exception
            // ==================================================

            long executionTime =
                    System.currentTimeMillis() - startTime;


            AuditLog auditLog = new AuditLog();

            auditLog.setUsername(username);
            auditLog.setAction(action);
            auditLog.setMethodName(httpMethod);
            auditLog.setRequestUri(requestUri);
            auditLog.setTimestamp(LocalDateTime.now());
            auditLog.setExecutionTime(executionTime);

            // Unexpected exception
            auditLog.setStatus(500);


            auditLogRepository.save(auditLog);


            // Very important:
            // Don't swallow the exception.
            //
            // GlobalExceptionHandler still needs to receive it.
            throw exception;
        }
    }


    // ==========================================================
    // Get currently authenticated username
    // ==========================================================

    private String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        if (authentication == null ||
                !authentication.isAuthenticated()) {

            return "anonymous";
        }


        return authentication.getName();
    }


    // ==========================================================
    // Determine HTTP response status
    // ==========================================================

    private int getResponseStatus(Object result) {

        if (result instanceof ResponseEntity<?> responseEntity) {

            return responseEntity
                    .getStatusCode()
                    .value();
        }


        // If controller returns an object directly,
        // assume successful response.
        return 200;
    }


    // ==========================================================
    // Save audit log
    // ==========================================================

    private void saveAuditLog(
            String username,
            String action,
            String methodName,
            String requestUri,
            long startTime,
            int status) {


        long executionTime =
                System.currentTimeMillis() - startTime;


        AuditLog auditLog = new AuditLog();


        auditLog.setUsername(username);

        auditLog.setAction(action);

        auditLog.setMethodName(methodName);

        auditLog.setRequestUri(requestUri);

        auditLog.setTimestamp(LocalDateTime.now());

        auditLog.setExecutionTime(executionTime);

        auditLog.setStatus(status);


        auditLogRepository.save(auditLog);
    }
}