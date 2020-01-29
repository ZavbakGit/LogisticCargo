package `fun`.gladkikh.logisticcargo

import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.ui.BaseActivity
import `fun`.gladkikh.common.ui.ext.onEvent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity:BaseActivity(){
    lateinit var viewModel: SecondViewModel
    override val contentId = R.layout.activity_second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModel = viewModel {
            onEvent(message, ::handleMessage)
            onEvent(failureData, ::handleError)
        }

        tvHello.setOnClickListener {
            viewModel.start()
        }

        btCancel.setOnClickListener {
            viewModel.stop()
        }

    }


    private fun handleMessage(message: String?) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(failure: Failure?) {
        Toast.makeText(this,failure.toString(), Toast.LENGTH_SHORT).show()
    }



}