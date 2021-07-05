package hn.edu.ujcv.pdm_2021_ii_pii_laboratorio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Alumnos
import hn.edu.ujcv.pdm_2021_ii_p2_laboratorio2.Clases.Clases
import kotlinx.android.synthetic.main.activity_matricula.*

class MatriculaActivity   : AppCompatActivity(){
    var valoresAlumnos    : HashMap<Int, Alumnos> = hashMapOf()
    var cuentasAlumnos    : ArrayList<Int> = ArrayList()
    var valoresClases3    : HashMap<Int, Clases> = hashMapOf()
    var nombresClases     : ArrayList<String> = ArrayList()
    var listItems         : ArrayList<String> = ArrayList()
    var adapter3          : ArrayAdapter<String>? = null

    var registroMatriculas: HashMap<Alumnos, ArrayList<Clases>> = hashMapOf()


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matricula)
        //prubas()
        inicializarAlumnos()
        inicializarClases()
        btnGuardarMat.setOnClickListener { guardarMatricula() }
        btnAgregar.setOnClickListener { agregar() }
        btnRegresar3.setOnClickListener { regresar() }
    }

   /*private fun prubas() {
        val alumno1 = Alumnos()
        alumno1.NumeroCuenta = 1
        alumno1.Nombre       = "Edu"
        alumno1.Correo       = "Gmail"

        val alumno2 = Alumnos()
        alumno2.NumeroCuenta = 2
        alumno2.Nombre       = "Mike"
        alumno2.Correo       = "Yahoo"
        Alumnos.Alumnos.put(alumno1.NumeroCuenta, alumno1)
        Alumnos.Alumnos.put(alumno2.NumeroCuenta, alumno2)

        val clase1 = Clases()
        clase1.codigo   = 1
        clase1.nombre   = "Sistemas"
        clase1.seccion  = "A"
        clase1.hora     = "12:00"
        clase1.edificio = "E1"
        clase1.aula     = "2001"

        val clase2 = Clases()
        clase2.codigo   = 2
        clase2.nombre   = "Programacion"
        clase2.seccion  = "B"
        clase2.hora     = "12:00"
        clase2.edificio = "E1"
        clase2.aula     = "2001"

        val clase3 = Clases()
        clase3.codigo   = 3
        clase3.nombre   = "Matematicas"
        clase3.seccion  = "C"
        clase3.hora     = "12:00"
        clase3.edificio = "E1"
        clase3.aula     = "2001"

        val clase4 = Clases()
        clase4.codigo   = 4
        clase4.nombre   = "Ingles"
        clase4.seccion  = "D"
        clase4.hora     = "12:00"
        clase4.edificio = "E1"
        clase4.aula     = "2001"

        Clases.Clases.put(clase1.codigo, clase1)
        Clases.Clases.put(clase2.codigo, clase2)
        Clases.Clases.put(clase3.codigo, clase3)
        Clases.Clases.put(clase4.codigo, clase4)
    }*/

    private fun limpiarDatos(){
        listItems.clear()

        adapter3?.notifyDataSetChanged()
        adapter3 = ArrayAdapter(this,android.R.layout.simple_list_item_1, listItems)
    }

    private fun regresar(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun guardarMatricula(){
        if (listItems.size == 0){
            Toast.makeText(applicationContext, "No ha clases registradas", Toast.LENGTH_SHORT).show()
        }else{
            val listaClases = ArrayList<Clases>()
            for (clase in valoresClases3){
                for (item in listItems){
                    if (clase.value.nombre.equals(item)){
                        listaClases.add(clase.value)
                    }
                }
            }

            val alumnoActual = valoresAlumnos[spinner1.selectedItem.toString().toInt()]!!

            Alumnos.Matriculas.put(alumnoActual, listaClases)

            Toast.makeText(applicationContext, "Guardado", Toast.LENGTH_SHORT).show()
            limpiarDatos()
        }
    }//-----

    private fun inicializarAlumnos(){
        val intentValores = getIntent().extras

        if (intentValores?.get("alumnosEnviados") != null){
            valoresAlumnos = intentValores["alumnosEnviados"] as HashMap<Int, Alumnos>
        }

        for (numeroCuenta in valoresAlumnos){
            cuentasAlumnos.add(numeroCuenta.value.NumeroCuenta)
        }

        var adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_item,cuentasAlumnos)
        spinner1.adapter = adapter1
    }

    private fun inicializarClases(){
        val intentValores = getIntent().extras

        if (intentValores?.get("valoresClases2") !=null){
            valoresClases3 = intentValores["valoresClases2"] as HashMap<Int, Clases>
        }

        for (nombreClase in valoresClases3){
            nombresClases.add(nombreClase.value.nombre)
        }

        var adapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item,nombresClases)
        spinner2.adapter = adapter2
    }

    private fun agregar(){
        var itemClase = ""
        itemClase = spinner2.selectedItem.toString()
        listItems.add(itemClase)
        adapter3?.notifyDataSetChanged()
        adapter3 = ArrayAdapter(this,android.R.layout.simple_list_item_1, listItems)
        lstView.adapter = adapter3
        Snackbar.make(MatriculaLayout, "Item añadido a la lista", Snackbar.LENGTH_LONG).setAction("Deshacer",desHacerOnClickListener).show()
    }

    var desHacerOnClickListener: View.OnClickListener = View.OnClickListener { view ->
        listItems.removeAt(listItems.size - 1)
        adapter3?.notifyDataSetChanged()
        Snackbar.make(view, "Item eliminado", Snackbar.LENGTH_LONG)
            .setAction("Deshacer", null).show()
    }




}