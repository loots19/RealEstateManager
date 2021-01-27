package com.openclassrooms.realestatemanager.database.dao

import android.content.ContentValues
import androidx.annotation.NonNull
import androidx.room.OnConflictStrategy
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.realestatemanager.R

class FakePropertyApi {
    companion object {

        fun prepopulateDatabase(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val property1 = ContentValues()
                    property1.put("id", 1)
                    property1.put("city", "Bouffémont")
                    property1.put("price", "1200000")
                    property1.put("type", "House")
                    property1.put("surface", "200")
                    property1.put("nbr_room", "6")
                    property1.put("nbr_bathRoom", "2")
                    property1.put("nbr_bedRoom", "4")
                    property1.put("address", "15 avenue berthelot 95570 ")
                    property1.put("date_entry", "24/11/2020")
                    property1.put("photo_cover", R.drawable.house1)
                    property1.put("description", "Within a modern, private and fully secure driveway with caretaker, a magnificent architect-designed house in perfect condition of approximately 200m2 of living space with 128m2 of outdoor space. This house consists of: On the ground floor, three bedrooms, two with bathroom / water and a separate toilet. These three bedrooms each overlook the outdoor spaces including a 40m2 garden and 20m2 Japanese garden. On the first floor, a double cathedral living room with fireplace opening onto two terraces, one of which is 20m2 and 10m2, and a fitted and equipped independent kitchen. On the 2nd and last floor, a master suite with bathroom, dressing room and an office space opening onto a terrace of 18m2. In the basement, a storage space of 20m2 and a closed double box attached to the house. This house, bright and quiet, with no real vis-à-vis is ideal for large families.\n")
                    property1.put("date_sale", "")
                    property1.put("property_lat", "49.04224")
                    property1.put("property_lng", "2.30468")
                    property1.put("agent_id", 1)
                    property1.put("agent_name", "loots")
                    property1.put("agent_mail","loots@gmail.com")


                    db.insert("property", OnConflictStrategy.IGNORE, property1)

                    val property2 = ContentValues()
                    property2.put("id", 2)
                    property2.put("city", "Domont")
                    property2.put("price", "1000000")
                    property2.put("type", "Duplex")
                    property2.put("surface", "180")
                    property2.put("nbr_room", "6")
                    property2.put("nbr_bathRoom", "2")
                    property2.put("nbr_bedRoom", "3")
                    property2.put("address", "1 rue lamartine  95330 ")
                    property2.put("date_entry", "24/11/2020")
                    property2.put("photo_cover", R.drawable.duplex3)
                    property2.put("description", "sublime duplex for sale in a recent building of high standing, highly secure with 24H / 24 concierge, quiet, not overlooked, great light, terraces and balconies on 2 levels overlooking a private garden. Accessible from the ground floor of the building, the recently renovated apartment is made up on the ground floor of a large entrance with a shower room and WC, a large living room, a storage room; In the basement a large bedroom with shower room and WC, a storage room; On the 1st floor a dining room with fitted kitchen, a 2nd living room, WC; on the 2nd floor 2 large suites, one with a walk-in shower room and the other with a bathroom; WC, dressing room and built-in cupboards. Elevator and interior stairs, central and electric air conditioning, central heating, safe, videophone on all levels, electric shutters, reinforced door. Guardian, digital code, intercom, alarm.\n")
                    property2.put("date_sale", "")
                    property2.put("property_lat", "49.04224")
                    property2.put("property_lng", "2.30468")
                    property2.put("agent_id", 1)
                    property2.put("agent_name", "david")
                    property2.put("agent_mail","david@gmail.com")



                    db.insert("property", OnConflictStrategy.IGNORE, property2)


                    val property3 = ContentValues()
                    property3.put("id", 3)
                    property3.put("city", "Ezanville")
                    property3.put("price", "1100000")
                    property3.put("type", "Flat")
                    property3.put("surface", "180")
                    property3.put("nbr_room", "6")
                    property3.put("nbr_bathRoom", "2")
                    property3.put("nbr_bedRoom", "3")
                    property3.put("address", "15 rue du languedoc ")
                    property3.put("date_entry", "24/11/2020")
                    property3.put("photo_cover", R.drawable.flat)
                    property3.put("description", "On the top three floors of a beautiful 1930s building, an 8-room apartment with luxurious amenities. It is composed, on the intermediate level (7th floor - 98 m²), of a living room, an office or an anteroom, a bedroom with its shower room. On the lower level (6th floor - 107 m²), four bedrooms, a bathroom and, on the last level (8th floor - 55 m²) a through kitchen living room opening on one side onto a terrace of 15 m² sunny and well sheltered, the other on a balcony. On the roof, solarium terrace of 30 m², without any vis-à-vis, with a 360 ° view. A large cellar and three technical rooms complete this exceptional property.")
                    property3.put("date_sale", "")
                    property3.put("property_lat", "49.04224")
                    property3.put("property_lng", "2.30468")
                    property3.put("agent_id", 2)
                    property3.put("agent_name", "loots")
                    property3.put("agent_mail","loots@gmail.com")


                    db.insert("property", OnConflictStrategy.IGNORE, property3)


                    val property4 = ContentValues()
                    property4.put("id", 4)
                    property4.put("city", "Ecouen")
                    property4.put("price", "1100000")
                    property4.put("type", "House")
                    property4.put("surface", "180")
                    property4.put("nbr_room", "6")
                    property4.put("nbr_bathRoom", "2")
                    property4.put("nbr_bedRoom", "3")
                    property4.put("address", "1 allée des princes 95540 ")
                    property4.put("date_entry", "24/11/2020")
                    property4.put("photo_cover", R.drawable.house4)
                    property4.put("description", "In an old building, overlooking a paved and wooded courtyard full of charm, a duplex apartment (like a house) of 112.06m2 LC (119.01 m2 in living area) offering on the ground floor, an entrance, a large double living room of 45m2 with open kitchen, two bedrooms, a bathroom, a shower room with WC, a laundry room, a dressing room and a separate WC. Upstairs, a large bedroom, a dressing room and a bathroom with shower and bathtub as well as a separate toilet. Atypical, calm, and facing South-West. Completely renovated.")
                    property4.put("date_sale", "")
                    property4.put("property_lat", "49.04224")
                    property4.put("property_lng", "2.30468")
                    property4.put("agent_id", 3)
                    property4.put("agent_name", "david")
                    property4.put("agent_mail","david@gmail.com")



                    db.insert("property", OnConflictStrategy.IGNORE, property4)


                    val photo1 = ContentValues()
                    photo1.put("photo_id", 1)
                    photo1.put("photo_url", R.drawable.house1bath)
                    photo1.put("name", "bathroom")
                    photo1.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo1)

                    val photo2 = ContentValues()
                    photo2.put("photo_id", 2)
                    photo2.put("photo_url", R.drawable.house1bed)
                    photo2.put("name", "bedroom")
                    photo2.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo2)

                    val photo3 = ContentValues()
                    photo3.put("photo_id", 3)
                    photo3.put("photo_url", R.drawable.house1liv)
                    photo3.put("name", "livingRoom")
                    photo3.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo3)

                    val photo4 = ContentValues()
                    photo4.put("photo_id", 4)
                    photo4.put("photo_url", R.drawable.house1bedroom)
                    photo4.put("name", "bedroom2")
                    photo4.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo4)


                    val photo5 = ContentValues()
                    photo5.put("photo_id", 5)
                    photo5.put("photo_url", R.drawable.house1)
                    photo5.put("name", "cover")
                    photo5.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo5)


                    val photo6 = ContentValues()
                    photo6.put("photo_id", 6)
                    photo6.put("photo_url", R.drawable.duplex3bed)
                    photo6.put("name", "bedroom")
                    photo6.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo6)

                    val photo7 = ContentValues()
                    photo7.put("photo_id", 7)
                    photo7.put("photo_url", R.drawable.duplex3bed2)
                    photo7.put("name", "bedroom2")
                    photo7.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo7)

                    val photo8 = ContentValues()
                    photo8.put("photo_id", 8)
                    photo8.put("photo_url", R.drawable.duplex3living)
                    photo8.put("name", "livingRoom")
                    photo8.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo8)

                    val photo15 = ContentValues()
                    photo15.put("photo_id", 15)
                    photo15.put("photo_url", R.drawable.duplex3)
                    photo15.put("name", "cover")
                    photo15.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo15)

                    val photo9 = ContentValues()
                    photo9.put("photo_id", 9)
                    photo9.put("photo_url", R.drawable.flatbath)
                    photo9.put("name", "bathroom")
                    photo9.put("property_id", 3)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo9)

                    val photo10 = ContentValues()
                    photo10.put("photo_id", 10)
                    photo10.put("photo_url", R.drawable.flatbed)
                    photo10.put("name", "bedroom")
                    photo10.put("property_id", 3)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo10)

                    val photo11 = ContentValues()
                    photo11.put("photo_id", 11)
                    photo11.put("photo_url", R.drawable.flatbed2)
                    photo11.put("name", "bedroom2")
                    photo11.put("property_id", 3)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo11)

                    val photo16 = ContentValues()
                    photo16.put("photo_id", 16)
                    photo16.put("photo_url", R.drawable.flat)
                    photo16.put("name", "cover")
                    photo16.put("property_id", 3)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo16)


                    val photo12 = ContentValues()
                    photo12.put("photo_id", 12)
                    photo12.put("photo_url", R.drawable.house4bed)
                    photo12.put("name", "bedroom")
                    photo12.put("property_id", 4)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo12)

                    val photo13 = ContentValues()
                    photo13.put("photo_id", 13)
                    photo13.put("photo_url", R.drawable.housebed42)
                    photo13.put("name", "bedroom2")
                    photo13.put("property_id", 4)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo13)

                    val photo14 = ContentValues()
                    photo14.put("photo_id", 14)
                    photo14.put("photo_url", R.drawable.housebath4)
                    photo14.put("name", "bathroom")
                    photo14.put("property_id", 4)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo14)

                    val photo17 = ContentValues()
                    photo17.put("photo_id", 17)
                    photo17.put("photo_url", R.drawable.house4)
                    photo17.put("name", "cover")
                    photo17.put("property_id", 4)
                    db.insert("photo", OnConflictStrategy.IGNORE, photo17)


                }

            }


        }

    }

}
