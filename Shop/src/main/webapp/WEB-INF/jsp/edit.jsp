<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
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
        <form:form action="/Shop/edit/${customerId}" method="post" commandName="prod">
            <form:hidden path="id"/>
            <div class="field">
                <form:label path="name">Name</form:label>
                <form:input path="name" />
                <form:errors path="name" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="about">About</form:label>
                <form:input path="about" />
                <form:errors path="about" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="price">Price</form:label>
                <form:input path="price" />
                <form:errors path="price" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="quantity">Quantity</form:label>
                <form:input path="quantity" />
                <form:errors path="quantity" cssClass="error"/>
            </div>
            <input type="submit" name="edit" value="Edit"/>
        </form:form>


    </body>
</html>
