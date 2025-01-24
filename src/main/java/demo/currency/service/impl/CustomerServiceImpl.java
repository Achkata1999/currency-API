package demo.currency.service.impl;

import demo.currency.model.entity.Customer;
import demo.currency.repository.CustomerRepository;
import demo.currency.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customersRepository;

    public CustomerServiceImpl(CustomerRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public Customer saveCustomer(String clientId) {

        Customer customer = new Customer();
        customer.setId(clientId);

        return customersRepository.save(customer);
    }
}
