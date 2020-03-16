package com.godbeom.baseapp.di

import androidx.room.Room
import com.godbeom.baseapp.persistence.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * DB관련 Inject
 * @since 2019-11-01
 * @author Beom-chul
 */

val PersistenceModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "push.db")
        .build() }
}





// migration Example


/**
 *   - state 컬럼 추가
 * */
//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE PushEntity ADD COLUMN state TEXT")
//    }
//}

/**법인 리스트 테이블 추가
 */
//val MIGRATION_2_3: Migration = object : Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("CREATE TABLE `CorporateUrlEntity` (" +
//                "`corporateKey` TEXT NOT NULL," +
//                " `corporateAlias` TEXT NOT NULL, " +
//                " `locale` TEXT NOT NULL, " +
//                " `lang` TEXT NOT NULL, " +
//                " `webUrl` TEXT NOT NULL, " +
//                " `endpointUrl` TEXT NOT NULL, " +
//                " `createdTime` INTEGER NOT NULL, "
//                + "PRIMARY KEY(`corporateKey`))")
//    }
//}