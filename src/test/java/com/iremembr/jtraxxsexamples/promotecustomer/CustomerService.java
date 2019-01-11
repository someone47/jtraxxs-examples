package com.iremembr.jtraxxsexamples.promotecustomer;

import com.iremembr.jtraxxs.ValueResult;

class CustomerService {

    private final EmailGateway emailGateway;
    private final CustomerRepository customerRepository;

    CustomerService(EmailGateway emailGateway, CustomerRepository customerRepository) {
        this.emailGateway = emailGateway;
        this.customerRepository = customerRepository;
    }

    String promote(long id) {
        return ValueResult
                .fromOptional(customerRepository.getById(id), "Customer not found. Id = " + id)
                .ensure(Customer::canBePromoted, "The customer has the highest status possible")
                .onSuccess(Customer::promote)
                .ensure(customer -> emailGateway.sendPromotionNotification(customer.email()))
                .map(customer -> "Customer promoted successfully. Id = " + customer.id())
                .orElseGet(msg -> msg);
    }
}
