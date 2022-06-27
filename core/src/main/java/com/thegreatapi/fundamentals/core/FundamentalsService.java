package com.thegreatapi.fundamentals.core;

import com.thegreatapi.fundamentals.core.model.Fundamentals;

public sealed interface FundamentalsService permits BrazilFundamentalsService {

    Fundamentals getFundamentals(String stock);
}
