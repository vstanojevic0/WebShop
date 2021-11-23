package controller;

import entities.Customer;
import entities.HibernateUtil;
import entities.Product;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProductController {

    Session session;
    Transaction tx = null;


    @RequestMapping(value = "/delete/{customerId}/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id,@PathVariable int customerId, Model model) {
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            String q = "delete from Product where id=:id";
            Query query = session.createQuery(q);

            query.setParameter("id", id);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
        return "redirect:/homePage/" + customerId;
    }

    @RequestMapping(value = "/edit/{customerId}/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable int id, @PathVariable int customerId, Model model) {
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            String q = "from Product where id=:id";
            Query query = session.createQuery(q);

            query.setParameter("id", id);
            Product product = (Product) query.uniqueResult();
            model.addAttribute("prod", product);
            model.addAttribute("customerId", customerId);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }

        return "edit";
    }

    @RequestMapping(value = "/edit/{customerId}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Product product,@PathVariable int customerId, Model model) {
        try {

            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            session.update(product);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
        return "redirect:/homePage/" + customerId;
    }

    @RequestMapping(value = "/add/{customerId}", method = RequestMethod.GET)
    public String add(@PathVariable int customerId, Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("customerId", customerId);
        return "addProduct";
    }

    @RequestMapping(value = "/add/{customerId}", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("product") Product product,
                      BindingResult result, @PathVariable int customerId) {
        if (result.hasErrors()) {
            return "addProduct";
        }

        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            session.save(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }

        return "redirect:/homePage/" + customerId;
    }

}
