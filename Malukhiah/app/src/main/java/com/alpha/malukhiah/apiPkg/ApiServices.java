package com.alpha.malukhiah.apiPkg;


import com.alpha.malukhiah.model.AddsPostPkgModel.AddsPost;
import com.alpha.malukhiah.model.BlockUserPkg.BlockData;
import com.alpha.malukhiah.model.CategoryProductPkgModel.CategoryProductPozo;
import com.alpha.malukhiah.model.ContactUsPkg.ContactPozo;
import com.alpha.malukhiah.model.FollowerPkg.FollowerList;
import com.alpha.malukhiah.model.FollowerPkg.Followuser;
import com.alpha.malukhiah.model.FollowingPkg.FollowingList;
import com.alpha.malukhiah.model.GetProfilePkg.GetProfileData;
import com.alpha.malukhiah.model.MyAdvListPkg.MyAdsList;
import com.alpha.malukhiah.model.MyFavAdvListPkg.MyFavAdsList;
import com.alpha.malukhiah.model.MyRecentViewPkg.MyRecentViewAdsList;
import com.alpha.malukhiah.model.MypurchasePkg.MyPurchaseAdsList;
import com.alpha.malukhiah.model.NotificaitonCountPkg.NotificationClear;
import com.alpha.malukhiah.model.NotificaitonCountPkg.NotificationCount;
import com.alpha.malukhiah.model.NotificationListPkg.GetNotificationListModel;
import com.alpha.malukhiah.model.RecentViewAddPkg.RecentViewAdd;
import com.alpha.malukhiah.model.aboutUs.AboutUsPozo;
import com.alpha.malukhiah.model.bannerPozoPkg.BannerListPozo;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
import com.alpha.malukhiah.model.forgetPasspkg.ForgetPassPozo;
import com.alpha.malukhiah.model.loginPkg.LoginPozo;
import com.alpha.malukhiah.model.memberShipPkg.MembershipPozo;
import com.alpha.malukhiah.model.profilePozoPkg.ProfilePozo;
import com.alpha.malukhiah.model.signupPkg.SignUpPozo;
import com.alpha.malukhiah.model.socialLoginPkgModel.SocialLoginModel;
import com.alpha.malukhiah.model.subChildCategoryPozoPkg.SubChildCategoryPozo;
import com.alpha.malukhiah.model.subcategoryPozoPkg.SubCategoryPozo;
import com.alpha.malukhiah.model.termsAndConditionPkg.TermsAndConditionPozo;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishListResponseModle;
import com.alpha.malukhiah.model.wishilistModlePkg.removeWishListPkg.DeleteWishListResponseModle;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {
    @FormUrlEncoded
    @POST("Authentication/login")
    Call<LoginPozo> login(@Field("email") String email,
                          @Field("password") String password,
                          @Field("device_token") String device_token
    );

    @FormUrlEncoded
    @POST("Authentication/registration")
    Call<SignUpPozo> registration(@Field("user_fullname") String user_fullname,
                                  @Field("user_phone") String user_phone,
                                  @Field("user_email") String user_email,
                                  @Field("user_country") String user_country,
                                  @Field("user_password") String user_password,
                                  @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("Authentication/SocialLogin")
    Call<SocialLoginModel> socialLogin(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("social") String social,
                                       @Field("facebookID") String facebookID,
                                       @Field("instaID") String instaID,
                                       @Field("gmailId") String gmailId,
                                       @Field("device_token") String device_token);
    @FormUrlEncoded
    @POST("Authentication/ResetPassword")
    Call<ForgetPassPozo> forget(@Field("email") String email);

    @Multipart
    @POST("User_Dashboard/updateUserProfile")
    Call<ProfilePozo> editProfile(@Part MultipartBody.Part user_id,
                                  @Part MultipartBody.Part name,
                                  @Part MultipartBody.Part email,
                                  @Part MultipartBody.Part number,
                                  @Part MultipartBody.Part gender,
                                  @Part MultipartBody.Part file);


    @GET("Products/Category")
    Call<CategoryPozo> getCategory();

    @FormUrlEncoded
    @POST("Products/getSubategory")
    Call<SubCategoryPozo> getSubCategory(@Field("cat_id") String email);
    @FormUrlEncoded
    @POST("Products/myAds")
    Call<MyAdsList> getMyAdvList(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("User_Dashboard/followingList")
    Call<FollowingList> getFollowingList(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("User_Dashboard/followerList")
    Call<FollowerList> getFollowerList(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("Products/myFavAds")
    Call<MyFavAdsList> getMyFavAdvList(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("Products/myPurchaseAds")
    Call<MyPurchaseAdsList> getMyPurchaseAdvList(@Field("user_id") String email);


    @FormUrlEncoded
    @POST("Products/childSubcat")
    Call<SubChildCategoryPozo> getSubChildCategory(@Field("subcat_id") String email);

    @FormUrlEncoded
    @POST("User_Dashboard/blockedUser")
    Call<BlockData> getBlockList(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("Products/ProductByCategory")
    Call<CategoryProductPozo> getProductByCategory(@Field("child_subcat_id") String email,
                                                   @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("User_Dashboard/recentAddViewList")
    Call<MyRecentViewAdsList> getRecentViewAdv(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("Payment/GetNotificationList")
    Call<GetNotificationListModel> notificaitonList(@Field("user_id") String str);

    @FormUrlEncoded
    @POST("Products/childSubcat")
    Call<SubChildCategoryPozo> PostName(@Field("subcat_id") String email);


    @GET("Products/Banner")
    Call<BannerListPozo> getBanner();

    @Multipart
    @POST("Products/AddsPost")
    Call<AddsPost> AddsPost(@Part MultipartBody.Part user_id,
                           @Part MultipartBody.Part title,
                           @Part MultipartBody.Part cat_id,
                           @Part MultipartBody.Part price,
                           @Part MultipartBody.Part description,
                           @Part MultipartBody.Part location,
                           @Part MultipartBody.Part latitude,
                           @Part MultipartBody.Part longnitue,
                           @Part MultipartBody.Part sub_cat_id,
                           @Part MultipartBody.Part child_subcat_id,
                           @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("User_Dashboard/getUserProfile")
    Call<GetProfileData> getProfileUser(@Field("user_id") String email);

    @GET("Users/TermsCondition")
    Call<TermsAndConditionPozo> termsCondition();


    @GET("Users/AboutUs")
    Call<AboutUsPozo> aboutUs();

    @GET("Users/PrivacyPolicy")
    Call<AboutUsPozo> privacyPolicy();


    @GET("Authentication/memberShipList")
    Call<MembershipPozo> getMembership();


    @FormUrlEncoded
    @POST("Authentication/contactForm")
    Call<ContactPozo> contactForm(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("message") String message
    );



    @FormUrlEncoded
    @POST("Products/addFavPost")
    Call<AddWishListResponseModle> addWishlist(@Field("user_id") String user_id,
                                               @Field("post_id") String product_id);

    @FormUrlEncoded
    @POST("User_Dashboard/followUser")
    Call<Followuser> addFollowlist(@Field("from_id") String user_id,
                                   @Field("to_id") String product_id);
    @FormUrlEncoded
    @POST("User_Dashboard/followUser")
    Call<Followuser> addFollowUnfOllowList(@Field("from_id") String user_id,
                                   @Field("to_id") String product_id);

    @FormUrlEncoded
    @POST("User_Dashboard/recentViewAdds")
    Call<RecentViewAdd> RecentViewAdd(@Field("user_id") String user_id,
                                      @Field("post_id") String product_id);

    @FormUrlEncoded
    @POST("Products/removeFavPost")
    Call<DeleteWishListResponseModle> deleteWishlists(@Field("user_id") String user_id,
                                                      @Field("post_id") String product_id);

    @FormUrlEncoded
    @POST("Payment/CountNotification")
    Call<NotificationCount> notificaoticout(@Field("user_id") String str);
    @FormUrlEncoded
    @POST("Payment/clearNotification")
    Call<NotificationClear> clearNotification(@Field("user_id") String user_id);

   /* @FormUrlEncoded
    @POST("Products/AddReviews")
    Call<RatingModle> addReview(@Field("product_id") String product_id,
                                @Field("user_id") String user_id,
                                @Field("rating") String rating,
                                @Field("comments") String comments);


    @FormUrlEncoded
    @POST("Products/removeReview")
    Call<RemoveWishListDataModle> removeReview(@Field("product_id") String product_id,
                                               @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("SocialLogin/Login")
    Call<SocialLogin> sociallogin(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("social") String social);


    @FormUrlEncoded
    @POST("Products/feeList")
    Call<DeliveryChargePozo> deliveryCharge(@Field("amount") String amount);
    //amount:500

    @GET("Products/couponList")
    Call<CouponListPozo> couponListing();

    @FormUrlEncoded
    @POST("User_Dashboard/saveAddress")
    Call<AddAddressDataModle> saveAddress(
            @Field("user_name") String user_name,
            @Field("mobile_no") String mobile_no,
            @Field("address1") String address1,
            @Field("address2") String address2,
            @Field("area") String area,
            @Field("city") String city,
            @Field("state") String state,
            @Field("pincode") String pincode,
            @Field("user_id") String user_id,
            @Field("HouseNo") String HouseNo,
            @Field("Locality") String Locality,
            @Field("DeliveryLocation") String DeliveryLocation);

    @FormUrlEncoded
    @POST("User_Dashboard/updateAddress")
    Call<UpdatedAddressDataModle> updateAddress(
            @Field("user_name") String user_name,
            @Field("mobile_no") String mobile_no,
            @Field("address1") String address1,
            @Field("address2") String address2,
            @Field("area") String area,
            @Field("city") String city,
            @Field("state") String state,
            @Field("pincode") String pincode,
            @Field("user_id") String user_id,
            @Field("address_id") String id,
            @Field("HouseNo") String HouseNo,
            @Field("Locality") String Locality,
            @Field("DeliveryLocation") String DeliveryLocation);

    @FormUrlEncoded
    @POST("Authentication/registration")
    Call<RegistrationModle> signup(@Field("user_fullname") String user_fullname,
                                   @Field("user_email") String user_email,
                                   @Field("user_phone") String user_phone,
                                   @Field("user_bdate") String user_bdate,
                                   @Field("user_city") String user_city,
                                   @Field("user_password") String user_password);

    @FormUrlEncoded
    @POST("Payment/confirmPayment")
    Call<PaymentConfirm> confirmPayment(@Field("invoice_id") String invoice_id,
                                        @Field("user_id") String user_id,
                                        @Field("payment_method") String payment_method,
                                        @Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("Products/ProductByCategory")
    Call<AllProduct> getCategoryProduct(@Field("subcat_id") String subcat_id, @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Products/Filters")
    Call<FilterPozoPozo> getCategoryProductByFilter(@Field("filter_id") String filter_id,
                                                    @Field("user_id") String user_id,
                                                    @Field("subcat_id") String subcat_id
    );


    @FormUrlEncoded
    @POST("Payment/GetMyInvoice")
    Call<MyAlldetailsOrder> getOrderdetail(@Field("Invoice_id") String Invoice_id);

    @FormUrlEncoded
    @POST("Products/Reviews")
    Call<ReviewProdctModel> gerReviewProductId(@Field("product_id") String product_id);


    @GET("Products/getAllProduct")
    Call<AllProduct> getAllproduct();

    @FormUrlEncoded
    @POST("Products/getAllProduct")
    Call<AllProduct> getAllProductMain(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Payment/GetMyAllInvoice")
    Call<MyAllOrder> getOrder(@Field("User_id") String User_id);

    @GET("Products/Category")
    Call<Allcategory> getAllCategory();

    @FormUrlEncoded
    @POST("Products/getSubategory")
    Call<SubCategoryPozo> getSubategory(@Field("cat_id") String cat_id);

    @FormUrlEncoded
    @POST("Authentication/ResetPassword")
    Call<ResetPasswordModle> app_resetpassword(@Field("email") String email);


    @FormUrlEncoded
    @POST("Products/changePassword")
    Call<ChangePassPozo> changePassword(
            @Field("user_id") String user_id,
            @Field("old_password") String old_password,
            @Field("new_password") String new_password);


    @FormUrlEncoded
    @POST("Products/Filters")
    Call<AllProduct> filter(@Field("filter_id") String filter_id);

    @GET("Products/Banner")
    Call<TopBannerModle> get_home_top_banner();

    @GET("Products/BannerList")
    Call<OfferBannerPozo> getOfferBanner();

    @FormUrlEncoded
    @POST("Products/GetOffers")
    Call<GetOfferDataModle> GetOffers(@Field("user_id") String user_id);

//TermsAndConditionsPozo


    @GET("Users/TermsCondition")
    Call<TermsAndConditionsPozo> getTemsAndCondition();


    @GET("Users/AboutUs")
    Call<AboutUsPozo> AboutUs();


    @FormUrlEncoded
    @POST("Payment/GetAllNotification")
    Call<NotificationModle> notificaotiCount(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Payment/clearNotification")
    Call<NotificationModle> notificaoticlear(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Payment/GetNotificationList")
    Call<NotificationListModle> notificationlist(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("User_Dashboard/getUserProfile")
    Call<GetUserProfileDataModle> getUserProfile(@Field("user_id") String user_id);

    @Multipart
    @POST("User_Dashboard/updateUserProfile")
    Call<UpdateUserDataModle> updateUserProfile(
            @Part MultipartBody.Part name,
            @Part MultipartBody.Part email,
            @Part MultipartBody.Part number,
            @Part MultipartBody.Part file,
            @Part MultipartBody.Part user_id);

    @FormUrlEncoded
    @POST("Products/search")
    Call<SearchDataModle> search(@Field("content") String content, @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("User_Dashboard/getAddress")
    Call<AddressListDataModle> getAddress(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("User_Dashboard/deleteAddress")
    Call<DeleteAddressDataModle> deleteAddress(@Field("address_id") String address_id);


    @FormUrlEncoded
    @POST("Authentication/generateOtp")
    Call<GenerateOtpDataModle> generateOtp(@Field("phone") String phone);


    @FormUrlEncoded
    @POST("Authentication/checkUserOtp")
    Call<LoginModle> otpVarification(@Field("otp") String otp, @Field("mobile") String mobile);

*//*    @FormUrlEncoded
    @POST("Payment/Billing")
    Call<PaymentModle> payment(
            @Field("address_id") String address_id,
            @Field("coupon_code") String coupon_code,
                               @Field("firstname") String firstname,
                               @Field("lastname") String lastname,
                               @Field("address") String address,
                               @Field("phone") String phone,
                               @Field("zip_code") String zip_code,
                               @Field("city") String city,
                               @Field("state") String state,
                               @Field("email") String email,
                               @Field("paymenttype") String paymenttype,
                               @Field("shipping") String shipping,
                               @Field("user_id") String user_id,
                               @Field("subtotal") String subtotal,
                               @Field("products") String products);*//*


    @Headers("Content-Type: application/json")
    @POST("Payment/Billing")
    Call<PaymentModle> payment(@Body String jsonBody);

    @FormUrlEncoded
    @POST("Users/ContactUs")
    Call<ContactUsDataModle>sendmsg(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("message") String message,
                                    @Field("subject") String subject);


    @FormUrlEncoded
    @POST("Products/zipLocation")
    Call<ContactUsDataModle> checkPostal(@Field("zipcode") String zipcode);


    @FormUrlEncoded
    @POST("Products/addWishList")
    Call<AddToWishListDataModle> addWishList(
            @Field("product_id") String product_id,
            @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Products/NotifyAdmin")
    Call<NotifyPozo> notifyMe(
            @Field("product_id") String product_id,
            @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Products/getWishList")
    Call<GetWishListDataModle> getWishList(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Products/removeWishlist")
    Call<RemoveWishListDataModle> removeWishlist(
            @Field("product_id") String product_id,
            @Field("user_id") String user_id);

    *//*
    @GET("licences")
    Call<LicencesModle> getlicences();
*//*

     *//* @Headers("Content-Type: application/json")
    // @FormUrlEncoded
    @POST("authApi/login")
    Call<LoginModle> login(@Body JsonObject body);

    @GET("authApi/getcollege")
    Call<GetCollegeModle> getcollege();

    @GET("examapi/getexam")
    Call<GetExamModle> getExam();

    @Headers("Content-Type: application/json")
    // @FormUrlEncoded
    @POST("authApi/createUser")
    Call<CreateUserModle> signUp(@Body JsonObject body);
*//*
     */
}
