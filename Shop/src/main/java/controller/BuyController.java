package controller;

import entities.Buy;
import entities.HibernateUtil;
import entities.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BuyController {

    Session session;
    Transaction tx = null;

    @RequestMapping(value = "/buy/{customerId}/{productId}", method = RequestMethod.POST)
    public String buy(@PathVariable int customerId, @PathVariable int productId, Model model) {
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            Buy b = new Buy();
            b.setIdCustomer(customerId);
            b.setIdProduct(productId);

            session.save(b);

            String q = "from Product where id=:productId";
            Query query = session.createQuery(q);

            query.setParameter("productId", productId);
            Product p = (Product) query.uniqueResult();
            p.setQuantity(p.getQuantity() - 1);

            if (p.getQuantity() <= 0) {
                session.delete(p);
            } else {
                session.update(p);
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
        return "buy";
    }
}
