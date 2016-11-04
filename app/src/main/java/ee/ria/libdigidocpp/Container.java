/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ee.ria.libdigidocpp;

public class Container {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Container(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Container obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        digidocJNI.delete_Container(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void save(String path) {
    digidocJNI.Container_save__SWIG_0(swigCPtr, this, path);
  }

  public void save() {
    digidocJNI.Container_save__SWIG_1(swigCPtr, this);
  }

  public String mediaType() {
    return digidocJNI.Container_mediaType(swigCPtr, this);
  }

  public void addDataFile(String path, String mediaType) {
    digidocJNI.Container_addDataFile(swigCPtr, this, path, mediaType);
  }

  public DataFiles dataFiles() {
    return new DataFiles(digidocJNI.Container_dataFiles(swigCPtr, this), true);
  }

  public void removeDataFile(long id) {
    digidocJNI.Container_removeDataFile(swigCPtr, this, id);
  }

  public void addAdESSignature(byte[] signature) {
    digidocJNI.Container_addAdESSignature(swigCPtr, this, signature);
  }

  public Signature prepareSignature(Signer signer) {
    long cPtr = digidocJNI.Container_prepareSignature(swigCPtr, this, Signer.getCPtr(signer), signer);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signatures signatures() {
    return new Signatures(digidocJNI.Container_signatures(swigCPtr, this), true);
  }

  public void removeSignature(long id) {
    digidocJNI.Container_removeSignature(swigCPtr, this, id);
  }

  public Signature sign(Signer signer) {
    long cPtr = digidocJNI.Container_sign(swigCPtr, this, Signer.getCPtr(signer), signer);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public static Container create(String path) {
    long cPtr = digidocJNI.Container_create(path);
    return (cPtr == 0) ? null : new Container(cPtr, false);
  }

  public static Container open(String path) {
    long cPtr = digidocJNI.Container_open(path);
    return (cPtr == 0) ? null : new Container(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert, String profile, StringVector roles, String city, String state, String postalCode, String country) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_0(swigCPtr, this, cert, profile, StringVector.getCPtr(roles), roles, city, state, postalCode, country);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert, String profile, StringVector roles, String city, String state, String postalCode) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_1(swigCPtr, this, cert, profile, StringVector.getCPtr(roles), roles, city, state, postalCode);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert, String profile, StringVector roles, String city, String state) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_2(swigCPtr, this, cert, profile, StringVector.getCPtr(roles), roles, city, state);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert, String profile, StringVector roles, String city) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_3(swigCPtr, this, cert, profile, StringVector.getCPtr(roles), roles, city);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert, String profile, StringVector roles) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_4(swigCPtr, this, cert, profile, StringVector.getCPtr(roles), roles);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert, String profile) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_5(swigCPtr, this, cert, profile);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

  public Signature prepareWebSignature(byte[] cert) {
    long cPtr = digidocJNI.Container_prepareWebSignature__SWIG_6(swigCPtr, this, cert);
    return (cPtr == 0) ? null : new Signature(cPtr, false);
  }

}
