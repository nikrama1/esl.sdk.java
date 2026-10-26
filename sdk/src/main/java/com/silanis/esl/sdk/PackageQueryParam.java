package com.silanis.esl.sdk;

/**
 * Query parameters that can be sent when retrieving a single package.
 *
 * @see com.silanis.esl.sdk.EslClient#getPackage(PackageId, java.util.Map)
 */
public enum PackageQueryParam {
    RAW_SIGNATURE_CAPTURE("rawSignatureCapture"),
    /** A collection of {@link DocumentPackageRequestExtension}, or their values separated by commas. */
    EXTENSIONS("extensions"),
    /** When true, the expiration date is returned in the transaction time zone, see {@link DocumentPackage#getExpiryDateWithOffset()}. */
    USE_TRANSACTION_TIMEZONE("useTransactionTimezone");

    private final String value;

    PackageQueryParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
