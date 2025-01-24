package demo.currency.service;

import demo.currency.model.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Customer saveCustomer(String clientId);
}
