package in.calibrage.palm360fa.localData;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.calibrage.palm360fa.Model.FarmerOtpResponceModel;
import in.calibrage.palm360fa.Model.GetBankDetailsByFarmerCode;
import in.calibrage.palm360fa.Model.LoginResponse;
import in.calibrage.palm360fa.Model.Product_new;
import in.calibrage.palm360fa.Model.SelectedProducts;


public class SharedPrefsData {
    private static SharedPrefsData instance = null;
    private static final String DEFAULTVALUESTRING = "N/A";
    private static final int DEFAULTVALUINT = 0;
    private static final String FF_DATA = "Akshayaapp";
    private SharedPreferences FarmerSharedPrefs = null;
    private static final String USER_ID = "user_id";
    private static final String CataGories = "catagories";
    private static final String Createduser = "userdetails";
    private static final String ADMIN_DATA = "admin_data";
    private static final String BankDetails = "bankdetails";
    private static final String isadminLogin = "adminlogin";
    private static final String isuserlogin = "userlogin";


    public static SharedPrefsData getInstance(Context context) {

        if (instance == null) {
            instance = new SharedPrefsData();
            instance.getPitchItSharedPrefs(context);
        }
        return instance;
    }

    private void getPitchItSharedPrefs(Context context) {
        if (this.FarmerSharedPrefs == null) {
            this.FarmerSharedPrefs = context.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
        }
    }

    private void loadFreshPitchItSharedPrefs(Context context) {
        this.FarmerSharedPrefs = context.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
    }

    public String getStringFromSharedPrefs(String key) {
        return FarmerSharedPrefs.getString(key, DEFAULTVALUESTRING);
    }

    public int getIntFromSharedPrefs(String key) {
        return FarmerSharedPrefs.getInt(key, DEFAULTVALUINT);
    }


    public void updateMultiValue(Context context, List<SharedPrefsBean> sharedPrefsBeans) {
        //getPitchItSharedPrefs(context);
        SharedPreferences.Editor editor = this.FarmerSharedPrefs.edit();

        for (SharedPrefsBean eachShrePref : sharedPrefsBeans) {
            if (eachShrePref.getIsInt()) {
                editor.putInt(eachShrePref.getKey(), eachShrePref.getValueInt());
            } else {
                editor.putString(eachShrePref.getKey(), eachShrePref.getValueString());
            }
        }
        editor.commit();
        loadFreshPitchItSharedPrefs(context);
    }

    public void updateStringValue(Context context, String key, String value) {
        //getPitchItSharedPrefs(context);
        SharedPreferences.Editor editor = this.FarmerSharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void updateIntValue(Context context, String key, int value) {
        //getPitchItSharedPrefs(context);
        SharedPreferences.Editor editor = this.FarmerSharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void ClearData(Context context) {
        getPitchItSharedPrefs(context);
        SharedPreferences profilePref = context.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = profilePref.edit();
        editor.clear();
        editor.apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

    public void saveUserId(Context context, String userId) {
        if (context != null) {
            SharedPreferences profilePref = context.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString(USER_ID, userId);

            // Commit the edits!
            editor.apply();

        }
    }

    public String getUserId(Context context) {
        SharedPreferences profilePref = context.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        return profilePref.getString(USER_ID, "");

    }

    public static void putString(Context context, String key, String value, String pref) {
        if (context != null && key != null) {
            if (pref != null && !pref.isEmpty()) {
                context.getSharedPreferences(pref, 0).edit().putString(key, value).apply();
            } else {
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
            }

        }
    }


    public static String getString(Context context, String key, String pref) {
        return context != null && key != null ? (pref != null && !pref.isEmpty() ?
                context.getSharedPreferences(pref, 0).getString(key, (String) null)
                : PreferenceManager.getDefaultSharedPreferences(context).getString(key, (String) null)) : null;
    }

    public static void putInt(Context context, String key, int value, String pref) {
        if (context == null || key == null) {
            return;
        }
        if (pref == null || pref.isEmpty()) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply();
        } else {
            context.getSharedPreferences(pref, Context.MODE_PRIVATE).edit().putInt(key, value).apply();
        }
    }

    public static int getInt(Context context, String key, String pref) {
        if (context == null || key == null) {
            return 0;
        }
        if (pref == null || pref.isEmpty()) {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
        } else {
            return context.getSharedPreferences(pref, Context.MODE_PRIVATE).getInt(key, 0);
        }
    }

    public static void putBool(Context context, String key, boolean value) {

        if (context != null) {
            SharedPreferences profilePref = context.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putBoolean(key, value);
            editor.apply();

        }
    }

    public static boolean getBool(Context context, String key) {
        SharedPreferences profilePref = context.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        return profilePref.getBoolean(key, false);
    }

    public static void saveFormerDetails(Context mContext, FarmerOtpResponceModel formerModel) {
        Gson gson = new Gson();

        if (mContext != null) {
            String json = gson.toJson(formerModel);
            SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString(CataGories, json);

            // Commit the edits!
            editor.apply();

        }
    }

    public static void saveCreatedUser(Context mContext, LoginResponse responseModel) {
        Gson gson = new Gson();

        if (mContext != null) {
            String json = gson.toJson(responseModel);
            SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString(Createduser, json);

            // Commit the edits!
            editor.apply();

        }
    }

    public static FarmerOtpResponceModel getCatagories(Context mContext) {
        Gson gson = new Gson();

        SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        String json = profilePref.getString(CataGories, "");
        FarmerOtpResponceModel obj = gson.fromJson(json, FarmerOtpResponceModel.class);
        return obj;

    }


    public static LoginResponse getCreatedUser(Context mContext) {
        Gson gson = new Gson();

        SharedPreferences UserPref = mContext.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        String json = UserPref.getString(Createduser, "");
        LoginResponse obj = gson.fromJson(json, LoginResponse.class);
        return obj;

    }

    //
//
//
    public static void saveCartitems(Context mContext, ArrayList<Product_new> myProducts) {
        Gson gson = new Gson();

        if (mContext != null) {
            String json = gson.toJson(myProducts);
            SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString("cart", json);

            // Commit the edits!
            editor.apply();

        }
    }

    public static ArrayList<Product_new> getCartData(Context mContext) {
        Gson gson = new Gson();

        SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        String json = profilePref.getString("cart", "");
        Type type = new TypeToken<List<Product_new>>() {
        }.getType();
        ArrayList<Product_new> obj = gson.fromJson(json, type);
        return obj;

    }

    public static void saveFertCartitems(Context mContext, ArrayList<SelectedProducts> myProducts) {
        Gson gson = new Gson();

        if (mContext != null) {
            String json = gson.toJson(myProducts);
            SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString("cart", json);

            // Commit the edits!
            editor.apply();

        }
    }

    public static ArrayList<SelectedProducts> getFertCartData(Context mContext) {
        Gson gson = new Gson();
        SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        String json = profilePref.getString("cart", "");
        Type type = new TypeToken<List<SelectedProducts>>() {
        }.getType();
        ArrayList<SelectedProducts> obj = gson.fromJson(json, type);
        return obj;
    }

    public static String getusername(Context ctx) {
        String middlename = SharedPrefsData.getCatagories(ctx).getResult().getFarmerDetails().get(0).getMiddleName()+"";
        String finalmiddlename = "";
        if ((middlename != null && !middlename.isEmpty() && !middlename.equals("null"))) {
            finalmiddlename = middlename + " ";
        }

        return  SharedPrefsData.getCatagories(ctx).getResult().getFarmerDetails().get(0).getFirstName() + " " + finalmiddlename + SharedPrefsData.getCatagories(ctx).getResult().getFarmerDetails().get(0).getLastName();
    }

    public static void savebankdetails(Context mContext, GetBankDetailsByFarmerCode bankmodel) {
        Gson gson = new Gson();

        if (mContext != null) {
            String json = gson.toJson(bankmodel);
            SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString(BankDetails, json);

            // Commit the edits!
            editor.apply();

        }
    }

    public static GetBankDetailsByFarmerCode getbankdetails(Context mContext) {
        Gson gson = new Gson();

        SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        String json = profilePref.getString(BankDetails, "");
        GetBankDetailsByFarmerCode obj = gson.fromJson(json, GetBankDetailsByFarmerCode.class);
        return obj;

    }

  /*  public void saveAdaminDetails(Context mContext, LoginResponse adminLoginData) {
        Gson gson = new Gson();
        if (mContext != null) {
            String json = gson.toJson(adminLoginData);
            SharedPreferences profilePref = mContext.getSharedPreferences(FF_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = profilePref.edit();
            editor.putString(ADMIN_DATA, json);
            // Commit the edits!
            editor.apply();

        }
    }
    public LoginResponse getAdminDetails(Context mContext) {
        Gson gson = new Gson();
        SharedPreferences UserPref = mContext.getSharedPreferences(FF_DATA,
                Context.MODE_PRIVATE);
        String json = UserPref.getString(ADMIN_DATA, "");
        LoginResponse obj = gson.fromJson(json, LoginResponse.class);
        return obj;
    }
*/
}
