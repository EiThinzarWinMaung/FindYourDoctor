package com.etzwm.healthcareapp.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.etzwm.healthcareapp.R
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Image Slider
        var hospitalsBanner: IntArray = intArrayOf(
            R.drawable.banner_aryu,
            R.drawable.banner_asia_royal,
            R.drawable.banner_aung_yadana,
            R.drawable.banner_bahosi,
            R.drawable.banner_grand_hantha,
            R.drawable.banner_kan_thar_yar,
            R.drawable.banner_osc,
            R.drawable.banner_pinlon,
            R.drawable.banner_pun_hlaing_siloam,
            R.drawable.banner_ssc,
            R.drawable.banner_thukha_gabar_1,
            R.drawable.banner_victoria)

        if(ViewFlipperBanner != null) {
            ViewFlipperBanner.setInAnimation(context, android.R.anim.fade_in)
            ViewFlipperBanner.setInAnimation(context, android.R.anim.fade_out)
        }

        if(ViewFlipperBanner != null) {
            for(img: Int in hospitalsBanner) {
                val imageView = ImageView(context)
                val layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                layoutParams.setMargins(0, 0, 0, 0)
                imageView.layoutParams = layoutParams
                imageView.setBackgroundResource(img)
                ViewFlipperBanner.addView(imageView)
            }
        }

        FindSpecialistCard.setOnClickListener {
            var action = StartFragmentDirections.actionNavigationStartToNavigationFindSpecialists()
            findNavController().navigate(action)
        }

        MedicalCheckupCard.setOnClickListener {
            var action = StartFragmentDirections.actionNavigationStartToMedicalCheckupPackagesByHospitalFragment()
            findNavController().navigate(action)
        }

        HospitalContactsCard.setOnClickListener {
            var action = StartFragmentDirections.actionNavigationStartToHospitalContactsFragment()
            findNavController().navigate(action)
        }

        RecommendSuggestCard.setOnClickListener {
            var action = StartFragmentDirections.actionNavigationStartToNavigationRecommendSuggest()
            findNavController().navigate(action)
        }
    }
}