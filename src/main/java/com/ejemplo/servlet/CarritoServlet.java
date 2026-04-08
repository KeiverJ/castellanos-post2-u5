package com.ejemplo.servlet;

import com.ejemplo.model.CarritoItem;
import com.ejemplo.model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Servlet que gestiona el carrito de compras en sesión.
 * Soporta agregar productos, limpiar el carrito y ver su contenido.
 */
@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        Map<Integer, CarritoItem> carrito = (Map<Integer, CarritoItem>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new LinkedHashMap<>();
            session.setAttribute("carrito", carrito);
        }

        if ("agregar".equals(accion)) {
            int idProd = Integer.parseInt(req.getParameter("idProducto"));
            // Obtener producto desde el contexto compartido por CatalogoServlet
            Producto prod = obtenerProducto(idProd);
            if (prod != null) {
                carrito.merge(idProd,
                        new CarritoItem(prod, 1),
                        (existing, nuevo) -> {
                            // Si ya existe, incrementar cantidad en 1
                            existing.setCantidad(existing.getCantidad() + 1);
                            return existing;
                        });
            }
        } else if ("limpiar".equals(accion)) {
            carrito.clear();
        }

        // Patrón PRG: redirigir según la acción ejecutada
        resp.sendRedirect(req.getContextPath() +
                ("verCarrito".equals(accion) ? "/carrito" : "/catalogo"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        @SuppressWarnings("unchecked")
        Map<Integer, CarritoItem> carrito = session != null
                ? (Map<Integer, CarritoItem>) session.getAttribute("carrito")
                : null;

        req.setAttribute("items", carrito != null ? carrito.values() : Collections.emptyList());
        req.getRequestDispatcher("/WEB-INF/views/carrito.jsp")
                .forward(req, resp);
    }

    /**
     * Obtiene un producto del catálogo compartido en el contexto de aplicación.
     * El catálogo es almacenado por CatalogoServlet al inicializarse.
     */
    @SuppressWarnings("unchecked")
    private Producto obtenerProducto(int id) {
        List<Producto> catalogo = (List<Producto>) getServletContext().getAttribute("catalogo");
        if (catalogo == null)
            return null;
        return catalogo.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElse(null);
    }
}