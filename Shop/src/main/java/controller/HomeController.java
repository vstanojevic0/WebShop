package controller;

import entities.Customer;
import entities.CustomerLogIn;
import entities.HibernateUtil;
import entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    Session session;
    Transaction tx = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("customerli", new CustomerLogIn());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute CustomerLogIn customerli, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("customerli", customerli);
            return "index";
        }

        try {

            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();
            String q = "from Customer where email=:email and password=:passw";
            Query query = session.createQuery(q);

            query.setParameter("email", customerli.getEmail());
            query.setParameter("passw", customerli.getPassword());

            Customer c = (Customer) query.uniqueResult();

            tx.commit();
            if (c != null) {
                return "redirect:/homePage/" + c.getId();
            } else {
                return "redirect:/";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            HibernateUtil.close();
        }

        return "index";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg(Model model) {
        model.addAttribute("customer", new Customer());
        return "reg";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String reg(@Valid @ModelAttribute("customer") Customer customer,
                      BindingResult result) {
        if (result.hasErrors()) {
            return "reg";
        }
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            session.save(customer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/homePage/{customerId}", method = RequestMethod.GET)
    public String firstPage(@PathVariable int customerId, Model model) {
        model.addAttribute("product", new Product());
        session = HibernateUtil.createSessionFactory().openSession();
        tx = session.beginTransaction();
        List<Product> list = new ArrayList<>();

        try {

            String hql = "from Product";
            Query query = session.createQuery(hql);
            list = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }
        model.addAttribute("customerId", customerId);
        model.addAttribute("productList", list);
        return "homePage";
    }

}
