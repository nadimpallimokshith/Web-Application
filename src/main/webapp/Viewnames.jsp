<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Registered Names</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registered Names</h1>
        <c:if test="${not empty userList}">
            <ul>
                <c:forEach var="user" items="${userList}">
                    <li>${user.name}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty userList}">
            <p>No registered names found.</p>
        </c:if>
    </div>
</body>
</html>
