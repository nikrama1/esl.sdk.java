package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageListOptions;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Visibility;

import java.util.Date;

/**
 * Builds the options sent when retrieving a page of packages or templates.
 */
public class PackageListOptionsBuilder {

    private boolean templates;
    private PackageStatus status;
    private Visibility visibility;
    private Date startDate;
    private Date endDate;
    private Boolean useTransactionTimezone;

    /**
     * Creates a new package list options builder.
     *
     * @return This
     */
    public static PackageListOptionsBuilder newPackageListOptions() {
        return new PackageListOptionsBuilder();
    }

    private PackageListOptionsBuilder() {
    }

    /**
     * Lists templates instead of packages.
     *
     * @return This
     */
    public PackageListOptionsBuilder forTemplates() {
        templates = true;
        return this;
    }

    /**
     * Lists only the packages with this status. Ignored when listing templates.
     * <p>
     * {@link PackageStatus#DRAFT} also returns declined, expired and opted out packages.
     *
     * @return This
     */
    public PackageListOptionsBuilder withStatus(PackageStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Only applies when listing templates. {@link Visibility#ACCOUNT} also returns the templates shared with the
     * account; {@link Visibility#SENDER} returns only the owner's templates.
     *
     * @return This
     */
    public PackageListOptionsBuilder withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    /**
     * Lists only the packages whose update date is within the range. Both dates are included and either may be null
     * for an open range.
     *
     * @return This
     */
    public PackageListOptionsBuilder withDateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    /**
     * Returns the expiration dates in the transaction time zone, see
     * {@link com.silanis.esl.sdk.DocumentPackage#getExpiryDateWithOffset()}.
     *
     * @return This
     */
    public PackageListOptionsBuilder withTransactionTimezone() {
        useTransactionTimezone = true;
        return this;
    }

    public PackageListOptions build() {
        PackageListOptions options = new PackageListOptions();
        options.setTemplates(templates);
        options.setStatus(status);
        options.setVisibility(visibility);
        options.setStartDate(startDate);
        options.setEndDate(endDate);
        options.setUseTransactionTimezone(useTransactionTimezone);
        return options;
    }
}
