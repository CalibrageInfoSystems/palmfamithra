package in.calibrage.palm360fa.service;

import com.google.gson.JsonObject;


import in.calibrage.palm360fa.Adapter.PaymentResponseModel;
import in.calibrage.palm360fa.Model.ActiveGodownsModel;
import in.calibrage.palm360fa.Model.AddFarmerTransportationResponse;
import in.calibrage.palm360fa.Model.CollectionResponceModel;
import in.calibrage.palm360fa.Model.Costconfigres;
import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.FarmerResponceModel;
import in.calibrage.palm360fa.Model.Farmersearchresponse;
import in.calibrage.palm360fa.Model.FertResponse;
import in.calibrage.palm360fa.Model.FertilizerSubCategories;
import in.calibrage.palm360fa.Model.GetAmount;
import in.calibrage.palm360fa.Model.GetBankDetailsByFarmerCode;
import in.calibrage.palm360fa.Model.GetCollectionInfoById;
import in.calibrage.palm360fa.Model.GetCompanyTransport;
import in.calibrage.palm360fa.Model.GetDistricts;
import in.calibrage.palm360fa.Model.GetDriverforTransportationTypes;
import in.calibrage.palm360fa.Model.GetDurationTypes;
import in.calibrage.palm360fa.Model.GetFarmerByIdResponse;
import in.calibrage.palm360fa.Model.GetHiringBasisTypes;
import in.calibrage.palm360fa.Model.GetIssueModel;
import in.calibrage.palm360fa.Model.GetLabourPackageDiscount;
import in.calibrage.palm360fa.Model.GetLabourTypes;
import in.calibrage.palm360fa.Model.GetMandals;
import in.calibrage.palm360fa.Model.GetPaymentModeTypes;
import in.calibrage.palm360fa.Model.GetPlotVillagesByFarmerCode;
import in.calibrage.palm360fa.Model.GetServicesByStateCode;
import in.calibrage.palm360fa.Model.GetSourceofTransportType;
import in.calibrage.palm360fa.Model.GetStates;
import in.calibrage.palm360fa.Model.GetTranspotationCharges;
import in.calibrage.palm360fa.Model.GetTypeofServices;
import in.calibrage.palm360fa.Model.GetTypeCdDmt;
import in.calibrage.palm360fa.Model.GetVillages;
import in.calibrage.palm360fa.Model.GetVisitRequestRepository;
import in.calibrage.palm360fa.Model.Getdestinations;
import in.calibrage.palm360fa.Model.GetquickpayDetailsModel;
import in.calibrage.palm360fa.Model.Getvisit;
import in.calibrage.palm360fa.Model.LabourDuration;
import in.calibrage.palm360fa.Model.LabourRecommendationsModel;
import in.calibrage.palm360fa.Model.LabourTermsNCondtionsModel;
import in.calibrage.palm360fa.Model.Labourservicetype;
import in.calibrage.palm360fa.Model.LoanResponse;
import in.calibrage.palm360fa.Model.LobourResponse;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.PaymentsType;
import in.calibrage.palm360fa.Model.QuickPayModel;
import in.calibrage.palm360fa.Model.QuickPayOTPres;
import in.calibrage.palm360fa.Model.QuickPayResponce;
import in.calibrage.palm360fa.Model.QuickpayBlockdateResponse;
import in.calibrage.palm360fa.Model.RaiseRequest;
import in.calibrage.palm360fa.Model.RecomPlotcodes;
import in.calibrage.palm360fa.Model.ResLoan;
import in.calibrage.palm360fa.Model.ResPole;
import in.calibrage.palm360fa.Model.ResQuickPayOTP;
import in.calibrage.palm360fa.Model.Resdelete;
import in.calibrage.palm360fa.Model.Resfert;
import in.calibrage.palm360fa.Model.RespHoliday;
import in.calibrage.palm360fa.Model.Resproduct;
import in.calibrage.palm360fa.Model.Resquickpay;
import in.calibrage.palm360fa.Model.SubsidyResponse;

import in.calibrage.palm360fa.Model.TransportResp;
import in.calibrage.palm360fa.Model.UserClusters;
import in.calibrage.palm360fa.Model.VendorTransportationResponse;

import in.calibrage.palm360fa.Model.VehicleTypeResponse;

import in.calibrage.palm360fa.Model.VisitresponseModel;
import in.calibrage.palm360fa.Model.labour_req_response;
import in.calibrage.palm360fa.Model.resGet3FInfo;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {


//    @GET
//    Observable<LerningsModel> getlernings(@Url String url);
//
//

    @POST(APIConstantURL.Collection)
    Observable<CollectionResponceModel> postcollection(@Body JsonObject data);

    @POST(APIConstantURL.Login)
    Observable<LoginResponse> getLoginPage(@Body JsonObject data);

//
    @GET
    Observable<RecomPlotcodes> getplots(@Url String url);

    @GET
    Observable<FarmerResponceModel> getFormerOTP(@Url String url);

    @GET
    Observable<VehicleTypeResponse> getvehicleTypes(@Url String url);

    @GET
    Observable<GetDriverforTransportationTypes> getdriverforTransportationTypes(@Url String url);

    @GET
    Observable<GetDurationTypes> getDurationTypes(@Url String url);

    @GET
    Observable<GetSourceofTransportType> getsourceofTransportTypes(@Url String url);

    @GET
    Observable<GetHiringBasisTypes> gethiringbasisTypes(@Url String url);

    @GET
    Observable<GetLabourTypes> getLabourTypes(@Url String url);

    @GET
    Observable<GetCompanyTransport> getcompanyTransport(@Url String url);

    @GET
    Observable<GetPaymentModeTypes> getpaymentModeTypes(@Url String url);

    @GET
    Observable<GetTypeofServices> getservivceeeTypes(@Url String url);

    @GET
    Observable<FarmerOtpResponceModel> getFormerdetails(@Url String url);
    @GET
    Observable<ResQuickPayOTP> getquickpayotp(@Url String url);

    @GET
    Observable<GetCollectionInfoById> getinfo(@Url String url);

    //
//    @GET
//    Observable<CropResponseModel> getCropmaintaindetails(@Url String url);
//
//
    @POST(APIConstantURL.payment_history)
    Observable<PaymentResponseModel> postpayment(@Body JsonObject data);

    @POST(APIConstantURL.transport_history)
    Observable<GetTranspotationCharges> posttrans(@Body JsonObject data);
    @GET
    Observable<Costconfigres> getcostconfi(@Url String url);

    @POST(APIConstantURL.GetRequestHeaderDetails)
    Observable<Getvisit> GetRequestheadervistDetails(@Body JsonObject data);

    @POST(APIConstantURL.getFarmerById)
    Observable<GetFarmerByIdResponse> getFarmerByIdpage(@Body JsonObject data);

    @GET
    Observable<resGet3FInfo> get3finfo(@Url String url);

    @GET
    Observable<GetVisitRequestRepository> getimages(@Url String url);
//
//
//    @GET
//    Observable<GetEncyclopediaDetails> getEncyclopediaDetails(@Url String url);
//
//    @GET
//    Observable<SpinnerModel> getSpinnerDetails(@Url String url);
//
//    @GET
//    Observable<Stand_recom_model> GetRecommendationdetails(@Url String url);
//
//
    @GET
    Observable<LabourRecommendationsModel> getrecommdetails(@Url String url);
//
    @GET
    Observable<LabourDuration> getLabourduration(@Url String url);

    @GET
    Observable<Labourservicetype> getLabourService(@Url String url);

//
    @POST(APIConstantURL.post_labour)
    Observable<LobourResponse> postLabour(@Body JsonObject data);

    @POST(APIConstantURL.Post_Loan)
    Observable<LoanResponse> postLoan(@Body JsonObject data);

    @POST(APIConstantURL.labour_amount)
    Observable<GetAmount> postservice_amount(@Body JsonObject data);

    @POST(APIConstantURL.post_export)
    Observable<String> postexport(@Body JsonObject data);
    @GET
    Observable<GetBankDetailsByFarmerCode> getbankdetails(@Url String url);


    @GET
    Observable<GetLabourPackageDiscount> getdiscount(@Url String url);


    //
//
    @GET
    Observable<QuickPayModel> getquick(@Url String url);

//
    @GET
    Observable<LabourTermsNCondtionsModel> getterms(@Url String url);
//
    @GET
    Observable<GetquickpayDetailsModel> getquickpaydetails(@Url String url);

//
    @GET
    Observable<LabourRecommendationsModel> getvisitplotdetails(@Url String url);

//
    @GET
    Observable<GetIssueModel> getIssuestypes(@Url String url);

    @POST(APIConstantURL.post_quickpay)
    Observable<QuickPayResponce> postquickpay(@Body JsonObject data);
//
//
    @POST(APIConstantURL.post_visit)
    Observable<VisitresponseModel> postvisit(@Body JsonObject data);

    @GET
    Observable<ActiveGodownsModel> getActiveGodowns(@Url String url);

    @GET
    Observable<PaymentsType> getpaymentModes(@Url String url);

//    @GET
//    Observable<BannerresponseModel> getbannerdetails(@Url String url);
//

    @POST(APIConstantURL.Post_fert)
    Observable<FertResponse> postfert(@Body JsonObject data);
//
//
    @GET
    Observable<SubsidyResponse> getsubsidy(@Url String url);
//
//
    @POST(APIConstantURL.labour_response)
    Observable<labour_req_response> postLabour_request(@Body JsonObject data);
//
//
    @POST(APIConstantURL.GetPoleRequestDetails)
    Observable<ResPole> GetPoleRequestDetails(@Body JsonObject data);


    @POST(APIConstantURL.GetFertilizerDetails)
    Observable<Resfert> GetfertRequestDetails(@Body JsonObject data);


    @POST(APIConstantURL.GetRequestHeaderDetails)
    Observable<Resquickpay> GetRequestheaderDetails(@Body JsonObject data);

    @POST(APIConstantURL.GetRequestHeaderDetails)
    Observable<ResLoan> GetRequestheaderLoanDetails(@Body JsonObject data);
//
//    @GET
//    Observable<Resbasicinfo> getbasicinfo(@Url String url);
//
//    @GET
//    Observable<resGet3FInfo> get3finfo(@Url String url);
//
//    @GET
//    Observable<res_plotdetails> getplotinfo(@Url String url);
//
    @POST(APIConstantURL.delete)
    Observable<Resdelete> postdelete(@Body JsonObject data);
//
    @GET
    Observable<Resproduct> getLoan(@Url String url);

    @POST(APIConstantURL.GetQuickpayDetails)
    Observable<GetquickpayDetailsModel> post_details(@Body JsonObject data);

    @POST(APIConstantURL.AddQuickPayOTP)
    Observable<QuickPayOTPres> post_quickpayotp(@Body JsonObject data);


    @POST(APIConstantURL.HolidayList)
    Observable<RespHoliday> postholiday(@Body JsonObject data);
    @GET
    Observable<RaiseRequest> getRaiseRequest(@Url String url);

    @POST(APIConstantURL.IsQuickPayBlockDate)
    Observable<QuickpayBlockdateResponse> quickpayBlockdate(@Body JsonObject data);
//
//    @GET
//    Observable<IsQuickPayBlockDate> getblockdate(@Url String url);

    @GET
    Observable<GetServicesByStateCode>getservices(@Url String url);


    @GET
    Observable<GetMandals> getmandals(@Url String url);

    @GET
    Observable<GetVillages> getvillages(@Url String url);
    @GET
    Observable<GetStates> getstates(@Url String url);
    @GET
    Observable<GetDistricts> getdistricts(@Url String url);

    @GET
    Observable<GetTypeCdDmt> gettypecddmtdata(@Url String url);
    @POST(APIConstantURL.GetFarmersById)
    Observable<Farmersearchresponse> farmersearch(@Body JsonObject data);

    @POST(APIConstantURL.addFarmerTransportation)
    Observable<AddFarmerTransportationResponse> addFarmerTransportationpage(@Body JsonObject data);

    @POST(APIConstantURL.AddVendorTransportation)

    Observable<VendorTransportationResponse>VendorTransportation(@Body JsonObject data);

    @GET
    Observable<GetPlotVillagesByFarmerCode> getvillagesbyfarmercode(@Url String url);

    @GET
    Observable<Getdestinations> getcc(@Url String url);
    @GET
    Observable<UserClusters> getclusters(@Url String url);

    @POST(APIConstantURL.AddTransportRequest)
    Observable<TransportResp> postTransportRequest(@Body JsonObject data);

    @GET
    Observable<FertilizerSubCategories> getfertilizercategory(@Url String url);
}
