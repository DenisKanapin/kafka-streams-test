/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package debezium.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class AvroUserInters extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8285210307406560774L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroUserInters\",\"namespace\":\"debezium.avro\",\"fields\":[{\"name\":\"user_id\",\"type\":\"long\"},{\"name\":\"page1\",\"type\":\"boolean\"},{\"name\":\"page1_button\",\"type\":\"boolean\"},{\"name\":\"page2\",\"type\":\"boolean\"},{\"name\":\"page2_button\",\"type\":\"boolean\"},{\"name\":\"page3\",\"type\":\"boolean\"},{\"name\":\"page3_button\",\"type\":\"boolean\"},{\"name\":\"__op\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"__table\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"__lsn\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"__source_ts_ms\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"__deleted\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}],\"connect.name\":\"debezium.avro.AvroUserInters\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AvroUserInters> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AvroUserInters> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<AvroUserInters> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<AvroUserInters> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<AvroUserInters> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this AvroUserInters to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a AvroUserInters from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a AvroUserInters instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static AvroUserInters fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private long user_id;
  private boolean page1;
  private boolean page1_button;
  private boolean page2;
  private boolean page2_button;
  private boolean page3;
  private boolean page3_button;
  private java.lang.String __op;
  private java.lang.String __table;
  private java.lang.Long __lsn;
  private java.lang.Long __source_ts_ms;
  private java.lang.String __deleted;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroUserInters() {}

  /**
   * All-args constructor.
   * @param user_id The new value for user_id
   * @param page1 The new value for page1
   * @param page1_button The new value for page1_button
   * @param page2 The new value for page2
   * @param page2_button The new value for page2_button
   * @param page3 The new value for page3
   * @param page3_button The new value for page3_button
   * @param __op The new value for __op
   * @param __table The new value for __table
   * @param __lsn The new value for __lsn
   * @param __source_ts_ms The new value for __source_ts_ms
   * @param __deleted The new value for __deleted
   */
  public AvroUserInters(java.lang.Long user_id, java.lang.Boolean page1, java.lang.Boolean page1_button, java.lang.Boolean page2, java.lang.Boolean page2_button, java.lang.Boolean page3, java.lang.Boolean page3_button, java.lang.String __op, java.lang.String __table, java.lang.Long __lsn, java.lang.Long __source_ts_ms, java.lang.String __deleted) {
    this.user_id = user_id;
    this.page1 = page1;
    this.page1_button = page1_button;
    this.page2 = page2;
    this.page2_button = page2_button;
    this.page3 = page3;
    this.page3_button = page3_button;
    this.__op = __op;
    this.__table = __table;
    this.__lsn = __lsn;
    this.__source_ts_ms = __source_ts_ms;
    this.__deleted = __deleted;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return user_id;
    case 1: return page1;
    case 2: return page1_button;
    case 3: return page2;
    case 4: return page2_button;
    case 5: return page3;
    case 6: return page3_button;
    case 7: return __op;
    case 8: return __table;
    case 9: return __lsn;
    case 10: return __source_ts_ms;
    case 11: return __deleted;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: user_id = (java.lang.Long)value$; break;
    case 1: page1 = (java.lang.Boolean)value$; break;
    case 2: page1_button = (java.lang.Boolean)value$; break;
    case 3: page2 = (java.lang.Boolean)value$; break;
    case 4: page2_button = (java.lang.Boolean)value$; break;
    case 5: page3 = (java.lang.Boolean)value$; break;
    case 6: page3_button = (java.lang.Boolean)value$; break;
    case 7: __op = value$ != null ? value$.toString() : null; break;
    case 8: __table = value$ != null ? value$.toString() : null; break;
    case 9: __lsn = (java.lang.Long)value$; break;
    case 10: __source_ts_ms = (java.lang.Long)value$; break;
    case 11: __deleted = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'user_id' field.
   * @return The value of the 'user_id' field.
   */
  public long getUserId() {
    return user_id;
  }


  /**
   * Sets the value of the 'user_id' field.
   * @param value the value to set.
   */
  public void setUserId(long value) {
    this.user_id = value;
  }

  /**
   * Gets the value of the 'page1' field.
   * @return The value of the 'page1' field.
   */
  public boolean getPage1() {
    return page1;
  }


  /**
   * Sets the value of the 'page1' field.
   * @param value the value to set.
   */
  public void setPage1(boolean value) {
    this.page1 = value;
  }

  /**
   * Gets the value of the 'page1_button' field.
   * @return The value of the 'page1_button' field.
   */
  public boolean getPage1Button() {
    return page1_button;
  }


  /**
   * Sets the value of the 'page1_button' field.
   * @param value the value to set.
   */
  public void setPage1Button(boolean value) {
    this.page1_button = value;
  }

  /**
   * Gets the value of the 'page2' field.
   * @return The value of the 'page2' field.
   */
  public boolean getPage2() {
    return page2;
  }


  /**
   * Sets the value of the 'page2' field.
   * @param value the value to set.
   */
  public void setPage2(boolean value) {
    this.page2 = value;
  }

  /**
   * Gets the value of the 'page2_button' field.
   * @return The value of the 'page2_button' field.
   */
  public boolean getPage2Button() {
    return page2_button;
  }


  /**
   * Sets the value of the 'page2_button' field.
   * @param value the value to set.
   */
  public void setPage2Button(boolean value) {
    this.page2_button = value;
  }

  /**
   * Gets the value of the 'page3' field.
   * @return The value of the 'page3' field.
   */
  public boolean getPage3() {
    return page3;
  }


  /**
   * Sets the value of the 'page3' field.
   * @param value the value to set.
   */
  public void setPage3(boolean value) {
    this.page3 = value;
  }

  /**
   * Gets the value of the 'page3_button' field.
   * @return The value of the 'page3_button' field.
   */
  public boolean getPage3Button() {
    return page3_button;
  }


  /**
   * Sets the value of the 'page3_button' field.
   * @param value the value to set.
   */
  public void setPage3Button(boolean value) {
    this.page3_button = value;
  }

  /**
   * Gets the value of the '__op' field.
   * @return The value of the '__op' field.
   */
  public java.lang.String getOp() {
    return __op;
  }


  /**
   * Sets the value of the '__op' field.
   * @param value the value to set.
   */
  public void setOp(java.lang.String value) {
    this.__op = value;
  }

  /**
   * Gets the value of the '__table' field.
   * @return The value of the '__table' field.
   */
  public java.lang.String getTable() {
    return __table;
  }


  /**
   * Sets the value of the '__table' field.
   * @param value the value to set.
   */
  public void setTable(java.lang.String value) {
    this.__table = value;
  }

  /**
   * Gets the value of the '__lsn' field.
   * @return The value of the '__lsn' field.
   */
  public java.lang.Long getLsn() {
    return __lsn;
  }


  /**
   * Sets the value of the '__lsn' field.
   * @param value the value to set.
   */
  public void setLsn(java.lang.Long value) {
    this.__lsn = value;
  }

  /**
   * Gets the value of the '__source_ts_ms' field.
   * @return The value of the '__source_ts_ms' field.
   */
  public java.lang.Long getSourceTsMs() {
    return __source_ts_ms;
  }


  /**
   * Sets the value of the '__source_ts_ms' field.
   * @param value the value to set.
   */
  public void setSourceTsMs(java.lang.Long value) {
    this.__source_ts_ms = value;
  }

  /**
   * Gets the value of the '__deleted' field.
   * @return The value of the '__deleted' field.
   */
  public java.lang.String getDeleted() {
    return __deleted;
  }


  /**
   * Sets the value of the '__deleted' field.
   * @param value the value to set.
   */
  public void setDeleted(java.lang.String value) {
    this.__deleted = value;
  }

  /**
   * Creates a new AvroUserInters RecordBuilder.
   * @return A new AvroUserInters RecordBuilder
   */
  public static debezium.avro.AvroUserInters.Builder newBuilder() {
    return new debezium.avro.AvroUserInters.Builder();
  }

  /**
   * Creates a new AvroUserInters RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroUserInters RecordBuilder
   */
  public static debezium.avro.AvroUserInters.Builder newBuilder(debezium.avro.AvroUserInters.Builder other) {
    if (other == null) {
      return new debezium.avro.AvroUserInters.Builder();
    } else {
      return new debezium.avro.AvroUserInters.Builder(other);
    }
  }

  /**
   * Creates a new AvroUserInters RecordBuilder by copying an existing AvroUserInters instance.
   * @param other The existing instance to copy.
   * @return A new AvroUserInters RecordBuilder
   */
  public static debezium.avro.AvroUserInters.Builder newBuilder(debezium.avro.AvroUserInters other) {
    if (other == null) {
      return new debezium.avro.AvroUserInters.Builder();
    } else {
      return new debezium.avro.AvroUserInters.Builder(other);
    }
  }

  /**
   * RecordBuilder for AvroUserInters instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroUserInters>
    implements org.apache.avro.data.RecordBuilder<AvroUserInters> {

    private long user_id;
    private boolean page1;
    private boolean page1_button;
    private boolean page2;
    private boolean page2_button;
    private boolean page3;
    private boolean page3_button;
    private java.lang.String __op;
    private java.lang.String __table;
    private java.lang.Long __lsn;
    private java.lang.Long __source_ts_ms;
    private java.lang.String __deleted;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(debezium.avro.AvroUserInters.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.user_id)) {
        this.user_id = data().deepCopy(fields()[0].schema(), other.user_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.page1)) {
        this.page1 = data().deepCopy(fields()[1].schema(), other.page1);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.page1_button)) {
        this.page1_button = data().deepCopy(fields()[2].schema(), other.page1_button);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.page2)) {
        this.page2 = data().deepCopy(fields()[3].schema(), other.page2);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.page2_button)) {
        this.page2_button = data().deepCopy(fields()[4].schema(), other.page2_button);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.page3)) {
        this.page3 = data().deepCopy(fields()[5].schema(), other.page3);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.page3_button)) {
        this.page3_button = data().deepCopy(fields()[6].schema(), other.page3_button);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.__op)) {
        this.__op = data().deepCopy(fields()[7].schema(), other.__op);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.__table)) {
        this.__table = data().deepCopy(fields()[8].schema(), other.__table);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
      if (isValidValue(fields()[9], other.__lsn)) {
        this.__lsn = data().deepCopy(fields()[9].schema(), other.__lsn);
        fieldSetFlags()[9] = other.fieldSetFlags()[9];
      }
      if (isValidValue(fields()[10], other.__source_ts_ms)) {
        this.__source_ts_ms = data().deepCopy(fields()[10].schema(), other.__source_ts_ms);
        fieldSetFlags()[10] = other.fieldSetFlags()[10];
      }
      if (isValidValue(fields()[11], other.__deleted)) {
        this.__deleted = data().deepCopy(fields()[11].schema(), other.__deleted);
        fieldSetFlags()[11] = other.fieldSetFlags()[11];
      }
    }

    /**
     * Creates a Builder by copying an existing AvroUserInters instance
     * @param other The existing instance to copy.
     */
    private Builder(debezium.avro.AvroUserInters other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.user_id)) {
        this.user_id = data().deepCopy(fields()[0].schema(), other.user_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.page1)) {
        this.page1 = data().deepCopy(fields()[1].schema(), other.page1);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.page1_button)) {
        this.page1_button = data().deepCopy(fields()[2].schema(), other.page1_button);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.page2)) {
        this.page2 = data().deepCopy(fields()[3].schema(), other.page2);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.page2_button)) {
        this.page2_button = data().deepCopy(fields()[4].schema(), other.page2_button);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.page3)) {
        this.page3 = data().deepCopy(fields()[5].schema(), other.page3);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.page3_button)) {
        this.page3_button = data().deepCopy(fields()[6].schema(), other.page3_button);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.__op)) {
        this.__op = data().deepCopy(fields()[7].schema(), other.__op);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.__table)) {
        this.__table = data().deepCopy(fields()[8].schema(), other.__table);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.__lsn)) {
        this.__lsn = data().deepCopy(fields()[9].schema(), other.__lsn);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.__source_ts_ms)) {
        this.__source_ts_ms = data().deepCopy(fields()[10].schema(), other.__source_ts_ms);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.__deleted)) {
        this.__deleted = data().deepCopy(fields()[11].schema(), other.__deleted);
        fieldSetFlags()[11] = true;
      }
    }

    /**
      * Gets the value of the 'user_id' field.
      * @return The value.
      */
    public long getUserId() {
      return user_id;
    }


    /**
      * Sets the value of the 'user_id' field.
      * @param value The value of 'user_id'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setUserId(long value) {
      validate(fields()[0], value);
      this.user_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'user_id' field has been set.
      * @return True if the 'user_id' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'user_id' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearUserId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'page1' field.
      * @return The value.
      */
    public boolean getPage1() {
      return page1;
    }


    /**
      * Sets the value of the 'page1' field.
      * @param value The value of 'page1'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setPage1(boolean value) {
      validate(fields()[1], value);
      this.page1 = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'page1' field has been set.
      * @return True if the 'page1' field has been set, false otherwise.
      */
    public boolean hasPage1() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'page1' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearPage1() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'page1_button' field.
      * @return The value.
      */
    public boolean getPage1Button() {
      return page1_button;
    }


    /**
      * Sets the value of the 'page1_button' field.
      * @param value The value of 'page1_button'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setPage1Button(boolean value) {
      validate(fields()[2], value);
      this.page1_button = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'page1_button' field has been set.
      * @return True if the 'page1_button' field has been set, false otherwise.
      */
    public boolean hasPage1Button() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'page1_button' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearPage1Button() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'page2' field.
      * @return The value.
      */
    public boolean getPage2() {
      return page2;
    }


    /**
      * Sets the value of the 'page2' field.
      * @param value The value of 'page2'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setPage2(boolean value) {
      validate(fields()[3], value);
      this.page2 = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'page2' field has been set.
      * @return True if the 'page2' field has been set, false otherwise.
      */
    public boolean hasPage2() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'page2' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearPage2() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'page2_button' field.
      * @return The value.
      */
    public boolean getPage2Button() {
      return page2_button;
    }


    /**
      * Sets the value of the 'page2_button' field.
      * @param value The value of 'page2_button'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setPage2Button(boolean value) {
      validate(fields()[4], value);
      this.page2_button = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'page2_button' field has been set.
      * @return True if the 'page2_button' field has been set, false otherwise.
      */
    public boolean hasPage2Button() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'page2_button' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearPage2Button() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'page3' field.
      * @return The value.
      */
    public boolean getPage3() {
      return page3;
    }


    /**
      * Sets the value of the 'page3' field.
      * @param value The value of 'page3'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setPage3(boolean value) {
      validate(fields()[5], value);
      this.page3 = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'page3' field has been set.
      * @return True if the 'page3' field has been set, false otherwise.
      */
    public boolean hasPage3() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'page3' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearPage3() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'page3_button' field.
      * @return The value.
      */
    public boolean getPage3Button() {
      return page3_button;
    }


    /**
      * Sets the value of the 'page3_button' field.
      * @param value The value of 'page3_button'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setPage3Button(boolean value) {
      validate(fields()[6], value);
      this.page3_button = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'page3_button' field has been set.
      * @return True if the 'page3_button' field has been set, false otherwise.
      */
    public boolean hasPage3Button() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'page3_button' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearPage3Button() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the '__op' field.
      * @return The value.
      */
    public java.lang.String getOp() {
      return __op;
    }


    /**
      * Sets the value of the '__op' field.
      * @param value The value of '__op'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setOp(java.lang.String value) {
      validate(fields()[7], value);
      this.__op = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the '__op' field has been set.
      * @return True if the '__op' field has been set, false otherwise.
      */
    public boolean hasOp() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the '__op' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearOp() {
      __op = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the '__table' field.
      * @return The value.
      */
    public java.lang.String getTable() {
      return __table;
    }


    /**
      * Sets the value of the '__table' field.
      * @param value The value of '__table'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setTable(java.lang.String value) {
      validate(fields()[8], value);
      this.__table = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the '__table' field has been set.
      * @return True if the '__table' field has been set, false otherwise.
      */
    public boolean hasTable() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the '__table' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearTable() {
      __table = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the '__lsn' field.
      * @return The value.
      */
    public java.lang.Long getLsn() {
      return __lsn;
    }


    /**
      * Sets the value of the '__lsn' field.
      * @param value The value of '__lsn'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setLsn(java.lang.Long value) {
      validate(fields()[9], value);
      this.__lsn = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the '__lsn' field has been set.
      * @return True if the '__lsn' field has been set, false otherwise.
      */
    public boolean hasLsn() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the '__lsn' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearLsn() {
      __lsn = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the '__source_ts_ms' field.
      * @return The value.
      */
    public java.lang.Long getSourceTsMs() {
      return __source_ts_ms;
    }


    /**
      * Sets the value of the '__source_ts_ms' field.
      * @param value The value of '__source_ts_ms'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setSourceTsMs(java.lang.Long value) {
      validate(fields()[10], value);
      this.__source_ts_ms = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the '__source_ts_ms' field has been set.
      * @return True if the '__source_ts_ms' field has been set, false otherwise.
      */
    public boolean hasSourceTsMs() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the '__source_ts_ms' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearSourceTsMs() {
      __source_ts_ms = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /**
      * Gets the value of the '__deleted' field.
      * @return The value.
      */
    public java.lang.String getDeleted() {
      return __deleted;
    }


    /**
      * Sets the value of the '__deleted' field.
      * @param value The value of '__deleted'.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder setDeleted(java.lang.String value) {
      validate(fields()[11], value);
      this.__deleted = value;
      fieldSetFlags()[11] = true;
      return this;
    }

    /**
      * Checks whether the '__deleted' field has been set.
      * @return True if the '__deleted' field has been set, false otherwise.
      */
    public boolean hasDeleted() {
      return fieldSetFlags()[11];
    }


    /**
      * Clears the value of the '__deleted' field.
      * @return This builder.
      */
    public debezium.avro.AvroUserInters.Builder clearDeleted() {
      __deleted = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AvroUserInters build() {
      try {
        AvroUserInters record = new AvroUserInters();
        record.user_id = fieldSetFlags()[0] ? this.user_id : (java.lang.Long) defaultValue(fields()[0]);
        record.page1 = fieldSetFlags()[1] ? this.page1 : (java.lang.Boolean) defaultValue(fields()[1]);
        record.page1_button = fieldSetFlags()[2] ? this.page1_button : (java.lang.Boolean) defaultValue(fields()[2]);
        record.page2 = fieldSetFlags()[3] ? this.page2 : (java.lang.Boolean) defaultValue(fields()[3]);
        record.page2_button = fieldSetFlags()[4] ? this.page2_button : (java.lang.Boolean) defaultValue(fields()[4]);
        record.page3 = fieldSetFlags()[5] ? this.page3 : (java.lang.Boolean) defaultValue(fields()[5]);
        record.page3_button = fieldSetFlags()[6] ? this.page3_button : (java.lang.Boolean) defaultValue(fields()[6]);
        record.__op = fieldSetFlags()[7] ? this.__op : (java.lang.String) defaultValue(fields()[7]);
        record.__table = fieldSetFlags()[8] ? this.__table : (java.lang.String) defaultValue(fields()[8]);
        record.__lsn = fieldSetFlags()[9] ? this.__lsn : (java.lang.Long) defaultValue(fields()[9]);
        record.__source_ts_ms = fieldSetFlags()[10] ? this.__source_ts_ms : (java.lang.Long) defaultValue(fields()[10]);
        record.__deleted = fieldSetFlags()[11] ? this.__deleted : (java.lang.String) defaultValue(fields()[11]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AvroUserInters>
    WRITER$ = (org.apache.avro.io.DatumWriter<AvroUserInters>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AvroUserInters>
    READER$ = (org.apache.avro.io.DatumReader<AvroUserInters>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.user_id);

    out.writeBoolean(this.page1);

    out.writeBoolean(this.page1_button);

    out.writeBoolean(this.page2);

    out.writeBoolean(this.page2_button);

    out.writeBoolean(this.page3);

    out.writeBoolean(this.page3_button);

    if (this.__op == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.__op);
    }

    if (this.__table == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.__table);
    }

    if (this.__lsn == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.__lsn);
    }

    if (this.__source_ts_ms == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.__source_ts_ms);
    }

    if (this.__deleted == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.__deleted);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.user_id = in.readLong();

      this.page1 = in.readBoolean();

      this.page1_button = in.readBoolean();

      this.page2 = in.readBoolean();

      this.page2_button = in.readBoolean();

      this.page3 = in.readBoolean();

      this.page3_button = in.readBoolean();

      if (in.readIndex() != 1) {
        in.readNull();
        this.__op = null;
      } else {
        this.__op = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.__table = null;
      } else {
        this.__table = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.__lsn = null;
      } else {
        this.__lsn = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.__source_ts_ms = null;
      } else {
        this.__source_ts_ms = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.__deleted = null;
      } else {
        this.__deleted = in.readString();
      }

    } else {
      for (int i = 0; i < 12; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.user_id = in.readLong();
          break;

        case 1:
          this.page1 = in.readBoolean();
          break;

        case 2:
          this.page1_button = in.readBoolean();
          break;

        case 3:
          this.page2 = in.readBoolean();
          break;

        case 4:
          this.page2_button = in.readBoolean();
          break;

        case 5:
          this.page3 = in.readBoolean();
          break;

        case 6:
          this.page3_button = in.readBoolean();
          break;

        case 7:
          if (in.readIndex() != 1) {
            in.readNull();
            this.__op = null;
          } else {
            this.__op = in.readString();
          }
          break;

        case 8:
          if (in.readIndex() != 1) {
            in.readNull();
            this.__table = null;
          } else {
            this.__table = in.readString();
          }
          break;

        case 9:
          if (in.readIndex() != 1) {
            in.readNull();
            this.__lsn = null;
          } else {
            this.__lsn = in.readLong();
          }
          break;

        case 10:
          if (in.readIndex() != 1) {
            in.readNull();
            this.__source_ts_ms = null;
          } else {
            this.__source_ts_ms = in.readLong();
          }
          break;

        case 11:
          if (in.readIndex() != 1) {
            in.readNull();
            this.__deleted = null;
          } else {
            this.__deleted = in.readString();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










