<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
    </head>
    <body>
        <header>    
            <h1>Welcome to Shop</h1>
            <form:form action="/Shop/profile/${customerId}" method="post">
                <input type="submit" value="Profile" />
            </form:form>

        </header>

        <table style="width: 100%">
            <tr >
                <th>Name</th>
                <th>About</th>
                <th>Price</th>
                <th>Quantity</th>
                <th></th>
            </tr>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <th>${product.name}</th>
                    <th>${product.about}</th>
                    <th>${product.price}</th>
                    <th>${product.quantity}</th>
                        <form:form action="/Shop/buy/${customerId}/${product.id}" method="post">
                        <th><input type="submit" value="Buy" /></th>
                        </form:form>
                        <form:form action="/Shop/edit/${customerId}/${product.id}" method="post">
                        <th><input type="submit" value="Edit" /></th>
                        </form:form>
                        <form:form action="/Shop/delete/${customerId}/${product.id}" method="post">
                        <th><input type="submit" value="Delete" /></th>
                        </form:form>
                </tr>
            </c:forEach>
        </table>
        <form:form action="/Shop/add/${customerId}" method="get">
            <input type="submit" value="Add New Product" />
        </form:form>
    </body>
</html>
