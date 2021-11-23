<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>

        <style>
            .field
            {
                clear:both;
                padding:5px;
            }

            .field label
            {
                text-align: left;
                width:100px;
                float:left;
            }

            .error
            {
                color: red;
            }

        </style>


    </head>

    <body>
        <form:form action="/Shop/login" method="post" commandName="customerli">
            <div class="field">
                <form:label path="email">Email</form:label>
                <form:input path="email"/>
                <form:errors path="email" cssClass="error"/>
            </div>
            <div class="field">
                <form:label path="password">Password</form:label>
                <form:password path="password"/>
                <form:errors path="password" cssClass="error"/>
            </div>
            <input type="submit" value="Submit"/>
        </form:form>
        <form:form action="/Shop/reg" method="get">
            <input type="submit" value="Go To Register" />
        </form:form>
    </body>
</html>

