package br.ufsm.csi.seguranca.pila.filtros;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cpol on 05/06/2017.
 */
@WebFilter("*.priv")
public class FiltroSeguranca implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String referer = request.getHeader("referer");
        URL url = new URL(referer);

        if (url.getHost().equals("localhost") && url.getPort() == 8080) {
            if (request.getSession().getAttribute("usuario") == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("logar.html");
            } else {

                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("hello.priv");
        }
    }

    @Override
    public void destroy() {

    }
}
