package com.iremembr.jtraxxsexamples.promotecustomer;

import com.iremembr.jtraxxs.VoidResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EmailGateway emailGateway;

    @Test
    void success() {
        // Given
        when(customerRepository.getById(4711))
                .thenReturn(Optional.of(new Customer(4711, "joe@doe.com", true)));
        when(emailGateway.sendPromotionNotification("joe@doe.com"))
                .thenReturn(VoidResult.ok());

        // When
        String result = customerService.promote(4711);

        // Then
        assertThat(result).isEqualTo("Customer promoted successfully. Id = 4711");
    }

    @Test
    void customerNotFound() {
        // Given
        when(customerRepository.getById(4711))
                .thenReturn(Optional.empty());

        // When
        String result = customerService.promote(4711);

        // Then
        assertThat(result).isEqualTo("Customer not found. Id = 4711");
        verify(emailGateway, never()).sendPromotionNotification(anyString());
    }

    @Test
    void emailSendingFailed() {
        // Given
        when(customerRepository.getById(4711))
                .thenReturn(Optional.of(new Customer(4711, "joe@doe.com", true)));
        when(emailGateway.sendPromotionNotification("joe@doe.com"))
                .thenReturn(VoidResult.fail("Email service down"));

        // When
        String result = customerService.promote(4711);

        // Then
        assertThat(result).isEqualTo("Email service down");
    }

    @Test
    void customerCanNotBePromoted() {
        // Given
        when(customerRepository.getById(4711))
                .thenReturn(Optional.of(new Customer(4711, "joe@doe.com", false)));

        // When
        String result = customerService.promote(4711);

        // Then
        assertThat(result).isEqualTo("The customer has the highest status possible");
        verify(emailGateway, never()).sendPromotionNotification(anyString());
    }
}
