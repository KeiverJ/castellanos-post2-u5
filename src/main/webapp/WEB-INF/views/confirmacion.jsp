<%@ page contentType="text/html;charset=UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Confirmación de Pedido</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/estilos.css"
    />
  </head>
  <body>
    <h1>¡Pedido Confirmado!</h1>

    <p>Tu pedido ha sido registrado exitosamente.</p>
    <p>Recibirás una confirmación pronto.</p>

    <a href="${pageContext.request.contextPath}/catalogo">
      Volver al catálogo
    </a>
  </body>
</html>
