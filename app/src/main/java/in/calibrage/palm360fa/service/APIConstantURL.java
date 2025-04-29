package in.calibrage.palm360fa.service;

public interface APIConstantURL {

//public static  final  String LOCAL_URL="https://3fakshaya.com/api/"; //16th july live
//public static  final  String LOCAL_URL="http://103.241.144.240:9098/api/";
//public static  final  String LOCAL_URL="http://103.241.144.240:9096/api/";

public static  final String LOCAL_URL="http://182.18.157.215/3FAkshaya/API/api/";//test local

    //String LookUpCategory = "GetActiveLookUp/9";
    String Login = "User/Login";
    String banner ="Banner";
    String Collection ="Collection";
    String Recommede_plots ="Farmer/GetActivePlotsByFarmerCode/";
    String Farmer_ID_CHECK ="farmer/SendOTP/";
    String Farmer_otp ="Farmer/";
    String quickpay_otp ="QuickPayRequest/IsQuickPayValid/";
    String payment_history ="Payment/GetVendorLedger";
    String transport_history ="Payment/GetTranspotationChargesByFarmerCode";
    String GetEncyclopediaDetails = "Encyclopedia/GetEncyclopediaDetails/";
    String GetRecommendationAges = "GetRecommendationAges";
    String GetActivePlotsByFarmerCode = "Farmer/GetActivePlotsByFarmerCode/";
    String GetLabourDuration = "TypeCdDmt/7";
    String GetLabourServicetype = "Farmer/GetServicesByPlotCode/";
    String CostConfig ="CostConfig/";
    String post_labour = "LabourRequest/AddLabourRequestHeader";
    String Post_Loan = " RequestHeader/AddRequestHeader";
    String labour_amount = "LabourServiceCost/GetLabourServiceCostCalculation";
    String GetUnPayedCollectionsByFarmerCode ="Farmer/GetUnPayedCollectionsByFarmerCode/";
    String GetLabourTermsandConditions ="LabourServiceCost/GetLabourServiceCost/null";
    String GetQuickpayDetails ="QuickPayRequest/GetQuickpayDetailsByFarmerCode";
   String AddQuickPayOTP ="QuickPayRequest/AddQuickPayOTP";
    String GetIssue = "TypeCdDmt/10";
    String post_quickpay = "QuickPayRequest/AddQuickpayRequest";
    String post_visit = "RequestHeader/AddVisitRequest";
    String GetActiveGodowns = "Godown/GetActiveGodowns";
    String GetPaymentsTypeByFarmerCode = "Farmer/GetPaymentsTypeByFarmerCode/";
    String GetBannerByStateCode = "Banner/GetBannerByStateCode/";
    String Post_fert = "FertilizerRequest";
    String Getfarmersubsidy= "FertilizerSubsidies/";
    String labour_response = "GetLabourRequestDetails";
    String GetPoleRequestDetails = "FertilizerRequest/GetPoleRequestDetails";
    String GetFertilizerDetails = "GetFertilizerDetails";
    String GetRequestHeaderDetails = "RequestHeader/GetRequestHeaderDetails";
    String GetContactInfo = "ContactInfo/GetContactInfo/";
    String Get3FInfo = "Farmer/Get3FInfo/";
    String GetPlotDetailsByFarmerCode = "Farmer/GetPlotDetailsByFarmerCode/";
    String GetProductDetailsByRequestCode = "GetProductDetailsByRequestCode/";
    String delete  = "AppOrDecFertilizerRequest";
    String CollectionInfoById ="Collection/CollectionInfoById/";
    String GetVisitRequestRepository ="RequestHeader/GetVisitRequestRepository/";
    String post_export ="Payment/ExportPayments";
    String GetBankDetailsByFarmerCode ="Farmer/GetBankDetailsByFarmerCode/";
    String GetLabourPackageDiscount ="LabourPackageDiscount/GetLabourPackageDiscount";
    String AddAppInstallation = "AppInstall/AddAppInstallation";
    String CanRaiseRequest ="RequestHeader/CanRaiseRequest/";
 String IsQuickPayBlockDate ="QuickPayBlockDate/IsQuickPayBlockDate";
    String HolidayList ="HolidayList/IsHoliday";
 String GetServicesByStateCode ="StateService/GetServicesByStateCode/";

 String GetStatesByCountryId ="FarmerRegistration/GetStatesByCountryId/1";
    String GetDistrictsByStateId ="FarmerRegistration/GetDistrictsByStateId/";

    //String Getvillages ="http://183.82.111.111/EasyFarm/API/api/Village/GetVillages/";
    String vehicleType = "TypeCdDmt/12";
    String driverforTransportationType = "TypeCdDmt/13";
    String durationType = "TypeCdDmt/26";
    String sourceofTransportType = "TypeCdDmt/15";
    String hiringbasis = "TypeCdDmt/16";
    String labourType = "TypeCdDmt/17";
    String paymentMode = "TypeCdDmt/18";
    String typeofServices = "TypeCdDmt/23";
    String typeofServicesfarmer = "TypeCdDmt/22";
    String companyTrnasport = "TypeCdDmt/21";

    String addFarmerTransportation = "TransportationService/AddFarmerTransportation";

    String getFarmerById = "Farmer/GetFarmersById";

    String Getmandals ="FarmerRegistration/GetGodavariMandalsByDistrictId/";

    String Getvillages ="FarmerRegistration/GetVillagesByMandalId/";

    String GetTypeCDdmt ="TypeCdDmt/";

    String GetFarmersById = "Farmer/GetFarmersById";
    String TransportationService = "TransportationService/GetPlotVillagesByFarmerCode/";
    String AddVendorTransportation = "TransportationService/AddVendorTransportation";
//   http://183.82.111.111/3FFarmerAPI/api/LabourRequest/GetLabourTermsandConditions/null

    String GetTransporttype= "TypeCdDmt/29";
    String GetClusters = "User/GetClustersByUserId/";
    String AddTransportRequest = "AddTransportRequest";
    String GetFertilizerSubCategories = "Categories/GetCategoriesByParentCategory/1";
}
