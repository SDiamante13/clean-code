package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CustomerBOTest {

    private CustomerBO customerBO = new CustomerBOImpl();

    @Test
    void getCustomerProductsSum_returnsSumOfProducts_whenAllOfTheCurrenciesMatch() throws DifferentCurrenciesException {
        List<Product> products = createProducts(new AmountImpl[]{
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO)
        });

        Amount totalAmount = customerBO.getCustomerProductsSum(products);

        assertThat(totalAmount).isEqualTo(new AmountImpl(new BigDecimal("11.0"), Currency.EURO));
    }

    private List<Product> createProducts(Amount[] amounts) {
        List<Product> products = new ArrayList<>();

        for (Amount amount : amounts) {
            products.add(new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount));
        }

        return products;
    }

    @Test
    void getCustomerProductsSum_throwsDifferentCurrenciesException_whenTheProductsContainsDifferentCurrencies() {
        List<Product> products = createProducts(new AmountImpl[]{
                new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO),
        });

        assertThrows(DifferentCurrenciesException.class, () ->
                customerBO.getCustomerProductsSum(products));
    }

    @Test
    void getCustomerProductsSum_returnsDefaultValue_whenGivenAnEmptyListOfProducts() throws DifferentCurrenciesException {
        List<Product> products = new ArrayList<>();

        Amount totalAmount = customerBO.getCustomerProductsSum(products);

        assertThat(totalAmount).isEqualTo(new AmountImpl(BigDecimal.ZERO, Currency.EURO));
    }

}