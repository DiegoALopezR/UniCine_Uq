package co.edu.uniquindio.unicine.filtros;


import co.edu.uniquindio.unicine.bean.SeguridadBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SeguridadFiltro implements Filter {

    public static final String PAGINA_INICIO = "/index.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final String requestURI = request.getRequestURI();

            //Obtenemos el objeto seguridadBean de la sesión actual
            SeguridadBean userManager = (SeguridadBean) request.getSession().getAttribute("seguridadBean");

            //Aplicar el filtro a esta carpeta
            if (requestURI.startsWith("/cliente/") ) {

                if (userManager != null) {
                    if (userManager.isAutenticado() && userManager.getTipoSesion().equals("cliente")) {
                    //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            }else if (requestURI.startsWith("/Administrador/") ) {

                if (userManager != null) {
                    if (userManager.isAutenticado() && userManager.getTipoSesion().equals("admin")) {
                        //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            }else if (requestURI.startsWith("/AdministradorTeatro/") ) {

                if (userManager != null) {
                    if (userManager.isAutenticado() && userManager.getTipoSesion().equals("admin_teatro")) {
                        //El usuario está logueado entonces si puede ver la página solicitada
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //El usuario no está logueado, entonces se redirecciona al inicio
                        response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                    }
                } else {
                    //El usuario no está logueado, entonces se redirecciona al inicio
                    response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
                }
            }else{
                //La página solicitada no está en la carpeta /usuario entonces el filtro no aplica
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
