package com.etzwm.healthcareapp.ui.recommend_suggest_write

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etzwm.healthcareapp.R
import com.etzwm.healthcareapp.api.ApiClient
import com.etzwm.healthcareapp.model.hospitals.Hospital
import com.etzwm.healthcareapp.model.recommend.DefaultResponse
import com.etzwm.healthcareapp.ui.hospital_contacts.HospitalContactsViewmodel
import kotlinx.android.synthetic.main.fragment_recommend_suggest_write.*
import kotlinx.android.synthetic.main.fragment_recommend_suggest_write.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RecommendSuggestWriteFragment : Fragment() {

    private var hospitalArray: List<Hospital> = ArrayList()
    lateinit var hospitalContactsViewmodel: HospitalContactsViewmodel
    lateinit var spinnerHospital: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recommend_suggest_write, container, false)

        var view = inflater.inflate(R.layout.fragment_recommend_suggest_write, container, false)

        spinnerHospital = view.findViewById(R.id.spinnerHospital)

//        var spinnerResult = spinnerHospital?.selectedItemId?.toInt()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDate: String = simpleDateFormat.format(Date())

//        Log.d("Date >>>>>>>", currentDate)
//        Log.d("Spinner >>>>>>", spinnerHospital?.selectedItemId?.toString())

//        view.btnRecommendPost.setOnClickListener {
//            var userPhone: String
//            var userEmail: String
//
//            if(TextUtils.isEmpty(edttxtEmail.text.toString())) {
//                userEmail = "-"
//            }
//            else {
//                userEmail = edttxtEmail.text.toString()
//            }
//
//            if(TextUtils.isEmpty(edttxtMobileNumber.text.toString())) {
//                userPhone = "-"
//            }
//            else {
//                userPhone = edttxtMobileNumber.text.toString()
//            }
//
//            if (TextUtils.isEmpty(edttxtName.text.toString())) {
//                Toast.makeText(context, "အမည်ဖြည့်သွင်းရန်လိုအပ်ပါသည်", Toast.LENGTH_SHORT).show()
//            }
//            else if (TextUtils.isEmpty(edttxtWrite.text.toString())) {
//                Toast.makeText(context, "အကြံပြုထောက်ခံချက်များရေးသားရန်လိုအပ်ပါသည်", Toast.LENGTH_SHORT).show()
//            }
// //           else if (spinnerHospital == null && spinnerHospital.getSelectedItem() == null ) {
//            else if (spinnerHospital?.selectedItemId?.toString() == "0") {
//                Toast.makeText(context, "ဆေးရုံရွေးချယ်ရန်လိုအပ်ပါသည်", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                val builder = AlertDialog.Builder(context)
//                builder.apply {
//                    setTitle("အတည်ပြုချက်တောင်းခြင်း")
//                    setMessage("သင်၏အမည်ကိုဖော်ပြရန်သဘောတူပါသလား ?")
//                    setIcon(R.drawable.ic_confirmation)
//
//                    setPositiveButton("သဘောတူပါသည်") {
//                            dialogInterface, i ->
//                        Toast.makeText(context, "လုပ်ဆောင်မှုအောင်မြင်ပါသည်", Toast.LENGTH_SHORT).show()
//                        Log.d("Name >>>>>>>>", edttxtName.text.toString())
//                        Log.d("Email >>>>>>>>", userEmail)
//                        Log.d("Mobile Number >>>>>>>>", userPhone)
//                        Log.d("Write >>>>>>>>", edttxtWrite.text.toString())
//                        Log.d("Date Click >>>>>>>>", currentDate)
//                        Log.d("Spinner Click >>>>>>>>", "value " + spinnerHospital?.selectedItemId.toInt())
//                        edttxtName.text.clear()
//                        edttxtEmail.text.clear()
//                        edttxtMobileNumber.text.clear()
//                        edttxtWrite.text.clear()
//                        spinnerHospital.setSelection(0)
//                    }
//                    setNegativeButton("သဘောမတူပါ") {
//                            dialogInterface, i ->
//                        Toast.makeText(context, "လုပ်ဆောင်မှုအောင်မြင်ပါသည်", Toast.LENGTH_SHORT).show()
//                        Log.d("Name >>>>>>>>", "Anonymous")
//                        Log.d("Email >>>>>>>>", userEmail)
//                        Log.d("Mobile Number >>>>>>>>", userPhone)
//                        Log.d("Write >>>>>>>>", edttxtWrite.text.toString())
//                        Log.d("Date Click >>>>>>>>", currentDate)
//                        Log.d("Spinner Click >>>>>>>>", "value " + spinnerHospital?.selectedItemId.toInt())
//                        edttxtName.text.clear()
//                        edttxtEmail.text.clear()
//                        edttxtMobileNumber.text.clear()
//                        edttxtWrite.text.clear()
//                        spinnerHospital.setSelection(0)
//                    }
// //                   dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(COLOR_I_WANT)
//                }
//                val alertDialog: AlertDialog = builder.create()
//                alertDialog.show()
//                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#02679A"))
//                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#02679A"))
//            }
//        }

        view.btnRecommendPost.setOnClickListener {

            var userPhone: String
            var userEmail: String

            if(TextUtils.isEmpty(edttxtEmail.text.toString())) {
                userEmail = "-"
            }
            else {
                userEmail = edttxtEmail.text.toString()
            }

            if(TextUtils.isEmpty(edttxtMobileNumber.text.toString())) {
                userPhone = "-"
            }
            else {
                userPhone = edttxtMobileNumber.text.toString()
            }

            if (TextUtils.isEmpty(edttxtName.text.toString())) {
                Toast.makeText(context, "အမည်ဖြည့်သွင်းရန်လိုအပ်ပါသည်", Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(edttxtWrite.text.toString())) {
                Toast.makeText(context, "အကြံပြုထောက်ခံချက်များရေးသားရန်လိုအပ်ပါသည်", Toast.LENGTH_SHORT).show()
            }
            else if (spinnerHospital?.selectedItemId?.toString() == "0") {
                Toast.makeText(context, "ဆေးရုံရွေးချယ်ရန်လိုအပ်ပါသည်", Toast.LENGTH_SHORT).show()
            }
            else {
                val builder = AlertDialog.Builder(context)
                builder.apply {
                    setTitle("အတည်ပြုချက်တောင်းခြင်း")
                    setMessage("သင်၏အမည်ကိုဖော်ပြရန်သဘောတူပါသလား ?")
                    setIcon(R.drawable.ic_confirmation)

                    setPositiveButton("သဘောတူပါသည်") {
                            dialogInterface, i ->
                        var apiClient = ApiClient()
                        var call = apiClient.writeRecommendSuggest(
                            edttxtName.text.toString(),
                            userEmail,
                            userPhone,
                            edttxtWrite.text.toString(),
                            currentDate,
                            spinnerHospital?.selectedItemId.toInt()
                        )

                        call.enqueue(object: Callback<DefaultResponse> {
                            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                                Log.d("Error >>>>>>>>", t.toString())
                            }

                            override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
//                    txtPostResponse.text = response.body().toString()
//                    Log.d("Success >>>>>>>>", response.body()?.success.toString())
                                Toast.makeText(context, "လုပ်ဆောင်မှုအောင်မြင်ပါသည်", Toast.LENGTH_SHORT).show()
                                edttxtName.text.clear()
                                edttxtEmail.text.clear()
                                edttxtMobileNumber.text.clear()
                                edttxtWrite.text.clear()
                                spinnerHospital.setSelection(0)
                            }
                        })
                    }
                    setNegativeButton("သဘောမတူပါ") {
                            dialogInterface, i ->
                        var apiClient = ApiClient()
                        var call = apiClient.writeRecommendSuggest(
                            "Anonymous (အမည်မသိ)",
                            userEmail,
                            userPhone,
                            edttxtWrite.text.toString(),
                            currentDate,
                            spinnerHospital?.selectedItemId.toInt()
                        )

                        call.enqueue(object: Callback<DefaultResponse> {
                            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                                Log.d("Error >>>>>>>>", t.toString())
                            }

                            override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
//                    txtPostResponse.text = response.body().toString()
//                    Log.d("Success >>>>>>>>", response.body()?.success.toString())
                                Toast.makeText(context, "လုပ်ဆောင်မှုအောင်မြင်ပါသည်", Toast.LENGTH_SHORT).show()
                                edttxtName.text.clear()
                                edttxtEmail.text.clear()
                                edttxtMobileNumber.text.clear()
                                edttxtWrite.text.clear()
                                spinnerHospital.setSelection(0)
                            }
                        })
                    }
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#02679A"))
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#02679A"))
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelHospital()
    }

    fun observeViewModelHospital() {
        hospitalContactsViewmodel = ViewModelProvider(this).get(HospitalContactsViewmodel::class.java)
        hospitalContactsViewmodel.loadHospital()
        hospitalContactsViewmodel.getHospital().observe(viewLifecycleOwner, Observer { hospital ->
            hospitalArray = hospital.hospitals

            var data: MutableList<String> = ArrayList()
            data.add(0, "ဆေးရုံရွေးရန် *")
            data.add(1, hospitalArray.get(0).hospital_name)
            data.add(2, hospitalArray.get(1).hospital_name)
            data.add(3, hospitalArray.get(2).hospital_name)
            data.add(4, hospitalArray.get(3).hospital_name)
            data.add(5, hospitalArray.get(4).hospital_name)
            data.add(6, hospitalArray.get(5).hospital_name)
            data.add(7, hospitalArray.get(6).hospital_name)
            data.add(8, hospitalArray.get(7).hospital_name)
            data.add(9, hospitalArray.get(8).hospital_name)
            data.add(10, hospitalArray.get(9).hospital_name)
            data.add(11, hospitalArray.get(10).hospital_name)
            data.add(12, hospitalArray.get(11).hospital_name)

//            hospitalArray.forEach {
//                data.add(1, it.hospital_name)
//            }

            spinnerHospital.adapter = context?.let {
                ArrayAdapter<String>(it, R.layout.support_simple_spinner_dropdown_item, data)
            }
        })
    }
}