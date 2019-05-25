package servlet;

import entity.ArticleEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/article")
public class ArcticleServlet extends HttpServlet {

    private EntityManager em;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blooog");
        em = factory.createEntityManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        em.getTransaction().begin();
        ArticleEntity article = new ArticleEntity("Artykuł nr 1");
        em.persist(article);
        em.persist(new ArticleEntity("Artykuł nr 2"));
        List<ArticleEntity> list =em.createQuery("from ArticleEntity").getResultList();
        PrintWriter out = resp.getWriter();
        for(ArticleEntity a: list){
            out.println(a.getContent());
        }
    }
}
