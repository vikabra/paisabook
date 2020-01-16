package com.tanmaypradhan.paisabook.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.tanmaypradhan.paisabook.database.dao.TransactionDAO;
import com.tanmaypradhan.paisabook.database.dao.TransactionDAO_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TransactionDAO _transactionDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TblTransactionLog` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` INTEGER NOT NULL, `proof` INTEGER NOT NULL, `pending` INTEGER NOT NULL, `totalAmount` REAL NOT NULL, `discountAmount` REAL NOT NULL, `name` TEXT, `contact` TEXT, `amountPaid` REAL NOT NULL, `amountDue` REAL NOT NULL, `dueDate` TEXT, `createdOn` TEXT, `timestamp` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TblTransactionLogDetails` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `transactionID` INTEGER NOT NULL, `itemName` TEXT, `quantity` INTEGER NOT NULL, `price` REAL NOT NULL, `amount` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9298f3de9961d208539b3bb3bcb5441a')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `TblTransactionLog`");
        _db.execSQL("DROP TABLE IF EXISTS `TblTransactionLogDetails`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTblTransactionLog = new HashMap<String, TableInfo.Column>(13);
        _columnsTblTransactionLog.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("proof", new TableInfo.Column("proof", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("pending", new TableInfo.Column("pending", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("discountAmount", new TableInfo.Column("discountAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("contact", new TableInfo.Column("contact", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("amountPaid", new TableInfo.Column("amountPaid", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("amountDue", new TableInfo.Column("amountDue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("dueDate", new TableInfo.Column("dueDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("createdOn", new TableInfo.Column("createdOn", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLog.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTblTransactionLog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTblTransactionLog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTblTransactionLog = new TableInfo("TblTransactionLog", _columnsTblTransactionLog, _foreignKeysTblTransactionLog, _indicesTblTransactionLog);
        final TableInfo _existingTblTransactionLog = TableInfo.read(_db, "TblTransactionLog");
        if (! _infoTblTransactionLog.equals(_existingTblTransactionLog)) {
          return new RoomOpenHelper.ValidationResult(false, "TblTransactionLog(com.tanmaypradhan.paisabook.database.entity.TblTransactionLog).\n"
                  + " Expected:\n" + _infoTblTransactionLog + "\n"
                  + " Found:\n" + _existingTblTransactionLog);
        }
        final HashMap<String, TableInfo.Column> _columnsTblTransactionLogDetails = new HashMap<String, TableInfo.Column>(6);
        _columnsTblTransactionLogDetails.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLogDetails.put("transactionID", new TableInfo.Column("transactionID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLogDetails.put("itemName", new TableInfo.Column("itemName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLogDetails.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLogDetails.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTblTransactionLogDetails.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTblTransactionLogDetails = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTblTransactionLogDetails = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTblTransactionLogDetails = new TableInfo("TblTransactionLogDetails", _columnsTblTransactionLogDetails, _foreignKeysTblTransactionLogDetails, _indicesTblTransactionLogDetails);
        final TableInfo _existingTblTransactionLogDetails = TableInfo.read(_db, "TblTransactionLogDetails");
        if (! _infoTblTransactionLogDetails.equals(_existingTblTransactionLogDetails)) {
          return new RoomOpenHelper.ValidationResult(false, "TblTransactionLogDetails(com.tanmaypradhan.paisabook.database.entity.TblTransactionLogDetails).\n"
                  + " Expected:\n" + _infoTblTransactionLogDetails + "\n"
                  + " Found:\n" + _existingTblTransactionLogDetails);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9298f3de9961d208539b3bb3bcb5441a", "cf24d7f78ca3b539ade4f558308a122b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "TblTransactionLog","TblTransactionLogDetails");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `TblTransactionLog`");
      _db.execSQL("DELETE FROM `TblTransactionLogDetails`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TransactionDAO transactionDAO() {
    if (_transactionDAO != null) {
      return _transactionDAO;
    } else {
      synchronized(this) {
        if(_transactionDAO == null) {
          _transactionDAO = new TransactionDAO_Impl(this);
        }
        return _transactionDAO;
      }
    }
  }
}
