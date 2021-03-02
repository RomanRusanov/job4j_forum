<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script>
        function validate() {
            var name = checkUserInput($('#name'));
            if (name !== "") {
                return true;
            }
            return false;
        }
        function checkUserInput(input) {
            var currentInput = $(input).val();
            if (currentInput === '' || currentInput === undefined) {
                alert("Поле: "+ $(input).attr('title') + " не заполнено")
                return "";
            }
            return currentInput;
        }
    </script>
    <title>Тема</title>
</head>
<body>
<div class="container mt-3">
    Login as : ${user.username}
</div>
<div class="container mt-3">
    <div class="row">
        <h4>
            <c:choose>
                <c:when test="${not empty post}">
                    Редактирование темы
                </c:when>
                <c:otherwise>
                    Создание новой темы
                </c:otherwise>
            </c:choose>
        </h4>
    </div>

    </head>
    <a href="/index">Перейти на главную страницу</a>
    <form  class="editForm" action="<c:url value='/save'/>" method='POST'>
        <input type="hidden" name="post_id" value="${post.id}" />
        <table>
            <tr>
                <c:choose>
                    <c:when test="${not empty post}">
                        <label for="name">Название темы:</label>
                        <input type='text' name='name' title="Название темы" id="name" value="${post.name}">
                    </c:when>
                    <c:otherwise>
                        <label for="name">Название темы:</label>
                        <input type='text' name='name' title="Название темы" id="name">
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="Сохранить" style="margin-left: 33%" onclick="return validate()"/></td>
            </tr>
        </table>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>