package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Alumnos
import kotlinx.android.synthetic.main.activity_alumno.*

class AlumnoActivity : AppCompatActivity() {
    var valoresAlumno: HashMap<Int, Alumnos> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno)
        btnGuardar.setOnClickListener { guardar() }
        btnRegresar.setOnClickListener { enviar() }
    }


    private fun enviar() {
        val intentAlumnos = Intent(this, MainActivity::class.java)
        startActivity(intentAlumnos)

    }

    private fun guardar() {
        if(txtNumCuenta.text.isEmpty()){
            Toast.makeText(applicationContext, "El numero de cuenta no puede estar vacio", Toast.LENGTH_LONG).show()
        }else if(txtNombre.text.isEmpty()) {
            Toast.makeText(applicationContext, "El nombre del alumno no puede estar vacio", Toast.LENGTH_LONG).show()
        }else if(txtCorreo.text.isEmpty()){
            Toast.makeText(applicationContext, "El correo no puede estar vacio", Toast.LENGTH_LONG).show()
        }else{
            val dato : Alumnos = Alumnos()
            dato.NumeroCuenta  = txtNumCuenta.text.toString().toInt()
            dato.Nombre        = txtNombre.text.toString()
            dato.Correo        = txtCorreo.text.toString()

            Alumnos.Alumnos.put(dato.NumeroCuenta, dato)
            Toast.makeText(applicationContext, "Datos agregados", Toast.LENGTH_SHORT).show()

            txtNumCuenta.text = null
            txtNombre.text    = null
            txtCorreo.text    = null
        }
    }
}