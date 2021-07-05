package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Clases
import kotlinx.android.synthetic.main.activity_matricula.*
import kotlinx.android.synthetic.main.fragment_correo.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [CorreoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CorreoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var listItems    : ArrayList<String> = ArrayList()
    var adapterFrag  : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //agregar()
        btnEnviar.setOnClickListener { enviarCorreo() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_correo, container, false)
    }

    private fun enviarCorreo() {
        var recipiente:  String = fraPara.text.toString()
        var asunto:      String = fraAsunto.text.toString()
        var mensaje:     String = fraMensaje.text.toString()
        val addressees = recipiente.split(",".toRegex()).toTypedArray()
        if(recipiente.isEmpty()) {
            Toast.makeText(activity, "Escriba el correo destinatario", Toast.LENGTH_LONG).show()
        }else if(asunto.isEmpty()){
            Toast.makeText(activity, "Escriba el asunto del correo", Toast.LENGTH_LONG).show()
        }else if(mensaje.isEmpty()){
            Toast.makeText(activity, "Escriba un mensaje en el correo", Toast.LENGTH_LONG).show()
        }else {
            val intent = Intent(Intent.ACTION_SEND)
            var data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, addressees)
            intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
            intent.putExtra(Intent.EXTRA_TEXT, mensaje)
            intent.setType("message/rfc822")
            startActivity(Intent.createChooser(intent,"Eliga un correo"))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CorreoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CorreoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}