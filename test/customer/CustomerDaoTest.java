package customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by G.Battaglino on 05/07/2017.
 */
public class CustomerDaoTest {

    @Test
    public void createCustomer() throws Exception {
        Customer c = new Customer("08810882", "Giovanni", "12341234");
        CustomerDao dao = new CustomerDao();

        if(dao.createCustomer(c)) {
            System.out.println("Customer created with success.");
        } else {
            fail("Failed to create a Customer.");
        }
    }

    @Test
    public void listAllCustomers() throws Exception {
        CustomerDao dao = new CustomerDao();

        for(Customer c: dao.listAllCustomers()){
            System.out.println("Customer name: " + c.getCustomerName());
        }
    }

    @Test
    public void updateCustomer() throws Exception {
        Customer c = new Customer("11112222", "Andrea", "98988989");
        CustomerDao dao = new CustomerDao();

        if (dao.createCustomer(c)){
            System.out.println("Customer updated with success.");
        }else{
            System.out.println("Failed to updated a customer.");
        }


    }

    @Test
    public void deleteCustomer() throws Exception {
        Customer c = new Customer();
        CustomerDao dao = new CustomerDao();

        if (dao.deleteCustomer(c)){
            System.out.println("Customer deleted with success.");
        }else{
            fail("Failed to delete a customer.");
        }
    }

}