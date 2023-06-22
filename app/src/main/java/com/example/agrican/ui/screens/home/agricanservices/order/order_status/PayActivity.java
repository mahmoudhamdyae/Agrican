//package com.example.agrican.ui.screens.home.agricanservices.order.order_status;
//
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatCheckBox;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkResponse;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.HttpStack;
//import com.android.volley.toolbox.Volley;
//import com.example.agrican.R;
//import com.paymob.acceptsdk.LocaleManager;
//import com.paymob.acceptsdk.ThreeDSecureWebViewActivty;
//import com.paymob.acceptsdk.helper.StringPOSTRequest;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import java.util.Objects;
//import morxander.editcard.EditCard;
//
//public class PayActivity extends AppCompatActivity implements View.OnClickListener {
//    private boolean hasBilling;
//    JSONObject billingData;
//    String paymentKey;
//    String token;
//    String maskedPanNumber;
//    Boolean saveCardDefault;
//    Boolean showSaveCard;
//    int themeColor;
//    String language = "en";
//    EditText nameText;
//    EditCard cardNumberText;
//    EditText monthText;
//    EditText yearText;
//    EditText cvvText;
//    AppCompatCheckBox saveCardCheckBox;
//    TextView saveCardText;
//    Button payBtn;
//    LinearLayout cardName_linearLayout;
//    LinearLayout expiration_linearLayout;
//    LinearLayout saveCard_linearLayout;
//    ProgressDialog mProgressDialog;
//    String verificationActivity_title;
//    Status status;
//    JSONObject payDict;
//    public static LocaleManager localeManager;
//
//    public PayActivity() {
//
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Toast.makeText(this, "hahahaha", Toast.LENGTH_SHORT).show();
//
//
//        this.resetVariables();
//        this.initUiTheme();
//        this.setContentView(R.layout.activity_card_information);
//        this.init();
//    }
//
//    void initUiTheme() {
//        Intent intent = this.getIntent();
//        if (intent.hasExtra("language")) {
//            this.language = intent.getStringExtra("language");
//            this.setLocale(this.language);
//        }
//
//        this.themeColor = intent.getIntExtra("theme_color", this.getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));
//    }
//
//    private void init() {
//        this.getAcceptParameters();
//        Intent intent = this.getIntent();
//        this.language = intent.getStringExtra("language");
//        this.linkViews(this.language);
//        this.updateLayout();
//        Log.d("test", "onCreate: " + this.themeColor);
//    }
//
//    public void setLocale(String lang) {
//        Locale myLocale = new Locale(lang);
//        Resources res = this.getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//        this.onConfigurationChanged(conf);
//    }
//
//    public void onBackPressed() {
//        this.onCancelPress();
//    }
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == 4) {
//            this.onCancelPress();
//        }
//
//        return true;
//    }
//
//    public void onClick(View v) {
//        if (v.getId() == R.id.pay) {
//            this.handlePayment();
//        }
//
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == 16908332) {
//            this.onCancelPress();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void onCancelPress() {
//        if (this.status == com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.Status.IDLE) {
//            Intent canceledIntent = new Intent();
//            this.setResult(1, canceledIntent);
//            this.finish();
//        }
//
//    }
//
//    private void handlePayment() {
//        String nameString = this.nameText.getText().toString();
//        String numberString = this.cardNumberText.getCardNumber();
//        String monthString = this.monthText.getText().toString();
//        String yearString = this.yearText.getText().toString();
//        String cvvString = this.cvvText.getText().toString();
//        JSONObject cardData = new JSONObject();
//        if (this.token != null) {
//            try {
//                cardData.put("identifier", this.token);
//                cardData.put("subtype", "TOKEN");
//                cardData.put("cvn", cvvString);
//            } catch (JSONException var10) {
//                return;
//            }
//        } else {
//            if (!com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.FormChecker.checkCardName(nameString)) {
//                Toast.makeText(this, this.getString(R.string.Empty_name_check), Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.FormChecker.checkCardNumber(numberString)) {
//                Toast.makeText(this, this.getString(R.string.Card_Number_check), Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.FormChecker.checkDate(monthString, yearString)) {
//                Toast.makeText(this, this.getString(R.string.Date_check), Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.FormChecker.checkCVV(cvvString)) {
//                Toast.makeText(this, this.getString(R.string.Cvv_check), Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            try {
//                cardData.put("identifier", numberString);
//                cardData.put("sourceholder_name", nameString);
//                cardData.put("subtype", "CARD");
//                cardData.put("expiry_month", monthString);
//                cardData.put("expiry_year", yearString);
//                cardData.put("cvn", cvvString);
//            } catch (JSONException var9) {
//                return;
//            }
//        }
//
//        try {
//            this.payAPIRequest(cardData);
//        } catch (JSONException var8) {
//            this.notifyErrorTransaction("An error occured while handling payment response");
//        }
//
//    }
//
//    void payAPIRequest(JSONObject cardData) throws JSONException {
//        JSONObject params = new JSONObject();
//        params.put("source", cardData);
//        params.put("api_source", "SDK");
//        if (this.hasBilling) {
//            params.put("billing", this.billingData);
//        }
//
//        params.put("payment_token", this.paymentKey);
//        String jsons = params.toString();
//        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
//
//        StringPOSTRequest request = new StringPOSTRequest("https://accept.paymobsolutions.com/api/acceptance/payments/pay", jsons, new Response.Listener<String>() {
//            public void onResponse(String response) {
//                com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.dismissProgressDialog();
//
//                try {
//                    JSONObject jsonResult = new JSONObject(response);
//                    Log.d("notice", "json output: " + jsonResult);
//                    String direct3dSecure = jsonResult.getString("is_3d_secure");
//                    com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.payDict = jsonResult;
//                    if (direct3dSecure.equals("true")) {
//                        String redirectionURL = jsonResult.getString("redirection_url");
//                        com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.open3DSecureView(redirectionURL);
//                    } else {
//                        com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.paymentInquiry();
//                    }
//                } catch (Exception var5) {
//                    com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.dismissProgressDialog();
//                    Log.d("notice", "exception caught " + var5.getMessage());
//                    com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.notifyErrorTransaction(var5.getMessage());
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError error) {
//                Log.d("notice", "json error output: " + error);
//                NetworkResponse networkResponse = error.networkResponse;
//                com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.dismissProgressDialog();
//                if (networkResponse != null && networkResponse.statusCode == 401) {
//                    com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.notifyErrorTransaction("Invalid or Expired Payment Key");
//                }
//
//            }
//        });
//        request.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
//        request.setTag(0);
//        queue.add(request);
//        this.showProgressDialog();
//    }
//
//    private void open3DSecureView(String url) {
//        Intent intent = this.getIntent();
//        this.themeColor = intent.getIntExtra("theme_color", this.getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));
//        Boolean actionBar = intent.getBooleanExtra("ActionBar", false);
//        Intent threeDSecureViewIntent = new Intent(this, ThreeDSecureWebViewActivty.class);
//        threeDSecureViewIntent.putExtra("three_d_secure_url", url);
//        threeDSecureViewIntent.putExtra("ActionBar", actionBar);
//        threeDSecureViewIntent.putExtra("theme_color", this.themeColor);
//        threeDSecureViewIntent.putExtra("three_d_secure_activity_title", this.verificationActivity_title);
//        this.startActivityForResult(threeDSecureViewIntent, 32);
//    }
//
//    public void processFinish(String apiName, String output, String status_code) {
//        this.dismissProgressDialog();
//        Log.d("notice", "output: " + output);
//        Log.d("notice", "status code: " + status_code);
//        if (Integer.parseInt(status_code) == 401) {
//            this.notifyErrorTransaction("Invalid or Expired Payment Key");
//        }
//
//        if (apiName.equalsIgnoreCase("USER_PAYMENT")) {
//            try {
//                JSONObject jsonResult = new JSONObject(output);
//                Log.d("notice", "json output: " + jsonResult);
//                String direct3dSecure = jsonResult.getString("is_3d_secure");
//                this.payDict = jsonResult;
//                if (direct3dSecure.equals("true")) {
//                    String redirectionURL = jsonResult.getString("redirection_url");
//                    this.open3DSecureView(redirectionURL);
//                } else {
//                    this.paymentInquiry();
//                }
//            } catch (Exception var7) {
//                this.dismissProgressDialog();
//                Log.d("notice", "exception caught " + var7.getMessage());
//                this.notifyErrorTransaction(var7.getMessage());
//            }
//        } else if (apiName.equalsIgnoreCase("CARD_TOKENIZER")) {
//            this.dismissProgressDialog();
//            this.notifySuccessfulTransactionSaveCard(output);
//        }
//
//    }
//
//    private void paymentInquiry() {
//        try {
//            Log.d("notice", this.payDict.toString());
//            String success = this.payDict.getString("success");
//            Log.d("notice", "txn_response_code is " + this.payDict.getInt("txn_response_code"));
//            if (this.payDict.getInt("txn_response_code") == 1) {
//                this.notifyErrorTransaction("There was an error processing the transaction");
//            }
//
//            if (this.payDict.getInt("txn_response_code") == 2) {
//                this.notifyErrorTransaction("Contact card issuing bank");
//            }
//
//            if (this.payDict.getInt("txn_response_code") == 4) {
//                this.notifyErrorTransaction("Expired Card");
//            }
//
//            if (this.payDict.getInt("txn_response_code") == 5) {
//                this.notifyErrorTransaction("Insufficient Funds");
//            }
//
//            if (success.equals("true")) {
//                if (this.saveCardCheckBox.isChecked()) {
//                    JSONObject cardData = new JSONObject();
//                    cardData.put("pan", this.cardNumberText.getCardNumber());
//                    cardData.put("cardholder_name", this.nameText.getText().toString());
//                    cardData.put("expiry_month", this.monthText.getText().toString());
//                    cardData.put("expiry_year", this.yearText.getText().toString());
//                    cardData.put("cvn", this.cvvText.getText().toString());
//                    String jsons = cardData.toString();
//                    RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
//                    HttpStack stack = null;
//
//                    StringPOSTRequest request = new StringPOSTRequest("https://accept.paymobsolutions.com/api/acceptance/tokenization?payment_token=" + this.paymentKey, jsons, new Response.Listener<String>() {
//                        public void onResponse(String response) {
//                            Log.d("notice", "tokenize response " + response);
//                            com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.dismissProgressDialog();
//                            com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.notifySuccessfulTransactionSaveCard(response);
//                        }
//                    }, new Response.ErrorListener() {
//                        public void onErrorResponse(VolleyError error) {
//                            NetworkResponse networkResponse = error.networkResponse;
//                            com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.dismissProgressDialog();
//                            Log.d("notice", "tokenize error response " + error);
//                            if (networkResponse != null && networkResponse.statusCode == 401) {
//                                com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.this.notifyErrorTransaction("Invalid or Expired Payment Key");
//                            }
//
//                        }
//                    });
//                    request.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
//                    request.setTag(0);
//                    queue.add(request);
//                } else {
//                    this.dismissProgressDialog();
//                    this.notifySuccesfulTransaction();
//                }
//            } else {
//                this.dismissProgressDialog();
//                this.notifyRejectedTransaction();
//            }
//        } catch (JSONException var8) {
//            this.notifyErrorTransaction("An error occured while reading returned message");
//        }
//
//    }
//
//    private void notifySuccessfulTransactionSaveCard(String saveCardData) {
//        Intent successIntent = new Intent();
//
//        try {
//            JSONObject savedCardDict = new JSONObject(saveCardData);
//            this.putSaveCardData(successIntent, savedCardDict);
//            this.putPayDataInIntent(successIntent);
//        } catch (JSONException var6) {
//            this.payDict.remove("merchant_order_id");
//
//            try {
//                this.payDict.put("merchant_order_id", "null");
//                this.putPayDataInIntent(successIntent);
//            } catch (JSONException var5) {
//                var5.printStackTrace();
//            }
//        }
//
//        this.setResult(8, successIntent);
//        this.finish();
//    }
//
//    private void putSaveCardData(Intent intent, JSONObject saveCardData) {
//        for(int i = 0; i < SAVE_CARD_DICT_KEYS.length; ++i) {
//            try {
//                intent.putExtra(SAVE_CARD_DICT_KEYS[i], saveCardData.getString(SAVE_CARD_DICT_KEYS[i]));
//            } catch (JSONException ignored) {
//            }
//        }
//
//    }
//
//    private void notifyErrorTransaction(String reason) {
//        Intent errorIntent = new Intent();
//        errorIntent.putExtra("transaction_error_reason", reason);
//        this.setResult(3, errorIntent);
//        this.finish();
//    }
//
//    private void notifyCancel3dSecure() {
//        Intent cancel3dSecureIntent = new Intent();
//
//        try {
//            this.putPayDataInIntent(cancel3dSecureIntent);
//            this.setResult(9, cancel3dSecureIntent);
//        } catch (JSONException var3) {
//            cancel3dSecureIntent.putExtra("raw_pay_response", this.payDict.toString());
//            this.setResult(10, cancel3dSecureIntent);
//        }
//
//        this.finish();
//    }
//
//    private void notifyRejectedTransaction() {
//        Intent rejectIntent = new Intent();
//
//        try {
//            this.putPayDataInIntent(rejectIntent);
//            this.setResult(4, rejectIntent);
//        } catch (JSONException var3) {
//            rejectIntent.putExtra("raw_pay_response", this.payDict.toString());
//            this.setResult(5, rejectIntent);
//        }
//
//        this.finish();
//    }
//
//    private void notifySuccesfulTransaction() {
//        Intent successIntent = new Intent();
//
//        try {
//            this.putPayDataInIntent(successIntent);
//            this.setResult(6, successIntent);
//            this.finish();
//        } catch (JSONException var3) {
//            this.notifySuccesfulTransactionParsingIssue(this.payDict.toString());
//        }
//
//    }
//
//    private void notifySuccesfulTransactionParsingIssue(String raw_pay_response) {
//        Intent successIntent = new Intent();
//        successIntent.putExtra("raw_pay_response", raw_pay_response);
//        this.setResult(7, successIntent);
//        this.finish();
//    }
//
//    private void putPayDataInIntent(Intent intent) throws JSONException {
//        for (String payDictKey : PAY_DICT_KEYS) {
//            intent.putExtra(payDictKey, this.payDict.getString(payDictKey));
//        }
//
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 32) {
//            if (resultCode == 2) {
//                this.notifyCancel3dSecure();
//            } else if (resultCode == 1) {
//                this.notifyCancel3dSecure();
//            } else if (resultCode == 17) {
//                String raw_pay_response = data.getStringExtra("raw_pay_response");
//
//                try {
//                    this.payDict = new JSONObject(raw_pay_response);
//                    this.paymentInquiry();
//                } catch (Exception var6) {
//                    this.notifySuccesfulTransactionParsingIssue(raw_pay_response);
//                }
//            }
//        }
//
//    }
//
//    private void showProgressDialog() {
//        this.mProgressDialog = new ProgressDialog(this);
//        this.mProgressDialog.setTitle(this.getString(R.string.processing));
//        this.mProgressDialog.setMessage(this.getString(R.string.wait));
//        this.mProgressDialog.setCancelable(false);
//        this.status = com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.Status.PROCESSING;
//        this.mProgressDialog.show();
//        this.getWindow().setFlags(16, 16);
//    }
//
//    private void dismissProgressDialog() {
//        if (this.mProgressDialog != null) {
//            this.mProgressDialog.dismiss();
//        }
//
//        this.status = com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.Status.IDLE;
//        this.getWindow().clearFlags(16);
//    }
//
//    private void linkViews(String language) {
//        Intent intent = this.getIntent();
//        boolean showActionBar = intent.getBooleanExtra("ActionBar", false);
//        if (showActionBar) {
//            ActionBar actionBar = this.getSupportActionBar();
//            Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
//            ColorDrawable colorDrawable = new ColorDrawable(this.themeColor);
//
//            actionBar.setBackgroundDrawable(colorDrawable);
//        } else {
//            Log.d("actionBar", "no actionBar");
//        }
//
//        Window window = this.getWindow();
//        window.addFlags(Integer.MIN_VALUE);
//        window.clearFlags(67108864);
//        window.setStatusBarColor(this.themeColor);
//        this.nameText = this.findViewById(R.id.cardName);
//        this.cardNumberText = this.findViewById(R.id.cardNumber);
//        this.monthText = this.findViewById(R.id.expiryMonth);
//        this.yearText = this.findViewById(R.id.expiryYear);
//        this.cvvText = this.findViewById(R.id.cvv);
//        if (language.equals("ar")) {
//            this.nameText.setGravity(5);
//            this.cardNumberText.setGravity(5);
//            this.monthText.setGravity(5);
//            this.yearText.setGravity(5);
//            this.cvvText.setGravity(5);
//        }
//
//        this.saveCardCheckBox = this.findViewById(R.id.saveCardCheckBox);
//        this.saveCardText = this.findViewById(R.id.saveCardText);
//        this.payBtn = this.findViewById(R.id.pay);
//        if (this.getIntent().getStringExtra("PAY_BUTTON_TEXT") != null && !this.getIntent().getStringExtra("PAY_BUTTON_TEXT").isEmpty()) {
//            this.payBtn.setText(this.getIntent().getStringExtra("PAY_BUTTON_TEXT"));
//        }
//
//        this.payBtn.setBackgroundColor(this.themeColor);
//        this.payBtn.setOnClickListener(this);
//        this.cardName_linearLayout = this.findViewById(R.id.cardName_linearLayout);
//        this.expiration_linearLayout = this.findViewById(R.id.expiration_linearLayout);
//        this.saveCard_linearLayout = this.findViewById(R.id.saveCard_linearLayout);
//    }
//
//    private void getAcceptParameters() {
//        Intent intent = this.getIntent();
//        if (intent.hasExtra("email")) {
//            this.billingData = new JSONObject();
//            this.readBillingData(intent);
//            this.hasBilling = true;
//        }
//
//        this.paymentKey = intent.getStringExtra("payment_key");
//        this.checkIfPassed("payment_key", this.paymentKey);
//        this.token = intent.getStringExtra("token");
//        this.maskedPanNumber = intent.getStringExtra("masked_pan_number");
//        if (this.token != null) {
//            this.checkIfPassed("masked_pan_number", this.maskedPanNumber);
//        }
//
//        this.saveCardDefault = intent.getBooleanExtra("save_card_default", false);
//        this.showSaveCard = intent.getBooleanExtra("show_save_card", true);
//        this.verificationActivity_title = intent.getStringExtra("three_d_secure_activity_title");
//        if (this.verificationActivity_title == null) {
//            this.verificationActivity_title = "";
//        }
//
//    }
//
//    private void readBillingData(Intent intent) {
//        try {
//            this.readBillingValue(intent, "first_name");
//            this.readBillingValue(intent, "last_name");
//            this.readBillingValue(intent, "building");
//            this.readBillingValue(intent, "floor");
//            this.readBillingValue(intent, "apartment");
//            this.readBillingValue(intent, "city");
//            this.readBillingValue(intent, "state");
//            this.readBillingValue(intent, "country");
//            this.readBillingValue(intent, "email");
//            this.readBillingValue(intent, "phone_number");
//            this.readBillingValue(intent, "postal_code");
//            Log.d("notice", "finished reading billing data");
//        } catch (JSONException ignored) {
//        }
//
//    }
//
//    private void readBillingValue(Intent intent, String key) throws JSONException {
//        String value = intent.getStringExtra(key);
//        this.checkIfPassed(key, value);
//        this.billingData.put(key, value);
//    }
//
//    private void checkIfPassed(String key, String value) {
//        if (value == null) {
//            this.abortForNotPassed(key);
//        }
//
//    }
//
//    private void abortForNotPassed(String key) {
//        Intent errorIntent = new Intent();
//        errorIntent.putExtra("missing_argument_value", key);
//        this.setResult(2, errorIntent);
//        this.finish();
//    }
//
//    private void resetVariables() {
//        this.billingData = null;
//        this.paymentKey = null;
//        this.token = null;
//        this.maskedPanNumber = null;
//        this.showSaveCard = true;
//        this.saveCardDefault = false;
//        this.mProgressDialog = null;
//        this.payDict = null;
//        this.verificationActivity_title = null;
//        this.status = com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity.Status.IDLE;
//        this.hasBilling = false;
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void updateLayout() {
//        this.saveCardCheckBox.setChecked(this.saveCardDefault);
//        this.saveCardCheckBox.setClickable(this.showSaveCard);
//        com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.ColorEditor.setAppCompatCheckBoxColors(this.saveCardCheckBox, -2139062144, Integer.parseInt(String.valueOf(this.themeColor)));
//        if (!this.showSaveCard) {
//            this.saveCardCheckBox.setVisibility(View.GONE);
//            if (this.saveCardDefault) {
//                this.saveCardText.setText("Your card will be saved for future use");
//            } else {
//                this.saveCard_linearLayout.setVisibility(View.GONE);
//            }
//        }
//
//        if (this.token != null) {
//            this.invalidateOptionsMenu();
//            this.cardName_linearLayout.setVisibility(View.GONE);
//            this.expiration_linearLayout.setVisibility(View.GONE);
//            this.saveCardCheckBox.setChecked(false);
//            this.saveCard_linearLayout.setVisibility(View.GONE);
//            this.cardNumberText.setHint(this.maskedPanNumber);
//            this.cardNumberText.setHintTextColor(this.getResources().getColor(R.color.colorText));
//            this.cardNumberText.setEnabled(false);
//            this.cardNumberText.setFocusable(false);
//            Intent intent = this.getIntent();
//            this.themeColor = intent.getIntExtra("theme_color", this.getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));
//        }
//    }
//
//
//    private void setApplicationLanguage(String newLanguage) {
//        Resources activityRes = this.getResources();
//        Configuration activityConf = activityRes.getConfiguration();
//        Locale newLocale = new Locale(newLanguage);
//        activityConf.setLocale(newLocale);
//
//        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());
//        Resources applicationRes = this.getApplicationContext().getResources();
//        Configuration applicationConf = applicationRes.getConfiguration();
//        applicationConf.setLocale(newLocale);
//        applicationRes.updateConfiguration(applicationConf, applicationRes.getDisplayMetrics());
//    }
//
//    protected void attachBaseContext(Context newBase) {
//        localeManager = new LocaleManager(newBase);
//        super.attachBaseContext(localeManager.setLocale(newBase));
//    }
//
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        localeManager.setLocale(this);
//        super.onConfigurationChanged(newConfig);
//        if (newConfig.locale == Locale.ENGLISH) {
//            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
//        } else if (newConfig.locale == Locale.FRENCH) {
//            Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    static enum Status {
//        IDLE,
//        PROCESSING;
//
//        private Status() {
//        }
//    }
//
//    static final String[] PAY_DICT_KEYS = new String[]{"amount_cents", "is_refunded", "is_capture", "captured_amount", "source_data.type", "pending", "merchant_order_id", "is_3d_secure", "id", "is_void", "currency", "is_auth", "is_refund", "owner", "is_voided", "source_data.pan", "profile_id", "success", "data.message", "source_data.sub_type", "error_occured", "is_standalone_payment", "created_at", "refunded_amount_cents", "integration_id", "order"};
//
//    static final String[] SAVE_CARD_DICT_KEYS = new String[]{"order_id", "card_subtype", "id", "email", "token", "created_at", "masked_pan", "merchant_id"};
//}
//
//class FormChecker {
//    FormChecker() {
//    }
//
//    static Boolean checkCVV(String cvvString) {
//        return cvvString != null && cvvString.length() == 3;
//    }
//
//    static Boolean checkCardName(String nameString) {
//        return nameString != null && nameString.length() != 0;
//    }
//
//    static Boolean checkCardNumber(String numberString) {
//        return numberString != null && numberString.length() == 16;
//    }
//
//    static Boolean checkDate(String monthString, String yearString) {
//        if (monthString != null && monthString.length() == 2 && yearString != null && yearString.length() == 2) {
//            int yearDiff = Integer.parseInt(yearString) - Integer.parseInt((new SimpleDateFormat("yy", Locale.GERMANY)).format(new Date()));
//            int monthDiff = Integer.parseInt(monthString) - Integer.parseInt((new SimpleDateFormat("MM", Locale.GERMANY)).format(new Date()));
//            return yearDiff > 0 || yearDiff == 0 && monthDiff >= 0;
//        } else {
//            return false;
//        }
//    }
//}
//
//class ColorEditor {
//    ColorEditor() {
//    }
//
//    @SuppressLint("RestrictedApi")
//    public static void setAppCompatCheckBoxColors(AppCompatCheckBox _checkbox, int _uncheckedColor, int _checkedColor) {
//        int[][] states = new int[][]{{-16842912}, {16842912}};
//        int[] colors = new int[]{_uncheckedColor, _checkedColor};
////        _checkbox.setSupportButtonTintList(new ColorStateList(states, colors));
//    }
//}