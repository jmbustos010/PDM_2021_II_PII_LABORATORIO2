package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Clases
import kotlinx.android.synthetic.main.activity_clases.*

class ClasesActivity : AppCompatActivity() {
    var valoresClases: HashMap<Int, Clases> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clases)
        btnGuardar2.setOnClickListener { guardar() }
        btnRegresar2.setOnClickListener { regresar() }
    }

    private fun regresar() {
        val intentClases = Intent(this, MainActivity::class.java)
        startActivity(intentClases)
    }

    private fun guardar() {
        if (txtCodigo.text.isEmpty()){
            Toast.makeText(applicationContext, "El codigo no puede estar vacio", Toast.LENGTH_SHORT).show()
        }else if (txtNombre2.text.isEmpty()){
            Toast.makeText(applicationContext, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
        }else if (txtSeccion.text.isEmpty()){
            Toast.makeText(applicationContext, "La seccion no puede estar vacio", Toast.LENGTH_SHORT).show()
        }else if (txtHora.text.isEmpty()){
            Toast.makeText(applicationContext, "La hora no puede estar vacio", Toast.LENGTH_SHORT).show()
        }else if (txtEdificio.text.isEmpty()){
            Toast.makeText(applicationContext, "El edificio/piso no puede estar vacio", Toast.LENGTH_SHORT).show()
        }else if (txtAula.text.isEmpty()){
            Toast.makeText(applicationContext, "El aula no puede estar vacio", Toast.LENGTH_SHORT).show()
        }else{
            val dato : Clases = Clases()
            dato.codigo   = txtCodigo.text.toString().toInt()
            dato.nombre   = txtNombre2.text.toString()
            dato.seccion  = txtSeccion.text.toString()
            dato.hora     = txtHora.text.toString()
            dato.edificio = txtEdificio.text.toString()
            dato.aula     = txtAula.text.toString()

            Clases.Clases.put(dato.codigo, dato)
            Toast.makeText(applicationContext, "Datos agregados", Toast.LENGTH_SHORT).show()

            txtCodigo.text   = null
            txtNombre2.text  = null
            txtSeccion.text  = null
            txtHora.text     = null
            txtEdificio.text = null
            txtAula.text     = null
        }
    }
}