package cat.itb.m78.exercices.viewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class PurchaseList : ViewModel(){
    class ShopItem(
        var item: String,
        var quantity: Int
    )

    val list = mutableStateOf(mutableListOf<ShopItem>())
    val name = mutableStateOf("")
    val quantity = mutableStateOf(0)

    fun addItem(){
        list.value.add(ShopItem(name.value, quantity.value))
    }
    fun updateName(value : String){name.value = value}
    fun updateQuantity(value : Int){quantity.value = value}
}

@Composable
fun FunPurchaseListView(){
    val buttonText = "Add"
    val fieldName = "Name"
    val fieldQuantity = "Quantity"
    val viewModel = viewModel { PurchaseList() }

    FunPurchaseList(viewModel.name.value, viewModel.quantity.value, fieldName, fieldQuantity,
                    buttonText, viewModel.list.value, viewModel::addItem,
                    viewModel::updateName, viewModel::updateQuantity)
}

@Composable
fun FunPurchaseList(inputName : String,
                    inputQuantity : Int,
                    fieldName : String,
                    fieldQuantity : String,
                    buttonText : String,
                    listItems : List<PurchaseList.ShopItem>,
                    addItem : ()->Unit,
                    updateName : (String)->Unit,
                    updateQuantity : (Int)->Unit){

    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Card(){
            TextField(inputName,
                label = {Text(fieldName)},
                onValueChange = { value -> updateName(value) })

            Spacer(Modifier.height(5.dp))

            TextField(inputQuantity.toString(),
                label = {Text(fieldQuantity)},
                onValueChange = { value -> updateQuantity(value.toInt()) })

            Button( onClick = {addItem()}){ Text(buttonText)}
        }
        LazyColumn(){
            items(listItems) { item ->
                Card(modifier = Modifier.padding(8.dp)){
                    Row(){
                        Text("Item: ${item.item}")
                        Text("Quantity: ${item.quantity}")
                    }
                }
            }
        }
    }
}