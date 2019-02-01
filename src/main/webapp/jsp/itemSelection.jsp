<%-- 
    Document   : itemSelected
    Created on : Aug 9, 2018, 7:51:19 AM
    Author     : Roger Brock
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    

        <style>
            div {
                padding-top: 5px;
                padding-bottom: 5px;

            }
            hr {
                margin-top: 1rem;
                margin-bottom: 1rem;
                border: 0;
                border-top: 3px solid rgba(0, 0, 0, 1);
            }
            input[type="number"]::-webkit-outer-spin-button,
            input[type="number"]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
            input{
                text-align: center;
            }
            .sq{
                border: 5px solid black;
            }
            a:hover{
                background-color: red;
            }

        </style>
    </head>

    <body>
        <div class="container">
            <h1 class="text-center">Vending Machine</h1>
            <hr/>
            <div class="row">
                <div class='col-md-9'>
                    <c:forEach var="item" items="${vendingItems}">
                        <div class='col-md-4'>
                            <a href="displaySelectedItem?itemNumber=${item.itemNumber}">
                                <div class="sq">
                                    <p><c:out value="${item.itemNumber}"/></p>
                                    <p class="text-center"><c:out value="${item.itemName}"/></p>
                                    <p class="text-center"><c:out value="${item.itemPrice}"/></p><br><br><br>
                                    <p class="text-center">Quantity Left: <c:out value="${item.itemQuantity}"/></p>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="col-md-3">
                
                <sf:form role="form" class="text-center" modelAttribute="total">
                    <div class="form-group">
                        <h2 >Total $ In</h2>
                        <sf:input type="number" class="form-control" path="total"/>
                        <div class="btn-group">
                            <div class="form-group-row">
                                <button type="button" class='btn btn-outline-primary'><a href="addMoney?moneyType=dollar">Add Dollar</a></button>
                                <button type="button" class='btn btn-outline-primary'><a href="addMoney?moneyType=quarter">Add Quarter</a></button>
                            </div>
                            <div class="form-group-row">
                                <button type="button" class='btn btn-outline-primary'><a href="addMoney?moneyType=dime">Add Dime</a></button>
                                <button type="button" class='btn btn-outline-primary'><a href="addMoney?moneyType=nickel">Add Nickel</a></button>
                            </div>
                        </div>  
                    </sf:form>
                        
                    <sf:form role="form" method="post" action="makePurchase" modelAttribute="item" class="text-center">
                        <div class="form-group">
                            <h2>Messages</h2>
                            <input type ="text" class="form-control" id="itemMessage" path="item"/>
                            <label for="item" class="col-sm-2"><h4>Item:</h4></label>
                            <div class="col-sm-10">
                                <sf:input type="number" class="form-control form-control-lg" path="itemNumber"/> 
                            </div>
                            <input type="submit" class="btn btn-outline-primary" value="Make Purchase">
                        </div>
                        <hr>
                    </sf:form>

                    <form role="form" method="post" action="returnChange" class="text-center">
                        <div class="form-group">
                            <h2>Change</h2>
                            <input type="text" class="form-control" id="change" path="giveChange"/>
                            <div>
                                <input type="submit" class="btn btn-outline-primary" value="Return Change">
                            </div>
                        </div>
                    </form>
                        
                </div>
            </div>
        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>