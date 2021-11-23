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
        <form:form action="/Shop/editCustomer" method="post" commandName="customer">
            <form:hidden path="id"/>
            <div class="field">
                <form:label path="firstName">First Name</form:label>
                <form:input path="firstName" />
                <form:errors path="firstName" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="lastName">Last Name</form:label>
                <form:input path="lastName" />
                <form:errors path="lastName" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="email">Email</form:label>
                <form:input path="email" />
                <form:errors path="email" cssClass="error"/>
            </div>

            <div class="field">
                <form:label path="password">Password</form:label>
                <form:input path="password" />
                <form:errors path="password" cssClass="error"/>
            </div>
            <input type="submit" name="edit" value="Edit"/>
        </form:form>
        <form:form action="/Shop/deleteCustomer/${customer.id}" method="post">
            <input type="submit" value="Delete" />
        </form:form>
        <form:form action="/Shop/homePage/${customerId}" method="get">
            <input type="submit" value="Home Page" />
        </form:form>
    </body>
</html>
