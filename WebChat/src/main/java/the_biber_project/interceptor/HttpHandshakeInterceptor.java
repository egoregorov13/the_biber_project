package the_biber_project.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import java.util.ArrayList;

@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {


        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            attributes.put("sessionId", session.getId());
            //public ArrayList<String> users = new ArrayList<String>();
            /*if(attributes.getOrDefault("users", "0") == "0") {
                ArrayList<String> users = new ArrayList<String>();
                users.add(session.getId());
                //attributes.putAll();
            }

            System.out.println("123: " + session.getAttributeNames().toString()); */
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {

    }

}