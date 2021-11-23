<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
    </head>
    <body>
        <h1>Cestitamo! Uspesno ste kupili proizvod!</h1>
        <form:form action="/Shop/homePage/${customerId}" method="get">
            <input type="submit" value="Home Page" />
        </form:form>
    </body>
</html>
