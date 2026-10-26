package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.PackageListQueryParam;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by mina on 29/12/14.
 */
public class GetCompletedPackagesWithinDateRangeExample extends SDKSample {

    public static final Date START_DATE = new DateTime(DateTimeZone.UTC).toDate();
    public static final Date END_DATE = new DateTime(DateTimeZone.UTC).toDate();

    public Page<DocumentPackage> draftPackages;
    public Page<DocumentPackage> sentPackages;
    public Page<DocumentPackage> declinedPackages;
    public Page<DocumentPackage> archivedPackages;
    public Page<DocumentPackage> completedPackages;

    public static void main( String... args ) {
        new GetCompletedPackagesWithinDateRangeExample().run();
    }

    @Override
    public void execute() {

        draftPackages = getPackagesByPackageStatus(PackageStatus.DRAFT, START_DATE, END_DATE);
        sentPackages = getPackagesByPackageStatus(PackageStatus.SENT, START_DATE, END_DATE);
        declinedPackages = getPackagesByPackageStatus(PackageStatus.DECLINED, START_DATE, END_DATE);
        archivedPackages = getPackagesByPackageStatus(PackageStatus.ARCHIVED, START_DATE, END_DATE);
        completedPackages = getPackagesByPackageStatus(PackageStatus.COMPLETED, START_DATE, END_DATE);

        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.DRAFT, draftPackages.getNumberOfElements());
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.SENT, sentPackages.getNumberOfElements());
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.DECLINED, declinedPackages.getNumberOfElements());
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.ARCHIVED, archivedPackages.getNumberOfElements());
        System.out.format("PackageStatus : %s, The number of pakcages : %d%n", PackageStatus.COMPLETED, completedPackages.getNumberOfElements());
    }

    private Page<DocumentPackage> getPackagesByPackageStatus(PackageStatus packageStatus, Date startDate, Date endDate) {
        Map<PackageListQueryParam, Object> params = new EnumMap<>(PackageListQueryParam.class);
        params.put(PackageListQueryParam.QUERY, packageStatus);
        params.put(PackageListQueryParam.START_DATE, startDate);
        params.put(PackageListQueryParam.END_DATE, endDate);
        Page<DocumentPackage> resultPage = eslClient.getPackageService().getPackages(new PageRequest(1), params);
        return resultPage;
    }
}
