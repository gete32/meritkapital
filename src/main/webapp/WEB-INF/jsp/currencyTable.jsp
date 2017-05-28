<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1> Currency Table </h1>
    <label>Filter by currency</label>
    <label>
        <select id="currency_selector">
            <c:forEach var="currency" items="${currencies}">
                <option value="${currency.name}">${currency.name}</option>
            </c:forEach>
        </select>
    </label>
    <label>From date</label>
    <label>
        <select id="slot_start_selector">
            <c:forEach var="slot" items="${startSlots}">
                <option value="${slot}">${slot}</option>
            </c:forEach>
        </select>
    </label>
    <label>To date</label>
    <label>
        <select id="slot_end_selector">
            <c:forEach var="slot" items="${endSlots}">
                <option value="${slot}">${slot}</option>
            </c:forEach>
        </select>
    </label>
    <table>
        <tr>
            <th>Currency</th>
            <th>Currency</th>
            <th>Start</th>
            <th>End</th>
            <th>Coefficient</th>
        </tr>

        <c:choose>
            <c:when test="${not empty currencyCoefficientDtos}">
                <c:forEach items="${currencyCoefficientDtos}" var="currency">
                    <tr>
                        <td id="table_view_first" class="table_view_first">${currency.firstCurrencyName}</td>
                        <td id="table_view_second">${currency.secondCurrencyName}</td>
                        <td id="table_view_start">${currency.startDate}</td>
                        <td id="table_view_end">${currency.endDate}</td>
                        <td id="table_view_coefficient">${currency.coefficient}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>
                    <span>
                        Empty list
                    </span>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</div>
