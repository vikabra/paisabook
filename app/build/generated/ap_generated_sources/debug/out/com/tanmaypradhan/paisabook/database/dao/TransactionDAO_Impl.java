package com.tanmaypradhan.paisabook.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tanmaypradhan.paisabook.database.entity.TblTransactionLog;
import com.tanmaypradhan.paisabook.database.entity.TblTransactionLogDetails;
import com.tanmaypradhan.paisabook.database.model.DBItemTotalModel;
import com.tanmaypradhan.paisabook.database.model.DBLogModel;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDAO_Impl implements TransactionDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TblTransactionLog> __insertionAdapterOfTblTransactionLog;

  private final EntityInsertionAdapter<TblTransactionLogDetails> __insertionAdapterOfTblTransactionLogDetails;

  public TransactionDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTblTransactionLog = new EntityInsertionAdapter<TblTransactionLog>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TblTransactionLog` (`uid`,`type`,`proof`,`pending`,`totalAmount`,`discountAmount`,`name`,`contact`,`amountPaid`,`amountDue`,`dueDate`,`createdOn`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TblTransactionLog value) {
        stmt.bindLong(1, value.uid);
        stmt.bindLong(2, value.type);
        final int _tmp;
        _tmp = value.proof ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = value.pending ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        stmt.bindDouble(5, value.totalAmount);
        stmt.bindDouble(6, value.discountAmount);
        if (value.name == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.name);
        }
        if (value.contact == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.contact);
        }
        stmt.bindDouble(9, value.amountPaid);
        stmt.bindDouble(10, value.amountDue);
        if (value.dueDate == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.dueDate);
        }
        if (value.createdOn == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.createdOn);
        }
        stmt.bindLong(13, value.timestamp);
      }
    };
    this.__insertionAdapterOfTblTransactionLogDetails = new EntityInsertionAdapter<TblTransactionLogDetails>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TblTransactionLogDetails` (`uid`,`transactionID`,`itemName`,`quantity`,`price`,`amount`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TblTransactionLogDetails value) {
        stmt.bindLong(1, value.uid);
        stmt.bindLong(2, value.transactionID);
        if (value.itemName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.itemName);
        }
        stmt.bindLong(4, value.quantity);
        stmt.bindDouble(5, value.price);
        stmt.bindDouble(6, value.amount);
      }
    };
  }

  @Override
  public long insertTransactionLogs(final TblTransactionLog transactionLogs) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTblTransactionLog.insertAndReturnId(transactionLogs);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTransactionLogDetails(final TblTransactionLogDetails... transactionLogDetails) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTblTransactionLogDetails.insert(transactionLogDetails);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public TblTransactionLog getTransactionLogByID(final int uid) {
    final String _sql = "SELECT * FROM TblTransactionLog WHERE uid = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, uid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfProof = CursorUtil.getColumnIndexOrThrow(_cursor, "proof");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
      final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
      final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
      final int _cursorIndexOfAmountDue = CursorUtil.getColumnIndexOrThrow(_cursor, "amountDue");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final TblTransactionLog _result;
      if(_cursor.moveToFirst()) {
        _result = new TblTransactionLog();
        _result.uid = _cursor.getInt(_cursorIndexOfUid);
        _result.type = _cursor.getInt(_cursorIndexOfType);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfProof);
        _result.proof = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfPending);
        _result.pending = _tmp_1 != 0;
        _result.totalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _result.discountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _result.name = _cursor.getString(_cursorIndexOfName);
        _result.contact = _cursor.getString(_cursorIndexOfContact);
        _result.amountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
        _result.amountDue = _cursor.getDouble(_cursorIndexOfAmountDue);
        _result.dueDate = _cursor.getString(_cursorIndexOfDueDate);
        _result.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        _result.timestamp = _cursor.getLong(_cursorIndexOfTimestamp);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TblTransactionLogDetails> getLogDetailsByID(final int transactionID) {
    final String _sql = "SELECT * FROM tbltransactionlogdetails WHERE transactionID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transactionID);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfTransactionID = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionID");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final List<TblTransactionLogDetails> _result = new ArrayList<TblTransactionLogDetails>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TblTransactionLogDetails _item;
        _item = new TblTransactionLogDetails();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.transactionID = _cursor.getLong(_cursorIndexOfTransactionID);
        _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        _item.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item.price = _cursor.getDouble(_cursorIndexOfPrice);
        _item.amount = _cursor.getDouble(_cursorIndexOfAmount);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TblTransactionLog> getAllTransactionLog() {
    final String _sql = "SELECT * FROM TblTransactionLog ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfProof = CursorUtil.getColumnIndexOrThrow(_cursor, "proof");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
      final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
      final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
      final int _cursorIndexOfAmountDue = CursorUtil.getColumnIndexOrThrow(_cursor, "amountDue");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<TblTransactionLog> _result = new ArrayList<TblTransactionLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TblTransactionLog _item;
        _item = new TblTransactionLog();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfProof);
        _item.proof = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfPending);
        _item.pending = _tmp_1 != 0;
        _item.totalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.discountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.contact = _cursor.getString(_cursorIndexOfContact);
        _item.amountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
        _item.amountDue = _cursor.getDouble(_cursorIndexOfAmountDue);
        _item.dueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        _item.timestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TblTransactionLog> getPendingTransactionLog() {
    final String _sql = "SELECT * FROM TblTransactionLog WHERE pending = 1 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfProof = CursorUtil.getColumnIndexOrThrow(_cursor, "proof");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
      final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
      final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
      final int _cursorIndexOfAmountDue = CursorUtil.getColumnIndexOrThrow(_cursor, "amountDue");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<TblTransactionLog> _result = new ArrayList<TblTransactionLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TblTransactionLog _item;
        _item = new TblTransactionLog();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfProof);
        _item.proof = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfPending);
        _item.pending = _tmp_1 != 0;
        _item.totalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.discountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.contact = _cursor.getString(_cursorIndexOfContact);
        _item.amountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
        _item.amountDue = _cursor.getDouble(_cursorIndexOfAmountDue);
        _item.dueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        _item.timestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TblTransactionLog> getCompleteTransactionLog() {
    final String _sql = "SELECT * FROM TblTransactionLog WHERE pending = 0 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfProof = CursorUtil.getColumnIndexOrThrow(_cursor, "proof");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
      final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
      final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
      final int _cursorIndexOfAmountDue = CursorUtil.getColumnIndexOrThrow(_cursor, "amountDue");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<TblTransactionLog> _result = new ArrayList<TblTransactionLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TblTransactionLog _item;
        _item = new TblTransactionLog();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfProof);
        _item.proof = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfPending);
        _item.pending = _tmp_1 != 0;
        _item.totalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.discountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.contact = _cursor.getString(_cursorIndexOfContact);
        _item.amountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
        _item.amountDue = _cursor.getDouble(_cursorIndexOfAmountDue);
        _item.dueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        _item.timestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllItemNames() {
    final String _sql = "SELECT DISTINCT itemName FROM TblTransactionLogDetails";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TblTransactionLog> getToReceiveBalance() {
    final String _sql = "SELECT * FROM TblTransactionLog WHERE pending = 1 AND type = 2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfProof = CursorUtil.getColumnIndexOrThrow(_cursor, "proof");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
      final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
      final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
      final int _cursorIndexOfAmountDue = CursorUtil.getColumnIndexOrThrow(_cursor, "amountDue");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<TblTransactionLog> _result = new ArrayList<TblTransactionLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TblTransactionLog _item;
        _item = new TblTransactionLog();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfProof);
        _item.proof = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfPending);
        _item.pending = _tmp_1 != 0;
        _item.totalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.discountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.contact = _cursor.getString(_cursorIndexOfContact);
        _item.amountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
        _item.amountDue = _cursor.getDouble(_cursorIndexOfAmountDue);
        _item.dueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        _item.timestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TblTransactionLog> getToPayBalance() {
    final String _sql = "SELECT * FROM tbltransactionlog WHERE pending = 1 AND type <> 2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfProof = CursorUtil.getColumnIndexOrThrow(_cursor, "proof");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
      final int _cursorIndexOfDiscountAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "discountAmount");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
      final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
      final int _cursorIndexOfAmountDue = CursorUtil.getColumnIndexOrThrow(_cursor, "amountDue");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<TblTransactionLog> _result = new ArrayList<TblTransactionLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TblTransactionLog _item;
        _item = new TblTransactionLog();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfProof);
        _item.proof = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfPending);
        _item.pending = _tmp_1 != 0;
        _item.totalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
        _item.discountAmount = _cursor.getDouble(_cursorIndexOfDiscountAmount);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.contact = _cursor.getString(_cursorIndexOfContact);
        _item.amountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
        _item.amountDue = _cursor.getDouble(_cursorIndexOfAmountDue);
        _item.dueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        _item.timestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Long> getStockTransactionIDs() {
    final String _sql = "SELECT uid FROM tbltransactionlog WHERE type = 1 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<Long> _result = new ArrayList<Long>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Long _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getLong(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DBItemTotalModel> getSumOfDistinctItems(final List<Long> transactionIDs) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT itemName, SUM(quantity) AS quantity, SUM(amount) AS amount FROM tbltransactionlogdetails WHERE transactionID IN (");
    final int _inputSize = transactionIDs.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") GROUP BY itemName");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : transactionIDs) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final List<DBItemTotalModel> _result = new ArrayList<DBItemTotalModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBItemTotalModel _item_1;
        _item_1 = new DBItemTotalModel();
        _item_1.itemName = _cursor.getString(_cursorIndexOfItemName);
        _item_1.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item_1.amount = _cursor.getDouble(_cursorIndexOfAmount);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Long> getSaleTransactionIDs() {
    final String _sql = "SELECT uid FROM tbltransactionlog WHERE type = 2 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<Long> _result = new ArrayList<Long>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Long _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getLong(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Long> getExpenseTransactionIDs() {
    final String _sql = "SELECT uid FROM tbltransactionlog WHERE type = 3 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<Long> _result = new ArrayList<Long>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Long _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getLong(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DBLogModel> getRecentItemLogs(final String itemName, final long startDate,
      final long endDate, final int type) {
    final String _sql = "SELECT TL.itemName AS itemName,T.type AS type, TL.quantity AS quantity, TL.price AS price, T.createdOn AS date FROM tbltransactionlog AS T INNER JOIN tbltransactionlogdetails AS TL ON T.uid = TL.transactionID WHERE TL.itemName = ? AND T.type = ? AND T.timestamp BETWEEN ? AND ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (itemName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, type);
    _argIndex = 3;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 4;
    _statement.bindLong(_argIndex, endDate);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<DBLogModel> _result = new ArrayList<DBLogModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBLogModel _item;
        _item = new DBLogModel();
        _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        _item.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item.price = _cursor.getDouble(_cursorIndexOfPrice);
        _item.date = _cursor.getString(_cursorIndexOfDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DBLogModel> getRecentItemLogs(final String itemName, final int type) {
    final String _sql = "SELECT TL.itemName AS itemName, T.type AS type, TL.quantity AS quantity, TL.price AS price, T.createdOn AS date FROM tbltransactionlog AS T INNER JOIN tbltransactionlogdetails AS TL ON T.uid = TL.transactionID WHERE TL.itemName = ? AND T.type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (itemName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, type);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<DBLogModel> _result = new ArrayList<DBLogModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBLogModel _item;
        _item = new DBLogModel();
        _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        _item.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item.price = _cursor.getDouble(_cursorIndexOfPrice);
        _item.date = _cursor.getString(_cursorIndexOfDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DBLogModel> getItemLogs(final String itemName) {
    final String _sql = "SELECT TL.itemName AS itemName, T.type AS type, TL.quantity AS quantity, TL.price AS price, T.createdOn AS date FROM tbltransactionlog AS T INNER JOIN tbltransactionlogdetails AS TL ON T.uid = TL.transactionID WHERE TL.itemName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (itemName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<DBLogModel> _result = new ArrayList<DBLogModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBLogModel _item;
        _item = new DBLogModel();
        _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        _item.type = _cursor.getInt(_cursorIndexOfType);
        _item.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item.price = _cursor.getDouble(_cursorIndexOfPrice);
        _item.date = _cursor.getString(_cursorIndexOfDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getTotalAmountByDate(final long startDate, final long endDate, final int type) {
    final String _sql = "SELECT SUM(totalAmount - discountAmount) FROM tbltransactionlog WHERE type = ? AND timestamp BETWEEN ? AND ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
