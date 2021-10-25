package com.shinetechchina.apa.paging3

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Arthur on 2021/10/25.
 */
abstract class Person() {
   abstract val name: String
}


data class Boy(override val name: String): Person()
data class Girl(override val name: String): Person()


sealed class PersonUiModel {

    class PersonModel(val title: String): PersonUiModel() {

        override fun toString(): String {
            return "PersonModel(title='$title')"
        }
    }

    object LoadMoreModel: PersonUiModel()
}


object UiModelComparator : DiffUtil.ItemCallback<PersonUiModel>() {
    override fun areItemsTheSame(
        oldItem: PersonUiModel,
        newItem: PersonUiModel
    ): Boolean {
        val isSameRepoItem = oldItem is PersonUiModel.PersonModel
                && newItem is PersonUiModel.PersonModel
                && oldItem.title == newItem.title

        val isSameSeparatorItem = oldItem is PersonUiModel.LoadMoreModel


        return isSameRepoItem || isSameSeparatorItem
    }

    override fun areContentsTheSame(
        oldItem: PersonUiModel,
        newItem: PersonUiModel
    ) = oldItem == newItem
}

data class ReqBody(
    var type: RequestType

)

enum class RequestType {

    Boy,

    Girl

}