package com.silanis.esl.sdk;

/**
 * Query parameters that can be sent when retrieving a page of packages or templates.
 * <p>
 * The page bounds come from the {@link PageRequest}. To retrieve only some package fields, use
 * {@link com.silanis.esl.sdk.service.PackageService#getPackagesFields(PackageStatus, PageRequest, java.util.Set)}.
 *
 * @see com.silanis.esl.sdk.service.PackageService#getPackages(PageRequest, java.util.Map)
 */
public enum PackageListQueryParam {
    /** A {@link PackageStatus}, or its API value, e.g. "SENT". */
    QUERY("query"),
    /** "package" or "template". */
    TYPE("type"),
    SEARCH("search"),
    SEARCH_TYPE("searchtype"),
    PREDEFINED("predefined"),
    /** A {@link Visibility}. */
    VISIBILITY("visibility"),
    /** A {@link java.util.Date}, or a date string. */
    START_DATE("startDate"),
    /** A {@link java.util.Date}, or a date string. */
    END_DATE("endDate"),
    /** A {@link java.util.Date}, or a date string. */
    LAST_UPDATED_START_DATE("lastUpdatedStartDate"),
    /** A {@link java.util.Date}, or a date string. */
    LAST_UPDATED_END_DATE("lastUpdatedEndDate"),
    DATE_FILTER_TYPE("dateFilterType"),
    SORT("sort"),
    /** "asc" or "desc". */
    DIR("dir"),
    OWNER_USER_ID("ownerUserId"),
    OWNER_EMAIL("ownerEmail"),
    /** When true, expiration dates are returned in the transaction time zone, see {@link DocumentPackage#getExpiryDateWithOffset()}. */
    USE_TRANSACTION_TIMEZONE("useTransactionTimezone");

    private final String value;

    PackageListQueryParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
