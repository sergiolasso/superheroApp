package com.example.proyectoyoutube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.View.OnClickListener
import com.example.proyectoyoutube.databinding.ActivityDetailSuperheroBinding
import com.example.proyectoyoutube.databinding.ActivitySuperHeroListBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding : ActivityDetailSuperheroBinding

    private var valorCombate : Int? = null

    companion object{

        const val EXTRA_ID = "extra_id"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id : String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)

        binding.botonBatalla.setOnClickListener(this)

    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = getRetrofit().create(ApiService::class.java).getSuperheroesDetail(id)

            if(superHeroDetail.body() != null){
                runOnUiThread{
                    createUi(superHeroDetail.body()!!)
                }

            }

        }
    }

    private fun createUi(superHero : SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperhero)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerstats)
        binding.tvSuperHeroRealName.text = superHero.biografyDetail.fullName
        binding.tvPublisher.text = superHero.biografyDetail.published
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.duravility)
        updateHeight(binding.viewIntelligentece, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        valorCombate = try {
            powerstats.combat.toFloat().roundToInt()
        } catch (e: NumberFormatException) {
            0
        }

    }

    private fun updateHeight(view : View, stat : String){
        val floatStat = try {
            stat?.toFloat() ?: 0f
        } catch (e: NumberFormatException) {
            0f
        }

        val params = view.layoutParams
        params.height = pxToDp(floatStat)
        view.layoutParams = params

    }

    private fun pxToDp( px : Float ) : Int{
       return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.botonBatalla.id -> {
                val intent = Intent(applicationContext, BatallaActivity::class.java)
                intent.putExtra("valor_combate", valorCombate)
                startActivity(intent)
            }
        }
    }
}