<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <h1>Carrito de Compras</h1>

    <c:choose>
        <c:when test="${empty items}">
            <p>El carrito está vacío.</p>
            <a href="${pageContext.request.contextPath}/catalogo">
                Volver al catálogo
            </a>
        </c:when>
        <c:otherwise>
            <%-- Tabla de items en el carrito --%>
            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio unitario</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0"/>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td>${item.producto.nombre}</td>
                            <td>
                                <fmt:formatNumber value="${item.producto.precio}"
                                    type="currency" currencySymbol="$"/>
                            </td>
                            <td>${item.cantidad}</td>
                            <td>
                                <fmt:formatNumber value="${item.subtotal}"
                                    type="currency" currencySymbol="$"/>
                            </td>
                        </tr>
                        <c:set var="total" value="${total + item.subtotal}"/>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3"><strong>Total</strong></td>
                        <td>
                            <strong>
                                <fmt:formatNumber value="${total}"
                                    type="currency" currencySymbol="$"/>
                            </strong>
                        </td>
                    </tr>
                </tfoot>
            </table>

            <%-- Acciones del carrito --%>
            <form method="post"
                  action="${pageContext.request.contextPath}/carrito">
                <input type="hidden" name="accion" value="limpiar">
                <button type="submit">Limpiar carrito</button>
            </form>
            <a href="${pageContext.request.contextPath}/catalogo">
                Seguir comprando
            </a>
        </c:otherwise>
    </c:choose>
</body>
</html>