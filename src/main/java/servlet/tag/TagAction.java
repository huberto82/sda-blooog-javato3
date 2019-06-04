package servlet.tag;

import servlet.HttpServletMethodProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

final public class TagAction {
    public enum GET{
        ADD((req, resp) ->{

        }),
        REMOVE((req, resp) -> {

        });
        private HttpServletMethodProcessor processor;

        GET(HttpServletMethodProcessor processor){
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }

    }

    public enum POST{
        ADD((req,resp) -> {

        });

        private HttpServletMethodProcessor processor;

        POST(HttpServletMethodProcessor processor){
            this.processor = processor;
        }

        public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processor.process(req, resp);
        }
    }
    private TagAction(){}
}
