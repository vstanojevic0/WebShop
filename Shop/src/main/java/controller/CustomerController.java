package controller;

import entities.Customer;
import entities.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    Session session;
    Transaction tx = null;

    @RequestMapping(value = "/profile/{customerId}", method = RequestMethod.POST)
    public String find (@PathVariable int customerId, Model model) {
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            String q = "from Customer where id=:id";
            Query query = session.createQuery(q);

            query.setParameter("id", customerId);

            Customer customer = (Customer) query.uniqueResult();
            model.addAttribute("customer", customer);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            HibernateUtil.close();
        }
        return "profile";
    }

    @RequestMapping(value = "/editCustomer", method = RequestMethod.POST)
    public String edit(@ModelAttribute Customer customer, Model model) {
        try {

            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            session.update(customer);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
        return "redirect:/homePage/" + customer.getId();
    }
    @RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.POST)
    public String deleteCustomer(@PathVariable int id, Model model) {
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            tx = session.beginTransaction();

            String q = "delete from Customer where id=:id";
            Query query = session.createQuery(q);

            query.setParameter("id", id);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
        return "redirect:/";
    }
}
