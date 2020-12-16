package com.openclassrooms.realestatemanager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PoiResult {

    inner class Geometry {

        @SerializedName("location")
        @Expose
        var location: Location? = null
        @SerializedName("viewport")
        @Expose
        var viewport: Viewport? = null

    }

    inner class Location {

        @SerializedName("lat")
        @Expose
        var lat: Double? = null
        @SerializedName("lng")
        @Expose
        var lng: Double? = null

    }

    inner class Northeast {

        @SerializedName("lat")
        @Expose
        var lat: Double? = null
        @SerializedName("lng")
        @Expose
        var lng: Double? = null

    }

    inner class OpeningHours {

        @SerializedName("open_now")
        @Expose
        var openNow: Boolean? = null

    }

    inner class Photo {

        @SerializedName("height")
        @Expose
        var height: Int? = null
        @SerializedName("html_attributions")
        @Expose
        var htmlAttributions: List<String>? = null
        @SerializedName("photo_reference")
        @Expose
        var photoReference: String? = null
        @SerializedName("width")
        @Expose
        var width: Int? = null

    }

    inner class PlusCode {

        @SerializedName("compound_code")
        @Expose
        var compoundCode: String? = null
        @SerializedName("global_code")
        @Expose
        var globalCode: String? = null

    }

    inner class PoiResult {

        @SerializedName("html_attributions")
        @Expose
        var htmlAttributions: List<Any>? = null
        @SerializedName("next_page_token")
        @Expose
        var nextPageToken: String? = null
        @SerializedName("results")
        @Expose
        var results: List<Result>? = null
        @SerializedName("status")
        @Expose
        var status: String? = null

    }

    inner class Result {

        @SerializedName("geometry")
        @Expose
        var geometry: Geometry? = null
        @SerializedName("icon")
        @Expose
        var icon: String? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("photos")
        @Expose
        var photos: List<Photo>? = null
        @SerializedName("place_id")
        @Expose
        var placeId: String? = null
        @SerializedName("reference")
        @Expose
        var reference: String? = null
        @SerializedName("scope")
        @Expose
        var scope: String? = null
        @SerializedName("types")
        @Expose
        var types: List<String>? = null
        @SerializedName("vicinity")
        @Expose
        var vicinity: String? = null
        @SerializedName("business_status")
        @Expose
        var businessStatus: String? = null
        @SerializedName("permanently_closed")
        @Expose
        var permanentlyClosed: Boolean? = null
        @SerializedName("plus_code")
        @Expose
        var plusCode: PlusCode? = null
        @SerializedName("rating")
        @Expose
        var rating: Double? = null
        @SerializedName("user_ratings_total")
        @Expose
        var userRatingsTotal: Int? = null
        @SerializedName("opening_hours")
        @Expose
        var openingHours: OpeningHours? = null

    }

    inner class Southwest {

        @SerializedName("lat")
        @Expose
        var lat: Double? = null
        @SerializedName("lng")
        @Expose
        var lng: Double? = null

    }

    inner class Viewport {

        @SerializedName("northeast")
        @Expose
        var northeast: Northeast? = null
        @SerializedName("southwest")
        @Expose
        var southwest: Southwest? = null

    }

}