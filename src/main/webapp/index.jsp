<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Names</title>
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
        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }
        input[type="text"], input[type="email"], input[type="tel"], textarea, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: "Times New Roman", Times, serif;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            font-family: "Times New Roman", Times, serif;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Register Names Here</h1>
        <form action="Savename" method="post" enctype="multipart/form-data">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <input type="submit" value="submit">
        </form>
    </div>
    <div class="container">
        <h1>Registered names</h1>
        <button id="viewUsersBtn">View Registered Names</button>
    </div>

    <script>
        document.getElementById("viewUsersBtn").addEventListener("click", function() {
            var userListWindow = window.open("", "_blank");
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        userListWindow.document.write(xhr.responseText);
                    } else {
                        console.error("Error fetching users: " + xhr.statusText);
                    }
                }
            };
            xhr.open("GET", "Viewnames", true);
            xhr.send();
        });
    </script>
</body>
</html>
