package com.example.mycallerapp.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycallerapp.activity.MainActivity
import com.example.mycallerapp.adpters.ContactsAdapter
import com.example.mycallerapp.databinding.FragmentContectsBinding
import com.example.mycallerapp.interfaces.OnCallBtnClickListener
import com.example.mycallerapp.viewmodel.CallerViewModel


class ContactsFragment : Fragment() {

    lateinit var viewModel : CallerViewModel
    lateinit var contactsAdapter : ContactsAdapter
    lateinit var binding : FragmentContectsBinding
    lateinit var Mobile : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var context : Context = requireContext()
        binding = FragmentContectsBinding.inflate(layoutInflater,container,false)

         viewModel = (activity as MainActivity).viewModel
        binding.rvContacts.layoutManager = LinearLayoutManager(context)

        checkPermission()

        // Inflate the layout for this fragment
        return binding.root
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS),1001)
        }else {
            contacts()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            contacts()
        }
        else {
           checkPermission()

        }
        if (requestCode==1004 && grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            callIntent(Mobile)
        }else{
           makeCall(Mobile)
        }
    }

    private fun contacts(){
        viewModel.showContects().observe(viewLifecycleOwner, Observer {
            contactsAdapter = ContactsAdapter(it,object : OnCallBtnClickListener{
                override fun onCallBtnClick(mob: String) {
                    makeCall(mob)
                    Mobile = mob
                }

            })
            binding.rvContacts.adapter = contactsAdapter
            contactsAdapter.notifyDataSetChanged()
        })
    }

    fun makeCall(mobile:String){
        if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),1004)
        }else{

            callIntent(mobile)
        }



    }
    fun callIntent(mobile: String){
        var intentCall = Intent(Intent.ACTION_CALL)
        intentCall.data = Uri.parse("tel:$mobile")
        startActivity(intentCall)
    }
}