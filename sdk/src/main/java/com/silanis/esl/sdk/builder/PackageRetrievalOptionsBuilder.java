package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageRetrievalOptions;

/**
 * Builder object used to customize how packages are retrieved.
 * for instance with option for use local time zone for expiry date
 */
public class PackageRetrievalOptionsBuilder {

    private boolean useTransactionTimezone = false;

    public static PackageRetrievalOptionsBuilder newPackageRetrievalOptions() {
        return new PackageRetrievalOptionsBuilder();
    }

    private PackageRetrievalOptionsBuilder() {
    }

    public PackageRetrievalOptionsBuilder withTransactionTimezone() {
        useTransactionTimezone = true;
        return this;
    }

    public PackageRetrievalOptions build() {
        PackageRetrievalOptions options = new PackageRetrievalOptions();
        options.setUseTransactionTimezone(useTransactionTimezone);
        return options;
    }
}
