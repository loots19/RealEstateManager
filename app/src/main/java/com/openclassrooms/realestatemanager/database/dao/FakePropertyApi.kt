package com.openclassrooms.realestatemanager.database.dao

import android.content.ContentValues
import android.util.Log
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
                    property1.put("property_id",1)
                    property1.put("city","Bouffémont")
                    property1.put("price","1200000")
                    property1.put("type","House")
                    property1.put("surface","200")
                    property1.put("nbr_room","6")
                    property1.put("nbr_bathRoom","2")
                    property1.put("nbr_bedRoom","4")
                    property1.put("address","15 avenue berthelot 95570 Bouffémont")
                    property1.put("date_entry","24/11/2020")
                    property1.put("photo_cover", R.drawable.house2)
                    property1.put("description","Within a modern, private and fully secure driveway with caretaker, a magnificent architect-designed house in perfect condition of approximately 200m2 of living space with 128m2 of outdoor space. This house consists of: On the ground floor, three bedrooms, two with bathroom / water and a separate toilet. These three bedrooms each overlook the outdoor spaces including a 40m2 garden and 20m2 Japanese garden. On the first floor, a double cathedral living room with fireplace opening onto two terraces, one of which is 20m2 and 10m2, and a fitted and equipped independent kitchen. On the 2nd and last floor, a master suite with bathroom, dressing room and an office space opening onto a terrace of 18m2. In the basement, a storage space of 20m2 and a closed double box attached to the house. This house, bright and quiet, with no real vis-à-vis is ideal for large families.\n")
                    property1.put("date_sale","")
                    property1.put("property_lat","49.04224")
                    property1.put("property_lng","2.30468")
                    property1.put("agent_id",1)
                    db.insert("property",OnConflictStrategy.IGNORE,property1)

                    val property2 = ContentValues()
                    property2.put("property_id",2)
                    property2.put("city","Domont")
                    property2.put("price","1000000")
                    property2.put("type","Duplex")
                    property2.put("surface","180")
                    property2.put("nbr_room","6")
                    property2.put("nbr_bathRoom","2")
                    property2.put("nbr_bedRoom","3")
                    property2.put("address","15 avenue berthelot 95570 Bouffémont")
                    property2.put("date_entry","24/11/2020")
                    property2.put("photo_cover", R.drawable.duplex3)
                    property2.put("description","sublime duplex for sale in a recent building of high standing, highly secure with 24H / 24 concierge, quiet, not overlooked, great light, terraces and balconies on 2 levels overlooking a private garden. Accessible from the ground floor of the building, the recently renovated apartment is made up on the ground floor of a large entrance with a shower room and WC, a large living room, a storage room; In the basement a large bedroom with shower room and WC, a storage room; On the 1st floor a dining room with fitted kitchen, a 2nd living room, WC; on the 2nd floor 2 large suites, one with a walk-in shower room and the other with a bathroom; WC, dressing room and built-in cupboards. Elevator and interior stairs, central and electric air conditioning, central heating, safe, videophone on all levels, electric shutters, reinforced door. Guardian, digital code, intercom, alarm.\n")
                    property2.put("date_sale","")
                    property2.put("property_lat","49.04224")
                    property2.put("property_lng","2.30468")
                    property2.put("agent_id",1)
                    db.insert("property",OnConflictStrategy.IGNORE,property2)


                    val property3 = ContentValues()
                    property3.put("property_id",3)
                    property3.put("city","Ezanville")
                    property3.put("price","1100000")
                    property3.put("type","Flat")
                    property3.put("surface","180")
                    property3.put("nbr_room","6")
                    property3.put("nbr_bathRoom","2")
                    property3.put("nbr_bedRoom","3")
                    property3.put("address","15 avenue berthelot 95570 Bouffémont")
                    property3.put("date_entry","24/11/2020")
                    property3.put("photo_cover", R.drawable.flat1)
                    property3.put("description","sublime duplex for sale in a recent building of high standing, highly secure with 24H / 24 concierge, quiet, not overlooked, great light, terraces and balconies on 2 levels overlooking a private garden. Accessible from the ground floor of the building, the recently renovated apartment is made up on the ground floor of a large entrance with a shower room and WC, a large living room, a storage room; In the basement a large bedroom with shower room and WC, a storage room; On the 1st floor a dining room with fitted kitchen, a 2nd living room, WC; on the 2nd floor 2 large suites, one with a walk-in shower room and the other with a bathroom; WC, dressing room and built-in cupboards. Elevator and interior stairs, central and electric air conditioning, central heating, safe, videophone on all levels, electric shutters, reinforced door. Guardian, digital code, intercom, alarm.\n")
                    property3.put("date_sale","")
                    property3.put("property_lat","49.04224")
                    property3.put("property_lng","2.30468")
                    property3.put("agent_id",2)
                    db.insert("property",OnConflictStrategy.IGNORE,property3)


                    val property4 = ContentValues()
                    property4.put("property_id",4)
                    property4.put("city","Ecouen")
                    property4.put("price","1100000")
                    property4.put("type","House")
                    property4.put("surface","180")
                    property4.put("nbr_room","6")
                    property4.put("nbr_bathRoom","2")
                    property4.put("nbr_bedRoom","3")
                    property4.put("address","15 avenue berthelot 95570 Bouffémont")
                    property4.put("date_entry","24/11/2020")
                    property4.put("photo_cover", R.drawable.house4)
                    property4.put("description","In an old building, overlooking a paved and wooded courtyard full of charm, a duplex apartment (like a house) of 112.06m2 LC (119.01 m2 in living area) offering on the ground floor, an entrance, a large double living room of 45m2 with open kitchen, two bedrooms, a bathroom, a shower room with WC, a laundry room, a dressing room and a separate WC. Upstairs, a large bedroom, a dressing room and a bathroom with shower and bathtub as well as a separate toilet. Atypical, calm, and facing South-West. Completely renovated.")
                    property4.put("date_sale","")
                    property4.put("property_lat","49.04224")
                    property4.put("property_lng","2.30468")
                    property4.put("agent_id",3)
                    db.insert("property",OnConflictStrategy.IGNORE,property4)





                }

            }


        }

    }

}
