package com.example.mycallerapp.fragments

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycallerapp.activity.MainActivity
import com.example.mycallerapp.adpters.MessageAdapter
import com.example.mycallerapp.databinding.CustomDialogMessageShowBinding
import com.example.mycallerapp.databinding.FragmentMessagesBinding
import com.example.mycallerapp.interfaces.OnMessageClick
import com.example.mycallerapp.models.MessagesItems
import com.example.mycallerapp.viewmodel.CallerViewModel


class MessagesFragment : Fragment() {

     lateinit var binding : FragmentMessagesBinding
     lateinit var rvAdapter: MessageAdapter
     lateinit var viewModel : CallerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMessagesBinding.inflate(layoutInflater,container,false)
        var context : Context = requireContext()
        binding.rvMessage.layoutManager = LinearLayoutManager(context)

        viewModel = (activity as MainActivity).viewModel

       checkPermission()
        // Inflate the layout for this fragment
        return binding.root
    }
   fun checkPermission(){
       if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_SMS) !=  PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_SMS),1003)
       }else{
           messageShow()
       }
   }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==1003 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            messageShow()
        }else{
           checkPermission()


        }
    }

    private fun messageShow(){
        viewModel.showMessages().observe(viewLifecycleOwner, Observer {
            rvAdapter = MessageAdapter(it,object :OnMessageClick{
                override fun onMessageClick(messagesItems: MessagesItems) {
                    msgDialog(messagesItems.Address,messagesItems.msgBody,messagesItems.date)
                }


            })
            binding.rvMessage.adapter = rvAdapter
            rvAdapter.notifyDataSetChanged()
        })
    }

    fun msgDialog(address:String,body:String,date:String){
        var bindingDialog = CustomDialogMessageShowBinding.inflate(layoutInflater)
        var dialogMsg = Dialog(requireContext())
        dialogMsg.setContentView(bindingDialog.root)
        var layoutparms = WindowManager.LayoutParams()
        layoutparms.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutparms.width = WindowManager.LayoutParams.MATCH_PARENT
        dialogMsg.window!!.attributes = layoutparms
        dialogMsg.show()
        dialogMsg.setCancelable(false)

        bindingDialog.msgAddress.text = address
        bindingDialog.msgBody.text = body
        bindingDialog.msgDate.text = date

        bindingDialog.dialogCancle.setOnClickListener {
            dialogMsg.dismiss()
        }
    }

}