package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Alumnos
import kotlinx.android.synthetic.main.activity_notas.*
import kotlinx.android.synthetic.main.fragment_correo.*

class CorreoActivity : AppCompatActivity() {
    var listItems    : ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correo)
        iniciar()
        btnBuscar.setOnClickListener { buscar() }
    }

    private fun iniciar() {
        for (cuentaAlumno in Alumnos.Matriculas){
            listItems.add (cuentaAlumno.key.NumeroCuenta)
        }
        var adapterFrag = ArrayAdapter(this,android.R.layout.simple_list_item_1, listItems)
        spinnerAlumno.adapter = adapterFrag
    }

    private fun buscar(){
        var mensaje = ""
        var infoClases = ""
        var alumnoSeleccionado = spinnerAlumno.selectedItem.toString().toInt()
        for(matricula in Alumnos.Matriculas){
            if (matricula.key.NumeroCuenta == alumnoSeleccionado) {
                fraPara.setText(matricula.key.Correo)
                for (clases in matricula.value) {
                    infoClases = infoClases + clases.toString() + "\n" + "\n"
                }
                mensaje = matricula.key.toString()+ "\n" + "\nClases Matriculadas son las siguientes: \n"+ "\n" + infoClases + "\n"
                fraMensaje.setText(mensaje)
            }
        }
    }
}