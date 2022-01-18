package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

import java.math.BigDecimal;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    public static final AmountImpl DEFAULT_AMOUNT = new AmountImpl(BigDecimal.ZERO, Currency.EURO);

    @Override
    public Amount getCustomerProductsSum(List<Product> products) throws DifferentCurrenciesException {
        if (products.isEmpty()) return DEFAULT_AMOUNT;

        if (anyCurrenciesAreDifferent(products)) {
            throw new DifferentCurrenciesException();
        }

        BigDecimal sum = products.stream()
                .map(product -> product.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AmountImpl(sum, products.get(0).getAmount().getCurrency());
    }

    private boolean anyCurrenciesAreDifferent(List<Product> products) {
        Currency firstProductCurrency = products.get(0).getAmount().getCurrency();
        return products.stream()
                .map(product -> product.getAmount().getCurrency())
                .anyMatch(currency -> !currency.equals(firstProductCurrency));
    }
}