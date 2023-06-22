@file:Suppress("DEPRECATION")

package com.example.agrican.ui.screens.home.agricanservices.order.confirm_order

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.Volley
import com.example.agrican.R
import com.paymob.acceptsdk.LocaleManager
import com.paymob.acceptsdk.ThreeDSecureWebViewActivty
import com.paymob.acceptsdk.helper.StringPOSTRequest
import morxander.editcard.EditCard
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PayActivity : AppCompatActivity(), View.OnClickListener {
    private var hasBilling = false
    var billingData: JSONObject? = null
    private var paymentKey: String? = null
    var token: String? = null
    var maskedPanNumber: String? = null
    var saveCardDefault: Boolean? = null
    var showSaveCard: Boolean? = null
    var themeColor = 0
    var language: String = "en"
    var nameText: EditText? = null
    var cardNumberText: EditCard? = null
    var monthText: EditText? = null
    var yearText: EditText? = null
    var cvvText: EditText? = null
    var payBtn: Button? = null
    private var cardNameLinearLayout: LinearLayout? = null
    private var expirationLinearLayout: LinearLayout? = null
    var mProgressDialog: ProgressDialog? = null
    private var verificationActivityTitle: String? = null
    private var status: Status? = null
    var payDict: JSONObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        Toast.makeText(this, "hahahaha", Toast.LENGTH_SHORT).show()



        resetVariables()
        initUiTheme()
        this.setContentView(R.layout.activity_card_information)
        init()
    }

    private fun initUiTheme() {
        val intent = this.intent
        if (intent.hasExtra("language")) {
            language = intent.getStringExtra("language") ?: "en"
            setLocale(language)
        }
        themeColor = intent.getIntExtra(
            "theme_color",
            this.applicationContext.resources.getColor(R.color.colorPrimaryDark)
        )
    }

    private fun init() {
        acceptParameters
        val intent = this.intent
        language = intent.getStringExtra("language") ?: "en"
        linkViews()
        updateLayout()
        Log.d("test", "onCreate: $themeColor")
    }

    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = this.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        onConfigurationChanged(conf)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        onCancelPress()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == 4) {
            onCancelPress()
        }
        return true
    }

    override fun onClick(v: View) {
        if (v.id == R.id.pay) {
            handlePayment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 16908332) {
            onCancelPress()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onCancelPress() {
        if (status == Status.IDLE) {
            val canceledIntent = Intent()
            this.setResult(1, canceledIntent)
            finish()
        }
    }

    private fun handlePayment() {
        val nameString = nameText!!.text.toString()
        val numberString = cardNumberText!!.cardNumber
        val monthString = monthText!!.text.toString()
        val yearString = yearText!!.text.toString()
        val cvvString = cvvText!!.text.toString()
        val cardData = JSONObject()
        if (token != null) {
            try {
                cardData.put("identifier", token)
                cardData.put("subtype", "TOKEN")
                cardData.put("cvn", cvvString)
            } catch (var10: JSONException) {
                return
            }
        } else {
            if (!FormChecker.checkCardName(nameString)) {
                Toast.makeText(this, this.getString(R.string.Empty_name_check), Toast.LENGTH_SHORT)
                    .show()
                return
            }
            if (!FormChecker.checkCardNumber(numberString)) {
                Toast.makeText(this, this.getString(R.string.Card_Number_check), Toast.LENGTH_SHORT)
                    .show()
                return
            }
            if (!FormChecker.checkDate(monthString, yearString)) {
                Toast.makeText(this, this.getString(R.string.Date_check), Toast.LENGTH_SHORT).show()
                return
            }
            if (!FormChecker.checkCVV(cvvString)) {
                Toast.makeText(this, this.getString(R.string.Cvv_check), Toast.LENGTH_SHORT).show()
                return
            }
            try {
                cardData.put("identifier", numberString)
                cardData.put("sourceholder_name", nameString)
                cardData.put("subtype", "CARD")
                cardData.put("expiry_month", monthString)
                cardData.put("expiry_year", yearString)
                cardData.put("cvn", cvvString)
            } catch (var9: JSONException) {
                return
            }
        }
        try {
            payAPIRequest(cardData)
        } catch (var8: JSONException) {
            notifyErrorTransaction("An error occurred while handling payment response")
        }
    }

    @Throws(JSONException::class)
    fun payAPIRequest(cardData: JSONObject?) {
        val params = JSONObject()
        params.put("source", cardData)
        params.put("api_source", "SDK")
        if (hasBilling) {
            params.put("billing", billingData)
        }
        params.put("payment_token", paymentKey)
        val jsons = params.toString()
        val queue = Volley.newRequestQueue(this.applicationContext)
        val request =
            StringPOSTRequest("https://accept.paymobsolutions.com/api/acceptance/payments/pay",
                jsons,
                { response ->
                    dismissProgressDialog()
                    try {
                        val jsonResult = JSONObject(response)
                        Log.d("notice", "json output: $jsonResult")
                        val direct3dSecure = jsonResult.getString("is_3d_secure")
                        payDict = jsonResult
                        if (direct3dSecure == "true") {
                            val redirectionURL = jsonResult.getString("redirection_url")
                            open3DSecureView(redirectionURL)
                        } else {
                            paymentInquiry()
                        }
                    } catch (var5: Exception) {
                        dismissProgressDialog()
                        Log.d("notice", "exception caught " + var5.message)
                        notifyErrorTransaction(var5.message)
                    }
                }
            ) { error ->
                Log.d("notice", "json error output: $error")
                val networkResponse = error.networkResponse
                dismissProgressDialog()
                if (networkResponse != null && networkResponse.statusCode == 401) {
                    notifyErrorTransaction("Invalid or Expired Payment Key")
                }
            }
        request.retryPolicy = DefaultRetryPolicy(30000, 1, 1.0f)
        request.tag = 0
        queue.add(request)
        showProgressDialog()
    }

    private fun open3DSecureView(url: String) {
        val intent = this.intent
        themeColor = intent.getIntExtra(
            "theme_color",
            this.applicationContext.resources.getColor(R.color.colorPrimaryDark)
        )
        val actionBar = intent.getBooleanExtra("ActionBar", false)
        val threeDSecureViewIntent = Intent(this, ThreeDSecureWebViewActivty::class.java)
        threeDSecureViewIntent.putExtra("three_d_secure_url", url)
        threeDSecureViewIntent.putExtra("ActionBar", actionBar)
        threeDSecureViewIntent.putExtra("theme_color", themeColor)
        threeDSecureViewIntent.putExtra("three_d_secure_activity_title", verificationActivityTitle)
        this.startActivityForResult(threeDSecureViewIntent, 32)
    }

    private fun paymentInquiry() {
        try {
            Log.d("notice", payDict.toString())
            val success = payDict!!.getString("success")
            Log.d("notice", "txn_response_code is " + payDict!!.getInt("txn_response_code"))
            if (payDict!!.getInt("txn_response_code") == 1) {
                notifyErrorTransaction("There was an error processing the transaction")
            }
            if (payDict!!.getInt("txn_response_code") == 2) {
                notifyErrorTransaction("Contact card issuing bank")
            }
            if (payDict!!.getInt("txn_response_code") == 4) {
                notifyErrorTransaction("Expired Card")
            }
            if (payDict!!.getInt("txn_response_code") == 5) {
                notifyErrorTransaction("Insufficient Funds")
            }
            if (success == "true") {
                dismissProgressDialog()
                notifySuccesfulTransaction()
            } else {
                dismissProgressDialog()
                notifyRejectedTransaction()
            }
        } catch (var8: JSONException) {
            notifyErrorTransaction("An error occured while reading returned message")
        }
    }

    private fun notifyErrorTransaction(reason: String?) {
        val errorIntent = Intent()
        errorIntent.putExtra("transaction_error_reason", reason)
        this.setResult(3, errorIntent)
        finish()
    }

    private fun notifyCancel3dSecure() {
        val cancel3dSecureIntent = Intent()
        try {
            putPayDataInIntent(cancel3dSecureIntent)
            this.setResult(9, cancel3dSecureIntent)
        } catch (var3: JSONException) {
            cancel3dSecureIntent.putExtra("raw_pay_response", payDict.toString())
            this.setResult(10, cancel3dSecureIntent)
        }
        finish()
    }

    private fun notifyRejectedTransaction() {
        val rejectIntent = Intent()
        try {
            putPayDataInIntent(rejectIntent)
            this.setResult(4, rejectIntent)
        } catch (var3: JSONException) {
            rejectIntent.putExtra("raw_pay_response", payDict.toString())
            this.setResult(5, rejectIntent)
        }
        finish()
    }

    private fun notifySuccesfulTransaction() {
        val successIntent = Intent()
        try {
            putPayDataInIntent(successIntent)
            this.setResult(6, successIntent)
            finish()
        } catch (var3: JSONException) {
            notifySuccesfulTransactionParsingIssue(payDict.toString())
        }
    }

    private fun notifySuccesfulTransactionParsingIssue(raw_pay_response: String?) {
        val successIntent = Intent()
        successIntent.putExtra("raw_pay_response", raw_pay_response)
        this.setResult(7, successIntent)
        finish()
    }

    @Throws(JSONException::class)
    private fun putPayDataInIntent(intent: Intent) {
        for (payDictKey in PAY_DICT_KEYS) {
            intent.putExtra(payDictKey, payDict!!.getString(payDictKey))
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 32) {
            when (resultCode) {
                2 -> {
                    notifyCancel3dSecure()
                }
                1 -> {
                    notifyCancel3dSecure()
                }
                17 -> {
                    val rawPayResponse = data!!.getStringExtra("raw_pay_response")
                    try {
                        payDict = rawPayResponse?.let { JSONObject(it) }
                        paymentInquiry()
                    } catch (var6: Exception) {
                        notifySuccesfulTransactionParsingIssue(rawPayResponse)
                    }
                }
            }
        }
    }

    private fun showProgressDialog() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setTitle(this.getString(R.string.processing))
        mProgressDialog!!.setMessage(this.getString(R.string.wait))
        mProgressDialog!!.setCancelable(false)
        status = Status.PROCESSING
        mProgressDialog!!.show()
        this.window.setFlags(16, 16)
    }

    private fun dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
        status = Status.IDLE
        this.window.clearFlags(16)
    }

    private fun linkViews() {
        val intent = this.intent
        val showActionBar = intent.getBooleanExtra("ActionBar", false)
        if (showActionBar) {
            val actionBar = this.supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            val colorDrawable = ColorDrawable(themeColor)
            actionBar!!.setBackgroundDrawable(colorDrawable)
        } else {
            Log.d("actionBar", "no actionBar")
        }
        val window = this.window
        window.addFlags(Int.MIN_VALUE)
        window.clearFlags(67108864)
        window.statusBarColor = themeColor
        nameText = findViewById(R.id.cardName)
        cardNumberText = findViewById(R.id.cardNumber)
        monthText = findViewById(R.id.expiryMonth)
        yearText = findViewById(R.id.expiryYear)
        cvvText = findViewById(R.id.cvv)

        payBtn = findViewById(R.id.pay)
        if (intent.getStringExtra("PAY_BUTTON_TEXT") != null && intent.getStringExtra("PAY_BUTTON_TEXT")!!
                .isNotEmpty()
        ) {
            payBtn?.text = intent.getStringExtra("PAY_BUTTON_TEXT")
        }
        payBtn?.setBackgroundColor(themeColor)
        payBtn?.setOnClickListener(this)
        cardNameLinearLayout = findViewById(R.id.cardName_linearLayout)
        expirationLinearLayout = findViewById(R.id.expiration_linearLayout)
    }

    private val acceptParameters: Unit
        get() {
            val intent = this.intent
            if (intent.hasExtra("email")) {
                billingData = JSONObject()
                readBillingData(intent)
                hasBilling = true
            }
            paymentKey = intent.getStringExtra("payment_key")
            checkIfPassed("payment_key", paymentKey)
            token = intent.getStringExtra("token")
            maskedPanNumber = intent.getStringExtra("masked_pan_number")
            if (token != null) {
                checkIfPassed("masked_pan_number", maskedPanNumber)
            }
            saveCardDefault = intent.getBooleanExtra("save_card_default", false)
            showSaveCard = intent.getBooleanExtra("show_save_card", true)
            verificationActivityTitle = intent.getStringExtra("three_d_secure_activity_title")
            if (verificationActivityTitle == null) {
                verificationActivityTitle = ""
            }
        }

    private fun readBillingData(intent: Intent) {
        try {
            readBillingValue(intent, "first_name")
            readBillingValue(intent, "last_name")
            readBillingValue(intent, "building")
            readBillingValue(intent, "floor")
            readBillingValue(intent, "apartment")
            readBillingValue(intent, "city")
            readBillingValue(intent, "state")
            readBillingValue(intent, "country")
            readBillingValue(intent, "email")
            readBillingValue(intent, "phone_number")
            readBillingValue(intent, "postal_code")
            Log.d("notice", "finished reading billing data")
        } catch (ignored: JSONException) {
        }
    }

    @Throws(JSONException::class)
    private fun readBillingValue(intent: Intent, key: String) {
        val value = intent.getStringExtra(key)
        checkIfPassed(key, value)
        billingData!!.put(key, value)
    }

    private fun checkIfPassed(key: String, value: String?) {
        if (value == null) {
            abortForNotPassed(key)
        }
    }

    private fun abortForNotPassed(key: String) {
        val errorIntent = Intent()
        errorIntent.putExtra("missing_argument_value", key)
        this.setResult(2, errorIntent)
        finish()
    }

    private fun resetVariables() {
        billingData = null
        paymentKey = null
        token = null
        maskedPanNumber = null
        showSaveCard = true
        saveCardDefault = false
        mProgressDialog = null
        payDict = null
        verificationActivityTitle = null
        status = Status.IDLE
        hasBilling = false
    }

    @SuppressLint("SetTextI18n")
    private fun updateLayout() {
        if (token != null) {
            invalidateOptionsMenu()
            expirationLinearLayout!!.visibility = View.GONE
            cardNumberText!!.hint = maskedPanNumber
            cardNumberText!!.setHintTextColor(this.resources.getColor(R.color.colorText))
            cardNumberText!!.isEnabled = false
            cardNumberText!!.isFocusable = false
            val intent = this.intent
            themeColor = intent.getIntExtra(
                "theme_color",
                this.applicationContext.resources.getColor(R.color.colorPrimaryDark)
            )
        }
    }

    override fun attachBaseContext(newBase: Context) {
        localeManager = LocaleManager(newBase)
        super.attachBaseContext(localeManager!!.setLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager!!.setLocale(this)
        super.onConfigurationChanged(newConfig)
        if (newConfig.locale === Locale.ENGLISH) {
            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show()
        } else if (newConfig.locale === Locale.FRENCH) {
            Toast.makeText(this, "French", Toast.LENGTH_SHORT).show()
        }
    }

    internal enum class Status {
        IDLE, PROCESSING
    }

    companion object {
        var localeManager: LocaleManager? = null
        val PAY_DICT_KEYS = arrayOf(
            "amount_cents",
            "is_refunded",
            "is_capture",
            "captured_amount",
            "source_data.type",
            "pending",
            "merchant_order_id",
            "is_3d_secure",
            "id",
            "is_void",
            "currency",
            "is_auth",
            "is_refund",
            "owner",
            "is_voided",
            "source_data.pan",
            "profile_id",
            "success",
            "data.message",
            "source_data.sub_type",
            "error_occured",
            "is_standalone_payment",
            "created_at",
            "refunded_amount_cents",
            "integration_id",
            "order"
        )
    }
}

internal object FormChecker {
    fun checkCVV(cvvString: String?): Boolean {
        return cvvString != null && cvvString.length == 3
    }

    fun checkCardName(nameString: String?): Boolean {
        return !nameString.isNullOrEmpty()
    }

    fun checkCardNumber(numberString: String?): Boolean {
        return numberString != null && numberString.length == 16
    }

    fun checkDate(monthString: String?, yearString: String?): Boolean {
        return if (monthString != null && monthString.length == 2 && yearString != null && yearString.length == 2) {
            val yearDiff = yearString.toInt() - SimpleDateFormat("yy", Locale.GERMANY)
                .format(Date()).toInt()
            val monthDiff = monthString.toInt() - SimpleDateFormat("MM", Locale.GERMANY)
                .format(Date()).toInt()
            yearDiff > 0 || yearDiff == 0 && monthDiff >= 0
        } else {
            false
        }
    }
}