package com.silanis.esl.sdk;

/**
 * Options applied when retrieving packages, either one package or a page of packages.
 *
 * @see com.silanis.esl.sdk.builder.PackageRetrievalOptionsBuilder
 */
public class PackageRetrievalOptions {

    private boolean useTransactionTimezone;

    public boolean isUseTransactionTimezone() {
        return useTransactionTimezone;
    }

    public void setUseTransactionTimezone(boolean useTransactionTimezone) {
        this.useTransactionTimezone = useTransactionTimezone;
    }
}
