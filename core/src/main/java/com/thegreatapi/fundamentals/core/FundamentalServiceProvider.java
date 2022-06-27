package com.thegreatapi.fundamentals.core;

import com.thegreatapi.fundamentals.core.model.Country;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class FundamentalServiceProvider {

    FundamentalsService getFor(Country country) {
        if (country == Country.BRAZIL) {
            return new BrazilFundamentalsService();
        }
        throw new UnsupportedOperationException("Unsupported country: " + country);
    }
}
