package com.b.simple.design.model.customer;

import java.math.BigDecimal;
import java.util.Objects;

public class AmountImpl implements Amount {

	BigDecimal value;
	Currency currency;

	public AmountImpl(BigDecimal value, Currency currency) {
		super();
		this.value = value;
		this.currency = currency;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AmountImpl amount = (AmountImpl) o;
		return Objects.equals(value, amount.value) && currency == amount.currency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, currency);
	}
}
