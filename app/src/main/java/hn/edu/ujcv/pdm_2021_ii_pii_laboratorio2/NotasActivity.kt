package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Alumnos
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Clases
import kotlinx.android.synthetic.main.activity_notas.*

class NotasActivity : AppCompatActivity() {
    var matriculasRegistradas = HashMap<Alumnos, ArrayList<Clases>>()
    var cuentasAlumnos = ArrayList<Int>()
    var nombresClases = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)
        inicializarMatriculas()
        btnBuscarClases.setOnClickListener { buscarClases() }
        btnGuardarNotas.setOnClickListener { guardarNotas() }
    }



    private fun inicializarMatriculas(){
        val intentValores = getIntent().extras

        if (intentValores?.get("matriculasAgregadas") != null){
            matriculasRegistradas = intentValores["matriculasAgregadas"] as HashMap<Alumnos, ArrayList<Clases>>
        }

        for (matricula in matriculasRegistradas){
            cuentasAlumnos.add(matricula.key.NumeroCuenta)
        }

        var adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_item,cuentasAlumnos)
        spinnerAlumnos.adapter = adapter1
    }

    private fun buscarClases(){
        var Clases = ArrayList<String>()
        var itemSeleccionado = spinnerAlumnos.selectedItem.toString().toInt()
        for (matriculas in matriculasRegistradas){
            if (itemSeleccionado == matriculas.key.NumeroCuenta ){
                var clasesActuales : ArrayList<Clases>
                clasesActuales = matriculas.value
                for (nombresClases in  clasesActuales){
                    Clases.add(nombresClases.nombre)
                }
            }
        }
        var adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_item,Clases)
        spinnerClases.adapter = adapter1
    }

    private fun guardarNotas(){
        if (txtNota1.text.isEmpty()){
            Toast.makeText(applicationContext, "Nota 1 vacia ", Toast.LENGTH_SHORT).show()
        }else if(txtNota2.text.isEmpty()){
            Toast.makeText(applicationContext, "Nota 2 vacia ", Toast.LENGTH_SHORT).show()
        }else if(txtNota3.text.isEmpty()){
            Toast.makeText(applicationContext, "Nota 3 vacia ", Toast.LENGTH_SHORT).show()
        }else if(txtNota1.text.toString().toInt() < 0 || txtNota1.text.toString().toInt() > 100 ){
            Toast.makeText(applicationContext, "Nota 1 no valida ", Toast.LENGTH_SHORT).show()
        }else if(txtNota2.text.toString().toInt() < 0 || txtNota2.text.toString().toInt() > 100 ){
            Toast.makeText(applicationContext, "Nota 2 no valida ", Toast.LENGTH_SHORT).show()
        }else if(txtNota3.text.toString().toInt() < 0 || txtNota3.text.toString().toInt() > 100 ){
            Toast.makeText(applicationContext, "Nota 3 no valida ", Toast.LENGTH_SHORT).show()
        }else{
            var itemClase = spinnerClases.selectedItem
            var AlumnoSeleccionado = spinnerAlumnos.selectedItem.toString().toInt()

            for (matricula in Alumnos.Matriculas){
                if ( AlumnoSeleccionado == matricula.key.NumeroCuenta ){
                    for (matriculaNota in matricula.value){
                        if (matriculaNota.nombre.equals(itemClase)){
                            matriculaNota.n1 = txtNota1.text.toString().toDouble()
                            matriculaNota.n2 = txtNota2.text.toString().toDouble()
                            matriculaNota.n3 = txtNota3.text.toString().toDouble()
                        }
                    }
                }
            }
            var prueba = Alumnos.Matriculas
            Toast.makeText(applicationContext, "pruebita", Toast.LENGTH_SHORT).show()
        }

        txtNota1.text = null
        txtNota2.text = null
        txtNota3.text = null
    }
}