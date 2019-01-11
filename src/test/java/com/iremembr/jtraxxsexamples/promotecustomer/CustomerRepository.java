package com.iremembr.jtraxxsexamples.promotecustomer;

import java.util.Optional;

interface CustomerRepository {

    Optional<Customer> getById(long id);

}
