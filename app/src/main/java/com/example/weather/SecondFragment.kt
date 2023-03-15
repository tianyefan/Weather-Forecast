package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.weather.databinding.FragmentSecondBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
// key = b1625391b144c1963fdc1b447f9d8d3e
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        Toast.makeText(requireContext(), "City: $ct", Toast.LENGTH_SHORT).show()


        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ct = arguments?.getString("ct")?.toInt()

        val cities = arrayOf("440103","440305","310101","110102","320505","210102")
        val city = ct?.let { cities[ct] }

        var loc_str = ""
        var date_str = ""
        var weather_str = ""
        var wind_str = ""
        var pow_str = ""
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.buttonSecond3.setOnClickListener {
            val key = "b1625391b144c1963fdc1b447f9d8d3e"
            val url = "https://restapi.amap.com/v3/weather/weatherInfo?city=$city&key=$key&extensions=all"

            if (url.isNotEmpty()) {
                val dataFetch = OkHttpClient()
                val request = Request.Builder().url(url).build()
                dataFetch.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.i("Response", "Received Response from Server")
                        response.use{
                            if(!response.isSuccessful){
                                Log.e("HTTP ERROR", "Something was wrong,or not load")
                            } else{
                                val body = response.body?.string()
                                val json = JSONObject(body)
                                val fore = json["forecasts"].toString().drop(1).dropLast(1)
                                val others = JSONObject(fore)
                                val casts = JSONObject(fore)["casts"].toString()

                                val sind = casts.indexOf('{', casts.indexOf('{') + 1)
                                val eind = casts.indexOf('}', casts.indexOf('}') + 1)
                                val tomorrow = casts.substring(sind,eind+1)
                                val tomJson = JSONObject(tomorrow)
                                loc_str = loc_str.plus("City: ${others["city"]},  Province: ${others["province"]}   ")
                                date_str = date_str.plus("Date: ${tomJson["date"]},  Week: ${tomJson["week"]}    ")
                                weather_str = weather_str.plus("Weather Day: ${tomJson["dayweather"]}  ${tomJson["daytemp"]} Cel, \nWeather Night: ${tomJson["nightweather"]} ${tomJson["nighttemp"]} Cel")
                                wind_str = wind_str.plus("Wind Day: ${tomJson["daywind"]}, Wind Night: ${tomJson["nightwind"]}")
                                pow_str = pow_str.plus("Day Power: ${tomJson["daypower"]}, Night Power: ${tomJson["nightpower"]}")

                                Log.println(Log.INFO,"test", pow_str)
                                binding.textviewSecond5.text = loc_str
                                binding.textviewSecond.text = date_str
                                binding.textviewSecond4.text = weather_str
                                binding.textviewSecond2.text = wind_str
                                binding.textviewSecond3.text = pow_str
                            }
                        }
                    }
                })
            } else {
               binding.textviewSecond.text = "URL was EMPTY !!"
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}