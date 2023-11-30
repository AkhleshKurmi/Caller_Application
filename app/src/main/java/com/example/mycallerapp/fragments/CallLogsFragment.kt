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
import com.example.mycallerapp.adpters.CallLogsAdapter
import com.example.mycallerapp.databinding.FragmentCallLogsBinding
import com.example.mycallerapp.interfaces.OnCallBtnClickListener
import com.example.mycallerapp.viewmodel.CallerViewModel


class CallLogsFragment : Fragment() {

lateinit var binding : FragmentCallLogsBinding
lateinit var viewModel: CallerViewModel
lateinit var rvAdapter: CallLogsAdapter
lateinit var Mobile:String
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var context : Context = requireContext()
        binding = FragmentCallLogsBinding.inflate(layoutInflater,container,false)
        binding.rvCallLog.layoutManager = LinearLayoutManager(context)

        viewModel = (activity as MainActivity).viewModel
       checkCallPermission()


        return binding.root
    }

        fun checkCallPermission (){
            if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CALL_LOG),1002)
            }else{
                getCallLogs()
            }
        }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 1002 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getCallLogs()
        }else{
           checkCallPermission()

        }
        if (requestCode==1004 && grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
          callIntent(Mobile)
        }else{
           makeCall(Mobile)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getCallLogs(){
        viewModel.showCallLog().observe(viewLifecycleOwner, Observer {
            rvAdapter = CallLogsAdapter(it,object : OnCallBtnClickListener{
                override fun onCallBtnClick(mob: String) {
                    makeCall(mob)
                    Mobile = mob
                }

            })
            binding.rvCallLog.adapter = rvAdapter
            rvAdapter.notifyDataSetChanged()
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