package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageListQueryParam;
import com.silanis.esl.sdk.PackageQueryParam;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

/**
 * Example demonstrating how to retrieve the expiration date of a package in the transaction time zone,
 * both for a single package and for a page of packages.
 * <p>
 * By default the expiration date is returned in UTC. With the useTransactionTimezone query parameter, it is also
 * available with the offset of the package time zone through {@link DocumentPackage#getExpiryDateWithOffset()}.
 */
public class GetPackageInTransactionTimezoneExample extends SDKSample {

    // A time zone without daylight saving time, so its offset is always +03:30
    public static final String PACKAGE_TIMEZONE_ID = "Asia/Tehran";

    private DocumentPackage packageInUtc;
    private DocumentPackage packageInTransactionTimezone;
    private DocumentPackage listedPackageInTransactionTimezone;

    public static void main(String... args) {
        new GetPackageInTransactionTimezoneExample().run();
    }

    @Override
    public void execute() {
        Date createdFrom = now().minusMinutes(1).toDate();

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withTimezoneId(PACKAGE_TIMEZONE_ID)
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        // Default behaviour: the expiration date is returned in UTC
        packageInUtc = eslClient.getPackage(packageId);

        // The expiration date is returned in the transaction time zone
        Map<PackageQueryParam, Object> params = new EnumMap<>(PackageQueryParam.class);
        params.put(PackageQueryParam.USE_TRANSACTION_TIMEZONE, true);
        packageInTransactionTimezone = eslClient.getPackage(packageId, params);
        System.out.println("Expiry date in the transaction time zone: " + packageInTransactionTimezone.getExpiryDateWithOffset());

        // The same parameter applies to a page of packages
        Map<PackageListQueryParam, Object> listParams = new EnumMap<>(PackageListQueryParam.class);
        listParams.put(PackageListQueryParam.QUERY, PackageStatus.DRAFT);
        listParams.put(PackageListQueryParam.START_DATE, createdFrom);
        listParams.put(PackageListQueryParam.END_DATE, now().plusMinutes(1).toDate());
        listParams.put(PackageListQueryParam.USE_TRANSACTION_TIMEZONE, true);
        Page<DocumentPackage> resultPage = eslClient.getPackageService().getPackages(new PageRequest(1), listParams);
        for (DocumentPackage documentPackage : resultPage) {
            if (packageId.equals(documentPackage.getId())) {
                listedPackageInTransactionTimezone = documentPackage;
            }
        }
    }

    public DocumentPackage getPackageInUtc() {
        return packageInUtc;
    }

    public DocumentPackage getPackageInTransactionTimezone() {
        return packageInTransactionTimezone;
    }

    public DocumentPackage getListedPackageInTransactionTimezone() {
        return listedPackageInTransactionTimezone;
    }
}
