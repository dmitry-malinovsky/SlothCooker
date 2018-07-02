package com.example.dmalinovschi.viewModels.RecipeFeed

import android.os.Parcel
import android.os.Parcelable

class RecipeRowModel(var totalCarbs: Int,
                     var totalProtein: Int,
                     var totalFat: Int,
                     var totalCcal: Int,
                     var title: String,
                     var id: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalCarbs)
        parcel.writeInt(totalProtein)
        parcel.writeInt(totalFat)
        parcel.writeInt(totalCcal)
        parcel.writeString(title)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeRowModel> {
        override fun createFromParcel(parcel: Parcel): RecipeRowModel {
            return RecipeRowModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipeRowModel?> {
            return arrayOfNulls(size)
        }
    }


}